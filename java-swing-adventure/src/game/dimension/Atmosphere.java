package game.dimension;

import java.util.Random;

public enum Atmosphere {
    PRAEHISTORISCH(
        new Temperature[]{Temperature.HEISS, Temperature.WARM, Temperature.SCHWUEL},
        new Smell[]{Smell.ERDIG, Smell.MODRIG, Smell.SCHWEFEL, Smell.VEGETATION},
        new Sound[]{Sound.TIERISCH, Sound.GROLLEND, Sound.KNACKEN},
        new Sky[]{Sky.NEBULOES, Sky.ORANGE, Sky.VIOLETT},
        new Ground[]{Ground.ERDIG, Ground.SUMPFIG, Ground.VULKANISCH},
        new Lighting[]{Lighting.DAEMMERIG, Lighting.GRELL, Lighting.PULSIEREND},
        new Weather[]{Weather.SCHWUEL, Weather.GEWITTERIG, Weather.NEBLIG}
    ),
    
    FUTURISTISCH(
        new Temperature[]{Temperature.KUEHL, Temperature.STERIL, Temperature.NORMAL},
        new Smell[]{Smell.METALLISCH, Smell.STERIL, Smell.CHEMISCH},
        new Sound[]{Sound.MECHANISCH, Sound.SUMMEND, Sound.DIGITALES_RAUSCHEN},
        new Sky[]{Sky.LEUCHTEND, Sky.HOLOGRAPHISCH, Sky.NEONBLAU, Sky.DUNKEL},
        new Ground[]{Ground.METALLISCH, Ground.GLAS, Ground.SCHWEBEND},
        new Lighting[]{Lighting.NEON, Lighting.KUENSTLICH, Lighting.STROBOSKOPISCH},
        new Weather[]{Weather.ENERGIEBLITZE, Weather.KUENSTLICHER_REGEN, Weather.WINDSTILL}
    ),
    
    KOSMISCH(
        new Temperature[]{Temperature.EISIG, Temperature.SCHWANKEND, Temperature.NULL},
        new Smell[]{Smell.VAKUUM, Smell.FREMDARTIG},
        new Sound[]{Sound.ECHO, Sound.KOSMISCHES_RAUSCHEN, Sound.NULL},
        new Sky[]{Sky.STERNENMEER, Sky.SCHWARZES_LOCH, Sky.NEBULOES, Sky.LEUCHTEND},
        new Ground[]{Ground.KRISTALLIN, Ground.SCHWEBEND, Ground.ASTEROIDENSTAUB, Ground.NULL},
        new Lighting[]{Lighting.STERNENLICHT, Lighting.PULSIEREND, Lighting.DUNKEL},
        new Weather[]{Weather.METEORSCHAUER, Weather.SONNENWIND, Weather.WINDSTILL}
    ),
    
    ORGANISCH(
        new Temperature[]{Temperature.WARM, Temperature.SCHWUEL, Temperature.KOERPERWARM},
        new Smell[]{Smell.VEGETATION, Smell.VERWESUNG, Smell.SUESSLICH, Smell.PILZARTIG},
        new Sound[]{Sound.ATMEND, Sound.SCHLEIMIG, Sound.WUCHERND, Sound.PULSIEREND},
        new Sky[]{Sky.FLEISCHIG, Sky.GRUENLICH, Sky.MEMBRAN},
        new Ground[]{Ground.FLEISCHIG, Ground.WUCHERND, Ground.SCHLEIMIG},
        new Lighting[]{Lighting.BIOLUMINESZENT, Lighting.GEDAEMPFT, Lighting.PULSIEREND},
        new Weather[]{Weather.SPORENNEBEL, Weather.ENZYMATISCH, Weather.SCHWUEL}
    ),
    
