package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Stack;

/**
* This hash map will be used to store entries that is in the min priority queue
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 12, 13, 2022
* COSI 21A PA3
*/

public class FindMinPath {

	/**
	 * use Dijkstra¡¯s algorithm to find the minimum path from home to destination
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MinPriorityQueue Q = new MinPriorityQueue();
		GraphWrapper gw = new GraphWrapper();
		GraphNode home = gw.getHome();
		home.priority = 0;
		Q.insert(home);
		boolean findGoal = false;
		GraphNode goalNode = null;
		PrintStream output = new PrintStream(new File ("answer.txt"));
		
		while (!Q.isEmpty() && !findGoal) {
			GraphNode curr = Q.pullHighestPriorityElement();
			if (curr.isGoalNode()) {
				findGoal = true;
				goalNode = curr;
			}
			else {
				if (curr.hasEast()) {
					GraphNode neighbor = curr.getEast();
					int x = curr.priority + curr.getEastWeight();
					if (!Q.hashMap.hasKey(neighbor)) {
						neighbor.priority = x;
						neighbor.previousNode = curr;
						neighbor.previousDirection = "East";
						Q.insert(neighbor);
					}
					else if (Q.hashMap.hasKey(neighbor) && x < neighbor.priority){
						neighbor.priority = x;
						Q.rebalance(neighbor);
						neighbor.previousNode = curr;
						neighbor.previousDirection = "East";
					}
					
				}
				if (curr.hasWest()) {
					GraphNode neighbor = curr.getWest();
					int x = curr.priority + curr.getWestWeight();
					if (!Q.hashMap.hasKey(neighbor)) {
						neighbor.priority = x;
						neighbor.previousNode = curr;
						neighbor.previousDirection = "West";
						Q.insert(neighbor);
					}
					else if (Q.hashMap.hasKey(neighbor) && x < neighbor.priority){
						neighbor.priority = x;
						Q.rebalance(neighbor);
						neighbor.previousNode = curr;
						neighbor.previousDirection = "West";
					}
				}
				if (curr.hasNorth()) {
					GraphNode neighbor = curr.getNorth();
					int x = curr.priority + curr.getNorthWeight();
					if (!Q.hashMap.hasKey(neighbor)) {
						neighbor.priority = x;
						neighbor.previousNode = curr;
						neighbor.previousDirection = "North";
						Q.insert(neighbor);
					}
					else if (Q.hashMap.hasKey(neighbor) && x < neighbor.priority){
						neighbor.priority = x;
						Q.rebalance(neighbor);
						neighbor.previousNode = curr;
						neighbor.previousDirection = "North";
					}
				}
				if (curr.hasSouth()) {
					GraphNode neighbor = curr.getSouth();
					int x = curr.priority + curr.getSouthWeight();
					if (!Q.hashMap.hasKey(neighbor)) {
						neighbor.priority = x;
						neighbor.previousNode = curr;
						neighbor.previousDirection = "South";
						Q.insert(neighbor);
					}
					else if (Q.hashMap.hasKey(neighbor) && x < neighbor.priority){
						neighbor.priority = x;
						Q.rebalance(neighbor);
						neighbor.previousNode = curr;
						neighbor.previousDirection = "South";
					}
				}
			}
		}	
		if (goalNode == null) {
			output.println("There is no path from start to the goal");
		}
		else {
			
			GraphNode curr = goalNode;
			String output1 = "";
			while(curr != null && !curr.getId().equals(home.getId())) {
				output1 = curr.previousDirection + "\n" + output1;
					curr = curr.previousNode;
			}
			output.println(output1);
			
		}

	}
	
	public static void printFile(GraphNode home, PrintStream output, GraphNode goal) {
		System.out.println(goal.previousDirection);
		if (goal != null && goal.previousNode != null && !goal.previousNode.getId().equals(home.getId())) {
			printFile(home, output, goal.previousNode);
		}
		output.println(goal.previousDirection);
		
	}

}
