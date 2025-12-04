package game.dimension;

import java.util.Random;

import game.area.AreaGenerator;
import game.area.AreaGraph;
import game.dimension.Atmosphere.*;

public class DimensionGenerator {
    
    // Name-Komponenten für Rick & Morty-style Namen
    private static final String[] PREFIXES = {
        "Zer", "Quan", "Mor", "Gry", "Neu", "Ep", "Kron", "Plu", "Xen", "Vor",
        "Lux", "Neb", "Cos", "Bio", "Syn", "Cyb", "Hol", "Psy", "Meta", "Para"
    };
    
    private static final String[] MIDDLES = {
        "plex", "tor", "tad", "ll", "rofl", "sil", "tin", "mor", "dex", "tron",
        "flux", "void", "mesh", "blast", "zone", "prime", "core", "sphere", "drift", "fall"
    };
    
    private static final String[] SUFFIXES = {
        "", " 2", " 7", " 9", " 23", " 42", " X", " Prime", " Alpha", " Omega",
        "-Δ", "-Γ", "-Φ", " Null", " Nexus", " Zero", "-1", " Beta", " Sigma", " Continuum",
        " Disco", " Feld", " Nebula", " Cluster", " Station", " Terminal"
    };
    
    public static Dimension generate(Random rng) {
        // Namen generieren
        String name = generateName(rng);
        
        // Atmosphären-Blueprints wählen
        Atmosphere primaryBlueprint = Atmosphere.getRandom(rng);
        Atmosphere secondaryBlueprint;
        
        // Sekundärer Blueprint sollte anders sein (75% Chance)
        if (rng.nextFloat() < 0.75f) {
            do {
                secondaryBlueprint = Atmosphere.getRandom(rng);
            } while (secondaryBlueprint == primaryBlueprint);
        } else {
            secondaryBlueprint = primaryBlueprint;
        }

        // Konkrete Werte aus Primary Blueprint auswählen
        Temperature temperature = primaryBlueprint.getRandomTemperature(rng);
        Smell smell = primaryBlueprint.getRandomSmell(rng);
        Sound sound = primaryBlueprint.getRandomSound(rng);
        Sky sky = primaryBlueprint.getRandomSky(rng);
        Ground ground = primaryBlueprint.getRandomGround(rng);
        Lighting lighting = primaryBlueprint.getRandomLighting(rng);
        Weather weather = primaryBlueprint.getRandomWeather(rng);

        // Manchmal Elemente vom Secondary einmischen (20% pro Element)
        if (rng.nextFloat() < 0.2f) {
            temperature = secondaryBlueprint.getRandomTemperature(rng);
        }
        if (rng.nextFloat() < 0.2f) {
            smell = secondaryBlueprint.getRandomSmell(rng);
        }
        if (rng.nextFloat() < 0.2f) {
            sound = secondaryBlueprint.getRandomSound(rng);
        }
        if (rng.nextFloat() < 0.2f) {
            sky = secondaryBlueprint.getRandomSky(rng);
        }
        if (rng.nextFloat() < 0.2f) {
            weather = secondaryBlueprint.getRandomWeather(rng);
        }

        // AtmosphereInstances erstellen
        AtmosphereInstance primaryAtmosphere = new AtmosphereInstance(
            primaryBlueprint, temperature, smell, sound, sky, ground, lighting, weather
        );
        
        // Secondary kann gleiche oder andere Werte haben
        AtmosphereInstance secondaryAtmosphere = new AtmosphereInstance(
            secondaryBlueprint,
            secondaryBlueprint.getRandomTemperature(rng),
            secondaryBlueprint.getRandomSmell(rng),
            secondaryBlueprint.getRandomSound(rng),
            secondaryBlueprint.getRandomSky(rng),
            secondaryBlueprint.getRandomGround(rng),
            secondaryBlueprint.getRandomLighting(rng),
            secondaryBlueprint.getRandomWeather(rng)
        );

        // Strukturelle Eigenschaften
        float ambientEnergy = generateAmbientEnergy(rng, primaryBlueprint);
        float hostility = generateHostility(rng, primaryBlueprint);
        int dangerLevel = calculateDangerLevel(ambientEnergy, hostility, rng);
        
        // Beschreibung generieren
        String description = generateDescription(rng, ambientEnergy, hostility, dangerLevel, 
            temperature, smell, sound, sky, ground, lighting, weather);

        Dimension dimension = new Dimension(name, description, 
                           primaryAtmosphere, secondaryAtmosphere,
                           ambientEnergy, hostility, dangerLevel);
        
        // Area-Graph generieren
        AreaGraph areaGraph = AreaGenerator.generateAreaGraph(rng, dimension);
        dimension.setAreaGraph(areaGraph);
        
        printDimensionStats(dimension);

        return dimension;
    }

    private static String generateName(Random rng) {
        StringBuilder name = new StringBuilder();
        
        // Basis-Name
        name.append(PREFIXES[rng.nextInt(PREFIXES.length)]);
        
        // 70% Chance auf Middle-Teil
        if (rng.nextFloat() < 0.7f) {
            name.append(MIDDLES[rng.nextInt(MIDDLES.length)]);
        }
        
        // Suffix
        name.append(SUFFIXES[rng.nextInt(SUFFIXES.length)]);
        
        // 10% Chance auf extra Modifier
        if (rng.nextFloat() < 0.1f) {
            String[] extraModifiers = {" Minor", " Major", " Ultra", " Sub", " Anti", " Proto"};
            name.insert(0, extraModifiers[rng.nextInt(extraModifiers.length)] + " ");
        }
        
        return name.toString();
    }
    
