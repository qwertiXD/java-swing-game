package game.encounter;

import java.util.Random;

import game.Player;
import game.dimension.Dimension;
import game.item.Item;
import game.item.ItemGenerator;

public class EncounterGenerator {
    
    private static final String[] ROOM_PREFIXES = {
        "Verlassener", "Dunkler", "Glühender", "Verfluchter", "Alter",
        "Geheimer", "Verborgener", "Zerstörter", "Heiliger", "Verdrehter"
    };
    
    private static final String[] ROOM_TYPES = {
        "Gang", "Saal", "Kammer", "Raum", "Tempel", "Labor",
        "Lagerhaus", "Bunker", "Garten", "Archiv"
    };
    
    public static Encounter generate(Random rng, Dimension dimension, Player player) {
        int roomType = rng.nextInt(100);
        
        if (roomType < 40) {
            // Kampfraum (40%)
            return generateCombatRoom(rng, dimension);
        } else if (roomType < 70) {
            // Loot-Raum (30%)
            return generateLootRoom(rng, dimension);
        } else if (roomType < 85) {
            // Event-Raum (15%)
            return generateEventRoom(rng, dimension);
        } else {
            // Händler-Raum (15%)
            return generateMerchantRoom(rng, dimension);
        }
    }

    @SuppressWarnings("unused")    
    private static Encounter generateCombatRoom(Random rng, Dimension dimension) {
        String prefix = ROOM_PREFIXES[rng.nextInt(ROOM_PREFIXES.length)];
        String type = ROOM_TYPES[rng.nextInt(ROOM_TYPES.length)];
        String name = prefix + " " + type;
        
        String[] enemies = {"Wächter", "Bestie", "Schatten", "Konstrukt", "Dämon"};
        String enemy = enemies[rng.nextInt(enemies.length)];
        
        String description = "Ein gefährlicher Ort. Du spürst eine feindliche Präsenz.";
        
        return new CombatEncounter(name, description, enemy, 
            10 + rng.nextInt(20), // HP
            5 + rng.nextInt(10));  // Damage
    }
    
    private static Encounter generateLootRoom(Random rng, Dimension dimension) {
        String prefix = ROOM_PREFIXES[rng.nextInt(ROOM_PREFIXES.length)];
        String type = ROOM_TYPES[rng.nextInt(ROOM_TYPES.length)];
        String name = prefix + " " + type;
        
        String description = "Der Raum scheint verlassen. Vielleicht findest du hier etwas Nützliches.";
        
        Item item = ItemGenerator.generate(rng, dimension);
        
        return new LootEncounter(name, description, item);
    }
    
    @SuppressWarnings("unused")
    private static Encounter generateEventRoom(Random rng, Dimension dimension) {
        String prefix = ROOM_PREFIXES[rng.nextInt(ROOM_PREFIXES.length)];
        String type = ROOM_TYPES[rng.nextInt(ROOM_TYPES.length)];
        String name = prefix + " " + type;
        
        String[] events = {
            "Ein seltsamer Schrein steht in der Mitte des Raums.",
            "Alte Schriften bedecken die Wände.",
            "Eine mysteriöse Vorrichtung pulsiert mit Energie.",
            "Du hörst ein leises Flüstern aus den Schatten."
        };
        
        String description = events[rng.nextInt(events.length)];
        
        return new EventEncounter(name, description);
    }
    
    @SuppressWarnings("unused")
    private static Encounter generateMerchantRoom(Random rng, Dimension dimension) {
        String[] merchants = {"Wanderer", "Händler", "Sammler", "Nomade", "Verkäufer"};
        String merchant = merchants[rng.nextInt(merchants.length)];
        String name = "Reisender " + merchant;
        
        String description = "Ein freundliches Gesicht in dieser feindlichen Welt.";
        
        return new MerchantEncounter(name, description);
    }
}