/**
* AVLPlayerNode class. Defining how to delete and insert a node and maintain balance factor
* Known Bugs: None
*
* @author Charles Cai 
* xucai@brandeis.edu 
* <11, 15, 2022> 
* COSI 21A PA2
*/
package main;
/**
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 */

public class AVLPlayerNode{
    private Player data;
    private double value;
    private AVLPlayerNode parent;
    private AVLPlayerNode leftChild;
    private AVLPlayerNode rightChild;
    private int rightWeight;
    public int height;
    private int balanceFactor;
    
    public AVLPlayerNode(Player data,double value){
        this.data = data;
        this.value = value;
    }
    
    /**
     * insert a node containing the information of player and its id or elo score
     * according to tree and maintain AVL condition
     * running time: O(log n)
     * @param newGuy
     * @param value
     * @return
     */
    public AVLPlayerNode insert(Player newGuy,double value){
    	AVLPlayerNode z = new AVLPlayerNode(newGuy, value); // create a node with given information
    	AVLPlayerNode v = null;
    	AVLPlayerNode w = findRoot(this); // root node
    	while (w != null) { // iterate to find the place to add node
    		v = w;
    		if (value < w.value) {
    			w = w.leftChild;
    		}
    		else {
    			w = w.rightChild;
    		}
    	}
    	z.parent = v;
    	
    	if (v == null) {
    		return z;
    	}
    	if (value < v.value) {
    		v.leftChild = z;
    	}
    	else {
    		v.rightChild = z;
    	}
    	
    	z.insertUpdateRightWeight(z);
    	z.updateBalanceFactor(z);  // update right weight and balance factor
    	
        AVLPlayerNode z2 = z.parent;
    	while (z2 != null) {
    		z2.checkRotation(); // check the path until we reach the root to see if there is unbalance
    		z2 = z2.parent;
    	}
    		
    	return findRoot(this);
    }
    
    /**
     * delete the node with given value and maintain AVL condition after deletion
     * running time: O(log n)
     * @param value
     * @return
     */
    public AVLPlayerNode delete(double value){
    	AVLPlayerNode z = searchNode(value); // search the given node
    	AVLPlayerNode y = null;
    	AVLPlayerNode x = null;
    	if (z == null) { // if the node is not found, return root
    		return this;
    	}
    	
    	if (z.leftChild == null || z.rightChild == null) { // one child or no child case
    		y = z;
    	}
    	else {
    		y = findSuccessor(z);  // two children case, we need to find its successor
    	}
    	
    	if (y.leftChild != null) {
    		x = y.leftChild;
    	}
    	else {
    		x = y.rightChild;
    	}
    	if (x != null) {
    		x.parent = y.parent;
    	}
    	if (y.parent == null) {
    		return x;
    	}
    	else {
    		if (y.value < y.parent.value) {
        		y.parent.leftChild = x;
        	}
        	else if (y.value > y.parent.value){
        		y.parent.rightChild = x;
        	}
    	}
    	if (y.value != z.value) {
    		z.data = y.data;
    		z.value = y.value;
    	}
    	
    	y.deleteUpdateRightWeight(y); // update right weight and balance factor
    	
    	y.updateBalanceFactor(y);
    	
    	while(y != null && y.parent != null) { 
    		y = y.deleteCheckRotation(y, y.parent); // trace the path to see if there is unbalanced
    		y = y.parent;
    	}
    	
    	return findRoot(y);
    }
    
    /**
     * check if the parent node is unbalanced, curr node is used to determine whether
     * deleted node is in parent right or left child
     * running time: O(1)
     * @param curr
     * @param parent
     * @return
     */
    public AVLPlayerNode deleteCheckRotation(AVLPlayerNode curr, AVLPlayerNode parent) {
    	if (parent.balanceFactor > 1 || parent.balanceFactor < -1) { // if unbalanced
    		if (parent.value > curr.value) {  // if deleted node is parent's right child
        		AVLPlayerNode z = parent.rightChild;
        		if (z.balanceFactor <= 0) {  // decide which rotation is needed
        			parent.rotateLeft();
        			return parent;
        		}
        		else {
        			z.rotateRight();
        			parent.rotateLeft();
        			return parent;
        		}
        	}
    		else {
        			if (parent.value < curr.value){   // if deleted node is parent's right child
            			AVLPlayerNode z = parent.leftChild;
                		if (z.balanceFactor >= 0) {  // decide which rotation is needed
                			parent.rotateRight();
                			return parent;
                		}
                		else {
                			z.rotateLeft();
                			parent.rotateRight();
                			return parent;
                		}
            		}
    		}
    		}
		return curr;
	}

