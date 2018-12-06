package Lab01;

public class LinkedList {
	// Class variables for the Linked List
	private static Node head;
	private static int numNodes;

	public static void main(String[] args) {
		System.out.println("/=/=/=/= TESTING /=/=/=/=");
		LinkedList ll = new LinkedList(10);
		ll.addAtHead(11);
		ll.addAtHead(12);
		ll.addAtHead(13);
		ll.addAtTail(8);
		ll.addAtTail(7);
		ll.addAtIndex(4, 9);
		ll.addAtIndex(4, 9);
		ll.deleteAtIndex(4);
		ll.printList();
		ll.reverse(head);
		System.out.println("");
		ll.printList();
		System.out.println(String.format("Num Nodes: %s", ll.countNumOfNode(head)));
	}

	public Node reverse(Node node) {
		if (node != null) {
			if (node.next == null) {
				head = node;
			} else {
				reverse(node.next).next = node;
				node.next = null;
			}
		}
		return node;
	}

	public int countNumOfNode(Node node) {
		int r = 0;
		if (node != null) {
			if (node.next == null)
				r = 1;
			else
				r = 1 + countNumOfNode(node.next);
		}
		return r;
	}

	public LinkedList(Object dat) {
		head = new Node(dat);
	}

	public void addAtHead(Object dat) {
		Node temp = head;
		head = new Node(dat);
		head.next = temp;
		numNodes++;
	}

	public void addAtTail(Object dat) {
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}

		temp.next = new Node(dat);
		numNodes++;
	}

	public void addAtIndex(int index, Object dat) {
		Node temp = head;
		Node holder;
		for (int i = 0; i < index - 1 && temp.next != null; i++) {
			temp = temp.next;
		}
		holder = temp.next;
		temp.next = new Node(dat);
		temp.next.next = holder;
		numNodes++;
	}

	public void deleteAtIndex(int index) {
		Node temp = head;
		for (int i = 0; i < index - 1 && temp.next != null; i++) {
			temp = temp.next;
		}
		temp.next = temp.next.next;
		numNodes--;
	}

	public static int find(Node n) {
		Node t = head;
		int index = 0;
		while (t != n) {
			index++;
			t = t.next;
		}
		return index;
	}

	public static Node find(int index) {
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public static void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

	public static int getSize() {
		return numNodes;
	}

	class Node {
		// Declare class variables
		private Node next;
		private Object data;

		public Node(Object dat) {
			data = dat;
		}

		public Object getData() {
			return data;
		}
	}
}
