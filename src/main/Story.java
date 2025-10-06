package main;

import main.Scene;

import java.util.Map;

// Exposes methods for the World to interact with the story

public class Story {

    private final Map<String, Scene> scenes;
    private final CommandDictionary dict;
    private final AlphabeticList globalList;
    private Scene currentScene;
    private PlayerState player;

    // Initializes the story based on the player type
    // Has the graph structure in here!!
    // combines scenes and choices in a elegant way!


    public Story(Map<String, Scene> scenes, CommandDictionary dict, AlphabeticList globalList) {
        this.scenes = scenes;
        this.dict = dict;
        this.globalList = globalList;
        this.currentScene = scenes.getOrDefault("S01_StartGame", scenes.values().iterator().next());
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
        return currentScene.getTitle() + "\n\n" + currentScene.getDescription();
    }

    private void applyEffects(String verb, String noun) {
        if ("train".equals(verb)) player.flags.add("trained");
        if ("help".equals(verb)) player.flags.add("helped");
        if ("read".equals(verb) && "scroll".equals(noun)) player.flags.add("readScroll");
        if ("accept".equals(verb) && "newmentor".equals(noun)) player.mentor = "switched";
    }

    public void jumpTo(String sceneId) {
        Scene s = scenes.get(sceneId);
        if (s != null) { currentScene = s; player.currentSceneId = s.getId(); }
    }

    public boolean save(String path) { return player.save(path); }
    public boolean load(String path) {
        PlayerState p = PlayerState.load(path);
        if (p == null) return false;
        this.player = p;
        Scene s = scenes.get(p.currentSceneId);
        if (s != null) currentScene = s;
        return true;
    }

    /**
     * Follow any automatic transitions (canonical verb="" and noun="") repeatedly.
     * Useful for initialization scenes that should immediately forward the player.
     */
    private void autoAdvance() {
        boolean advanced;
        do {
            advanced = false;
            // attempt to find an empty-key transition for current scene
            String nextId = currentScene.getNextId("", "");
            if (nextId != null) {
                Scene next = scenes.get(nextId);
                if (next != null) {
                    currentScene = next;
                    player.currentSceneId = currentScene.getId();
                    advanced = true;
                } else {
                    // transition target missing; stop and surface an error in logs if desired
                    advanced = false;
                }
            }
        } while (advanced);
    }



}
