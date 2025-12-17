package game;
public class Game {
    
    private GameState state;

    public void start() {
        // Neuen GameState mit zufälligem Seed initialisieren
        long seed = System.currentTimeMillis();
        start(seed);
    }

    public void start(String seed) {
        start(stringToSeed(seed));
    }

    public void start(long seed) {
        GameWindow.print("=================================");
        GameWindow.print("  SWING ADVENTURE GAME");
        GameWindow.print("=================================");
        GameWindow.print("");
        
        GameState.initialize(seed);
        state = GameState.getInstance();
        
        // Spiel starten
        state.startNewRun();
    }
    
    public void processInput(String input) {
        if (state != null) {
            state.processCommand(input);
        }
    }

    private long stringToSeed(String seedString) {
        // Versuch: Ist es eine Zahl?
        try {
            return Long.parseLong(seedString);
        } catch (NumberFormatException e) {
            // Wenn nicht: FNV-1a Hash für Text
            return fnv1a64(seedString);
        }
    }

    private long fnv1a64(String input) {
        final long FNV_PRIME = 1099511628211L;
        long hash = 1469598103934665603L; // Basiswert

        for (int i = 0; i < input.length(); i++) {
            hash ^= (byte)(input.charAt(i));
            hash *= FNV_PRIME;
        }
        return hash;
    }

}