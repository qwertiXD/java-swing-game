package game;
public class NPC {
    
    private String name;
    private String personality;
    private String role;
    private int relationshipLevel; // -100 bis +100
    
    public NPC(String name, String personality, String role) {
        this.name = name;
        this.personality = personality;
        this.role = role;
        this.relationshipLevel = 0;
    }
    
    public void modifyRelationship(int change) {
        relationshipLevel += change;
        if (relationshipLevel > 100) relationshipLevel = 100;
        if (relationshipLevel < -100) relationshipLevel = -100;
    }
    
    public String getGreeting() {
        if (relationshipLevel > 50) {
            return "Ah, mein Freund! Schön dich zu sehen.";
        } else if (relationshipLevel < -50) {
            return "Du... was willst du?";
        } else {
            return "Grüße, Wanderer.";
        }
    }
    
    // Getter
    public String getName() { return name; }
    public String getPersonality() { return personality; }
    public String getRole() { return role; }
    public int getRelationshipLevel() { return relationshipLevel; }
}