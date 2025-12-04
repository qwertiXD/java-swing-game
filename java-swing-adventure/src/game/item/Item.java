package game.item;
public class Item {
    
    private final String prefix;
    private final String baseName;
    private final String effect;
    
    private final int damageBonus;
    private final int healthBonus;
    
    public Item(String prefix, String baseName, String effect, int damageBonus, int healthBonus) {
        this.prefix = prefix;
        this.baseName = baseName;
        this.effect = effect;
        this.damageBonus = damageBonus;
        this.healthBonus = healthBonus;
    }
    
    public String getFullName() {
        if (prefix.isEmpty()) {
            return baseName;
        }
        return prefix + " " + baseName;
    }
    
    public String getDescription() {
        StringBuilder desc = new StringBuilder(getFullName());
        if (!effect.isEmpty()) {
            desc.append(" - ").append(effect);
        }
        if (damageBonus > 0) {
            desc.append(" [+").append(damageBonus).append(" Angriff]");
        }
        if (healthBonus > 0) {
            desc.append(" [+").append(healthBonus).append(" Leben]");
        }
        return desc.toString();
    }
    
    // Getter
    public String getPrefix() { return prefix; }
    public String getBaseName() { return baseName; }
    public String getEffect() { return effect; }
    public int getDamageBonus() { return damageBonus; }
    public int getHealthBonus() { return healthBonus; }
}