package game.encounter;

import java.util.Random;

import game.GameState;
import game.GameWindow;
import game.Player;
import game.item.Item;
import game.item.ItemGenerator;

public class CombatEncounter extends Encounter {
    
    @SuppressWarnings("FieldMayBeFinal")
    private String enemyName;
    @SuppressWarnings("FieldMayBeFinal")
    private int enemyHealth, enemyDamage;
    
    public CombatEncounter(String name, String description, String enemyName, int enemyHealth, int enemyDamage) {
        super(name, description, "KAMPF");
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyDamage = enemyDamage;
    }
    
    @Override
    public void trigger(GameState state) {
        Player player = state.getPlayer();
        Random rng = state.getEventRng();
        
        GameWindow.print("Ein " + enemyName + " versperrt dir den Weg!");
        GameWindow.print("Leben des Gegners: " + enemyHealth);
        GameWindow.print("");
        
        // Einfacher automatischer Kampf
        int round = 1;
        int currentEnemyHealth = enemyHealth;
        
        while (currentEnemyHealth > 0 && !player.isDead()) {
            GameWindow.print("--- Runde " + round + " ---");
            
            // Spieler greift an
            int playerDamage = player.getAttackPower() + rng.nextInt(5);
            currentEnemyHealth -= playerDamage;
            GameWindow.print("Du fügst " + playerDamage + " Schaden zu!");
            
            if (currentEnemyHealth <= 0) {
                GameWindow.print("Der " + enemyName + " wurde besiegt!");
                state.incrementEnemiesDefeated();
                
                // Loot-Chance
                if (rng.nextInt(100) < 60) {
                    Item loot = ItemGenerator.generate(state.getLootRng(), state.getCurrentDimension());
                    GameWindow.print("");
                    player.addItem(loot);
                }
                break;
            }
            
            GameWindow.print("Gegner Leben: " + currentEnemyHealth);
            
            // Gegner greift an
            int damage = enemyDamage + rng.nextInt(3);
            player.takeDamage(damage);
            
            if (player.isDead()) {
                state.playerDied("Besiegt von " + enemyName);
                return;
            }
            
            GameWindow.print("");
            round++;
        }
        
        GameWindow.print("");
        GameWindow.print("Der Raum ist nun sicher.");
        GameWindow.print("");
        
        state.generateNewEncounters();
    }
}