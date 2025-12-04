package game.area;

import game.dimension.Dimension;
import game.encounter.Encounter;
import java.util.Set;

public class Area {
    
    private final String name;
    private final String description;
    private final AreaType type;
    private boolean isExplored;  // Besser vielleicht: Wert von 0-100 um die Exploration zu tracken, Dazu brauche ich erst aber encounter
    private final float hostility;
    @SuppressWarnings("unused")

    private Set<Encounter> encounters;

    private final Dimension parentDimension;
    
    public Area(String name, String description, AreaType type, 
                Dimension parentDimension, float hostility) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.parentDimension = parentDimension;
        this.hostility = hostility;
        this.isExplored = false;
    }
    
    public void explore() {
        this.isExplored = true;
    }
    
    /**
     * Gibt eine atmosphärische Beschreibung basierend auf Areal und Dimension zurück
     */
    public String getFullDescription() {
        StringBuilder desc = new StringBuilder();
        
        desc.append("=== ").append(name).append(" ===\n");
        desc.append(description).append("\n\n");
        
        return desc.toString();
    }
    
    // Getter
    public String getName() { return name; }
    public String getDescription() { return description; }
    public AreaType getType() { return type; }
    public boolean isExplored() { return isExplored; }
    public float getHostility() { return hostility; }
    public Dimension getParentDimension() { return parentDimension; }
}