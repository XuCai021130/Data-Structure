package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Elevator;
import main.Job;
import main.Person;

class StudentJobTest {
	Building a = new Building(10);
	Person x = new Person("Andy", "Wang");
	Person y= new Person("Andy", "Zhang");
	Elevator aa  = a.getElevator();
	Job [] jobs = aa.getJobs();
	
	@Test
	void test() {
		x.enterBuilding(a, 2);
		y.enterBuilding(a, 8);
		assertEquals(jobs[0].getFloor(), 2);
		assertEquals(jobs[1].getFloor(), 8);
		assertEquals(jobs[0].getPerson().toString(), "Andy Wang");
		assertEquals(jobs[1].getPerson().toString(), "Andy Zhang");
		
	}

}