    private static String generateDescription(Random rng, float ambientEnergy, float hostility, int dangerLevel,
                Temperature temperature, Smell smell, Sound sound, Sky sky, Ground ground, Lighting lighting, Weather weather) {

        String tempDesc = temperature.getRandomDescription(rng);
        String smellDesc = smell.getRandomDescription(rng);
        String soundDesc = sound.getRandomDescription(rng);
        String skyDesc = sky.getRandomDescription(rng);
        String groundDesc = ground.getRandomDescription(rng);
        String lightDesc = lighting.getRandomDescription(rng);
        String weathDesc = weather.getRandomDescription(rng);

        StringBuilder desc = new StringBuilder();
        desc.append(skyDesc).append(" ").append(lightDesc).append("\n");
        desc.append(groundDesc).append(" ").append(weathDesc).append("\n");
        desc.append(tempDesc).append("\n");
        if (smell != Smell.VAKUUM) desc.append(smellDesc);
        if (sound != Sound.NULL) desc.append(" ").append(soundDesc);
        
        // Danger Warning
        if(dangerLevel >= 4) {
            desc.append("\n ");
            int choice = (int)(ambientEnergy * hostility * 4);
            switch (choice % 5) {
                case 0 -> desc.append("Jeder Instinkt schreit, dass du hier nicht sicher bist.");
                case 1 -> desc.append("Die Luft vibriert vor latenter Gefahr.");
                case 2 -> desc.append("Du spürst, dass der Tod hier allgegenwärtig ist.");
                case 3 -> desc.append("Überleben wird hier zur größten Herausforderung.");
                default -> desc.append("Vorsicht ist geboten.");
            }
        }

        return desc.toString();
    }
    
    private static float generateAmbientEnergy(Random rng, Atmosphere atmosphere) {
        return switch (atmosphere) {
            case PRAEHISTORISCH, INDUSTRIELL 
                -> 0.6f + rng.nextFloat() * 0.4f; // 0.6-1.0 (stabiler)
            case VERZERRT, KOSMISCH 
                -> 0.1f + rng.nextFloat() * 0.4f; // 0.1-0.5 (instabil)
            case ORGANISCH, VERFALLEN 
                -> 0.3f + rng.nextFloat() * 0.5f; // 0.3-0.8 (variabel)
            default 
                -> 0.4f + rng.nextFloat() * 0.5f; // 0.4-0.9 (normal)
        }; 
    }
    
    private static float generateHostility(Random rng, Atmosphere atmosphere) {  
        return switch (atmosphere) {
            case MYSTISCH 
                -> 0.1f + rng.nextFloat() * 0.4f; // 0.1-0.5 (eher friedlich)
            case VERZERRT, VERFALLEN 
                -> 0.5f + rng.nextFloat() * 0.4f; // 0.5-0.9 (gefährlich)
            case ORGANISCH, KOSMISCH 
                -> 0.2f + rng.nextFloat() * 0.7f; // 0.2-0.9 (sehr variabel)
            default 
                -> 0.3f + rng.nextFloat() * 0.4f; // 0.3-0.7 (normal)
        };     
    }
        
    private static int calculateDangerLevel(float ambientEnergy, float hostility, Random rng) {
        // Gewichte: Hostility ist dominanter, Ambient hat aber Einfluss
        final float wAmbient = 0.35f;
        final float wHost = 0.65f;

        // lineare Basis-Kombination 
        float base = ambientEnergy * wAmbient + hostility * wHost;

        // Kleiner Synergie-Boost, nur wenn beide relativ hoch sind
        float synergy = 0f;
        if (ambientEnergy > 0.6f && hostility > 0.6f) {
            synergy = ambientEnergy * hostility * 0.06f; // geringer Impact
        }

        float dangerScore = base + synergy;

        // Sehr seltener Ausreißerbonus: wenn ambient extrem niedrig und hostility sehr hoch
        if (ambientEnergy < 0.05f && hostility > 0.96f) dangerScore += 0.28f;

        // Kleine RNG-Streuung (optional). RNG macht selten Kategorien springen.
        dangerScore += (rng.nextFloat() - 0.5f) * 0.16f; // ±0.08

        // Clamp
        dangerScore = Math.max(0f, Math.min(1f, dangerScore));

        // Mapping auf 1-5 mit strengerer 4/5-Schwelle
        if (dangerScore < 0.25f) return 1;
        if (dangerScore < 0.45f) return 2;
        if (dangerScore < 0.65f) return 3;
        if (dangerScore < 0.90f) return 4;
        return 5;
    }


    
    // Hilfsmethode für Testing/Debugging
    public static void printDimensionStats(Dimension dim) {
        System.out.println("=== DIMENSION: " + dim.getName() + " ===");
        AtmosphereInstance primary = dim.getPrimaryAtmosphere();
        System.out.println("Primary Blueprint: " + primary.getBlueprint());
        System.out.println("Danger Level: " + dim.getDangerLevel() + "/5");
        System.out.println("Ambient Energy: " + String.format("%.2f", dim.getAmbientEnergy()));
        System.out.println("Hostility: " + String.format("%.2f", dim.getHostility()));
        System.out.println();
        System.out.println("Sky: " + primary.getSky());
        System.out.println("Ground: " + primary.getGround());
        System.out.println("Temperature: " + primary.getTemperature());
        System.out.println("Smell: " + primary.getSmell());
        System.out.println("Sound: " + primary.getSound());
        System.out.println("Lighting: " + primary.getLighting());
        System.out.println("Weather: " + primary.getWeather());
        System.out.println();
        System.out.println("Description:");
        System.out.println(dim.getDescription());
        System.out.println();
    }
}