package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Each story scenario is modeled as a scene which is extended from an abstractScene class
 */
public class Scene extends abstractScene {
    private String id;
    private String title;
    private String description;
    private HashMap<String, String> transitions = new HashMap<>();
    private ArrayList<String> choices = new ArrayList<>();

    // Use simple pipe separator
    // This prevents clean() from accidentally removing it
    public static final String SEPARATOR = "|";

    public Scene(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Clean input by trimming and lowercasing.
     * Removed replaceAll for control chars since we're using a safe separator.
     */
    private static String clean(String s) {
        if (s == null) return "";
        return s.trim().toLowerCase();
    }

    /**
     * Create transition key from verb and noun.
     * Uses pipe separator which won't be affected by cleaning.
     */
    public static String makeKey(String verb, String noun) {
        String v = clean(verb);
        String n = clean(noun);
        return v + SEPARATOR + n;
    }

    /**
     * Add a choice mapping with canonical verb and noun.
     */
    public void addChoice(String verb, String noun, String nextSceneId) {
        String key = makeKey(verb, noun);
        transitions.put(key, nextSceneId);

        // Build human-readable choice string
        String choice = verb + (noun == null || noun.isEmpty() ? "" : " " + noun);
        choices.add(choice);
    }

    /**
     * Return the next scene id for a verb+noun canonical pair.
     * Falls back to verb-only if exact match not found.
     */
    public String getNextId(String verb, String noun) {
        String key = makeKey(verb, noun);
        String next = transitions.get(key);

        if (next != null) return next;

        // Fallback to verb-only (empty noun)
        String verbOnly = makeKey(verb, "");
        return transitions.get(verbOnly);
    }

    @Override
    public ArrayList<String> getChoices() {
        return new ArrayList<>(choices);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return id;
    }
}