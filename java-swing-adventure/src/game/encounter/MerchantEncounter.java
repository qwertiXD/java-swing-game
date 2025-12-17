package game.encounter;

import game.GameState;
import game.GameWindow;
import game.Player;
import game.item.Item;
import game.item.ItemGenerator;

public class MerchantEncounter extends Encounter {
    
    public MerchantEncounter(String name, String description) {
        super(name, description, "HÄNDLER");
    }
    
    @Override
    public void trigger(GameState state) {
        Player player = state.getPlayer();
        
        GameWindow.print("Ein mysteriöser Händler bietet dir seine Waren an.");
        GameWindow.print("'Ah, ein Reisender! Hier, nimm dies als Geschenk.'");
        GameWindow.print("");
        
        // Händler gibt kostenloses Item (für diese Version)
        Item item = ItemGenerator.generate(state.getLootRng(), state.getCurrentDimension());
        player.addItem(item);
        
        // Kleine Heilung
        player.heal(20);
        
        GameWindow.print("");
        GameWindow.print("Der Händler nickt dir zu und verschwindet.");
        GameWindow.print("");
        
        state.generateNewEncounters();
    }
}