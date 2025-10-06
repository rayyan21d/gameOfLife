package main;

import java.util.*;

/**
 * Builds canonical mappings for verbs and nouns (synonyms -> canonical token),
 * and populates an AlphabeticList (your binary tree) with Element objects for each canonical token.
 *
 * Usage:
 *   CommandDictionary dict = new CommandDictionary();
 *   dict.populateAlphabeticList(alphabeticList);
 *   String canonVerb = dict.canonicalizeVerb("run");
 *   String canonNoun = dict.canonicalizeNoun("the big gate");
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
        addVerb("walk", "walk", "go", "move", "step");
        addVerb("grab", "grab", "take", "pick", "get");
        addVerb("climb", "climb", "ascend");
        addVerb("talk", "talk", "speak", "say");
        addVerb("meditate", "meditate", "focus", "calm");
        addVerb("bend", "bend", "use", "channel");
        addVerb("inspect", "inspect", "look", "examine", "search");
        addVerb("attack", "attack", "hit", "strike", "fight");
        addVerb("flee", "flee", "run", "escape");
        addVerb("help", "help", "aid", "assist");
        addVerb("train", "train", "practice", "drill");
        addVerb("read", "read", "study");
        addVerb("sneak", "sneak", "stealth");
        addVerb("parley", "parley", "negotiate", "talk");
        addVerb("choose", "choose", "select");
        addVerb("accept", "accept", "agree");
        addVerb("decline", "decline", "refuse");
        addVerb("restart", "restart", "restartgame", "restart:game");
    }

    private void buildNounMap() {
        addNoun("torch", "torch", "lamp");
        addNoun("scroll", "scroll", "paper", "tome");
        addNoun("statue", "statue", "idol");
        addNoun("bridge", "bridge");
        addNoun("monk", "monk", "elder");
        addNoun("gate", "gate", "door");
        addNoun("potion", "potion", "elixir");
        addNoun("enemy", "enemy", "general", "boss");
        addNoun("village", "village", "town");
        addNoun("forest", "forest", "woods");
        addNoun("ruins", "ruins", "remains");
        addNoun("allies", "allies", "friends");
        addNoun("scrolls", "scrolls", "ancient scrolls");
        addNoun("mentor", "mentor", "master");
        addNoun("element", "element");
        addNoun("bender", "bender", "element");
        addNoun("game", "game");
    }

    public String canonicalizeVerb(String raw) {
        if (raw == null) return null;
        return verbCanon.getOrDefault(raw.trim().toLowerCase(), raw.trim().toLowerCase());
    }

    public String canonicalizeNoun(String raw) {
        if (raw == null) return null;
        String cleaned = raw.trim().toLowerCase();
        // Try whole noun first, then try last token guess (allow multi-word nouns)
        if (nounCanon.containsKey(cleaned)) return nounCanon.get(cleaned);
        // try last word
        String[] parts = cleaned.split("\\s+");
        for (int i = parts.length - 1; i >= 0; i--) {
            String sub = String.join(" ", Arrays.copyOfRange(parts, i, parts.length));
            if (nounCanon.containsKey(sub)) return nounCanon.get(sub);
        }
        // fallback to first token
        return nounCanon.getOrDefault(parts[0], cleaned);
    }

    /**
     * Populate an AlphabeticList with Element objects for all canonical verbs and nouns.
     * Assumes AlphabeticList.insert(Node) accepts nodes; uses Element.setIdentifier() to set id.
     */
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

    /**
     * Lookup an Element by canonical token in the provided AlphabeticList.
     * Returns the Element if found, otherwise null.
     */
    public Element lookupInList(AlphabeticList list, String canonicalToken) {
        if (canonicalToken == null) return null;
        int id = sorter.Sort(canonicalToken);
        Node cur = list.root; // your Node.root is public per earlier code
        while (cur != null) {
            if (id == cur.getID()) {
                Element e = cur.getNodeElement();
                if (e != null && canonicalToken.equals(e.getName())) return e;
                // If name mismatch due to hash collision, search neighbors
                // search left subtree
                Element left = searchSubtreeForName(cur.left, canonicalToken);
                if (left != null) return left;
                Element right = searchSubtreeForName(cur.right, canonicalToken);
                if (right != null) return right;
                return cur.getNodeElement(); // best-effort
            } else if (id < cur.getID()) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    private Element searchSubtreeForName(Node node, String name) {
        if (node == null) return null;
        Element e = node.getNodeElement();
        if (e != null && name.equals(e.getName())) return e;
        Element left = searchSubtreeForName(node.left, name);
        if (left != null) return left;
        return searchSubtreeForName(node.right, name);
    }
}
