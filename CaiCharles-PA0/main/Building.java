package main;

/**
 * Description: This class constructs a building with given number of floors and there is a elevator
 * inside the building.
 * Known Bugs: None
 * 
 * @author Xu (Charles) Cai
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Building {
	Floor [] floors;
	Elevator elevator;

    /**
     * This constructor constructs a building object with numFloors of floors and an array of floors 
     * instances. It also includes a elevator that belongs to this building.
     * @param numFloors
     */
	public Building(int numFloors) {
		floors = new Floor[numFloors + 1];
		for (int i = 0; i < floors.length; i++) { // Use for loop to construct floor object in each floor
			floors[i] = new Floor();
		}
		elevator = new Elevator();
	}
	
	/**
	 * This method test whether the floor that the person wishes to go to can be reached.
	 * If the request is approved, person's status changes from "In Lobby" to "Waiting to be serviced"
	 * @param person
	 * @param floor
	 * @return
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor <= floors.length - 1 && floor > 0) { // The floor that the person wishes to go to must
			this.elevator.createJob(person, floor);    // be small or equal to building's floor number 
			person.status++;                           // and larger than zero
			return true; // If person's job is valid, he or her's status changes to "Waiting to be serviced"
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Once the method is called, the elevator will process all the jobs 
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
	 * Make sure the person successfully entered the desired floor
	 * @param person
	 * @param floor
	 */
	public void enterFloor(Person person, int floor) {
		floors[floor].enterFloor(person);
	}
	
	/**
	 * Return the number of floors of this building
	 * @return
	 */
	public int getFloorNumbers() {
		return floors.length - 1;
	}
	
	/**
	 * Return the elevator object
	 * @return
	 */
	public Elevator getElevator() {
		return elevator;
	}
	
	/**
	 * Return the floors array
	 * @return
	 */
	public Floor[] getFloors() {
		return floors;
	}
	
	/**
	 * This method tells you how many floors this building has.
	 * Output template: "This building has x floor"
	 */
	public String toString() {
		if (floors.length - 1 == 1) {
			return "This building has " + (floors.length -1) + " floor";
		}
		else {
			return "This building has " + (floors.length -1) + " floors";
		}
	}
}
