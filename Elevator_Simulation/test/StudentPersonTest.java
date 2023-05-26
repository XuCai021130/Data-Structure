package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;

class StudentPersonTest {

	Building east = new Building(7);
	Person a = new Person("Alice", "Wang");
	Person b = new Person("Alice", "Wang");
	Person c = new Person("Alice", "Li");
	Person d = new Person("Alice", "Wu");
	
	@Test
	void test1() {
		a.enterBuilding(east, 3);
		b.enterBuilding(east, 3);
		c.enterBuilding(east, 0);
		d.enterBuilding(east, 8);
		assertEquals(a.getLocation(), "Waiting to be serviced");
		assertEquals(b.getLocation(), "Waiting to be serviced");
		east.startElevator();
		
		assertEquals(a.getLocation(), "In Floor 3");
		assertEquals(b.getLocation(), "In Floor 3");
		assertEquals(c.getLocation(), "In Lobby");
		assertEquals(d.getLocation(), "In Lobby");
	}
	
	@Test
	void test2() {
		assertEquals(a.toString(), "Alice Wang");
		assertEquals(d.toString(), "Alice Wu");
	}

}
