package game.encounter;

import game.GameState;

public abstract class Encounter {
    
    protected String name;
    protected String description;
    protected String difficulty;
    
    public Encounter(String name, String description, String difficulty) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }
    
    public abstract void trigger(GameState state);
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDifficulty() { return difficulty; }
}