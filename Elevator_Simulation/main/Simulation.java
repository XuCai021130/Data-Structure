package main;

/**
 * Description: In this file,  I construct several person objects and a building object to test
 * some edge cases.
 * Known Bugs:None
 * 
 * @author Xu (Charles) Cai
 * xucai@brandeis.edu
 * 9, 6, 2022
 * COSI 21A PA0
 */
public class Simulation {

	public static void main(String[] args) {
		Building skyLine = new Building(10);
		Person a = new Person("Xu", "Cai");
		Person b = new Person("Junjiao", "Sun");
		Person c = new Person("Hanfu", "Zhao");
		Person d = new Person("Huining", "Liu");
		Person e = new Person ("Shentong", "Rao");
		Person f = new Person("Qiuyang", "Wang");
		Person g = new Person("Jihai", "Wang");
		Person h = new Person("Yukun", "Zhang");
		Person i = new Person("Yanhao", "Chen");
		Person j = new Person("Xiaoyang", "Zhang");
		
		a.enterBuilding(skyLine, 3); 
		b.enterBuilding(skyLine, 3);
		c.enterBuilding(skyLine, 3);
		Elevator aa  = skyLine.getElevator();
		Job [] aaa = aa.getJobs();
		System.out.println(aaa[0]);

		skyLine.startElevator();
		System.out.println();
		d.enterBuilding(skyLine, 7);
		skyLine.startElevator();
		System.out.println();
		e.enterBuilding(skyLine, 2);
		f.enterBuilding(skyLine, 1);
		skyLine.startElevator();
		System.out.println();
		j.enterBuilding(skyLine, -1);
		System.out.println(j.getLocation());
		System.out.println(skyLine.toString());
		Elevator ss = skyLine.getElevator();

	}
	

}
