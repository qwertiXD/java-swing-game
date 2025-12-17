package game.dimension;

import game.area.AreaGraph;
import game.area.AreaGraphGenerator;
import java.util.Random;

public class DimensionGenerator {
    
    // Name-Komponenten
    private static final String[] PREFIXES = {
        "Zer", "Quan", "Mor", "Neu", "Ep", "Kron", "Xen", "Lux"
    };
    
    private static final String[] MIDDLES = {
        "plex", "tor", "tad", "sil", "tron", "flux", "mesh", "zone"
    };
    
    private static final String[] SUFFIXES = {
        "", " Prime", " Alpha", " Nexus", " Zero", "-Δ", " Station"
    };
    
    public static Dimension generate(Random rng) {
        // Namen generieren
        String name = generateName(rng);
        
        // Genre generieren
        Genre genre = Genre.generateRandom(rng);
        
        // Strukturelle Eigenschaften berechnen
        float ambientEnergy = calculateAmbientEnergy(rng, genre);
        float hostility = calculateHostility(rng, genre);
        int dangerLevel = calculateDangerLevel(ambientEnergy, hostility, rng);
        
        // Beschreibung generieren
        String description = "Genre: " + genre + "\n" + 
                           "Danger: " + getDangerIndicator(dangerLevel);
        
        Dimension dimension = new Dimension(name, genre, description,
                                          ambientEnergy, hostility, dangerLevel);
        
        // Area-Graph generieren
        AreaGraph areaGraph = AreaGraphGenerator.generateAreaGraph(rng, dimension);
        dimension.setAreaGraph(areaGraph);
        
        printDimensionStats(dimension);
        
        return dimension;
    }

    private static String generateName(Random rng) {
        StringBuilder name = new StringBuilder();
        
        name.append(PREFIXES[rng.nextInt(PREFIXES.length)]);
        
        if (rng.nextFloat() < 0.7f) {
            name.append(MIDDLES[rng.nextInt(MIDDLES.length)]);
        }
        
        name.append(SUFFIXES[rng.nextInt(SUFFIXES.length)]);
        
        return name.toString();
    }
    
    private static float calculateAmbientEnergy(Random rng, Genre genre) {
        float base = genre.getMood().getAmbientEnergyModifier();
        return Math.max(0f, Math.min(1f, base + (rng.nextFloat() - 0.5f) * 0.3f));
    }
    
    private static float calculateHostility(Random rng, Genre genre) {
        float locationBase = genre.getLocation().getBaseHostility();
        float moodMod = genre.getMood().getHostilityModifier();
        float combined = (locationBase + moodMod) / 2f;
        
        return Math.max(0f, Math.min(1f, combined + (rng.nextFloat() - 0.5f) * 0.2f));
    }
    
    private static int calculateDangerLevel(float ambientEnergy, float hostility, Random rng) {
        final float wAmbient = 0.35f;
        final float wHost = 0.65f;

        float base = ambientEnergy * wAmbient + hostility * wHost;

        float synergy = 0f;
        if (ambientEnergy > 0.6f && hostility > 0.6f) {
            synergy = ambientEnergy * hostility * 0.06f;
        }

        float dangerScore = base + synergy;
        dangerScore += (rng.nextFloat() - 0.5f) * 0.16f;
        dangerScore = Math.max(0f, Math.min(1f, dangerScore));

        if (dangerScore < 0.25f) return 1;
        if (dangerScore < 0.45f) return 2;
        if (dangerScore < 0.65f) return 3;
        if (dangerScore < 0.90f) return 4;
        return 5;
    }

    private static String getDangerIndicator(int danger) {
        return switch (danger) {
            case 1 -> "[★☆☆☆☆]";
            case 2 -> "[★★☆☆☆]";
            case 3 -> "[★★★☆☆]";
            case 4 -> "[★★★★☆]";
            case 5 -> "[★★★★★]";
            default -> "[???]";
        };
    }
    
    public static void printDimensionStats(Dimension dim) {
        System.out.println("=== DIMENSION: " + dim.getName() + " ===");
        System.out.println("Genre: " + dim.getGenre());
        System.out.println("Danger Level: " + dim.getDangerLevel() + "/5");
        System.out.println("Ambient Energy: " + String.format("%.2f", dim.getAmbientEnergy()));
        System.out.println("Hostility: " + String.format("%.2f", dim.getHostility()));
        System.out.println();
    }
}