package main;

/**
* This entry class constructs a entry with a graph node and a value
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 12, 13, 2022
* COSI 21A PA3
*/

/**
 * 
 * @author Lenovo
 * Entry class with constructor and set methods
 */
public class Entry {
	public GraphNode g;
	public int value;
	
	/**
	 * 
	 * @param g
	 * @param value
	 * constructor a entry with given node and value
	 * running time: O(1)
	 */
	public Entry(GraphNode g, int value) {
		this.g = g;
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 * get the value stored in the entry
	 * running time: O(1)
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return
	 * get the graph node stored in the entry
	 * running time: O(1)
	 */
	public GraphNode getGraphNode() {
		return g;
	}
	
	/**
	 * return a string representation of entry
	 * running time: O(1)
	 */
	public String toString() {
		return g + " & " + "i = " + value;
	}


}
