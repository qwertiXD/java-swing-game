package game.area;

import java.util.Random;
import game.dimension.Atmosphere;

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
*/

public enum AreaType {
    // Natürliche Areale
    WILDERNESS("Wildnis", 0.3f, 0.5f),
    FOREST("Wald", 0.4f, 0.4f),
    SWAMP("Sumpf", 0.3f, 0.6f),
    DESERT("Wüste", 0.5f, 0.5f),
    MOUNTAIN("Gebirge", 0.4f, 0.6f),
    CAVE("Höhle", 0.5f, 0.7f),
    OCEAN("Ozean", 0.3f, 0.5f),
    
    // Zivilisations-Areale
    SETTLEMENT("Siedlung", 0.7f, 0.2f),
    CITY("Stadt", 0.8f, 0.3f),
    VILLAGE("Dorf", 0.7f, 0.2f),
    OUTPOST("Außenposten", 0.6f, 0.4f),
    MARKET("Markt", 0.8f, 0.1f),
    
    // Verfallene Areale
    RUIN("Ruine", 0.3f, 0.6f),
    TEMPLE("Tempel", 0.5f, 0.5f),
    GRAVEYARD("Friedhof", 0.4f, 0.5f),
    ABANDONED_CITY("Verlassene Stadt", 0.3f, 0.7f),
    CRYPT("Krypta", 0.4f, 0.8f),
    
    // Technologische Areale
    FACTORY("Fabrik", 0.6f, 0.5f),
    LABORATORY("Labor", 0.7f, 0.4f),
    STATION("Station", 0.7f, 0.3f),
    REACTOR("Reaktor", 0.5f, 0.7f),
    DATACORE("Datenkern", 0.6f, 0.5f),
    
    // Kosmische Areale
    VOID("Leere", 0.2f, 0.8f),
    NEBULA("Nebel", 0.3f, 0.6f),
    ASTEROID_FIELD("Asteroidenfeld", 0.4f, 0.7f),
    PORTAL("Portal", 0.5f, 0.6f),
    RIFT("Riss", 0.2f, 0.9f),
    
    // Organische Areale
    HIVE("Brutkolonie", 0.4f, 0.8f),
    GARDEN("Garten", 0.6f, 0.3f),
    MEMBRANE("Membran", 0.3f, 0.7f),
    FLESH_PIT("Fleischgrube", 0.2f, 0.9f),
    SPORE_FIELD("Sporenfeld", 0.3f, 0.6f),
    
    // Mystische Areale
    SHRINE("Schrein", 0.6f, 0.4f),
    SANCTUM("Sanktum", 0.7f, 0.3f),
    NEXUS("Nexus", 0.5f, 0.6f),
    ALTAR("Altar", 0.6f, 0.5f),
    MONOLITH("Monolith", 0.5f, 0.7f);
    
    private final String displayName;
    private final float baseStability;
    private final float baseHostility;
    
    AreaType(String displayName, float baseStability, float baseHostility) {
        this.displayName = displayName;
        this.baseStability = baseStability;
        this.baseHostility = baseHostility;
    }
    
    public String getDisplayName() { return displayName; }
    public float getBaseStability() { return baseStability; }
    public float getBaseHostility() { return baseHostility; }
    
    /**
     * Wählt einen passenden AreaType basierend auf Atmpsphere
     */
    public static AreaType getRandomForAtmosphere(Random rng, Atmosphere atmosphere) {
        AreaType[] options;
        
        switch (atmosphere) {
            case PRAEHISTORISCH -> {options = new AreaType[]
                    {WILDERNESS, FOREST, SWAMP, CAVE, MOUNTAIN};}
            case FUTURISTISCH -> {options = new AreaType[]
                    {CITY, STATION, LABORATORY, FACTORY, DATACORE, OUTPOST};}
            case KOSMISCH -> {options = new AreaType[]
                    {VOID, NEBULA, ASTEROID_FIELD, PORTAL, RIFT, STATION};}
            case ORGANISCH -> {options = new AreaType[]
                    {HIVE, GARDEN, MEMBRANE, FLESH_PIT, SPORE_FIELD, SWAMP};}
            case VERZERRT -> {options = new AreaType[]
                    {RIFT, PORTAL, VOID, NEXUS, MONOLITH};}
            case INDUSTRIELL -> {options = new AreaType[]
                    {FACTORY, REACTOR, CITY, OUTPOST, RUIN};}
            case MYSTISCH -> {options = new AreaType[]
                    {SHRINE, SANCTUM, TEMPLE, ALTAR, MONOLITH, NEXUS};}
            case VERFALLEN-> {options = new AreaType[]
                    {RUIN, ABANDONED_CITY, GRAVEYARD, CRYPT, TEMPLE};}
            default -> {options = new AreaType[]
                    {WILDERNESS, SETTLEMENT, RUIN, CAVE};}
        }
        
        return options[rng.nextInt(options.length)];
    }
    
    /**
     * Gibt eine Liste von AreaTypes zurück, die gut zur Atmosphere passen
     */
    public static AreaType[] getAllForAtmosphere(Atmosphere atmosphere) {
        switch (atmosphere) {
            case PRAEHISTORISCH -> { return new AreaType[]
                    {WILDERNESS, FOREST, SWAMP, CAVE, MOUNTAIN, DESERT};}
            case FUTURISTISCH -> { return new AreaType[]
                    {CITY, STATION, LABORATORY, FACTORY, DATACORE, OUTPOST, REACTOR};}
            case KOSMISCH -> {return new AreaType[]
                    {VOID, NEBULA, ASTEROID_FIELD, PORTAL, RIFT, STATION};}
            case ORGANISCH -> {return new AreaType[]
                    {HIVE, GARDEN, MEMBRANE, FLESH_PIT, SPORE_FIELD, SWAMP, FOREST};}
            case VERZERRT -> {return new AreaType[]
                    {RIFT, PORTAL, VOID, NEXUS, MONOLITH, ABANDONED_CITY};}
            case INDUSTRIELL -> {return new AreaType[]
                    {FACTORY, REACTOR, CITY, OUTPOST, RUIN, LABORATORY};}
            case MYSTISCH -> {return new AreaType[]
                    {SHRINE, SANCTUM, TEMPLE, ALTAR, MONOLITH, NEXUS, GRAVEYARD};}
            case VERFALLEN -> {return new AreaType[]
                    {RUIN, ABANDONED_CITY, GRAVEYARD, CRYPT, TEMPLE, OUTPOST};}
            default -> {return new AreaType[]
                    {WILDERNESS, SETTLEMENT, RUIN, CAVE, FOREST};}
        }
    }
}