package statsapp;

public class MyLinkedList {
	private Node head;
	private Node tail;
	private int size;
	private double sum;

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
		sum = 0;
	}

	public void add(double num) {
		Node newNode = new Node(num);

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}

		size++;
		sum += num;
	}

	public Node getHead() {
		return head;
	}

	public int getSize() {
		return size;
	}

	public double getSum() {
		return sum;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		String result = "[";
		Node current = head;
		while (current != null) {
			result += current.getData();
			if (current.getNext() != null) {
				result += ", ";
			}
			current = current.getNext();
		}
		return result + "]";
	}
}
