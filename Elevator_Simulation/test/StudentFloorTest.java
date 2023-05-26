package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Floor;
import main.Person;

class StudentFloorTest {
	Building a = new Building(10);
	Person b = new Person ("Charles", "Cai");
	Floor[] floors = a.getFloors();
	
	@Test
	void test1() {
		assertTrue(floors[3].whetherEmpty() == true);
		assertEquals(floors[3].toString(), "No one stays in this floor");
		a.enterFloor(b, 3);
		assertTrue(floors[3].whetherEmpty() == false);
		assertEquals(floors[3].toString(), "Charles Cai stays in this floor");
	}

}
