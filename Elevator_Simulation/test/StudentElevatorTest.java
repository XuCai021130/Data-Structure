package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;
import main.Job;
import main.Elevator;
import main.Floor;

class StudentElevatorTest {

	Building a = new Building(10);
	Person x = new Person("Andy", "Wang");
	Person y= new Person("Andy", "Zhang");
	
	
	@Test
	void test1() {
		x.enterBuilding(a, 4);
		y.enterBuilding(a, 10);
		Elevator elevatorForBuildingA = a.getElevator();
		Job [] jobs = elevatorForBuildingA.getJobs();
		assertEquals(jobs[0].toString(), "Andy Wang wishes to go to 4 floor");
		assertEquals(jobs[1].toString(), "Andy Zhang wishes to go to 10 floor");
	}
	
	@Test
	void test2() {
	} 

}
