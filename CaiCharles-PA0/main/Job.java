package main;

/**
 * Description: Job class is used for people''s request. Each people's valid request will be 
 * translated to a job and store in elevator
 * Known Bugs:None
 * 
 * @author Xu (Charles) Can
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Job {
	Person person;
	int floor;
	
	/**
	 * This constructor will create a job object with information about person and desired floor
	 * @param person
	 * @param floor
	 */
	public Job (Person person, int floor) {
		this.person = person;
		this.floor = floor;
	}
	
	/**
	 * Return the floor number of this job
	 * @return
	 */
	public int getFloor() {
		return floor;
	}
	
	/**
	 * Return job requested person's name 
	 * @return
	 */
	public Person getPerson() {
		return person;
	}
	
	/**
	 * Return the information of this job.
	 * Output template: "Person wishes to go to x floor"
	 */
	public String toString() {
		return this.person + " wishes to go to " + this.floor + " floor";
	}
}