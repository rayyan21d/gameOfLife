package main;

/**
 * Binary search tree node wrapper for Element.
 * Exposes left/right for tree navigation (used by AlphabeticList).
 */
public class Node {
    public Element e;
    public int identifier;
    public Node left = null;
    public Node right = null;

    public Node() {}

    public Node(Element e) {
        this.e = e;
        if (e != null) this.identifier = e.getIdentifier();
    }

    public Element getNodeElement() { return e; }
    public int getID() { return identifier; }
    public void setElement(Element e) {
        this.e = e;
        if (e != null) this.identifier = e.getIdentifier();
    }
}
