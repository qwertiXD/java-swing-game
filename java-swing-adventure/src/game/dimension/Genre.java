package game.dimension;

import java.util.Random;

/**
 * Genre definiert Ort und Gefühl einer Dimension
 */
public class Genre {
    
    private final LocationGenre location;
    private final MoodGenre mood;
    
    public Genre(LocationGenre location, MoodGenre mood) {
        this.location = location;
        this.mood = mood;
    }
    
    public static Genre generateRandom(Random rng) {
        LocationGenre loc = LocationGenre.values()[rng.nextInt(LocationGenre.values().length)];
        MoodGenre mood = MoodGenre.values()[rng.nextInt(MoodGenre.values().length)];
        return new Genre(loc, mood);
    }
    
    @Override
    public String toString() {
        return location.getDisplayName() + " × " + mood.getDisplayName();
    }
    
    // Getter
    public LocationGenre getLocation() { return location; }
    public MoodGenre getMood() { return mood; }
    
    
    /**
     * Orts-Genre: Wo spielt die Dimension?
     */
    public enum LocationGenre {
        SCI_FI("Sci-Fi", 0.4f),
        FANTASY("Fantasy", 0.3f),
        HISTORICAL("Historical", 0.25f),
        CONTEMPORARY("Contemporary", 0.2f);
        
        private final String displayName;
        private final float baseHostility;
        
        LocationGenre(String displayName, float baseHostility) {
            this.displayName = displayName;
            this.baseHostility = baseHostility;
        }
        
        public String getDisplayName() { return displayName; }
        public float getBaseHostility() { return baseHostility; }
    }
    
    
    /**
     * Gefühls-Genre: Welche Atmosphäre herrscht?
     */
    public enum MoodGenre {
        HORROR("Horror", 0.7f, 0.8f),
        MYSTERY("Mystery", 0.4f, 0.5f),
        COMEDY("Comedy", 0.2f, 0.3f),
        ADVENTURE("Adventure", 0.45f, 0.6f);
        
        private final String displayName;
        private final float hostilityModifier;
        private final float ambientEnergyModifier;
        
        MoodGenre(String displayName, float hostilityMod, float ambientEnergyMod) {
            this.displayName = displayName;
            this.hostilityModifier = hostilityMod;
            this.ambientEnergyModifier = ambientEnergyMod;
        }
        
        public String getDisplayName() { return displayName; }
        public float getHostilityModifier() { return hostilityModifier; }
        public float getAmbientEnergyModifier() { return ambientEnergyModifier; }
    }
} 