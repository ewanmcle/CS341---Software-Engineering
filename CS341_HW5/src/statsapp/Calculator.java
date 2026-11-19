package statsapp;

public class Calculator {
	public double getMean(MyLinkedList list) {
		if (list.isEmpty()) {
			return 0;
		}
		return list.getSum() / list.getSize();
	}

	public double getStdDev(MyLinkedList list, double mean) {
		if (list.getSize() < 2) {
			return 0;
		}

		double total = 0;
		Node current = list.getHead();
		while (current != null) {
			double diff = current.getData() - mean;
			total += diff * diff;
			current = current.getNext();
		}

		return Math.sqrt(total / (list.getSize() - 1));
	}
}
