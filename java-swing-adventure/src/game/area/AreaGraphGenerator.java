package game.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.dimension.Dimension;

public class AreaGraphGenerator {

    /**
     * Generiert ein vollständiges Areal-Netzwerk für eine Dimension
     */
    public static AreaGraph generateAreaGraph(Random rng, Dimension dimension) {
        int areaCount = 3 + rng.nextInt(10); // 3-12 Areale
        List<Area> areas = new ArrayList<>();
        
        // 1. Alle Areale generieren
        Area startArea = AreaGenerator.generateArea(rng, dimension, dimension.getDangerLevel()); // Start = Hub
        areas.add(startArea);
        
        for (int i = 1; i < areaCount; i++) {
            int danger = calculateDanger(i, areaCount, dimension);
            Area area = AreaGenerator.generateArea(rng, dimension, danger);
            areas.add(area);
        }
        
        // 2. Areale verbinden (Graph-Struktur)
        AreaGraph graph = new AreaGraph(rng, areas);
        connectAreas(rng, graph);
        
        return graph;
    }

    /**
     * Berechnet Gefahrenlevel basierend auf Position im Graph
     */
    private static int calculateDanger(int index, int total, Dimension dimension) {
        float progress = (float) index / total;
        int baseDanger = dimension.getDangerLevel();
        
        // Danger steigt mit Fortschritt
        int areaDanger = baseDanger + (int) (progress * 2);
        
        return Math.max(1, Math.min(5, areaDanger));
    }
        
    /**
     * Verbindet Areale zu einem Graph
     */
    private static void connectAreas(Random rng, AreaGraph graph) {
        // Start-Areal mit 2-3 Arealen verbinden
        Area start = graph.getCurrentArea();
        int startConnections = 2 + rng.nextInt(3); // 2-4
        
        List<Area> allAreas = graph.getAllAreas();

        for (int i = 1; i <= Math.min(startConnections, allAreas.size() - 1); i++) {
            graph.connectBidirectional(start, allAreas.get(i));
        }
        
        // Restliche Areale verbinden
        for (int i = 1; i < allAreas.size(); i++) {
            Area current = allAreas.get(i);
            int connections = graph.getNeighbors(current).size();
            
            // Jedes Areal sollte 1-4 Verbindungen haben
            int targetConnections = 1 + rng.nextInt(4);
            
            while (connections < targetConnections && connections < allAreas.size() - 1) {
                // Zufälliges anderes Areal wählen
                Area other = allAreas.get(rng.nextInt(allAreas.size()));
                
                if (other != current && !graph.getNeighbors(current).contains(other)) {
                    graph.connectBidirectional(current, other);
                    connections++;
                }
            }
        }
    }
}
