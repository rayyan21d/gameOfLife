package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Scene extends abstractScene {
    // Contains information about one scene only
    private String id;
    private String title;
    private String description;
    private HashMap<String, String> transitions = new HashMap<>();
    private ArrayList<String> choices = new ArrayList<>();

    // A Non-printable character perfect for internal representation
    public static final String UNIT_SEPARATOR = "\u001F";


    public Scene(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    private static String clean(String s) {
        if (s == null) return "";
        return s.trim().toLowerCase().replaceAll("\\p{C}", "");
    }

    public static String makeKey(String verb, String noun) {
        String v = clean(verb);
        String n = clean(noun);
        return v + UNIT_SEPARATOR + n;
    }

    /**
     * Add a choice mapping. Use canonical verb and noun (both should be normalized).
     * If noun is empty, use verb + ":" as the key.
     */
    public void addChoice(String verb, String noun, String nextSceneId) {
        String key = makeKey(verb, noun);
        transitions.put(key, nextSceneId);

        String choice = verb + (noun == null || noun.isEmpty() ? "" : " " + noun);
        choices.add(choice);
    }


    /**
     * Return the next scene id for a verb+noun canonical pair.
     */
    public String getNextId(String verb, String noun) {

        String key = makeKey(verb, noun);
        String next = transitions.get(key);

        if (next != null) return next;
        // fallback to verb-only (noun empty)
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
    public String getDescription() { return description; }

    @Override
    public String getId() {
        return id;
    }


}
