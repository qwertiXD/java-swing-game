package game;
import game.area.Area;
import game.area.AreaGraph;
import game.dimension.Dimension;
import game.dimension.DimensionGenerator;
import game.encounter.Encounter;
import game.item.Item;
import game.quest.Quest;
import game.quest.QuestGenerator;
import java.util.*;

public class GameState {
    
    private static GameState instance;
    
    // Seed-System
    private final long rootSeed;
    private Random worldRng;
    private Random npcRng;
    private Random lootRng;
    private Random eventRng;
    
    // Spieler
    private final Player player;
    
    // Aktuelle Dimension und Areal
    private Dimension currentDimension;
    private Area currentArea;
    
    // Räume und Navigation
    private Encounter currentEncounter;
    
    // Quests
    private final List<Quest> activeQuests;
    @SuppressWarnings("unused")
    private final List<Quest> completedQuests;
    
    // NPCs in dieser Welt
    @SuppressWarnings("unused")
    private final Map<String, NPC> worldNPCs;
    
    // Statistiken
    private int encountersExplored;
    private int enemiesDefeated;
    private int areasExplored;
    
    // Navigation Mode
    private enum NavigationMode { AREA_SELECTION, ENCOUNTER_SELECTION }
    private NavigationMode navMode;
    
    private GameState(long seed) {
        this.rootSeed = seed;
        initializeRNGs();
        
        this.player = new Player("Wanderer");
        this.activeQuests = new ArrayList<>();
        this.completedQuests = new ArrayList<>();
        this.worldNPCs = new HashMap<>();
        this.encountersExplored = 0;
        this.enemiesDefeated = 0;
        this.areasExplored = 0;
        this.navMode = NavigationMode.AREA_SELECTION;
    }
    
    public static void initialize(long seed) {
        instance = new GameState(seed);
    }
    
    public static GameState getInstance() {
        if (instance == null) {
            initialize(System.currentTimeMillis());
        }
        return instance;
    }
    
    private void initializeRNGs() {
        Random root = new Random(rootSeed);
        worldRng = new Random(root.nextLong());
        npcRng = new Random(root.nextLong());
        lootRng = new Random(root.nextLong());
        eventRng = new Random(root.nextLong());
    }
    
    public void startNewRun() {
        // Dimension generieren
        currentDimension = DimensionGenerator.generate(worldRng);
        GameWindow.print("=== NEUER RUN GESTARTET ===");
        GameWindow.print("Seed: " + rootSeed);
        GameWindow.print("");
        GameWindow.print("Du erwachst in: " + currentDimension.getName());
        GameWindow.print(currentDimension.getDescription());
        GameWindow.print("");
        
        // Areal-Graph laden und Start-Areal betreten
        AreaGraph areaGraph = currentDimension.getAreaGraph();
        currentArea = areaGraph.getCurrentArea();
        areasExplored = 1;
        
        // Startquest
        Quest startQuest = QuestGenerator.generateQuest(eventRng, currentDimension, player);
        if (startQuest != null) {
            activeQuests.add(startQuest);
            GameWindow.print("Neue Quest: " + startQuest.getTitle());
            GameWindow.print("");
        }
        
        // Direkt ins erste Areal eintreten
        enterArea(0);
    }
    
    public void showAreaSelection() {
        AreaGraph graph = currentDimension.getAreaGraph();
        
        GameWindow.print("--- AREAL-ÜBERSICHT ---");
        GameWindow.print("Aktueller Ort: " + currentArea.getName());
        GameWindow.print("Erkundet: " + graph.getExploredCount() + "/" + graph.getTotalAreaCount());
        GameWindow.print("");
        
        List<Area> connected = graph.getNeighbors(currentArea);
        if (connected.isEmpty()) {
            GameWindow.print("Keine weiteren Areale verfügbar.");
            GameWindow.print("");
            return;
        }
        
        GameWindow.print("Verfügbare Areale:");
        for (int i = 0; i < connected.size(); i++) {
            Area area = connected.get(i);
            String status = area.isExplored() ? "[Erkundet]" : "[Unbekannt]";
            
            GameWindow.print((i + 1) + ". " + area.getName() + " " + status);
        }
        
        GameWindow.print("");
        GameWindow.print("Tippe 'explore <nummer>' um ein Areal zu betreten");
        GameWindow.print("Oder: 'status', 'quests', 'map' für Übersicht");
    }
    
