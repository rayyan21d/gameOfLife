package main;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * PlayerState holds the persistent runtime state for the player.
 * Serializable so Story.save/load can use Java object serialization.
 */
public class PlayerState implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public String benderType;
    public String mentor;
    public Set<String> flags = new HashSet<>();
    public Set<String> inventory = new HashSet<>();
    public String currentSceneId;

    public PlayerState() {}

    // Factory
    // Stores the state of the Player, can load and save state of a player's journey
    public static PlayerState create(String name, String benderType, String startSceneId) {
        PlayerState p = new PlayerState();
        p.name = name;
        p.benderType = benderType;
        p.currentSceneId = startSceneId;
        return p;
    }

    /**
     * Save this PlayerState to a file using Java serialization.
     * Returns true on success, false on error.
     */
    public boolean save(String path) {
        if (path == null || path.trim().isEmpty()) return false;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Load PlayerState from a file using Java serialization.
     * Returns the loaded PlayerState or null on failure.
     */
    public static PlayerState load(String path) {
        if (path == null || path.trim().isEmpty()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object o = ois.readObject();
            if (o instanceof PlayerState) return (PlayerState) o;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return null;
    }
}
