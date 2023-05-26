package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Entry;
import main.GraphNode;

class EntryTest {

	GraphNode GN1 = new GraphNode("abc", false);
	GraphNode GN2 = new GraphNode("abcd", false);
	Entry a = new Entry(GN1, 0);
	Entry b = new Entry(GN2, 4);
	@Test
	void test() {
		assertEquals(a.getGraphNode().getId(), "abc");
		assertEquals(b.getGraphNode().getId(), "abcd");
		
		assertEquals(a.getValue(), 0);
		assertEquals(b.getValue(), 4);
	}

}
