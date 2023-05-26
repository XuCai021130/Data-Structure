package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.io.*;

import main.GraphNode;
import main.HashMap;
import org.junit.jupiter.api.Test;

class HashMapTest {

	HashMap a = new HashMap ();
	
	@Test
	void test1() throws FileNotFoundException{ // set the  initial capacity of the hashmap is only 5
		
		GraphNode GN1 = new GraphNode("abcd", false);
		GraphNode GN2 = new GraphNode("bacd", false);
		GraphNode GN3 = new GraphNode("acbd", false);
		GraphNode GN4 = new GraphNode("cbad", false);
		GraphNode GN5 = new GraphNode("cabd", false);
		GraphNode GN6 = new GraphNode("dacb", false);
		GraphNode GN7 = new GraphNode("dbca", false); // create nodes with id that hash to the same value to test collision
		
		a.set(GN1, 0);
		a.set(GN2, 1);
		a.set(GN3, 2);
		a.set(GN4, 3);
		a.set(GN5, 4);  // add them to hash map
		a.set(GN6, 4);
		a.set(GN7, 4);
		
		System.out.println(a);
		assertEquals(a.size, 7);

	}
	
	
	@Test
	void test2() {
		GraphNode GN1 = new GraphNode("abcd", false);
		GraphNode GN2 = new GraphNode("abdc", false);
		GraphNode GN3 = new GraphNode("acbd", false);
		GraphNode GN4 = new GraphNode("acdb", false);
		GraphNode GN5 = new GraphNode("bacd", false);
		GraphNode GN6 = new GraphNode("bcad", false);
		GraphNode GN7 = new GraphNode("bcda", false);
		
		a.set(GN1, 0);
		a.set(GN2, 1);
		a.set(GN3, 2);
		a.set(GN4, 3);
		
		assertEquals(a.size, 4);
		
		System.out.println(a);
		
		a.set(GN5, 4);
		assertEquals(a.size, 5);
		
		System.out.println(a);
		
		a.set(GN6, 5);
		assertEquals(a.size, 6);
		
		System.out.println(a);


		
		
		
		
		
		
	}

}
