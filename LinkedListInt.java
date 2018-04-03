/*
 * LinkedListInt - collection of Nodes (containing int data)
 * Here will contain methods to access the Linked List
 */

public class LinkedListInt{
	Node head;
	int size = 0;
	/*
	 * Constructors
	 */
	public LinkedListInt(Node start){
		this.head = start;
		this.size = calcSize();
	}
	public LinkedListInt(){
		this.head = null;
	}


	public static void main(String []args){
		LinkedListInt aList = new LinkedListInt();
		aList.prepend(22);
		aList.prepend(37);
		aList.append(62);
		aList.append(49, 1);

		Node bNode = new Node(17);		
		LinkedListInt bList = new LinkedListInt(bNode);
		bList.prepend(18);
		bList.prepend(19);
		bList.append(20);
		bList.append(43, 2);

		aList.printList();
		bList.printList();
		bList.reverseList();
	}

	public int calcSize(){
		Node curPos = this.head;
		this.size = 0;
		while(curPos != null){
			this.size++;
			curPos = curPos.next;
		}
		return this.size;
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

		this.size++;
	}

	/*
	 * The append method inserts the element at the end or specified position
	 * Accepts int representing the data
	 */
	public void append(int val){
		Node newNode = new Node(val);
		Node curPos = this.head;
		while(curPos.next != null){
			curPos = curPos.next;
		}
		curPos.next = newNode;
	}
	
	public void append(int val, int pos){
		Node newNode = new Node(val);
		Node curPos = this.head;
		if(pos == 0){
			this.prepend(val);
		}else{
			int count = 0;
			while((curPos != null) && (count < pos)){
				if(count != pos -1){
					curPos = curPos.next;					
				}	
				count++;	
			}
			newNode.next = curPos.next;
			curPos.next = newNode;
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
		System.out.print("LinkedList: ");
		while(curPos != null){
			System.out.printf("%d -> ", curPos.data);
			curPos = curPos.next;
		}
		System.out.println("NULL");
	}

}