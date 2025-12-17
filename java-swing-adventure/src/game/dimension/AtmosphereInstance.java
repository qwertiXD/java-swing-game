package game.dimension;

import game.dimension.Atmosphere.*;

/**
 * Eine Atmosphären-Instanz für eine spezifische Dimension.
 * Während Atmosphere das Enum mit den möglichen Werten ist (blueprint),
 * speichert AtmosphereInstance die tatsächlich gewählten Werte.
 */
public class AtmosphereInstance {
    
    private final Atmosphere blueprint;
    private final Temperature temperature;
    private final Smell smell;
    private final Sound sound;
    private final Sky sky;
    private final Ground ground;
    private final Lighting lighting;
    private final Weather weather;
    
    public AtmosphereInstance(Atmosphere blueprint, 
                              Temperature temperature,
                              Smell smell,
                              Sound sound,
                              Sky sky,
                              Ground ground,
                              Lighting lighting,
                              Weather weather) {
        this.blueprint = blueprint;
        this.temperature = temperature;
        this.smell = smell;
        this.sound = sound;
        this.sky = sky;
        this.ground = ground;
        this.lighting = lighting;
        this.weather = weather;
    }
    
    // Getter
    public Atmosphere getBlueprint() { return blueprint; }
    public Temperature getTemperature() { return temperature; }
    public Smell getSmell() { return smell; }
    public Sound getSound() { return sound; }
    public Sky getSky() { return sky; }
    public Ground getGround() { return ground; }
    public Lighting getLighting() { return lighting; }
    public Weather getWeather() { return weather; }
}