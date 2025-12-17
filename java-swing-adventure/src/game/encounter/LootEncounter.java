package game.encounter;

import game.GameState;
import game.GameWindow;
import game.Player;
import game.item.Item;

public class LootEncounter extends Encounter {
    
    private final Item item;
    
    public LootEncounter(String name, String description, Item item) {
        super(name, description, "LOOT");
        this.item = item;
    }
    
    @Override
    public void trigger(GameState state) {
        Player player = state.getPlayer();
        
        GameWindow.print("Du durchsuchst den Raum und findest etwas!");
        GameWindow.print("");
        
        player.addItem(item);
        
        GameWindow.print("");
        GameWindow.print("Du verlässt den Raum.");
        GameWindow.print("");
        
        state.generateNewEncounters();
    }
}