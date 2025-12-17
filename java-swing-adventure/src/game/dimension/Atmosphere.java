package game.dimension;

import java.util.Random;

public enum Atmosphere {
    
    /**
     * =========================================================
     * =========================================================
     *              Hierher kommen die Fehler!!!
     * =========================================================
     * =========================================================
     * 
     * KOMPLETTÜBERHOLUNG HIER NÖTIG!
     * 
     * Erstmal werden die AtmophereTypen vollkommen überholt
     * Außerdem werden in Zukunft die Atmosphäre-Typen in der Area ausgegeben und nichtmehr Dimensionsabhängig sein
     * Dimensionen und Areale erlauben beide verschiedene Eigenschaften-Typen
     * Daraus wird dann ein Pool gebaut und entsprechend für die Area generiert.
     * Vielleicht helfen Prioritätenlisten oder ähnliches. Bitte hierzu nochmal genauer Gedanken machen.
     * 
     * Zuerst wird ein funktionsfähiger Dimensions- und Arealgenerator gebaut, anschließend kann dann das Spiel implementiert werden.
     * 
     * 
     * 
     * 
     * 
     * ----------------------------------------
     * Weitere Überlegungen
     * ----------------------------------------
     * 
     * Areale werden generiert. In einem Areal herrscht eine sound-kulisse.
     * Dazu muss aber auch gewusst werden, was in dem Areal alles zu finden ist.
     * Dementsprechend muss die Atmosphäre gesetzt/erzählt werden.
     */
    