    VERZERRT(
        new Temperature[]{Temperature.PARADOX, Temperature.SCHWANKEND, Temperature.NULL},
        new Smell[]{Smell.PARADOX, Smell.SYNÄSTHETISCH, Smell.FREMDARTIG},
        new Sound[]{Sound.RUECKWAERTS, Sound.DIMENSIONSRISS, Sound.UNHEIMLICH, Sound.VERZERRT},
        new Sky[]{Sky.FRAGMENTIERT, Sky.KALEIDOSKOPISCH, Sky.FALSCH, Sky.UNENDLICH},
        new Ground[]{Ground.NICHT_EUKLIDISCH, Ground.FLUESSIG, Ground.SCHWANKEND, Ground.NULL},
        new Lighting[]{Lighting.UNMOEGLICH, Lighting.STROBOSKOPISCH, Lighting.VERSCHOBEN},
        new Weather[]{Weather.REALITAETSBRUECHE, Weather.ZEITSCHLEIFEN, Weather.PARADOX}
    ),
    
    INDUSTRIELL(
        new Temperature[]{Temperature.HEISS, Temperature.RAUCHIG, Temperature.STICKIG},
        new Smell[]{Smell.OEL, Smell.RAUCH, Smell.METALLISCH, Smell.CHEMISCH},
        new Sound[]{Sound.HÄMMERND, Sound.DAMPFEND, Sound.KREISCHEND},
        new Sky[]{Sky.RAUCHVERHANGEN, Sky.ASCHGRAU, Sky.ORANGE},
        new Ground[]{Ground.EISENROST, Ground.PFLASTER, Ground.GITTER},
        new Lighting[]{Lighting.FLAMMEN, Lighting.GEDAEMPFT, Lighting.FUNKENFLUG},
        new Weather[]{Weather.ASCHEREGEN, Weather.HITZEWELLE, Weather.RUSS}
    ),
    
    MYSTISCH(
        new Temperature[]{Temperature.KUEHL},
        new Smell[]{Smell.WEIHRAUCH, Smell.MAGISCH, Smell.DUFTEND},
        new Sound[]{Sound.GESANG, Sound.GLOCKEN, Sound.FLUESTERN, Sound.MELODISCH},
        new Sky[]{Sky.MAGISCH, Sky.AURORAL, Sky.STERNENMEER, Sky.VIOLETT},
        new Ground[]{Ground.RUNEN, Ground.KRISTALLIN, Ground.NEBELHAFT},
        new Lighting[]{Lighting.MAGISCH, Lighting.GEISTERHAFT, Lighting.AURORAL},
        new Weather[]{Weather.MAGIESTROEME, Weather.NEBLIG, Weather.ÄTHERWIND}
    ),
    
    VERFALLEN(
        new Temperature[]{Temperature.KALT, Temperature.SCHWUEL, Temperature.MODRIG},
        new Smell[]{Smell.VERWESUNG, Smell.SCHIMMEL, Smell.MODRIG, Smell.FAEULNIS},
        new Sound[]{Sound.KNARZEND, Sound.TROPFEND, Sound.ECHO, Sound.NULL},
        new Sky[]{Sky.BLEICH, Sky.DUNKEL, Sky.ZERBROCHEN},
        new Ground[]{Ground.VERFAULT, Ground.ZERBROCHEN, Ground.UEBERWUCHERT},
        new Lighting[]{Lighting.DUNKEL, Lighting.DAEMMERIG, Lighting.VERFALLEND},
        new Weather[]{Weather.DAUERREGEN, Weather.NEBLIG, Weather.WINDSTILL}
    ), 
    
    ERDAEHNLICH(
        new Temperature[]{Temperature.NORMAL, Temperature.KOERPERWARM, Temperature.WARM, Temperature.KUEHL},
        new Smell[]{Smell.VEGETATION, Smell.DUFTEND, Smell.ERDIG},
        new Sound[]{Sound.NULL, Sound.RUHIG, Sound.TIERISCH},
        new Sky[]{Sky.KLAR, Sky.WOLKENVERHANGEN},
        new Ground[]{Ground.ERDIG, Ground.STEINIG, Ground.UEBERWUCHERT, Ground.WAESSRIG},
        new Lighting[]{Lighting.DUNKEL, Lighting.HELL, Lighting.STERNENLICHT},
        new Weather[]{Weather.KLAR, Weather.NEBLIG, Weather.WINDSTILL, Weather.SCHWUEL}
    );
    

