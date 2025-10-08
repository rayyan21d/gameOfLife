package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary search tree keyed by integer identifier.
 * FIXED: Improved collision handling to maintain BST structure and use a collision list.
 */
public class AlphabeticList {
    public Node root = null;

    public AlphabeticList() {}

    public AlphabeticList(Node root) { this.root = root; }

    /**
     * Insert node by identifier. If identifier exists, store in collision list.
     * FIXED: Now maintains BST structure properly and uses a collision list within nodes.
     */
    public void insert(Node node) {
        if (node == null || node.getNodeElement() == null) return;
        if (root == null) {
            root = node;
            return;
        }
        Node cur = root;
        while (true) {
            if (node.getID() < cur.getID()) {
                if (cur.left == null) {
                    cur.left = node;
                    return;
                }
                cur = cur.left;
            } else if (node.getID() > cur.getID()) {
                if (cur.right == null) {
                    cur.right = node;
                    return;
                }
                cur = cur.right;
            } else {
                // FIXED: identifier collision - add to collision list in current node
                cur.addCollision(node.getNodeElement());
                return;
            }
        }
    }

    /**
     * Lookup by identifier (returns first matching Element with same id).
     */
    public Element findById(int id) {
        Node cur = root;
        while (cur != null) {
            if (id == cur.getID()) return cur.getNodeElement();
            if (id < cur.getID()) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    /**
     * Find by id and exact name match (handles collisions properly).
     */
    public Element findByIdAndName(int id, String name) {
        if (name == null) return null;
        Node cur = root;
        String nameLower = name.trim().toLowerCase();

        while (cur != null) {
            if (id == cur.getID()) {
                // Check primary element
                Element e = cur.getNodeElement();
                if (e != null && e.getName() != null &&
                        e.getName().trim().toLowerCase().equals(nameLower)) {
                    return e;
                }
                // Check collision list
                if (cur.collisions != null) {
                    for (Element colElem : cur.collisions) {
                        if (colElem != null && colElem.getName() != null &&
                                colElem.getName().trim().toLowerCase().equals(nameLower)) {
                            return colElem;
                        }
                    }
                }
                return null; // ID found but name doesn't match
            }
            if (id < cur.getID()) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    /**
     * Best-effort lookup by exact name. Traverses whole tree to find matching Element.name.
     */
    public Element findByName(String name) {
        if (name == null) return null;
        return findByNameRecursive(root, name.trim().toLowerCase());
    }

    private Element findByNameRecursive(Node node, String nameLower) {
        if (node == null) return null;

        // Check primary element
        Element e = node.getNodeElement();
        if (e != null && e.getName() != null &&
                e.getName().trim().toLowerCase().equals(nameLower)) {
            return e;
        }

        // FIXED: Check collision list
        if (node.collisions != null) {
            for (Element colElem : node.collisions) {
                if (colElem != null && colElem.getName() != null &&
                        colElem.getName().trim().toLowerCase().equals(nameLower)) {
                    return colElem;
                }
            }
        }

        // Search subtrees
        Element left = findByNameRecursive(node.left, nameLower);
        if (left != null) return left;

        Element right = findByNameRecursive(node.right, nameLower);
        if (right != null) return right;

        return null;
    }

    /**
     * Returns all Element names for debugging / diagnostics.
     */
    public List<String> allNames() {
        List<String> out = new ArrayList<>();
        collectNames(root, out);
        return out;
    }

    private void collectNames(Node node, List<String> out) {
        if (node == null) return;

        // Collect primary element
        Element e = node.getNodeElement();
        if (e != null && e.getName() != null) out.add(e.getName());

        // FIXED: Collect from collision list
        if (node.collisions != null) {
            for (Element colElem : node.collisions) {
                if (colElem != null && colElem.getName() != null) {
                    out.add(colElem.getName());
                }
            }
        }

        collectNames(node.left, out);
        collectNames(node.right, out);
    }
}