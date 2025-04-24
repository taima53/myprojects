package application;

public class DNode<T extends Comparable<T>> {
	private T data;
	private DNode next;
	private DNode prev;

	/* constructor with arguments */
	public DNode(T data) {
		this.data = data;
	}

	/* Getter & Setter methods of data */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/* Getter & Setter methods of next */
	public DNode getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	/* Getter & Setter methods of previous */
	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}

	/* to String method to print data */
	public String toString() {
		return data + " ";
	}
}
