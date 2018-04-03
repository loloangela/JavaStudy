/*
 * Node class to be used in LinkedList
 * To contain LinkedList of integers
 * Written By: Lori A. Oliver
 */

public class Node{
	Node next;
	int data;

	public Node(int val){
		this.data = val;
	}

	public Node(){
		this.data = -1;
	}
}