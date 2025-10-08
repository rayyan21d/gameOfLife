package main;

import java.util.*;

/**
 * This is the dictionary for lookup and storing verbs and nouns
 */
public class CommandDictionary {
    private final Map<String, String> verbCanon = new HashMap<>();
    private final Map<String, String> nounCanon = new HashMap<>();
    private final AlphabeticSort sorter = new AlphabeticSort();

    public CommandDictionary() {
        buildVerbMap();
        buildNounMap();
    }

    private void addVerb(String canonical, String... synonyms) {
        verbCanon.put(canonical, canonical);
        for (String s : synonyms) verbCanon.put(s.trim().toLowerCase(), canonical);
    }

    private void addNoun(String canonical, String... synonyms) {
        nounCanon.put(canonical, canonical);
        for (String s : synonyms) nounCanon.put(s.trim().toLowerCase(), canonical);
    }

    private void buildVerbMap() {
        // Core movement
        addVerb("walk", "walk", "go", "move", "step", "travel", "wander");
        addVerb("flee", "flee", "run", "escape", "retreat");

        // Interaction
        addVerb("talk", "talk", "speak", "say", "discuss", "converse");
        addVerb("help", "help", "aid", "assist", "support", "heal");

        // Training & Development
        addVerb("train", "train", "practice", "drill", "exercise");
        addVerb("meditate", "meditate", "focus", "calm", "contemplate", "reflect");

        // Combat
        addVerb("attack", "attack", "hit", "strike", "fight", "battle");

        // Learning
        addVerb("read", "read", "study", "learn");
        addVerb("inspect", "inspect", "look", "examine", "search", "investigate");

        // Choices & Decisions
        addVerb("choose", "choose", "select", "pick");

        // Game control
        addVerb("restart", "restart", "restartgame", "restart:game", "reset");

        // Specialized (for backward compatibility if needed)
        addVerb("grab", "grab", "take", "get");
        addVerb("climb", "climb", "ascend");
        addVerb("bend", "bend", "use", "channel");
        addVerb("sneak", "sneak", "stealth");
        addVerb("parley", "parley", "negotiate");
        addVerb("accept", "accept", "agree");
        addVerb("decline", "decline", "refuse", "reject");
    }

    private void buildNounMap() {
        // Elements (CRITICAL - used at game start!)
        addNoun("fire", "fire", "flame", "flames", "🔥");
        addNoun("water", "water", "waves", "ocean", "💧");
        addNoun("earth", "earth", "stone", "rock", "ground", "🌍");
        addNoun("air", "air", "wind", "breeze", "💨");

        // People
        addNoun("mentor", "mentor", "master", "teacher", "elder", "guide");
        addNoun("monk", "monk", "elder", "sage", "priest", "guardian");
        addNoun("enemy", "enemy", "foe", "opponent", "general", "boss", "bandits", "thugs", "leader");
        addNoun("allies", "allies", "friends", "companions", "army", "forces", "warriors");

        // Places
        addNoun("village", "village", "town", "home", "settlement");
        addNoun("forest", "forest", "woods", "wilderness", "trees");
        addNoun("ruins", "ruins", "remains", "temple", "shrine");

        // Objects
        addNoun("scroll", "scroll", "scrolls", "paper", "tome", "book", "text");
        addNoun("torch", "torch", "lamp", "light");
        addNoun("statue", "statue", "idol");
        addNoun("gate", "gate", "door");
        addNoun("potion", "potion", "elixir");
        addNoun("bridge", "bridge");

        // Abstract concepts
        addNoun("element", "element", "power", "energy");
        addNoun("bender", "bender");
        addNoun("game", "game");

        // Leave empty string for verb-only commands
        addNoun("", "");
    }

    // A function to convert verbs into canonical form
    // this form acts as a state variable for internal representation
    public String canonicalizeVerb(String raw) {
        if (raw == null) return null;
        return verbCanon.getOrDefault(raw.trim().toLowerCase(), raw.trim().toLowerCase());
    }

    public String canonicalizeNoun(String raw) {
        if (raw == null || raw.trim().isEmpty()) return "";
        String cleaned = raw.trim().toLowerCase();

        // Try whole noun first
        if (nounCanon.containsKey(cleaned)) return nounCanon.get(cleaned);

        // Try multi-word matching (last word first)
        String[] parts = cleaned.split("\\s+");
        for (int i = parts.length - 1; i >= 0; i--) {
            String sub = String.join(" ", Arrays.copyOfRange(parts, i, parts.length));
            if (nounCanon.containsKey(sub)) return nounCanon.get(sub);
        }

        // Fallback to first token
        return nounCanon.getOrDefault(parts[0], cleaned);
    }

    // This builds up the list which performs lookups
    public void populateAlphabeticList(AlphabeticList list) {
        Set<String> verbs = new HashSet<>(verbCanon.values());
        Set<String> nouns = new HashSet<>(nounCanon.values());

        for (String v : verbs) {
            Element e = new Element(v);
            e.setIdentifier(sorter.Sort(v));
            Node n = new Node(e);
            list.insert(n);
        }
        for (String nm : nouns) {
            Element e = new Element(nm);
            e.setIdentifier(sorter.Sort(nm));
            Node n = new Node(e);
            list.insert(n);
        }
    }

    // Search a word in the list/dictionary.
    public Element lookupInList(AlphabeticList list, String canonicalToken) {
        if (canonicalToken == null || list == null) return null;

        int id = sorter.Sort(canonicalToken);
        Element result = list.findByIdAndName(id, canonicalToken);

        if (result == null) {
            result = list.findByName(canonicalToken);
        }

        return result;
    }
}