package game.area;

import java.util.*;

/**
 * Repräsentiert das komplette Areal-Netzwerk einer Dimension
 */
public class AreaGraph {
    
    private final Map<Area, List<Area>> edges = new HashMap<>();
    private Area currentArea; 

    public AreaGraph(Random rng, List<Area> allAreas) {
        for (Area elem : allAreas) {
            addArea(elem);
        }
        this.currentArea = allAreas.get(rng.nextInt(allAreas.size()));
        this.currentArea.explore();
    }

    
    public void addArea(Area area) {
        edges.putIfAbsent(area, new ArrayList<>());
    }
    
    public void connectOnedirectional(Area a, Area b) {
        addArea(a);
        addArea(b);
        
        edges.get(a).add(b);
    }

    public void connectBidirectional(Area a, Area b) {
        addArea(a);
        addArea(b);

        edges.get(a).add(b);
        edges.get(b).add(a);
    }


    // =====================================
    // Ab hier Interaktion mit dem Ara-Graph
    // =====================================

    /**
     * Bewegt den Spieler zu einem neuen Areal
     */
    public boolean moveTo(Area target) {
        if (!getNeighbors(currentArea).isEmpty()) {
            currentArea = target;
            target.explore();
            return true;
        }
        return false;
    }
    
    /**
     * Bewegt den Spieler zu einem Areal per Index (aus der Connection-Liste)
     */
    public boolean moveToIndex(int index) {
        List<Area> connections = getNeighbors(currentArea);
        if (index >= 0 && index < connections.size()) {
            return moveTo(connections.get(index));
        }
        return false;
    }
    
    /**
     * Gibt alle erkundeten Areale zurück
     */
    public List<Area> getExploredAreas() {
        List<Area> explored = new ArrayList<>();
        for (Area area : getAllAreas()) {
            if (area.isExplored()) {
                explored.add(area);
            }
        }
        return explored;
    }
    
    public int getExploredCount() {
        return getExploredAreas().size();
    }
    
    public int getTotalAreaCount() {
        return getAllAreas().size();
    }
    
    public boolean isFullyExplored() {
        return getExploredCount() == getTotalAreaCount();
    }
    
    /**
     * Debug-Ausgabe der Graph-Struktur
     */
    public void printGraphStructure() {
        System.out.println("=== AREA GRAPH ===");
        System.out.println("Total Areas: " + getAllAreas().size());
        System.out.println("Explored: " + getExploredCount());
        System.out.println();
        
        for (Area area : getAllAreas()) {
            String status = area.isExplored() ? "[X]" : "[ ]";
            System.out.println(status + " " + area.getName());
            System.out.println("    Connections: " + getNeighbors(area).size());
            for (Area conn : getNeighbors(area)) {
                System.out.println("      -> " + conn.getName());
            }
            System.out.println();
        }
    }

    // Getter
    public List<Area> getAllAreas() { return edges.keySet().stream().toList(); }   
    public List<Area> getNeighbors(Area area) { return edges.getOrDefault(area, List.of()); }
    public Area getCurrentArea() { return currentArea; }
}