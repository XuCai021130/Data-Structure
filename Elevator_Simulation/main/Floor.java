package main;

/**
 * Description: The floor class includes an array of person that stays in this floor and a bollean value 
 * recording whether this floor is empty
 * Known Bugs:None
 * 
 * @author Xu (Charles) Cai
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Floor {
	Person [] currentPersons;
	int numOfPerson;
	boolean emptyFloor;
	
	/**
	 * This constructor creates a floor object with an array of person and a boolean value of 
	 * whether the floor is empty
	 */
	public Floor() {
		currentPersons = new Person[100];
		numOfPerson = 0;
		emptyFloor = true;
	}
	
	/**
	 * put the person into the corresponding floor
	 * @param person
	 */
	public void enterFloor(Person person) {
		currentPersons[numOfPerson] = person; // put the person in to the floor
		numOfPerson++;
		emptyFloor = false;    
	}
	
	/**
	 * return the emptyFloor field
	 * @return
	 */
	public boolean whetherEmpty() {
		return emptyFloor;
	}
	
	/**
	 * This method will return persons who stay in this floor. If no one in this floor,
	 * it will tell you no one in this floor
	 */
	public String toString() {
		int i = 0;
		String printStatement = "";
		if (emptyFloor) {
			return "No one stays in this floor";
		}
		else {
			while (currentPersons[i] != null) {
				printStatement += (currentPersons[i] + " ");
				i++;
			}
			if (i == 1) {
				return printStatement + "stays in this floor";
			}
			else {
				return printStatement + "stay in this floor";
			}
			
		}
		
	}
}
