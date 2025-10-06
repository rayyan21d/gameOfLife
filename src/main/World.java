package main;
import main.Story;

// Responsible for loading the world up
// Initializes the player
// Loads the story for the player

// Exposes methods for the View to interact with the story

import java.util.Map;

/**
 * World coordinates Story and the static resources (scenes, dictionary, list).
 */
public class World {
    public final Map<String, Scene> scenes;
    public final CommandDictionary dict;
    public final AlphabeticList globalList;
    public final Story story;

    public World() {
        this.scenes = SceneFactory.buildAllScenes();
        this.dict = new CommandDictionary();
        this.globalList = new AlphabeticList();
        this.dict.populateAlphabeticList(globalList);
        this.story = new Story(scenes, dict, globalList);
    }
}
