package main;

import main.Command;

public class Parser {

    private final CommandDictionary dict;
    public Parser(CommandDictionary dict){
        this.dict = dict;
    };

    /**
     * Parse raw user input into a canonical Command.
     *
     * Why we split this way:
     * - We assume typical commands are "verb [noun...]" where the first token is the verb
     *   and everything after it is the noun phrase (which may be multi-word).
     * - That preserves multi-word nouns like "ancient scroll" or "old wooden gate".
     * - We canonicalize both verb and noun via CommandDictionary so they match Scene keys.
     */
    public Command parse(String input){

        if(input == null || input.length() == 0) return null;
        String s  = input.trim().toLowerCase().replaceAll("\\p{C}", "");

        // Parsing logic
        // Extract verbRaw (first token) and nounRaw (rest, possibly multi-word)
        String verbRaw;
        String nounRaw = "";
        int firstSpace = s.indexOf(' ');
        if (firstSpace == -1) {
            // single-token command -> verb-only
            verbRaw = s;
        } else {
            verbRaw = s.substring(0, firstSpace);
            nounRaw = s.substring(firstSpace + 1).trim();
        }

        // Canonicalize using CommandDictionary provided at Parser construction
        String verb = dict.canonicalizeVerb(verbRaw);
        String noun = dict.canonicalizeNoun(nounRaw);

        // If canonicalization removed verb (null/empty) treat as invalid
        if (verb == null || verb.trim().isEmpty()) return null;

        return new Command(verb, noun == null ? "" : noun);

    };
}