	/**
     * do a right rotation and update right weight and balance factor
     * running time: O(1)
     */
    private void rotateRight(){
    	AVLPlayerNode newR = this.leftChild;
    	this.leftChild = newR.rightChild;
    	if (newR.rightChild != null) {
    		newR.rightChild.parent = this;
    	}
    	newR.parent = this.parent;
    	
    	if (this.parent != null) {
    		if (this == this.parent.leftChild) {
    			this.parent.leftChild = newR;
    		}
    		else {
    			this.parent.rightChild = newR;
    		}
    	}
    	newR.rightChild = this;
    	this.parent = newR;	
    	
    	newR.rightWeight += (this.rightWeight + 1); // update right weight and balance factor
    	newR.updateBalanceFactor(this);
    }

    /**
     * do a left rotation and update right weight and balance factor
     * running time: O(1)
     */
    private void rotateLeft(){
    	AVLPlayerNode newR = this.rightChild;
    	this.rightChild = newR.leftChild;
    	if (newR.leftChild != null) {
    		newR.leftChild.parent = this;
    	}
    	newR.parent = this.parent;
    	
    	if (this.parent != null) {
    		if (this == this.parent.leftChild) {
    			this.parent.leftChild = newR;
    		}
    		else {
    			this.parent.rightChild = newR;
    		}
    	}
    	newR.leftChild = this;
    	this.parent = newR;	
    	
    	this.rightWeight -= (newR.rightWeight + 1); // update right weight and balance factor
    	this.updateBalanceFactor(this);
    }
	
    /**
     * return the player object with the given value
     * running time: O(log n)
     * @param value
     * @return
     */
    public Player getPlayer(double value){
    	if (this.value == value) {
    		return this.data;
    	}
    	else if (this.rightChild != null && value > this.value) {
    		return this.rightChild.getPlayer(value);
    	}
    	else if (this.leftChild != null && value < this.value){
    		return this.leftChild.getPlayer(value);
    	}
    
    	return null;
    }
    
    
    /**
     * return the rank of the player with the given elo score
     * running time: O(log n)
     * @param value
     * @return
     */
    public int getRank(double value) {
    	if (this.value == value) {
    		return this.rightWeight + 1;
    	}
    	else if (this.rightChild != null && value > this.value) { // if value is greater than current node
    		return this.rightChild.getRank(value);
    	}
    	else if(this.leftChild != null && value < this.value) {
    		return this.leftChild.getRank(value) + 1 + this.rightWeight;
    	}
    	else{
    		return 0;
    	}
    }
    
    /**
     * return the node with the given value, return null if not found
     * running time: O(log n)
     * @param value
     * @return
     */
    public AVLPlayerNode searchNode(double value) {
    	if (this.value == value) {
    		return this;
    	}
    	else if (this.rightChild != null && value > this.value) { // resursively find node with the given value
    		return this.rightChild.searchNode(value);
    	}
    	else if (this.leftChild != null && value < this.value){
    		return this.leftChild.searchNode(value);
    	}
    	else {
        	return null;
    	}
    }
    
    /**
     * return the tree of names with parentheses separating subtrees
     * eg "((bob)alice(bill))"
     * running time: O(n)
     * @return
     */
    public String treeString(){
    	String output = "";
    	output = treeStringHelper(output);
    	return output;
    }
    
    /**
     * helper method for printing tree string
     * running time: O(n)
     * @param s
     * @return
     */
    public String treeStringHelper(String s) {
    	s += "(";
    	if (this.leftChild != null) {  // in order traversal
    		s = this.leftChild.treeStringHelper(s); 
    	}
    	s += this.data.getName();
    	if (this.rightChild != null) {
    		s = this.rightChild.treeStringHelper(s);
    	}
    	s += ")";
    	return s;
    }
    
    /**
     * find the successor of the given node
     * running time: O(log n)
     * @param v
     * @return
     */
    public AVLPlayerNode findSuccessor(AVLPlayerNode v) {
    	if (v.rightChild != null) {  // if the root has right child
    		return v.findMin(v.rightChild);
    	}
    	else {
    		AVLPlayerNode w = v.parent;  // if root does not have right child
    		while (w != null && v == w.rightChild) {
    			v = w;
    			w = w.parent;
    		}
    		return w;
    	}
    }
    
    /**
     * find the minimum value of the tree
     * running time: O(log n)
     * @param v
     * @return
     */
    public AVLPlayerNode findMin(AVLPlayerNode v) {
    	while (v.leftChild != null) {
    		v = v.leftChild;
    	}
    	return v;
    }
    
