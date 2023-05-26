package main;

/**
 * Description: Elevator will be used to carry people to desired floor. Each movement of elevator will
 * be reported. 
 * Known Bugs: None
 * 
 * @author Xu (Charles) Cai
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3; 
	Job [] jobs;
	int currentLocation;
	int jobNumbers;
	int finishedJobs;
	
	/**
	 * This constructor creates a elevator with corresponding fields
	 */
	public Elevator() {
		this.jobs = new Job [3];
		jobNumbers = 0;
		currentLocation = 0;
		finishedJobs = 0;
	}
	
	/**
	 * Pass the person's job request into elevator's array of jobs; If the length of array is not enough
	 * to accommodate the total number of jobs. It will expand.
	 * @param person
	 * @param floor
	 */
	public void createJob(Person person, int floor) {
		jobNumbers++;
		if (jobNumbers > jobs.length) {
			Job [] newJobs = new Job[jobNumbers]; // create a new empty array with enough size
			for (int i = 0; i < jobs.length; i++) {
				newJobs[i] = jobs[i];       // pass the previous jobs in to the new array
			}
			jobs = newJobs;   // update the old array
		}
		jobs[jobNumbers-1] = new Job (person, floor); // add new job to the job array
		
	}
	
	/**
	 * process all jobs inside the job array
	 */
	public void processAllJobs() {
		int i = 0;
		while(i < jobs.length) {
			if (jobs[i] != null) { 
				processJob(jobs[i]);
			}
			i++;
		}
		jobs = new Job [3]; // After processing all jobs, initialize elevator's fields again
		jobNumbers=0;
		finishedJobs = 0;
	}
	
	/**
	 * process each job and print elevator's location for each movement
	 * @param job
	 */
	public void processJob(Job job) {
		if (currentLocation ==0) {  // when elevator starts to work
			for (int i = 0; i < job.floor; i++) {
				System.out.println(reportLocation());
				currentLocation++;
			}
			System.out.println(reportLocation());
			job.person.status += job.floor;
			exit(job.person, job.floor);  // after  movement, put people into the floor
		}
		else if (job.floor > currentLocation) {  // If the next job floor is larger than current location
			for (int i = currentLocation; i < job.floor; i++) {
				System.out.println(reportLocation());
				currentLocation++;
			}
			System.out.println(reportLocation());
			job.person.status += job.floor;
			exit(job.person, job.floor);
		}
		else {  // if the next job floor is smaller than current location , or the next job floor is same 
			for (int i = currentLocation; i > job.floor; i--) { // floor
				System.out.println(reportLocation());
				currentLocation--;
			}
			System.out.println(reportLocation());
			job.person.status += job.floor;
			exit(job.person, job.floor);
		}
		
		finishedJobs++;
		if (finishedJobs % maxOccupants == 0 || finishedJobs == jobNumbers) { // if processed jobs reached
			while (currentLocation != 0) {                                    // maxOccupants or all job
				System.out.println(reportLocation());                         // finished. return to the lobby
				currentLocation--;
			}
			System.out.println(reportLocation());
		}
				
	}
	
	/**
	 * After elevator carries the person to the desired floor, let him or her 
	 * enter the corresponding floor array of person
	 * @param person
	 * @param floor
	 */
	public void exit(Person person, int floor) {
		person.buildingYouAreIn.floors[floor].enterFloor(person);
	}
	
	/**
	 * return the current location of elevator
	 * @return
	 */
	public String reportLocation() {
		if(this.currentLocation > 0) {
			return "Elevator at floor " + this.currentLocation;
		}
		else {
			return "Elevator at Lobby";
		}
	}
	
	/**
	 * return the array of jobs
	 * @return
	 */
	public Job[] getJobs() {
		return jobs;
	}
	
	/**
	 * return elevator's status
	 */
	public String toString() {
		return "Elevator currently stays in" + currentLocation + "floor";
	}
	
}