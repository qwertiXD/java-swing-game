package game.quest;
import java.util.Random;

import game.Player;
import game.dimension.Dimension;

public class QuestGenerator {
    
    private static final String[] QUEST_TYPES = {
        "EXPLORE", "DEFEAT", "SURVIVE", "COLLECT"
    };
    
    public static Quest generateQuest(Random rng, Dimension dimension, Player player) {
        String type = QUEST_TYPES[rng.nextInt(QUEST_TYPES.length)];
        
        String title = "";
        String description = "";
        
        switch (type) {
            case "EXPLORE":
                int rooms = 3 + rng.nextInt(5);
                title = "Erforsche " + dimension.getName();
                description = "Betrete " + rooms + " verschiedene Räume.";
                break;
                
            case "DEFEAT":
                int enemies = 2 + rng.nextInt(4);
                title = "Bezwinge die Feinde";
                description = "Besiege " + enemies + " Gegner in dieser Dimension.";
                break;
                
            case "SURVIVE":
                title = "Überlebe";
                description = "Erkunde weiter, ohne zu sterben.";
                break;
                
            case "COLLECT":
                int items = 2 + rng.nextInt(3);
                title = "Sammle Ausrüstung";
                description = "Finde " + items + " Items.";
                break;
        }
        
        return new Quest(title, description, type);
    }
}