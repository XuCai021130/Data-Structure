package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Floor;
import main.Person;

class StudentBuildingTest {

	Building a = new Building (1);
	Building b = new Building (100);
	Person aa = new Person("Alan", "Wang");
	Person bb = new Person("Alan", "Zhang");
	
	
	@Test
	void test1() {
		
		assertTrue(a.getFloorNumbers() == 1);
		assertTrue(b.getFloorNumbers() == 100);

	}
	
	@Test
	void test2() {
		
		assertTrue(a.enterElevatorRequest(aa, 2) == false);
		assertTrue(b.enterElevatorRequest(bb, 99) == true);
		
	}
	
	@Test
	void test3() {
		
	}
}