    /**
     * Sinnesparameter
     * Hier werden die Parameter direkt mit ihrer Beschreibung verbunden.
     * Das System ist noch nicht fertig und muss noch implementiert werden
     */
    /**
     * Temperature
     */
    public enum Temperature {
        EISIG("Eisige Kälte beißt in deine Haut.","Die Kälte hat hier eine Stimme, ein leises Sirren wie altes Eis.","Die Luft ist so kalt, dass sie beim Einatmen kristallisiert.","Frost zeichnet feine Muster auf deine Kleidung, ohne sie zu berühren.","Ein eisiger Wind schneidet durch deine Kleidung."),
        KALT("Kalte Luft umgibt dich.","Ein nüchterner, scharfer Hauch von Winter liegt in der Luft.","Kälte krabbelt über deine Haut."),
        KUEHL("Eine angenehme Kühle liegt in der Luft."), 
        NORMAL("Die Temperatur hier ist erträglich.","Die Temperatur ist angenehm.","Hier ist es nicht zu warm, nicht zu kalt.","Weder zitterst, noch schwitzt du."),
        KOERPERWARM("Die Umgebung fühlt sich körperwarm an."),
        SCHWUEL("Die Umgebung fühlt sich feucht-warm an."), 
        WARM("Die Umgebung ist angenehm warm.","Sonnenstrahlen wärmen deine Haut.","Wärme umgibt dich"),
        HEISS("Ballende Hitze steigt auf.","Drückende Hitze macht jeden Atemzug schwer.","Drückende Hitze macht jeden Atemzug zur Qual."),
        RAUCHIG("Heiße, rauchige Luft brennt in deiner Lunge."), 
        STICKIG("Stickige Luft erschwert das Atmen."), 
        MODRIG("Modrige Feuchtigkeit durchdringt alles."),
        STERIL("Die Luft fühlt sich steril und leblos an."), 
        SCHWANKEND("Die Temperatur schwankt unruhig.","Die Luft schwankt unaufhörlich zwischen warm und kühl.","Wärme und Kälte wechseln in kleinen, kaum planbaren Intervallen."), 
        NULL("Temperatur hat hier keine Bedeutung.","Es gibt Luftzustand, aber kein Empfinden von warm oder kalt."),
        PARADOX("Es ist gleichzeitig warm und kalt.","Die Temperatur ist seltsam...","Zwei Temperaturzustände existieren parallel statt im Widerspruch.","Die Luft fühlt sich doppeldeutig, aber stabil an.");

