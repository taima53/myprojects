package application;

public class Node<T extends Comparable<T>> {
	private T data;
	private Node<T> next;

	/* constructor with arguments */
	public Node(T data) {
		this.data = data;
	}

	/* Getter & Setter of data */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/* Getter & Setter of next */
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	/* to String method to print data */
	public String toString() {
		return data+"";
	}
}
