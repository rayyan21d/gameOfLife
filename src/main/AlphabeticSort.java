package main;

/**
 * Polynomial rolling hash for tokens (verbs / nouns / names).
 * Produces a stable non-negative int suitable as an identifier.
 */
public class AlphabeticSort {

    //
    private final long MOD = 1_000_000_007L;
    private final long BASE = 131L;

    public AlphabeticSort() {}

    /**
     * Hash a token to a non-negative int identifier.
     */
    public int Sort(String word) {
        if (word == null) return 0;
        String s = word.trim().toLowerCase();
        long h = 0L;
        for (int i = 0; i < s.length(); i++) {
            h = (h * BASE + (int) s.charAt(i) + 1) % MOD;
        }
        return (int) (h & 0x7fffffff);
    }
}
