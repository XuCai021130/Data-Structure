/*
package test;
 import static org.junit.Assert.assertEquals;



import main.AVLPlayerNode;
import main.Player;

class AVLPlayerNodeTest {
	
	Player a = new Player("Davis", 1, 100);
	Player b = new Player("Tatum", 2, 200);
	Player c = new Player("Morant", 3, 300);
	Player d = new Player("James", 4, 400);
	Player e = new Player("Leonard", 5, 500);
	Player f = new Player("Green", 6, 600);
	Player g = new Player("Curry", 7, 700);

	
	AVLPlayerNode root1 = new AVLPlayerNode(g, 700);
	AVLPlayerNode root2 = new AVLPlayerNode(a, 100);
	AVLPlayerNode n1 = new AVLPlayerNode(b, 200);
	AVLPlayerNode n2 = new AVLPlayerNode(c, 300);
	AVLPlayerNode n3 = new AVLPlayerNode(d, 400);
	AVLPlayerNode n4 = new AVLPlayerNode(e, 500);
	AVLPlayerNode n5 = new AVLPlayerNode(f, 600);
	

	@Test
	void testInsert() { // test insert and delete method and also test some minor methods 
		                // including treeString, getRank, getRightWeight, getBalanceFactor
		AVLPlayerNode r1 = root1.insert(a, 100);
		AVLPlayerNode r2 = r1.insert(b, 200); 
		assertEquals(r2.treeString(), "((Davis)Tatum(Curry))"); // CHECK if tree string is correct
		
		
		AVLPlayerNode r3 = r2.insert(c, 300);
		assertEquals(r3.treeString(), "((Davis)Tatum((Morant)Curry))");
		assertEquals(r3.getRank(200), 3);  // get rank and right weight to see if it is correct
		assertEquals(r3.getRightWeight(), 2);
		
		AVLPlayerNode r4 = r3.insert(d, 400);
		assertEquals(r4.getRightWeight(), 3);
		assertEquals(r4.treeString(), "((Davis)Tatum((Morant)James(Curry)))");
		assertEquals(r4.getRank(200), 4);
		
		AVLPlayerNode r5 = r4.insert(e, 500);
		assertEquals(r5.treeString(), "(((Davis)Tatum(Morant))James((Leonard)Curry))");
		assertEquals(r5.getRightWeight(), 2);
		assertEquals(r5.getBalanceFactor(), 0);
		
		AVLPlayerNode r6 = r5.insert(f, 600);
		
		assertEquals(r6.treeString(), "(((Davis)Tatum(Morant))James((Leonard)Green(Curry)))");
		assertEquals(r6.getBalanceFactor(), 0);
		assertEquals(r6.getRightWeight(), 3);
		
		AVLPlayerNode r7 = r6.delete(400); // do some deletion
		
		assertEquals(r7.getBalanceFactor(), 0);
		assertEquals(r7.getRank(300), 4);
		assertEquals(r7.getRightWeight(), 2);
		assertEquals(r7.searchNode(700).getRightWeight(), 0);
		assertEquals(r7.searchNode(600).getRightWeight(), 1); // test its rightweight and also rank and balance factor
		assertEquals(r7.treeString(), "(((Davis)Tatum(Morant))Leonard(Green(Curry)))");
		
		
		AVLPlayerNode r8 = r6.delete(500);
		assertEquals(r8.treeString(), "(((Davis)Tatum(Morant))Green(Curry))");
		assertEquals(r8.getBalanceFactor(), 1);


		
		
	
		
	}
	

}
*/

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class AVLUnitTesting {
 Player a = new Player("Russell", 0, 500);
 Player b = new Player("Hamilton", 1, 1000);
 Player c = new Player("Verstappen", 2, 1500);
 Player d = new Player("Perez", 3, 2000);
 Player e = new Player("Leclerc", 4, 2500);
 Player f = new Player("Sainz", 5, 3000);
 Player g = new Player("Zhou", 6, 3500);
 Player h = new Player("Bottas", 7, 4000);
 
 AVLPlayerNode root1 = new AVLPlayerNode(d, 2000);
 AVLPlayerNode rootA = new AVLPlayerNode(h, 4000);
 AVLPlayerNode l1 = new AVLPlayerNode(a, 500);
 AVLPlayerNode l2 = new AVLPlayerNode(b, 1000);
 AVLPlayerNode l3 = new AVLPlayerNode(c, 1500);
 AVLPlayerNode r1 = new AVLPlayerNode(e, 2000);
 AVLPlayerNode r2 = new AVLPlayerNode(f, 2500);
 AVLPlayerNode r3 = new AVLPlayerNode(g, 3000);
 
 //mainly test insert, also have getRank and getPlayer in the test just for it to cover more general cases
 @Test
 void testInsert() {
  AVLPlayerNode root1 = rootA.insert(a, 500);
  assertTrue(root1.treeString().equals("((Russell)Bottas)"));
  AVLPlayerNode root2 = root1.insert(b, 1000);
  
  assertEquals(root2.treeString(),"((Russell)Hamilton(Bottas))");
  AVLPlayerNode root3 = root2.insert(c, 1500);
  assertTrue(root3.treeString().equals("((Russell)Hamilton((Verstappen)Bottas))"));
  assertTrue(root3.getRightWeight() == 2);
  assertTrue(root3.getRank(4000) == 1);
  assertTrue(root3.getPlayer(1000).equals(b));
  assertTrue(root3.getPlayer(6000) == null);
  assertTrue(root3.getRank(1000) == 3);
  AVLPlayerNode root4 = root3.insert(d, 2000);
  assertTrue(root4.treeString().equals("((Russell)Hamilton((Verstappen)Perez(Bottas)))"));
  AVLPlayerNode root5 = root4.insert(e, 2500);
  assertEquals(root5.treeString(), "(((Russell)Hamilton(Verstappen))Perez((Leclerc)Bottas))");
  assertEquals(root5.getBalanceFactor(), 0);
  assertTrue(root5.getRank(1500) == 4);
  assertTrue(root5.getRank(500) == 6);
 }
}
