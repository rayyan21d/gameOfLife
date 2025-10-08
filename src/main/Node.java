package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary search tree node wrapper for Element.
 * Added collision list to handle hash collisions properly.
 */
public class Node {
    public Element e;
    public int identifier;
    public Node left = null;
    public Node right = null;

    // Added collision list for elements with same hash
    public List<Element> collisions = null;

    public Node() {}

    public Node(Element e) {
        this.e = e;
        if (e != null) this.identifier = e.getIdentifier();
    }

    public Element getNodeElement() {
        return e;
    }

    public int getID() {
        return identifier;
    }

    public void setElement(Element e) {
        // FIXED: Added validation
        if (e == null) {
            this.e = null;
            this.identifier = 0;
            return;
        }
        this.e = e;
        this.identifier = e.getIdentifier();
    }

    /**
     * New method to add collision elements
     */
    public void addCollision(Element elem) {
        if (elem == null) return;

        // Initialize collision list if needed
        if (collisions == null) {
            collisions = new ArrayList<>();
        }

        // Check for duplicate names before adding
        String newName = elem.getName();
        if (newName != null) {
            String newNameLower = newName.trim().toLowerCase();

            // Check if already exists in collisions
            for (Element existing : collisions) {
                if (existing != null && existing.getName() != null &&
                        existing.getName().trim().toLowerCase().equals(newNameLower)) {
                    return; // Already exists, don't add duplicate
                }
            }

            // Check if matches primary element
            if (e != null && e.getName() != null &&
                    e.getName().trim().toLowerCase().equals(newNameLower)) {
                return; // Already exists as primary, don't add duplicate
            }
        }

        collisions.add(elem);
    }

    /**
     * Method to get all elements
     */
    public List<Element> getAllElements() {
        List<Element> all = new ArrayList<>();
        if (e != null) {
            all.add(e);
        }
        if (collisions != null) {
            all.addAll(collisions);
        }
        return all;
    }
}