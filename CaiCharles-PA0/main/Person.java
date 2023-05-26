package main;

/**
 * Description:  This Person class construct a person object with name, current status, and the building 
 * this person wishes to get in. 
 * Known Bugs: None
 * 
 * @author Xu (Charles) Cai
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Person {
	String name;
	int status;
	Building buildingYouAreIn; 
	
	/**
	 * Person's constructor will create a person with name and current status
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		name = firstName + " " + lastName;
		status = -1;
	}
	
	/**
	 * This method will pass the building that the person wishes to get in to person's field 
	 * and can be used to test whether the entered floor is valid.
	 * @param building
	 * @param floor
	 * @return
	 */
	public boolean enterBuilding(Building building, int floor) {
		buildingYouAreIn = building;
		return buildingYouAreIn.enterElevatorRequest(this, floor);
	}
	
	/**
	 * return person's current status. "Waiting to be serviced" means this person's request is valid but
	 * the job  is not finished yet. "In Lobby" means person's request is invalid and person will
	 * stays in lobby forever.
	 * @return
	 */
	public String getLocation() {
		if (this.status == 0) { // when the job request is valid, status will increment by 1 
			return "Waiting to be serviced";
		}
		else if (this.status == -1) { // this is the initial status
			return "In Lobby";
		}
		else {
			return "In Floor " + this.status;
		}
	}
	
	/**
	 * return person last and first name
	 */
	public String toString() {
		return name;
	}
}
