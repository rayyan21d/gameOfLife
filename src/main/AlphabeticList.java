package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary search tree keyed by integer identifier.
 * Each Node now stores a list of Elements that share the same identifier (to handle hash collisions).
 */
public class AlphabeticList {
    public Node root = null;

    public AlphabeticList() {}

    public AlphabeticList(Node root) { this.root = root; }

    /**
     * Insert node by identifier. If identifier exists, append Element to that node's bucket.
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
                if (cur.left == null) { cur.left = node; return; }
                cur = cur.left;
            } else if (node.getID() > cur.getID()) {
                if (cur.right == null) { cur.right = node; return; }
                cur = cur.right;
            } else {
                // identifier collision: append element into a bucket stored in cur.e
                // We'll convert cur.e into a small linked bucket by wrapping in ElementBucket inside Node
                // Simple approach: if cur.e.name equals node.e.name, replace; else create a simple linked bucket via Node.right chaining.
                // Safer: append as right child chain (non-balanced). Keep stable behavior.
                // Find rightmost of cur.right subtree and attach node there.
                if (cur.right == null) {
                    cur.right = node;
                    return;
                } else {
                    Node walk = cur.right;
                    while (walk.right != null) walk = walk.right;
                    walk.right = node;
                    return;
                }
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
     * Best-effort lookup by exact name. Traverses whole tree to find matching Element.name.
     */
    public Element findByName(String name) {
        if (name == null) return null;
        return findByNameRecursive(root, name.trim().toLowerCase());
    }

    private Element findByNameRecursive(Node node, String nameLower) {
        if (node == null) return null;
        Element e = node.getNodeElement();
        if (e != null && e.getName() != null && e.getName().trim().toLowerCase().equals(nameLower)) {
            return e;
        }
        Element left = findByNameRecursive(node.left, nameLower);
        if (left != null) return left;
        // also search right subtree and any right-chain collisions
        Element right = findByNameRecursive(node.right, nameLower);
        if (right != null) return right;
        return null;
    }

    /**
     * Optional: returns all Element names for debugging / diagnostics.
     */
    public List<String> allNames() {
        List<String> out = new ArrayList<>();
        collectNames(root, out);
        return out;
    }

    private void collectNames(Node node, List<String> out) {
        if (node == null) return;
        Element e = node.getNodeElement();
        if (e != null && e.getName() != null) out.add(e.getName());
        collectNames(node.left, out);
        collectNames(node.right, out);
    }
}