    /**
     * this method will find the root of the given node v
     * running time: O(log(n))
     * @param v
     * @return
     */
    public AVLPlayerNode findRoot(AVLPlayerNode v) {
    	if (v.parent == null) {
    		return v;
    	}
    	else {
    		return findRoot(v.parent);  //recursively find parent of the node v
    	}
    }
    
    /**
     * this method will make sure all the node's right weight field
     * is updated after insert operation
     * running time: O(log n)
     * @param v
     */
    public void insertUpdateRightWeight(AVLPlayerNode v) {
    	AVLPlayerNode parentNode = v.parent;
    	while (parentNode != null) {
    		if (parentNode.rightChild != null) { // if the parent's rightchild is current node, right weight increases
    			if (parentNode.rightChild.value == v.value) {  // by one
        			parentNode.rightWeight ++;
        		}
        	}
    		v = parentNode;
    		parentNode = parentNode.parent;
    		}
    }
    
    /**
     * this method will make sure all the node's right weight field
     * is updated after delete operation
     * running time: O(log n)
     * @param v
     */
    public void deleteUpdateRightWeight(AVLPlayerNode v) {
    	AVLPlayerNode parentNode = v.parent;
    	while (parentNode != null) {
    		if (parentNode.rightChild != null) {
    			if (parentNode.rightChild.value == v.value) {
        			parentNode.rightWeight --;
        		}
        	}
    		v = parentNode;
    		parentNode = parentNode.parent;
    		}
    }
    
    /**
     * this method will make sure all the node's balance factor field
     * is updated after insert and delete operation
     * running time = O(log n)
     * @param v
     */
    public void updateBalanceFactor(AVLPlayerNode v) {
    	while (v != null) {
    		AVLPlayerNode left = v.leftChild;
        	AVLPlayerNode right = v.rightChild;
        	if (left == null && right == null) {  // if node v has no child
        		v.height = 0;
        		v.balanceFactor = 0;
        	}
        	else if (left == null && right != null) {  // if it has one child
        		v.height = right.height + 1;
        		v.balanceFactor = -right.height - 1;
        	}
        	else if (left != null && right == null) {
        		v.height = left.height + 1;
        		v.balanceFactor = left.height + 1;
        	}
        	else {
        		if (left.height < right.height) { // if it has two child
        			v.height = right.height + 1;
        		}
        		else {
        			v.height = left.height + 1;
        		}
        		v.balanceFactor = left.height - right.height;
        	}
        	v = v.parent;
    	}
    }
    
    /**
     * check if we need to rotate to maintain 
     * the AVL tree balance
     * running time: O(1)
     */
    public void checkRotation() {
    	if (this.balanceFactor < -1) {  // if right tree is  heavy
    		if (this.rightChild.balanceFactor <= 0) { //single left rotation
    			this.rotateLeft();
    		}
    		else {
    			this.rightChild.rotateRight();  // right left rotation
    			this.rotateLeft();
    		}
    	}
    	
    	else if (this.balanceFactor > 1) {
    		if (this.leftChild.balanceFactor >= 0) { //single right rotation
    			this.rotateRight();
    		}
    		else {
    			this.leftChild.rotateLeft();  // left right rotation
    			this.rotateRight();
    		}
    	}
    }
    
    /**
     * get right weight
     * running time: O(1)
     * @return
     */
    public int getRightWeight() {
    	return this.rightWeight;
    }
    
    /**
     * get balance factor
     * running time: O(1)
     * @return
     */
    public int getBalanceFactor() {
    	return this.balanceFactor;
    }
    	
    //this should return a formatted scoreboard in descending order of value
    //see example printout in the pdf for the command L
    /**
     * Return a formatted scoreboard in descending order of value
     * running time: O(n)
     * @return
     */
    public String scoreboard(){
    	String output = "NAME\t\t  ID\tSCORE\n";
    	output = scoreboardHelper(output);
    	return output;
    }
    
    /**
     * scoreboard helper method
     * running time: O(n)
     * @param s
     * @return
     */
    public String scoreboardHelper(String s) {
    	if (this.rightChild != null) {  // recursively find the node from highest elo to lowest
    		s = this.rightChild.scoreboardHelper(s);
    	}
    	s += this.data.getName() +"\t\t  " + this.data.getID() + "\t" + this.data.getELO() + "\n";
    	if (this.leftChild != null) {
    		s = this.leftChild.scoreboardHelper(s);
    	}
    	return s;
    }
	
}
    
	
