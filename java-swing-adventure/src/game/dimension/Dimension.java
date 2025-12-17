package game.dimension;

import game.area.AreaGraph;

public class Dimension {
    
    private final String name;
    private final Genre genre;
    private final String description;
    private final int dangerLevel; // 1-5
    
    // Areal-System
    private AreaGraph areaGraph;
    
    // Strukturelle Eigenschaften
    private final float ambientEnergy; // 0.0-1.0: Event-Frequenz
    private final float hostility; // 0.0-1.0: Wie feindlich ist die Umgebung?
    
    public Dimension(String name, Genre genre, String description,
                     float ambientEnergy, float hostility, int dangerLevel) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.ambientEnergy = ambientEnergy;
        this.hostility = hostility;
        this.dangerLevel = dangerLevel;
    }

    // Setter für AreaGraph 
    public void setAreaGraph(AreaGraph areaGraph) {
        this.areaGraph = areaGraph;
    }

    // Getter
    public String getName() { return name; }
    public Genre getGenre() { return genre; }
    public String getDescription() { return description; }
    public int getDangerLevel() { return dangerLevel; }
    public float getAmbientEnergy() { return ambientEnergy; }
    public float getHostility() { return hostility; }
    public AreaGraph getAreaGraph() { return areaGraph; }
}