        private final String[] descriptions;
        Temperature(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    /**
     * Smell
     */
    public enum Smell {
        METALLISCH("Ein scharfer, metallischer Geruch liegt in der Luft.","Ein Duft von frisch geschliffenem Eisen und kalten Maschinenteilen erfüllt dich."),
        DUFTEND("Angenehme Düfte erfüllen die Atmosphäre.","Ein leichter, blumiger Hauch schafft ein Gefühl von Frische."),
        MODRIG("Modriger Geruch steigt auf.","Es riecht nach feuchtem Holz, das zu lange im Schatten lag."),
        ERDIG("Der Geruch von feuchter Erde ist allgegenwärtig."),
        SCHARF("Ein scharfer, beißender Geruch sticht in die Nase.","Die Luft kratzt im Rachen und wirkt unangenehm trocken."),
        STERIL("Die Luft riecht steril und desinfiziert.","Ein klinischer Reinheitsgeruch liegt über Allem, fast ohne natürliche Note."),
        CHEMISCH("Chemische Dämpfe reizen deine Sinne.","Ein künstlicher Geruch erreicht dich, der leicht stechend im Kopf bleibt."),
        VAKUUM(""),
        FREMDARTIG("Ein vollkommen unbekannter Geruch umgibt dich.","Die Gerüche kannst du kaum einordnen, ungewohnt aber nicht zwingend unangenehm."),
        VEGETATION("Der intensive Geruch von Pflanzenwuchs dominiert.","Grüne, chlorophyllartige Noten riechen natürlich und lebendig."),
        VERWESUNG("Süßlicher Verwesungsgeruch hängt in der Luft.","Süßlicher Verwesungsgeruch hängt in der Luft. Schwer und penetrant, mit klebrigen, fauligen Nuancen.","Es riecht schwer und penetrant, mit klebrigen, fauligen Nuancen."),
        SUESSLICH("Ein unheimlich süßlicher Duft ist präsent."),
        PILZARTIG("Pilzartiger, erdiger Geruch erfüllt die Luft.","Die Atmosphäre erinnert an Moos und dunkle Waldböden."),
        PARADOX("Der Geruch ist widersprüchlich und unmöglich."),
        SYNÄSTHETISCH("Du riechst Farben und schmeckst Töne.","Es gibt keinen klassischer Geruch, es fühlt sich an wie ein sensorisches Gesamtgefühl."),
        OEL("Schwerer Ölgeruch klebt in der Luft.","Ein Geruch, so schmierig, dunkel und langanhaltend in der Wahrnehmung."),
        RAUCH("Beißender Rauch brennt in den Augen.","Es riecht verkohlt, trocken und nach schwelender Asche."),
        SCHWEFEL("Schwefelgestank erfüllt die Atmosphäre.","Ein faulig-gelber Gestank, schwer und chemisch zugleich."),
        WEIHRAUCH("Weihrauch-Duft schwebt mystisch umher.","Es riecht Würzig, aromatisch und leicht harzig warm."),
        MAGISCH("Ein unerklärlicher, magischer Duft.","Ein seltsamer Geruch liegt in der Luft; kaum greifbar aber spürbar präsent."),
        SCHIMMEL("Schimmelgeruch durchdringt alles.","Es riecht pelzig und nach Schimmel, feucht und muffig."),
        FAEULNIS("Fäulnis und Zerfall dominieren die Sinne.");

        private final String[] descriptions;
        Smell(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }



    /**
     * Sound
     */
    public enum Sound { 
        RUHIG("Friedliche Stille umgibt dich.","Nur feinste Umgebungsgeräusche sind wahrnehmbar."),
        LAUT("Lauter Lärm erfüllt die Luft.","Geräusche überschlagen sich auf chaotische Weise.","Geräusche überschlagen sich und wirken chaotisch."),
        MECHANISCH("Mechanisches Rattern ist zu hören.","Metallisches Klicken begleitet deine Bewegungen."),
        TIERISCH("Tierische Laute hallen durch die Gegend.","Rufe und Bewegungsgeräusche umgeben dich."),
        UNHEIMLICH("Seltsame, schwer einzuordnende Geräusche sind präsent."),
        NULL("Absolute Stille herrscht - unnatürlich still.","Es gibt keinen Wind, kein Echo, keine Geräusche."),
        ECHO("Jedes Geräusch hallt ungewöhnlich lang nach.","Schritte hallen mehrfach von unsichtbaren Wänden wieder."),
        SUMMEND("Ein konstantes Summen liegt in der Luft.","Du hörst einen gleichmäßigen, tiefen und kaum zuordbaren Ton."),
        DIGITALES_RAUSCHEN("Feines, digitales Knistern ist wahrnehmbar.","Singaltöne sind wie ein Rauschen im Hintergrund zu hören."),
        KOSMISCHES_RAUSCHEN("Ein fernes, gleichmäßiges Grundrauschen ist hörbar.","Ein Rauschen ist zu hören. Es klingt wie eine sehr entfernte Frequenz."),
        ATMEND("Rhythmische Klangschwankungen sind zu hören."),
        SCHLEIMIG("Weiche, gleitende Geräusche durchziehen die Umgebung.","Töne wirken gedämpft und leicht organisch.","Deine Schritte hören sich an, als würdest du über Schleim laufen."),
        WUCHERND("Knackende, leichte Strukturgeräusche sind wahrnehmbar."),
        PULSIEREND("Ein dezentes, rhythmisches Klopfen ist präsent."),
        GROLLEND("Tiefes, fernes Grollen schwingt durch den Boden."),
        KNACKEN("Vereinzeltes Knacken ist zu hören."),
        RUECKWAERTS("Manche Klänge wirken zeitlich verschoben.","Geräuschverläufe scheinen an ihrem Ende zu beginnen.","Geräuschverläufe scheinen am Ende zu beginnen."),
        DIMENSIONSRISS("Ein abruptes, schneidendes Brummen entsteht kurzzeitig."),
        VERZERRT("Geräusche klingen irgendwie falsch.","Frequenzen scheinen leicht verschoben und unharmonisch."),
        HÄMMERND("Wiederholtes, hartes Klopfen ist hörbar."),
        DAMPFEND("Zischende, weiche Druckgeräusche sind wahrnehmbar."),
        KREISCHEND("Hohes Kreischen taucht in Spitzen auf."),
        GESANG("Ferne, stimmähnliche Klangmuster liegen im Raum."),
        GLOCKEN("Dumpfe, entfernte Glockentöne sind hörbar."),
        FLUESTERN("Leises, undeutliches Stimmenrauschen ist präsent."),
        MELODISCH("Melodische Tonschleifen sind wahrnehmbar.","Ein melodisches Motiv klingt durch die Gegend. Es scheint sich zu wiederholen."),
        KNARZEND("Holz- oder strukturähnliches Knarzen durchzieht die Umgebung."),
        TROPFEND("Regelmäßiges Tropfen ist hörbar.");

        private final String[] descriptions;
        Sound(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }  
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    
    /**
     * Sky
     */
    public enum Sky { 
        KLAR("Der Himmel ist kristallklar."),
        NEBULOES("Dichter Nebel verschleiert den Himmel."),
        ROT("Ein bedrohlicher roter Himmel erstreckt sich über dir."),
        LEUCHTEND("Der Himmel glüht in unnatürlichen Farben."),
        WOLKENVERHANGEN("Dichter Rauch verhüllt alles über dir."),
        HOLOGRAPHISCH("Holographische Projektionen flackern am Himmel."),
        NEONBLAU("Ein elektrisches Blau dominiert den Himmel."),
        DUNKEL("Ewige Finsternis bedeckt alles."),
        STERNENMEER("Unzählige Sterne leuchten in greifbarer Nähe."),
        SCHWARZES_LOCH("Ein Schwarzes Loch verdunkelt den Horizont."),
        FLEISCHIG("Der Himmel besteht aus pulsierendem Gewebe."),
        GRUENLICH("Ein krankhaftes Grün färbt die Luft."),
        MEMBRAN("Eine organische Membran spannt sich über die Welt."),
        FRAGMENTIERT("Der Himmel zerbricht in geometrische Fragmente."),
        KALEIDOSKOPISCH("Realitäten überlagern sich am Himmel."),
        FALSCH("Der Himmel sieht einfach... falsch aus."),
        UNENDLICH("Der Himmel erstreckt sich in unmögliche Richtungen."),
        RAUCHVERHANGEN("Dichter Rauch verhüllt alles über dir."),
        ASCHGRAU("Asche regnet langsam vom grauen Himmel."),
        ORANGE("Ein orangefarbener Schimmer liegt über allem."),
        MAGISCH("Arkane Symbole zeichnen sich am Firmament ab."),
        AURORAL("Leuchtende Auren tanzen am Himmel."),
        VIOLETT("Ein tiefes Violett färbt die Atmosphäre."),
        BLEICH("Ein krankhaft bleicher Himmel lastet über der Welt."),
        ZERBROCHEN("Risse durchziehen den Himmel wie zerbrochenes Glas.");

        private final String[] descriptions;
        Sky(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }   
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    
    /**
     * Ground
     */
    public enum Ground { 
        STEINIG("Steiniger Untergrund knirscht unter deinen Füßen."),
        ERDIG("Weicher Erdboden gibt unter deinem Gewicht nach."),
        METALLISCH("Metallplatten bilden einen kalten Boden."),
        WAESSRIG("Wasser bedeckt den Grund."),
        PFLASTER("Altes Pflaster erstreckt sich vor dir."),
        NULL("Es gibt keinen festen Boden - du schwebst."),
        GLAS("Durchsichtiges Glas gibt den Blick nach unten frei."),
        SCHWEBEND("Schwebende Plattformen bilden den Pfad."),
        KRISTALLIN("Kristalline Strukturen ragen aus dem Boden."),
        ASTEROIDENSTAUB("Feiner Staub wirbelt bei jedem Schritt auf."),
        FLEISCHIG("Der Boden fühlt sich warm und pulsierend an."),
        WUCHERND("Wuchernde Vegetation überwuchert alles."),
        SCHLEIMIG("Schleimiger Belag bedeckt jede Oberfläche."),
        NICHT_EUKLIDISCH("Der Boden folgt geometrischen Unmöglichkeiten."),
        FLUESSIG("Die Oberfläche fließt wie eine Flüssigkeit."),
        SCHWANKEND("Der Untergrund schwankt unberechenbar."),
        EISENROST("Rostige Eisenkonstruktionen bilden den Grund."),
        GITTER("Metallgitter geben Einblick in die Tiefe."),
        SUMPFIG("Sumpfiges Gelände erschwert jeden Schritt."),
        VULKANISCH("Vulkanisches Gestein strahlt Hitze ab."),
        RUNEN("Leuchtende Runen sind in den Boden eingelassen."),
        NEBELHAFT("Nebel verschleiert den Boden unter dir."),
        VERFAULT("Verfaulte Materialien zerfallen bei Berührung."),
        ZERBROCHEN("Zerbrochene Strukturen liegen überall verstreut."),
        UEBERWUCHERT("Pflanzen haben längst die Kontrolle übernommen."),
        MERKWUERDIG("Der Boden ist merkwürdig.");

        private final String[] descriptions;
        Ground(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }   
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }        
    }

    
    /**
     * Lighting
     */
    public enum Lighting {
        HELL("Grelles Licht blendet dich."),
        DUNKEL("Nur schwaches Licht durchdringt die Finsternis."),
        DAEMMERIG("Ewige Dämmerung herrscht hier."),
        GRELL("Gleißendes Licht sticht in deine Augen."),
        PULSIEREND("Das Licht pulsiert in unregelmäßigem Rhythmus."),
        NEON("Neonlicht taucht alles in künstliche Farben."),
        KUENSTLICH("Sterile Beleuchtung erhellt die Umgebung."),
        STROBOSKOPISCH("Stroboskopisches Flackern desorientiert dich."),
        STERNENLICHT("Nur Sternenlicht spendet schwache Orientierung."),
        BIOLUMINESZENT("Organismen leuchten sanft in der Dunkelheit."),
        GEDAEMPFT("Gedämpftes Licht schafft eine bedrückende Atmosphäre."),
        UNMOEGLICH("Das Licht kommt aus Richtungen, die nicht existieren sollten."),
        VERSCHOBEN("Die Lichtquelle scheint sich ständig zu verschieben."),
        FLAMMEN("Flammen werfen tanzende Schatten."),
        FUNKENFLUG("Funken sprühen durch die Luft."),
        MAGISCH("Magisches Licht erhellt ohne Quelle."),
        GEISTERHAFT("Geisterhafte Illumination schwebt umher."),
        AURORAL("Leuchtende Schleier ziehen durch die Luft."),
        VERFALLEND("Das Licht selbst scheint zu verfallen."),
        UNGEWOEHNLICH("Das Licht ist ungewöhnlich."); 

        private final String[] descriptions;
        Lighting(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    
    /**
     * Weather
     */
    public enum Weather {
        KLAR("Das Wetter ist ruhig und stabil."),
        WINDSTILL("Nicht der leiseste Lufthauch bewegt sich."),
        SCHWUEL("Schwüle Hitze macht das Atmen schwer."),
        GEWITTERIG("Gewitter toben in der Ferne."),
        NEBLIG("Dichter Nebel schränkt die Sicht ein."),
        ENERGIEBLITZE("Energieblitze zucken durch die Luft."),
        KUENSTLICHER_REGEN("Künstlicher Regen fällt in präzisen Intervallen."),
        METEORSCHAUER("Meteore streifen die Atmosphäre."),
        SONNENWIND("Geladene Partikel wirbeln umher."),
        SPORENNEBEL("Sporen schweben träge durch die Luft."),
        ENZYMATISCH("Ätzende Substanzen kondensieren aus der Luft."),
        REALITAETSBRUECHE("Die Realität selbst scheint zu brechen."),
        ZEITSCHLEIFEN("Zeit verhält sich hier... anders."),
        PARADOX("Widersprüchliche Wetterphänomene existieren gleichzeitig."),
        ASCHEREGEN("Asche rieselt beständig herab."),
        HITZEWELLE("Eine drückende Hitzewelle lastet über allem."),
        RUSS("Rußpartikel schweben überall in der Luft."),
        MAGIESTROEME("Magische Strömungen sind spürbar."),
        ÄTHERWIND("Ätherische Winde tragen fremdartige Energien."),
        DAUERREGEN("Endloser Regen prasselt herab."),
        UNBESTAENDIG("Das Wetter ist unbeständig.");

        private final String[] descriptions;
        Weather(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)];  }
    }

    
    private final Temperature[] temperatures;
    private final Smell[] smells;
    private final Sound[] sounds;
    private final Sky[] skies;
    private final Ground[] grounds;
    private final Lighting[] lightings;
    private final Weather[] weathers;
    
    Atmosphere(Temperature[] te, Smell[] sm, Sound[] so, Sky[] sk, Ground[] gr, Lighting[] li, Weather[] we) {
        this.temperatures = te; 
        this.smells = sm; 
        this.sounds = so; 
        this.skies = sk; 
        this.grounds = gr;
        this.lightings = li;
        this.weathers = we;
    }

    public Temperature[] getTemperatures() { return temperatures; }
    public Smell[] getSmells() { return smells; }
    public Sound[] getSounds() { return sounds; }
    public Sky[] getSkies() { return skies; }
    public Ground[] getGrounds() { return grounds; }
    public Lighting[] getLightings() { return lightings; }
    public Weather[] getWeathers() { return weathers; }
    
    public Temperature getRandomTemperature(Random rng) { return temperatures[rng.nextInt(temperatures.length)]; }
    public Smell getRandomSmell(Random rng) { return smells[rng.nextInt(smells.length)]; }
    public Sound getRandomSound(Random rng) { return sounds[rng.nextInt(sounds.length)]; }
    public Sky getRandomSky(Random rng) { return skies[rng.nextInt(skies.length)]; }
    public Ground getRandomGround(Random rng) { return grounds[rng.nextInt(grounds.length)]; }
    public Lighting getRandomLighting(Random rng) { return lightings[rng.nextInt(lightings.length)]; }
    public Weather getRandomWeather(Random rng) { return weathers[rng.nextInt(weathers.length)]; }

    public static Atmosphere getRandom(Random rng) {
        Atmosphere[] values = Atmosphere.values();
        return values[rng.nextInt(values.length)];
    }
}