package game.area;

import java.util.*;
import game.dimension.Dimension;
import game.dimension.Atmosphere;

public class AreaGenerator {
    
    // Präfixe für Areal-Namen (passend zu Archetypen)
    private static final Map<Atmosphere, String[]> ATMOSPHERE_PREFIXES = new HashMap<>();
    
    static {
        ATMOSPHERE_PREFIXES.put(Atmosphere.PRAEHISTORISCH, new String[]{
            "Urzeitlicher", "Versteinerter", "Prähistorischer", "Fossiler", "Primitiver"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.FUTURISTISCH, new String[]{
            "Cyber", "Neo", "Hyper", "Tech", "Synthese", "Quantum"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.KOSMISCH, new String[]{
            "Sternen", "Astral", "Kosmischer", "Leerer", "Unendlicher"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.ORGANISCH, new String[]{
            "Wuchernder", "Lebender", "Pulsierender", "Organischer", "Verwobener"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.VERZERRT, new String[]{
            "Verzerrter", "Fragmentierter", "Paradoxer", "Unmöglicher", "Gebrochener"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.INDUSTRIELL, new String[]{
            "Rostiger", "Dampfender", "Industrieller", "Mechanischer", "Stählerner"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.MYSTISCH, new String[]{
            "Geheiligter", "Arkaner", "Mystischer", "Uralter", "Ätherischer"
        });
        ATMOSPHERE_PREFIXES.put(Atmosphere.VERFALLEN, new String[]{
            "Verfallener", "Verlorener", "Vergessener", "Toter", "Ruinöser"
        });
    }
    
    private static final String[] ATMOSPHERIC_MODIFIERS = {
        "Verborgener", "Endloser", "Dunkler", "Leuchtender", "Stiller",
        "Wilder", "Seltsamer", "Gefrorener", "Brennender", "Schwebender"
    };
    
    /**
     * Generiert ein vollständiges Areal-Netzwerk für eine Dimension
     */
    public static AreaGraph generateAreaGraph(Random rng, Dimension dimension) {
        int areaCount = 3 + rng.nextInt(10); // 3-12 Areale
        List<Area> areas = new ArrayList<>();
        
        // 1. Alle Areale generieren
        Area startArea = generateArea(rng, dimension, dimension.getDangerLevel()); // Start = Hub
        areas.add(startArea);
        
        for (int i = 1; i < areaCount; i++) {
            int danger = calculateDanger(i, areaCount, dimension);
            Area area = generateArea(rng, dimension, danger);
            areas.add(area);
        }
        
        // 2. Areale verbinden (Graph-Struktur)
        AreaGraph graph = new AreaGraph(rng, areas);
        connectAreas(rng, graph);
        
        return graph;
    }
    
    /**
     * Generiert ein einzelnes Areal
     */
    private static Area generateArea(Random rng, Dimension dimension, int dangerLevel) {

        Atmosphere primary = dimension.getPrimaryAtmosphere().getBlueprint();
        AreaType type = AreaType.getRandomForAtmosphere(rng, primary);
        
        String name = generateAreaName(rng, dimension, type);
        String description = generateDescription(rng, dimension, type);
        float hostility = calculateHostility(rng, dimension, type, dangerLevel);
        
        return new Area(name, description, type, dimension, hostility);
    }
    
    /**
     * Generiert prozeduralen Namen für Areal
     */
    private static String generateAreaName(Random rng, Dimension dimension, AreaType type) {

        Atmosphere archetype = dimension.getPrimaryAtmosphere().getBlueprint();
        StringBuilder name = new StringBuilder();
        
        // 60% Chance auf Präfix
        if (rng.nextFloat() < 0.6f) {
            String[] prefixes = ATMOSPHERE_PREFIXES.get(archetype);
            if (prefixes != null) {
                name.append(prefixes[rng.nextInt(prefixes.length)]).append(" ");
            } else {
                name.append(ATMOSPHERIC_MODIFIERS[rng.nextInt(ATMOSPHERIC_MODIFIERS.length)]).append(" ");
            }
        }
        
        // Base-Name vom AreaType
        name.append(type.getDisplayName());
        
        // 20% Chance auf Suffix
        if (rng.nextFloat() < 0.2f) {
            String[] suffixes = {" des Vergessens", " der Leere", " der Ewigkeit", 
                                " des Chaos", " der Stille", " des Echos"};
            name.append(suffixes[rng.nextInt(suffixes.length)]);
        }
        
        return name.toString();
    }
    
    /**
     * Generiert atmosphärische Beschreibung
     */
    private static String generateDescription(Random rng, Dimension dimension, AreaType type) {
        List<String> sentences = new ArrayList<>();
        
        // Eröffnungssatz basierend auf AreaType
        sentences.add(getOpeningSentence(rng, type, dimension));
        
        // Sinnliche Details von Dimension übernehmen
        if (rng.nextFloat() < 0.7f) {
            sentences.add(getDimensionalDetail(rng, dimension));
        }

        sentences.add(getDangerHint(rng, type));
        
        return String.join(" ", sentences);
    }
    
    @SuppressWarnings("unused")
    private static String getOpeningSentence(Random rng, AreaType type, Dimension dimension) {
        String[][] templates = {
            {"Vor dir erstreckt sich", "Du erreichst", "Ein Gebiet offenbart sich:", "Du betrittst"},
            {" ein", " eine", " das", " die"},
            {" weites", " verfallenes", " pulsierendes", " geheimnisvolles", " bedrohliches", " stilles"}
        };
        
        String verb = templates[0][rng.nextInt(templates[0].length)];
        String article = templates[1][rng.nextInt(templates[1].length)];
        String adjective = templates[2][rng.nextInt(templates[2].length)];
        
        return verb + article + adjective + " " + type.getDisplayName() + ".";
    }
    
    @SuppressWarnings("unused")
    private static String getDimensionalDetail(Random rng, Dimension dimension) {
        String[] templates = {
            "Die Atmosphäre ist durchdrungen von %s.",
            "Überall spürst du %s.",
            "%s erfüllt die Umgebung.",
            "Du bemerkst %s."
        };
        
        String[] details = {
            "fremden Energien",
            "latenter Gefahr",
            "alter Macht",
            "verblasster Erinnerungen",
            "unsterblicher Präsenz",
            "dimensionaler Verzerrung"
        };
        
        String template = templates[rng.nextInt(templates.length)];
        String detail = details[rng.nextInt(details.length)];
        
        return String.format(template, detail);
    }
    
    @SuppressWarnings("unused")
    private static String getDangerHint(Random rng, AreaType type) {
        String[] hints = {
            "Etwas lauert hier.",
            "Du solltest vorsichtig sein.",
            "Die Luft vibriert vor Spannung.",
            "Jeder Instinkt sagt dir: Sei auf der Hut.",
            "Hier ist etwas nicht richtig.",
            "Du fühlst dich beobachtet."
        };
        
        return hints[rng.nextInt(hints.length)];
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
    
    private static float calculateHostility(Random rng, Dimension dimension, AreaType type, int danger) {        
        float base = (dimension.getHostility() + type.getBaseHostility()) / 2f;
        base += (danger - 1) * 0.1f; // Höherer Danger = mehr Hostility
        
        return Math.max(0f, Math.min(1f, base + (rng.nextFloat() - 0.5f) * 0.15f));
    }
}