    NORMAL(
        new Temperature[]{Temperature.WARM, Temperature.KUEHL, Temperature.NORMAL},
        new Smell[]{Smell.ERDIG, Smell.BLUMIG, Smell.HARZIG},
        new Sound[]{Sound.PLÄTSCHERN, Sound.RUHIG, Sound.TIERISCH, Sound.URBAN},
        new Sky[]{Sky.KLAR, Sky.WOLKENVERHANGEN},
        new Ground[]{Ground.ERDIG, Ground.PFLASTER, Ground.STEINIG, Ground.SUMPFIG, Ground.UEBERWUCHERT},
        new Lighting[]{Lighting.DAEMMERIG, Lighting.DUNKEL, Lighting.HELL, Lighting.STERNENLICHT},
        new Weather[]{Weather.SCHWUEL, Weather.KLAR, Weather.NEBLIG, Weather.WINDSTILL}
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
        EISIG("Eisige Kälte beißt in deine Haut.", "Die Luft ist so kalt, dass sie beim Einatmen kristallisiert.", "Frost zeichnet feine Muster auf deine Kleidung, ohne sie zu berühren.","Ein eisiger Wind schneidet durch deine Kleidung."),
        KALT("Kalte Luft umgibt dich.", "Kälte kriecht deinen Nacken hinab."),
        KUEHL("Eine angenehme Kühle liegt in der Luft.", "Ein kühler Hauch zieht durch die Luft."), 
        NORMAL("Die Temperatur hier ist erträglich.", "Die Temperatur ist angenehm.", "Hier ist es nicht zu warm, nicht zu kalt"),
        WARM("Die Umgebung ist angenehm warm.", "Wärme umgibt dich.", "Die Temperatur ist wohlig warm."),
        HEISS("Ballende Hitze steigt auf.", "Drückende Hitze macht jeden Atemzug schwer.", "Drückende Hitze macht jeden Atemzug zur Qual."),
        SCHWUEL("Die Umgebung fühlt sich feucht-warm an.", "Schwüle Wärme klebt an deiner Kleigung."), 
        SCHWANKEND("Die Temperatur schwankt unruhig", "Dein Körper kann sich nicht entscheiden, ob er zittern oder schwitzen soll.", "Hitze- und Kälteschübe wechseln sich ab."), 
        NULL(""),
        PARADOX("Es ist gleichzeitig warm und kalt.", "Die Temperatur fühlt sich seltsam an");

        private final String[] descriptions;
        Temperature(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    /**
     * Smell
     */
    public enum Smell {
        ERDIG("Ein schwerer, erdiger Geruch erreicht deine Nase."),
        MOOSIG("Es riecht nach feuchtem Moos und nasser Erde.", "Es riecht nach tiefem Wald und Moos.", "Es riecht nach feuchtem Moos."),
        RAUCHIG("Ein dicker Rauch hängt in der Luft.", "Ein rauchiger Geruch reizt deinen Hals."),
        KOKELIG("Es riecht verkokelt und als hätte es gebrannt."),
        METALLISCH("Ein Geruch nach Metall liegt in der Luft.", "Scharfer, metallischer Geruch legt sich auf deine Zunge."),
        ÖLIG("Die Luft ist schwer und dunkel - es riecht nach Öl.", "Es riecht nach schwerem Öl."),
        CHLORIG("Ein stechender Chlor-Geruch liegt in der Luft.", "Es riecht hier wie im Schwimmbad!", "Ein beißender Chlor-Geruch erfüllt dich."),
        SYNTHETISCH("Es riecht nach Plastik und Gummi.", "Es riecht hier wie im Krankenhaus.", "Künstlcher Geruch wie Gummi kribbelt in deiner Nase."),
        BLUMIG("Sanfte, blumige Süße weht in eine Nase.", "Ein leichter, blumiger Duft liegt in der Luft.", "Es riecht nach einer schönen Blumenwiese."),
        FRUCHTIG("Fruchtige Noten liegen in der Luft.", "Es riecht süß und saftig.", "In deine Nase weht der Duft nach süßen Früchten."),
        HARZIG("Es riecht nach frischem Kiefernholz.", "Es riecht nach warmem und dichtem Harz"),
        MUFFIG("Ein muffiger Geruch liegt in der Luft.", "Es riecht nach Staub und stickigen, alten Räumen.", "Es riecht irgendiwe alt und muffig."),
        MODRIG("Es riecht modrig und schwer.", "Es riecht nach Verfall und feuchtem Moder.", "Modergeruch liegt schwer über der Umgebung."),
        SALZIG("Es duftet nach Meer und Weite.", "Ein starker Salzgeruch liegt drückend in der Luft.", "Es riecht nach Salz und Ozeanen."),
        KELLERFEUCHT("Es riecht hier nach feuchtem, alten Keller.", "Es riecht nach altem, feuchtem Stein.", "Es ist feucht und riecht nach Keller."),
        WÜRZIG("Du wirst von einem Duft der exotischsten Gewürze erfüllt.", "Die verschiedensten Gewürze kitzeln in deiner Nase.", "Es riecht nach Kräuter und Pfeffer.", "Viele verschiedene Gewürze duften in der Luft."),
        NULL;

        private final String[] descriptions;
        Smell(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }



    /**
     * Sound
     */
    public enum Sound { 
        RUHIG("Friedliche Stille umgibt dich."),
        RATTERN("Lautes Rattern ist zu hören.", "Du hörst lautes Rattern - ähnlich wie bei einem Zug."),
        TIERISCH("Tiergeräusche erfüllen die Luft.", "Laute Tiere sind überall zu hören."), 
        KNACKEN("Du hörst wiederholt lautes Knacken.", "Immer wieder knackt es laut."),
        GROLLEN("Ein tiefes Grollenersch erschüttert dich.", "Tiefes, markerschütterndes Grollen ertönt."),
        VERZERRT("Verzerrte Gräusche klirren, die kaum identifiierbar sind.", "Die Geräuschkulisse ist surreal - kaum identifizierbar."),
        HALLEND("Echo schallt überall zu dir zurück."),
        DUMPF("Geräusche wirken hier ganz dumpf.", "Die Umgebung verschlingt alle Geräusche"),
        KNISTERN("Die Luft knistert energisch.", "Du hörst lautes knistern."),
        ZISCHEN("Druckvolles Zischen erfüllt die Luft.", "Lautes zischen ertönt."),
        PLÄTSCHERN("In der Nähe scheint es zu plätschern.", "Es plätschert hörbar.", "Plätschern erreicht deine Ohren."),
        SCHRILL("Schrille Töne erschrecken dich.", "Ein schrilles Geräusch fährt in Mark und Bein."),
        SURREND("Konstantes surren ertönt die Umgebung"),
        FLUESTERN("In der Nähe hörst du leises Flüstern.", "Geheimnisvolles Flüstern dringt in deine Ohren"),
        MELODISCH("Es herrscht eine melodische Atmospähre, voller Lieder und Gesang.", "Unbekannte Melodien erklingen."),
        RAUSCHEN("Sanftes rauschen bewegt die Umgebung"),
        URBAN("Städtisches Treiben ist zu hören.");

        private final String[] descriptions;
        Sound(String... descriptions) { this.descriptions = descriptions; }
        public String[] getDescriptions() { return descriptions; }  
        public String getRandomDescription(Random rng) { return descriptions[rng.nextInt(descriptions.length)]; }
    }

    
    /**
     * Sky - ab hier noch weiter überarbeiten bitte!
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