package main;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class abstractScene {

    /**
     * Return canonical keys
     */
    public abstract ArrayList<String> getChoices();

    public abstract String getNextId(String verb, String noun);

    /**
     * Return a short title for the scene.
     */
    public abstract String getTitle();

    public abstract String getDescription();

    /**
     * Return the scene id.
     */
    public abstract String getId();

}
