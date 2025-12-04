package game.encounter;

import java.util.Random;

import game.GameState;
import game.GameWindow;
import game.Player;
import game.item.Item;
import game.item.ItemGenerator;

public class EventEncounter extends Encounter {
    
    public EventEncounter(String name, String description) {
        super(name, description, "EVENT");
    }
    
    @Override
    public void trigger(GameState state) {
        Player player = state.getPlayer();
        Random rng = state.getEventRng();
        
        int eventType = rng.nextInt(4);
        
        switch (eventType) {
            case 0 -> {
                // Heilung
                int healAmount = 15 + rng.nextInt(20);
                GameWindow.print("Eine heilende Aura erfüllt den Raum.");
                player.heal(healAmount);
            }
                
            case 1 -> {
                // Schaden
                int damage = 5 + rng.nextInt(15);
                GameWindow.print("Eine Falle wird ausgelöst!");
                player.takeDamage(damage);
            }
                
            case 2 -> {
                // Extra Item
                GameWindow.print("Du entdeckst einen versteckten Schatz!");
                Item item = ItemGenerator.generate(state.getLootRng(), state.getCurrentDimension());
                player.addItem(item);
            }
                
            case 3 -> {
                // Mysteriöses Ereignis
                GameWindow.print("Die Realität scheint zu flimmern...");
                GameWindow.print("Du fühlst dich... verändert.");
                if (rng.nextBoolean()) {
                    player.heal(10);
                } else {
                    player.takeDamage(5);
                }
            }
        }
        
        if (player.isDead()) {
            state.playerDied("Tödliches Event");
            return;
        }
        
        GameWindow.print("");
        GameWindow.print("Du verlässt den Raum.");
        GameWindow.print("");
        
        state.generateNewEncounters();
    }
}