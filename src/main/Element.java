package main;

public class Element {
	
	/**
	 * The base attribute for the Element
	 * class is a string that stores the element's name.
	 * The integer "identifier" should be derived from the
	 * element's string data using the "AlphabeticSort"
	 * class in some way.
	 */
	String elementName;
	int identifier;
	
	public Element(String name) {
		this.elementName = name;
	}
	
	public String getName() {
		return this.elementName;
	}
	
	/**
	 * This setter stores a desired numerical value
	 * based on the element's name.
	 * @param sortMethod The sortMethod integer ought
	 * to be created using the AlphabeticSort's Sort()
	 * method.
	 */
	public void setIdentifier(int sortMethod) {
		this.identifier = sortMethod;
	}
	public int getIdentifier() {
		return this.identifier;
	}

}
