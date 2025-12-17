package game.quest;
public class Quest {
    
    private String title;
    private String description;
    private String type;
    private boolean completed;
    
    public Quest(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.completed = false;
    }
    
    public void complete() {
        completed = true;
    }
    
    // Getter
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public boolean isCompleted() { return completed; }
}