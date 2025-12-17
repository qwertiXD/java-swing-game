package game.area;

import game.dimension.Genre;
import java.util.*;

/**
 * Ich möchte hier weniger Archetypen-spezifisch arbeiten
 * soll heißen:
 * Zuerst Biome wie Wald, Raumstation, Stadt, Ozean, Gebirge, Void, Wildnis, etc.
 * In diesen Biomen befinden sich dann entsprechende Areale. Das könnten Areale sein wie:
 * Camp, Dorf, Krypta, Labor, Reaktor, Garten, Markt, etc.
 * Diese Areale beinhalten spezifische Encounter. Das sind beispielsweise:
 * Räume für Gebäude oder größere Strukturen wie Raumstationen.
 * Kleine Encounter wie einen Schrein, ein Grab, ein Altar. Oder genauso Händler, Quest-Geber bzw. einfach NPCs.
 * 
 * 
 * 
 * All in All:
 * Dimension → Biome/Areale → POIs/Locations → Encounter
 * 
 * Dimension: Welt/Realität
 * Biome/Areal: grobe Umgebung (Wald, Stadt, Raumschiff-Deck)
 * POI/Location: konkrete Orte innerhalb des Areals (Labor, Schrein, Markt, Korridor)
 * Encounter: einzelne Begegnungen oder interaktive Events innerhalb einer Location/eines Areals
 * 
 * 
 *     public static AreaType getRandomWeighted(Random rng) {
        return switch (rng.nextInt(100)) {
            case 0,1,2,3,4 -> TRADER;
            case 5,6,7,8,9,10,11,12 -> QUEST_GIVER;
            case 13,14,15,16,17 -> GUARDIAN;
            case 18,19,20 -> TIME_ECHO;
            case 21,22,23,24,25 -> WILD_BEAST;
            case 26,27,28,29 -> LOST_AI;
            case 30,31,32,33 -> PORTAL_POI;
            case 34,35,36,37,38 -> GRAVE_POI;
            case 39,40,41 -> NEXUS;
            default -> values()[rng.nextInt(values().length)];
        };
    }

* So könnte eine Gewichtete AreaGeneration aussehen! 
*/
/**
 * AreaTypes können genre-spezifisch oder universal sein
 */
public enum AreaType {
    
    // === UNIVERSELLE AREALE (funktionieren überall) ===
    WILDERNESS("Wildnis", 0.5f, -1, true),
    RUINS("Ruinen", 0.4f, -1, true),
    CROSSROADS("Kreuzung", 0.2f, -1, true),
    
    // === SCI-FI SPEZIFISCH ===
    SPACE_STATION("Raumstation", 0.3f, 1, false),
    REACTOR_CORE("Reaktorkern", 0.7f, 1, false),
    CRYO_BAY("Kryokammer", 0.2f, 1, false),
    
    // === FANTASY SPEZIFISCH ===
    ENCHANTED_FOREST("Verzauberter Wald", 0.4f, -1, false),
    ANCIENT_TEMPLE("Alter Tempel", 0.5f, 2, false),
    MAGE_TOWER("Magierturm", 0.3f, 1, false),
    
    // === HISTORICAL SPEZIFISCH ===
    MEDIEVAL_VILLAGE("Mittelalterliches Dorf", 0.2f, -1, false),
    BATTLEFIELD("Schlachtfeld", 0.6f, -1, false),
    
    // === CONTEMPORARY SPEZIFISCH ===
    CITY_DISTRICT("Stadtviertel", 0.3f, -1, false),
    SUBWAY_TUNNEL("U-Bahn-Tunnel", 0.4f, -1, false);
    
    
    private final String displayName;
    private final float baseHostility;
    private final int maxOccurrences; // -1 = unbegrenzt
    private final boolean isUniversal;
    
    AreaType(String displayName, float baseHostility, int maxOccurrences, boolean isUniversal) {
        this.displayName = displayName;
        this.baseHostility = baseHostility;
        this.maxOccurrences = maxOccurrences;
        this.isUniversal = isUniversal;
    }
    
    public String getDisplayName() { return displayName; }
    public float getBaseHostility() { return baseHostility; }
    public int getMaxOccurrences() { return maxOccurrences; }
    public boolean isUniversal() { return isUniversal; }
    
    
    /**
     * Gibt passende AreaTypes für ein Genre zurück
     */
    public static List<AreaType> getTypesForGenre(Genre genre) {
        List<AreaType> types = new ArrayList<>();
        
        // Universelle Areale hinzufügen
        for (AreaType type : values()) {
            if (type.isUniversal) {
                types.add(type);
            }
        }
        
        // Genre-spezifische Areale hinzufügen
        switch (genre.getLocation()) {
            case SCI_FI -> {
                types.add(SPACE_STATION);
                types.add(REACTOR_CORE);
                types.add(CRYO_BAY);
            }
            case FANTASY -> {
                types.add(ENCHANTED_FOREST);
                types.add(ANCIENT_TEMPLE);
                types.add(MAGE_TOWER);
            }
            case HISTORICAL -> {
                types.add(MEDIEVAL_VILLAGE);
                types.add(BATTLEFIELD);
            }
            case CONTEMPORARY -> {
                types.add(CITY_DISTRICT);
                types.add(SUBWAY_TUNNEL);
            }
        }
        
        return types;
    }
    
    
    /**
     * Wählt einen zufälligen passenden AreaType aus
     */
    public static AreaType getRandomForGenre(Random rng, Genre genre, Map<AreaType, Integer> occurrences) {
        List<AreaType> available = getTypesForGenre(genre);
        
        // Filtere Typen, die ihr Maximum erreicht haben
        available.removeIf(type -> {
            if (type.maxOccurrences == -1) return false; // Unbegrenzt
            int count = occurrences.getOrDefault(type, 0);
            return count >= type.maxOccurrences;
        });
        
        if (available.isEmpty()) {
            // Fallback auf universelle Typen ohne Limit
            available = new ArrayList<>();
            for (AreaType type : values()) {
                if (type.isUniversal) available.add(type);
            }
        }
        
        return available.get(rng.nextInt(available.size()));
    }
}