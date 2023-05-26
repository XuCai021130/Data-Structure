package main;

/**
* This hash map will be used to store entries that is in the min priority queue
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
 * construct a hash map that can resize itself when necessary
 */
public class HashMap {
	public Entry [] hashMap;
	public int size;
	
	/**
	 * construct a HashMap object
	 * running time: O(1)
	 */
	public HashMap() {
		size = 0;
		hashMap = new Entry [1000];
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * update the value of graphnode when exist and add a entry to the hashmap when does not exist
	 * running time: average O(1)
	 */
	public void set(GraphNode key, int value) {
		if(key != null) {
			if (hasKey(key)) {
				hashMap[search(key)].value = value;  // use search method to find the correct index
			}
			else {
				hashMap[search(key)] = new Entry(key, value);
				size++;
				rehash();
			}
		}
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 * return the value of the entry with corresponding graph node g
	 * running time: average O(1)
	 */
	public int getValue(GraphNode g) {
		if (hasKey(g)) {
			return hashMap[search(g)].getValue();
		}
		else {
			return -1;
		}
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 * testify if a entry contains graph node in the hash map
	 * running time: average O(1)
	 */
	public boolean hasKey(GraphNode g) {
		int index = search(g);
		if (g == null) {
			return false;
		}
		else if (index == -1){ // when the item is deleted
			return false;
		}
		else {
			if (hashMap[index] == null) { // if the corect position is null, the element is not in the hash map
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * 
	 * @param g
	 * @param m
	 * @return
	 * find the key of the graph node by the given hash function according to the id of the graph node
	 * m is the size of the hash table
	 * running time: O(1)
	 */
	public int hashCode(GraphNode g, int m) {
		int hashCode = 0;
		String id = g.getId();
		for (int i = 0; i < id.length(); i ++) {
			hashCode += id.charAt(i);
		}
		return hashCode % m;
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 * if the graph node g exists, return the index where the graph node is located
	 * otherwise, return the correct position where the node should be inserted
	 * running time: average O(1)
	 */
	public int search(GraphNode g) {
		int index = hashCode(g, hashMap.length);
		
		for (int i = index; i < hashMap.length; i++) {
			if (hashMap[i] == null) {  // if the element does not exist, return the position where we meet a null element
				return i;
			}
			else {
				if (hashMap[i].getGraphNode().getId() == g.getId()) { // if two items have same id, it proves that we found the node
					if (hashMap[i].getValue() != -1) {  // still need to check whether it is deleted
						return i;
					}
				}
			}
		}
		
		for (int j = 0; j < index; j++) { // make sure to wrap around
			if (hashMap[j] == null) {
				return j;
			}
			else {
				if (hashMap[j].getGraphNode().getId() == g.getId()) {
					if (hashMap[j].getValue() != -1) {
						return j;
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * test if the current hash table need to expand after each addition
	 * running time: O(n)
	 */
	public void rehash() {
		if ((Double.valueOf(size) / Double.valueOf(hashMap.length)) >= 0.6) {
			Entry [] oldHashMap = hashMap;
			Entry [] newHashMap = new Entry[oldHashMap.length * 2];
			hashMap = newHashMap;
			
			size = 0;
			
			for (int i = 0; i < oldHashMap.length; i++) { // traverse the whole hash map to add each item into the new hash map
				if (oldHashMap[i] != null) {
					if (oldHashMap[i].getValue() != -1) {
						set(oldHashMap[i].getGraphNode(), oldHashMap[i].getValue());
					}
				}
			}
		}
		
	}
	
	/**
	 * return a string representation of the hash map
	 * running time: O(n)
	 */
	public String toString() {
		String output = "[";
		
		for (int i = 0; i < hashMap.length - 1; i++) {
			if (hashMap[i] != null) {
				output += hashMap[i] + ", ";
			}
			else {
				output += "null, ";
			}
		}
		if (hashMap[hashMap.length - 1] != null) {
			output += hashMap[hashMap.length - 1];
		}
		else {
			output += "null";
		}
		output += "]";
		return output;
	}
}
