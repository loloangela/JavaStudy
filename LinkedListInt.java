/*
 * LinkedListInt - collection of Nodes (containing int data)
 * Here will contain methods to access the Linked List
 */

public class LinkedListInt{
	Node head;
	/*
	 * Constructors
	 */
	public LinkedListInt(Node start){
		this.head = start;
	}
	public LinkedListInt(){
		this.head = null;
	}


	public static void main(String []args){
		LinkedListInt aList = new LinkedListInt();
		aList.prepend(22);
		aList.prepend(37);
		aList.prepend(62);

		Node bNode = new Node(17);		
		LinkedListInt bList = new LinkedListInt(bNode);
		bList.prepend(18);
		bList.prepend(19);
		bList.prepend(20);

		aList.printList();
		bList.printList();
		bList.reverseList();
	}
	/*
	 * The prepend method inserts element at the beginning of the LinkedList.
	 * Accepts int and creates a new node and makes it the new
	 */
	public void prepend(int val){
		Node newNode = new Node(val);

		if(head == null){
			head = newNode;
		}else{
			newNode.next = head;
			head = newNode;
		}
	}

	/*
	 * The reverseList method prints out the LinkedList in reverse order
	 */
	public void reverseList(){
		LinkedListInt revList = new LinkedListInt();
		Node curPos = head;

		while(curPos != null){
			revList.prepend(curPos.data);
			curPos = curPos.next;
		}

		revList.printList();
	}

	/* The printList method prints the LinkedList contents */
	public void printList(){
		Node curPos = head;
		while(curPos != null){
			System.out.printf("%d\n", curPos.data);
			curPos = curPos.next;
		}
		
	}

}