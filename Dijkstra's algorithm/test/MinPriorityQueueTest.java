package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GraphNode;
import main.MinPriorityQueue;
import main.HashMap;

class MinPriorityQueueTest {

	MinPriorityQueue a = new MinPriorityQueue();
	GraphNode GN1 = new GraphNode("G1", false);
	GraphNode GN2 = new GraphNode("G2", false);
	GraphNode GN3 = new GraphNode("G3", false);
	GraphNode GN4 = new GraphNode("G4", false);
	GraphNode GN5 = new GraphNode("G5", false);
	GraphNode GN6 = new GraphNode("G6", false);
	GraphNode GN7 = new GraphNode("G7", false);
	
	
	@Test
	void test1() {
		GN1.priority = 100;
		GN2.priority =90;
		GN3.priority =80;
		GN4.priority = 70;
		GN5.priority = 60;
		GN6.priority = 40;
		GN7.priority = 1;
		GraphNode GN8 = new GraphNode("G8", false);
		GN8.priority = 10;
		
		
		assertEquals(a.size, 0);
		assertEquals(a.tail, -1);

		
		a.insert(GN1);
		System.out.println(a);
		assertEquals(a.size, 1);
		assertEquals(a.tail, 0);
		
		
		a.insert(GN2);
		System.out.println(a);
		assertEquals(a.size, 2);
		assertEquals(a.tail, 1);
		
		a.insert(GN3);
		System.out.println(a);

		
		a.insert(GN4);
		System.out.println(a);
		
		a.insert(GN5);
		System.out.println(a);
		
		a.insert(GN6);
		System.out.println(a);
		
		a.insert(GN7);
		System.out.println(a);
		assertEquals(a.hashMap.getValue(GN7), 0);
		assertEquals(a.hashMap.getValue(GN7), 0);
		
		a.insert(GN8);
		System.out.println(a);
		System.out.println(a.hashMap);
		
		GN6.priority = 99;
		a.rebalance(GN6);
		System.out.println(a);
		System.out.println(a.hashMap);

		
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);

		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		System.out.println(a.hashMap);
		
		a.pullHighestPriorityElement();
		System.out.println(a);
		

		
	}
	
	@Test
	void test2() {
		GN1.priority = 1;
		GN2.priority = 2;
		a.insert(GN1);
		a.insert(GN2);
		System.out.println(a);
		System.out.println(a.pullHighestPriorityElement());
		System.out.println(a);
		System.out.println(a.pullHighestPriorityElement());
		System.out.println(a);
		assertEquals(a.size, 0);
		


		
	}

}
