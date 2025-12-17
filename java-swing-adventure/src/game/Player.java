package game;
import java.util.*;

import game.item.Item;

public class Player {

    private String name;
    private int health;
    private int maxHealth;
    private Set<Item> inventory;
    
    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.inventory = new LinkedHashSet<>();
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        GameWindow.print("Du erleidest " + damage + " Schaden! (Leben: " + health + "/" + maxHealth + ")");
    }
    
    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
        GameWindow.print("Du heilst " + amount + " Leben! (Leben: " + health + "/" + maxHealth + ")");
    }
    
    public void addItem(Item item) {
        inventory.add(item);
        GameWindow.print(">>> " + item.getFullName() + " erhalten!");
        
        // Item-Effekt anwenden
        if (item.getHealthBonus() != 0) {
            maxHealth += item.getHealthBonus();
            health += item.getHealthBonus();
            GameWindow.print("    Maximales Leben: +" + item.getHealthBonus());
        }
    }
    
    public boolean isDead() {
        return health <= 0;
    }
    
    public int getAttackPower() {
        int base = 10;
        for (Item item : inventory) {
            base += item.getDamageBonus();
        }
        return base;
    }
    
    // Getter
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public Set<Item> getInventory() { return inventory; }
}