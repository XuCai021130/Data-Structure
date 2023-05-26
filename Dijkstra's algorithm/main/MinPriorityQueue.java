package main;

/**
* This min priority queue class will be used to create a heap like data structure to store values of node 
* according to its priority
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
 * min priority queue class, containing methods such as insert, delete root, rebalance
 */
public class MinPriorityQueue {
	public GraphNode [] heap;
	public HashMap hashMap;
	public int size;
	public int tail;
	
	/**
	 * construct a empty min priority queue
	 * running time: O(n)
	 */
	public MinPriorityQueue() {
		heap = new GraphNode[600];
		hashMap = new HashMap();
		tail = -1;
		size = 0;
	}
	
	
	/**
	 * 
	 * @param g
	 * insert the given node to the queue and also update into the hash map,
	 * rebalance if necessary
	 * running time: O(log n)
	 */
	public void insert(GraphNode g) {
		if (size == 0) {
			heap[tail + 1] = g;
			hashMap.set(g, tail + 1);
			size ++;
			tail ++;
		}
		else {
			heap[tail + 1] = g;
			hashMap.set(g, tail + 1);
			rebalance(g);
			size++;
			tail++;
			resize();
		}
	}
	
	/**
	 * 
	 * @return
	 * return and remove the root of the queue, which is the node with smallest priority, and
	 * update into hash map correspondingly
	 * running time: O(log n)
	 */
	public GraphNode pullHighestPriorityElement() {
		if (size == 0) {
			return null;
		}
		else if (size == 1){
			size --;
			GraphNode temp = heap[0];
			heap[0] = null;
			tail --;
			hashMap.set(temp, -1);
			return temp;
		}
		else {
			GraphNode temp = heap[0];
			heap[0] = heap[tail];
			heap[tail] = null;
			hashMap.set(temp, -1); // set the root node to -1 to indicate deletion
			hashMap.set(heap[0], 0);
			size --;
			tail --;
			rebalance(heap[0]);
			return temp;
		}
	}
	
	/**
	 * 
	 * @param g
	 * rebalance the queue after any addition, deletion, or changing priority operation
	 * running time: O(log n)
	 */
	public void rebalance(GraphNode g) {
		int index = hashMap.getValue(g);
			heapifyUp(index);
			heapifyDown(index);
	}
	
	/**
	 * 
	 * @return
	 * return a boolean value of whether the queue is empty
	 * running time: O(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * resize the queue if the array is full
	 * running time: O(n)
	 */
	public void resize() {
		if (size == heap.length) {
			GraphNode [] newHeap = new GraphNode[heap.length * 2];
			for (int i = 0; i < size; i++) {
				newHeap[i] = heap[i];
			}
			heap = newHeap;
		}
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 * return the parent index of given index
	 * running time: O(1)
	 */
	public int parent (int i) {
		return  (i - 1) / 2;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 * return the left child index of given index
	 * running time: O(1)
	 */
	public int left (int i) {
		return i * 2 + 1;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 * return the right child index of the given index
	 * running time: O(1)
	 */
	public int right (int i) {
		return (i + 1) * 2 ;
	}
	
	/**
	 * 
	 * @param i
	 * heapify up if necessary, change the hash map accordingly
	 * running time: O(log n)
	 */
	public void heapifyUp(int i) {
		while (i > 0 && heap[i].priority < heap[parent(i)].priority) {
			hashMap.set(heap[parent(i)], i);
			hashMap.set(heap[i], parent(i));
			swap(i, parent(i));
			i = parent(i);
		}
	}
	
	/**
	 * 
	 * @param i
	 * heapify down if necessary, change the hash map accordingly
	 * running time: O(log n)
	 */
	public void heapifyDown(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = -1;
		if (l < size && heap[l].priority < heap[i].priority) {
			smallest = l;
		}
		else {
			smallest = i;
		}
		if (r < size && heap[r].priority < heap[smallest].priority) {
			smallest = r;
		}
		if (smallest != i) {
			hashMap.set(heap[i], smallest);
			hashMap.set(heap[smallest], i);
			swap(i, smallest);
			heapifyDown(smallest);
		}
	}
	
	/**
	 * 
	 * @param i1
	 * @param i2
	 * swap the element stored in two index
	 * running time: O(1)
	 */
	public void swap(int i1, int i2) {
		GraphNode temp = heap[i1];
		heap[i1] = heap[i2];
		heap[i2] = temp;
	}
	
	/**
	 * return a string representation of the queue
	 * running time: O(n)
	 */
	public String toString() {
		String output = "[";
		
		for (int i = 0; i < size; i++) {
			output += heap[i] + ", ";
		}
		
		output += "]";
		return output;
	}
	
	

}
