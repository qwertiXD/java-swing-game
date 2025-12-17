package game.area;

import game.dimension.Dimension;
import game.dimension.Genre;
import game.encounter.Encounter;
import game.encounter.EncounterGenerator;
import java.util.*;

public class AreaGenerator {
    
    // Adjektive für Namen
    private static final String[] ADJECTIVES = {
        "Verlassener", "Dunkler", "Alter", "Verborgener", "Gefrorener",
        "Brennender", "Vergessener", "Wilder", "Stiller", "Pulsierender"
    };
    
    // Suffixe für Namen
    private static final String[] SUFFIXES = {
        "", " des Vergessens", " der Leere", " der Ewigkeit", " des Echos"
    };
    
    /**
     * Generiert ein einzelnes Areal
     */
    public static Area generateArea(Random rng, Dimension dimension, 
                                   int dangerLevel, Map<AreaType, Integer> occurrences) {
        
        Genre genre = dimension.getGenre();
        
        // AreaType wählen (respektiert Occurrence-Limits)
        AreaType type = AreaType.getRandomForGenre(rng, genre, occurrences);
        
        // Occurrence tracken
        occurrences.put(type, occurrences.getOrDefault(type, 0) + 1);
        
        String name = generateAreaName(rng, type);
        String description = generateDescription(rng, genre, type);
        float hostility = calculateHostility(rng, dimension, type, dangerLevel);
        
        List<Encounter> encounters = generateEncounters(rng, dimension);

        return new Area(name, description, type, dimension, hostility, encounters);
    }
    
    /**
     * Generiert prozeduralen Namen für Areal
     */
    private static String generateAreaName(Random rng, AreaType type) {
        StringBuilder name = new StringBuilder();
        
        // 60% Chance auf Adjektiv
        if (rng.nextFloat() < 0.6f) {
            name.append(ADJECTIVES[rng.nextInt(ADJECTIVES.length)]).append(" ");
        }
        
        // Base-Name vom AreaType
        name.append(type.getDisplayName());
        
        // 20% Chance auf Suffix
        if (rng.nextFloat() < 0.2f) {
            name.append(SUFFIXES[rng.nextInt(SUFFIXES.length)]);
        }
        
        return name.toString();
    }
    
    /**
     * Generiert Beschreibung basierend auf Genre und Type
     */
    private static String generateDescription(Random rng, Genre genre, AreaType type) {
        List<String> elements = new ArrayList<>();
        
        // Genre-spezifische Beschreibungen
        elements.add(getGenreDescription(rng, genre));
        elements.add(getTypeDescription(rng, type));
        
        return String.join(" ", elements);
    }
    
    private static String getGenreDescription(Random rng, Genre genre) {
        String[] descriptions = switch (genre.getLocation()) {
            case SCI_FI -> new String[]{
                "Technologie durchdringt jeden Winkel.",
                "Displays flackern in unregelmäßigen Intervallen.",
                "Die Luft riecht nach Ozon und Metall."
            };
            case FANTASY -> new String[]{
                "Magische Energien durchziehen die Atmosphäre.",
                "Alte Runen glimmen schwach an den Wänden.",
                "Die Luft knistert vor arkaner Kraft."
            };
            case HISTORICAL -> new String[]{
                "Geschichte lastet schwer auf diesem Ort.",
                "Jahrhunderte alte Spuren zeugen von vergangenen Zeiten.",
                "Der Geruch von Staub und Alter erfüllt die Luft."
            };
            case CONTEMPORARY -> new String[]{
                "Moderne Zivilisation hat hier ihre Spuren hinterlassen.",
                "Der Alltag hat diesen Ort geprägt.",
                "Vertraute und doch fremde Elemente umgeben dich."
            };
        };
        
        return descriptions[rng.nextInt(descriptions.length)];
    }
    
    private static String getTypeDescription(Random rng, AreaType type) {
        // Einfache generische Beschreibungen - können später erweitert werden
        String[] generic = {
            "Etwas fühlt sich hier nicht richtig an.",
            "Du spürst eine gewisse Spannung in der Luft.",
            "Der Ort wirkt verlassen, aber nicht tot."
        };
        
        return generic[rng.nextInt(generic.length)];
    }
    
    private static float calculateHostility(Random rng, Dimension dimension, 
                                           AreaType type, int danger) {
        float base = (dimension.getHostility() + type.getBaseHostility()) / 2f;
        base += (danger - 1) * 0.1f;
        
        return Math.max(0f, Math.min(1f, base + (rng.nextFloat() - 0.5f) * 0.15f));
    }

    private static List<Encounter> generateEncounters(Random rng, Dimension dimension) {
        List<Encounter> encounters = new ArrayList<>();
        
        int encounterCount = 2 + rng.nextInt(3); // 2-4 Räume pro Areal
        for (int i = 0; i < encounterCount; i++) {
            Encounter encounter = EncounterGenerator.generate(rng, dimension);
            encounters.add(encounter);
        }
        
        return encounters;
    }
}