    public void enterArea(int index) {
        AreaGraph graph = currentDimension.getAreaGraph();
        
        if (graph.moveToIndex(index)) {
            currentArea = graph.getCurrentArea();
            
            if (!currentArea.isExplored()) {
                areasExplored++;
            }
            
            GameWindow.print("");
            GameWindow.print(currentArea.getFullDescription());
            
            // Areal Betreten
            GameWindow.print(">>> Du befindest dich im: " + currentArea.getName());
            GameWindow.print(currentArea.getDescription());
            
            // Navigation auf Encounter-Auswahl setzen
            navMode = NavigationMode.ENCOUNTER_SELECTION;
            
            // Sofort ersten Encounter starten
            if (!currentArea.getEncounters().isEmpty()) {
                GameWindow.print("");
                enterEncounter(0);
            } else {
                GameWindow.print("Dieses Areal scheint leer zu sein.");
                GameWindow.print("");
                showAreaSelection();
            }
        } else {
            GameWindow.print("Ungültige Auswahl!");
        }
    }
    
    public void showEncounterSelection() {
        if (currentArea.getEncounters().isEmpty()) {
            GameWindow.print("Keine weiteren Aktivitäten in diesem Areal.");
            GameWindow.print("");
            navMode = NavigationMode.AREA_SELECTION;
            showAreaSelection();
            return;
        }
        
        GameWindow.print("--- Aktivitäten in: " + currentArea.getName() + " ---");
        for (int i = 0; i < currentArea.getEncounters().size(); i++) {
            Encounter encounter = currentArea.getEncounters().get(i);
            GameWindow.print((i + 1) + ". " + encounter.getName() + " [" + encounter.getDifficulty() + "]");
        }
        GameWindow.print("");
        GameWindow.print("Tippe die Nummer ein, um fortzufahren.");
        GameWindow.print("Oder: 'back' für Areal-Auswahl, 'status', 'quests', 'map'");
    }
    
    public void enterEncounter(int index) {
        if (index < 0 || index >= currentArea.getEncounters().size()) {
            GameWindow.print("Ungültige Auswahl!");
            return;
        }
        
        currentEncounter = currentArea.getEncounters().get(index);
        encountersExplored++;
        
        GameWindow.print("");
        GameWindow.print("=== " + currentEncounter.getName() + " ===");
        GameWindow.print(currentEncounter.getDescription());
        GameWindow.print("");
        
        currentEncounter.trigger(this);
    }
    
    public void processCommand(String command) {
        command = command.trim().toLowerCase();
        
        if (command.equals("status")) {
            showPlayerStatus();
        } else if (command.equals("quests")) {
            showQuests();
        } else if (command.equals("map")) {
            showMap();
        } else if (command.equals("back") && navMode == NavigationMode.ENCOUNTER_SELECTION) {
            navMode = NavigationMode.AREA_SELECTION;
            showAreaSelection();
        } else if (command.startsWith("explore ") && navMode == NavigationMode.AREA_SELECTION) {
            try {
                int areaIndex = Integer.parseInt(command.substring(8)) - 1;
                enterArea(areaIndex);
            } catch (NumberFormatException e) {
                GameWindow.print("Ungültige Eingabe!");
            }
        } else {
            try {
                int encounterIndex = Integer.parseInt(command) - 1;
                if (navMode == NavigationMode.ENCOUNTER_SELECTION) {
                    enterEncounter(encounterIndex);
                } else {
                    GameWindow.print("Verwende 'explore <nummer>' um Areale zu betreten.");
                }
            } catch (NumberFormatException e) {
                GameWindow.print("Unbekannter Befehl: " + command);
            }
        }
    }
    
