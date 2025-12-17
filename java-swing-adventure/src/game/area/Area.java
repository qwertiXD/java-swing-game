package game.area;

import game.dimension.Dimension;
import game.encounter.Encounter;
import java.util.List;

public class Area {
    
    private final String name;
    private final String description;
    private final AreaType type;
    private final Dimension parentDimension;
    
    private boolean isExplored;
    private final float hostility;

    
    private final List<Encounter> encounters;
    
    public Area(String name, String description, AreaType type, 
                Dimension parentDimension, float hostility,
                List<Encounter> encounters) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.parentDimension = parentDimension;
        this.hostility = hostility;
        this.isExplored = false;
        this.encounters = encounters;
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
        desc.append("Typ: ").append(type.getDisplayName()).append("\n");
        desc.append(description).append("\n\n");
        
        // Danger Hint
        if (hostility > 0.7f) {
            desc.append("⚠ Dieses Gebiet fühlt sich extrem gefährlich an.\n");
        } else if (hostility > 0.5f) {
            desc.append("⚠ Vorsicht ist hier geboten.\n");
        }
        
        return desc.toString();
    }
    
    // Getter
    public String getName() { return name; }
    public String getDescription() { return description; }
    public AreaType getType() { return type; }
    public boolean isExplored() { return isExplored; }
    public float getHostility() { return hostility; }
    public Dimension getParentDimension() { return parentDimension; }
    public List<Encounter> getEncounters() { return this.encounters; }
}