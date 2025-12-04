package game.item;
import java.util.Random;

import game.dimension.Dimension;

public class ItemGenerator {
    
    private static final String[] PREFIXES = {
        "Glitzernder", "Verfluchter", "Alter", "Mystischer", "Scharfer",
        "Zerbrochener", "Leuchtender", "Dunkler", "Heiliger", "Rostiger",
        "Kristallener", "Brennender", "Gefrorener", "Vergessener", "Lebendiger"
    };
    
    private static final String[] BASE_NAMES = {
        "Dolch", "Schwert", "Ring", "Amulett", "Stab",
        "Schild", "Handschuh", "Stiefel", "Helm", "Kristall",
        "Trank", "Buch", "Kette", "Armband", "Splitter"
    };
    
    private static final String[] EFFECTS = {
        "verleiht innere Stärke",
        "flüstert alte Geheimnisse",
        "pulsiert mit Energie",
        "fühlt sich warm an",
        "scheint zu atmen",
        "reflektiert fremde Welten",
        "vibriert leicht",
        "ist erstaunlich leicht",
        "riecht nach Ozon",
        "leuchtet im Dunkeln"
    };
    
    public static Item generate(Random rng, Dimension dimension) {
        String prefix = "";
        if (rng.nextInt(100) < 70) { // 70% haben einen Prefix
            prefix = PREFIXES[rng.nextInt(PREFIXES.length)];
        }
        
        String baseName = BASE_NAMES[rng.nextInt(BASE_NAMES.length)];
        
        String effect = "";
        if (rng.nextInt(100) < 50) { // 50% haben einen Effekt-Text
            effect = EFFECTS[rng.nextInt(EFFECTS.length)];
        }
        
        // Stat-Boni
        int damageBonus = 0;
        int healthBonus = 0;
        
        int statRoll = rng.nextInt(100);
        if (statRoll < 40) {
            damageBonus = 2 + rng.nextInt(8); // +2 bis +9 Damage
        } else if (statRoll < 70) {
            healthBonus = 10 + rng.nextInt(20); // +10 bis +29 Health
        } else {
            // Beide Stats (seltener)
            damageBonus = 1 + rng.nextInt(5);
            healthBonus = 5 + rng.nextInt(15);
        }
        
        return new Item(prefix, baseName, effect, damageBonus, healthBonus);
    }
}