    private void showPlayerStatus() {
        GameWindow.print("");
        GameWindow.print("=== SPIELER STATUS ===");
        GameWindow.print("Name: " + player.getName());
        GameWindow.print("Leben: " + player.getHealth() + "/" + player.getMaxHealth());
        GameWindow.print("Inventar: " + player.getInventory().size() + " Items");
        
        if (!player.getInventory().isEmpty()) {
            GameWindow.print("");
            GameWindow.print("--- Ausrüstung ---");
            for (Item item : player.getInventory()) {
                GameWindow.print("• " + item.getFullName());
            }
        }
        
        GameWindow.print("");
        GameWindow.print("Areale erkundet: " + areasExplored);
        GameWindow.print("Räume erkundet: " + encountersExplored);
        GameWindow.print("Besiegte Gegner: " + enemiesDefeated);
        GameWindow.print("");
    }
    
    private void showQuests() {
        GameWindow.print("");
        GameWindow.print("=== AKTIVE QUESTS ===");
        if (activeQuests.isEmpty()) {
            GameWindow.print("Keine aktiven Quests.");
        } else {
            for (Quest quest : activeQuests) {
                GameWindow.print("• " + quest.getTitle());
                GameWindow.print("  " + quest.getDescription());
            }
        }
        GameWindow.print("");
    }
    
    private void showMap() {
        GameWindow.print("");
        GameWindow.print("=== KARTE ===");
        GameWindow.print("Dimension: " + currentDimension.getName());
        GameWindow.print("");
        
        AreaGraph graph = currentDimension.getAreaGraph();
        GameWindow.print("Areale erkundet: " + graph.getExploredCount() + "/" + graph.getTotalAreaCount());
        GameWindow.print("");
        
        List<Area> explored = graph.getExploredAreas();
        if (!explored.isEmpty()) {
            GameWindow.print("Entdeckte Orte:");
            for (Area area : explored) {
                String current = (area == currentArea) ? " <- HIER" : "";
                GameWindow.print("• " + area.getName() + current);
            }
        }
        GameWindow.print("");
    }
    
    public void generateNewEncounters() {
        // Encounter aus der Liste entfernen
        if (currentEncounter != null) {
            currentArea.getEncounters().remove(currentEncounter);
        }
        
        // Wenn noch Encounters übrig sind, zeige Auswahl
        if (!currentArea.getEncounters().isEmpty()) {
            GameWindow.print("Du verlässt den Ort.");
            GameWindow.print("");
            navMode = NavigationMode.ENCOUNTER_SELECTION;
            showEncounterSelection();
        } else {
            // Areal vollständig erkundet - zurück zur Areal-Auswahl
            GameWindow.print("Du hast dieses Areal vollständig erkundet.");
            GameWindow.print("");
            navMode = NavigationMode.AREA_SELECTION;
            showAreaSelection();
        }
    }
    
    public void playerDied(String cause) {
        GameWindow.print("");
        GameWindow.print("===================");
        GameWindow.print("     DU BIST TOT    ");
        GameWindow.print("===================");
        GameWindow.print("Todesursache: " + cause);
        GameWindow.print("");
        GameWindow.print("Areale erkundet: " + areasExplored);
        GameWindow.print("Räume erkundet: " + encountersExplored);
        GameWindow.print("Besiegte Gegner: " + enemiesDefeated);
        GameWindow.print("");
        GameWindow.print("Das Spiel ist vorbei. Schließe das Fenster oder starte neu.");
    }
    
    // Getter
    public Player getPlayer() { return player; }
    public Dimension getCurrentDimension() { return currentDimension; }
    public Area getCurrentArea() { return currentArea; }
    public Random getWorldRng() { return worldRng; }
    public Random getNpcRng() { return npcRng; }
    public Random getLootRng() { return lootRng; }
    public Random getEventRng() { return eventRng; }
    public List<Quest> getActiveQuests() { return activeQuests; }
    public void incrementEnemiesDefeated() { enemiesDefeated++; }
}