package main;

import main.Scene;

import java.util.Map;

/**
 * It manages the pathway a player takes or is responsible for maintaining the choices and scenes the player has traversed
 */
public class Story {

    private final Map<String, Scene> scenes;
    private final CommandDictionary dict;
    private final AlphabeticList globalList;
    private Scene currentScene;
    private PlayerState player;

    public Story(Map<String, Scene> scenes, CommandDictionary dict, AlphabeticList globalList) {
        this.scenes = scenes;
        this.dict = dict;
        this.globalList = globalList;

        this.currentScene = scenes.getOrDefault("S01_Prologue", scenes.values().iterator().next());
        this.player = new PlayerState();
        player.currentSceneId = currentScene.getId();

        // Auto-advance through any scenes that are intended to forward immediately
        autoAdvance();
    }

    public Scene getCurrentScene() { return currentScene; }
    public PlayerState getPlayer() { return player; }

    public String processCommand(Command cmd) {
        if (cmd == null) return "I didn't understand that.";
        String verb = cmd.getVerb();
        String noun = cmd.getNoun();

        if (verb == null || verb.isEmpty()) return "You must provide an action.";

        Element vElem = dict.lookupInList(globalList, verb);
        Element nElem = (noun == null || noun.isEmpty()) ? null : dict.lookupInList(globalList, noun);

        if (vElem == null) return "Unknown action: '" + verb + "'. Try another verb.";

        String nextId = currentScene.getNextId(verb, noun);
        if (nextId == null) return "You can't " + verb + (noun == null || noun.isEmpty() ? "" : " " + noun) + " here.";

        // apply simple effects (expand as needed)
        applyEffects(verb, noun);

        Scene next = scenes.get(nextId);
        if (next == null) return "Transition broken: " + nextId;
        currentScene = next;
        player.currentSceneId = currentScene.getId();

        // Auto-advance if the new scene has empty transitions
        autoAdvance();

        return currentScene.getTitle() + "\n" + currentScene.getDescription();
    }

    private void applyEffects(String verb, String noun) {
        // Track element choice
        if ("choose".equals(verb)) {
            if ("fire".equals(noun)) player.benderType = "Fire";
            else if ("water".equals(noun)) player.benderType = "Water";
            else if ("earth".equals(noun)) player.benderType = "Earth";
            else if ("air".equals(noun)) player.benderType = "Air";
        }

        // Track other actions
        if ("train".equals(verb)) player.flags.add("trained");
        if ("help".equals(verb)) player.flags.add("helped");
        if ("read".equals(verb) && "scroll".equals(noun)) player.flags.add("readScroll");
        if ("attack".equals(verb)) player.flags.add("violent");
        if ("meditate".equals(verb)) player.flags.add("spiritual");
    }

    public void jumpTo(String sceneId) {
        Scene s = scenes.get(sceneId);
        if (s != null) {
            currentScene = s;
            player.currentSceneId = s.getId();
            autoAdvance();
        }
    }

    public boolean save(String path) { return player.save(path); }

    public boolean load(String path) {
        PlayerState p = PlayerState.load(path);
        if (p == null) return false;
        this.player = p;
        Scene s = scenes.get(p.currentSceneId);
        if (s != null) {
            currentScene = s;
            autoAdvance();
        }
        return true;
    }

    /**
     * Follow any automatic transitions (canonical verb="" and noun="") repeatedly.
     * Useful for initialization scenes that should immediately forward the player.
     */
    private void autoAdvance() {
        boolean advanced;
        int maxLoops = 10; // Prevent infinite loops
        int loops = 0;

        do {
            advanced = false;
            loops++;

            if (loops > maxLoops) {
                System.err.println("Warning: Auto-advance loop detected at scene " + currentScene.getId());
                break;
            }

            // attempt to find an empty-key transition for current scene
            String nextId = currentScene.getNextId("", "");
            if (nextId != null) {
                Scene next = scenes.get(nextId);
                if (next != null && !next.getId().equals(currentScene.getId())) {
                    currentScene = next;
                    player.currentSceneId = currentScene.getId();
                    advanced = true;
                } else {
                    // transition target missing or points to self; stop
                    advanced = false;
                }
            }
        } while (advanced);
    }
}