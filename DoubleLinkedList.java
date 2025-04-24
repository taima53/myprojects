package application;

public class DoubleLinkedList<T extends Comparable<T>> {
	DNode<T> dummyHead = new DNode<>(null);
	DNode<T> head = dummyHead;

	/* insert method that added data successfully */
	public void insert(T data) {
		DNode newNode = new DNode(data);
		/* case1 : if we have only the dummy head. */
		if (dummyHead.getNext() == null) {
			dummyHead.setNext(newNode);
			newNode.setPrev(dummyHead);
		} else {
			DNode<T> curr = dummyHead.getNext();
			for (; curr != null && curr.getData().compareTo(data) < 0 && curr.getNext() != null; curr = curr.getNext())
				;
			/* case2 : insert at first. */
			if (curr.getPrev() == dummyHead && curr.getData().compareTo(data) > 0) {
				newNode.setNext(curr);
				newNode.setPrev(dummyHead);
				dummyHead.setNext(newNode);
				curr.setPrev(newNode);
				/* case3 : insert at last. */
			} else if (curr.getNext() == null && curr.getData().compareTo(data) < 0) {
				newNode.setPrev(curr);
				curr.setNext(newNode);
				/* case4 : insert between. */
			} else {
				newNode.setNext(curr);
				newNode.setPrev(curr.getPrev());
				curr.getPrev().setNext(newNode);
				curr.setPrev(newNode);
			}

		}

	}

	/* delete method. */
	public T delete(T data) {
		if (dummyHead.getNext() != null) {
			DNode<T> curr = dummyHead.getNext();
			for (; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
				;
			if (curr.getData().compareTo(data) == 0) {
				/* case1 : delete at first. */
				if (curr.getPrev() == dummyHead) {
					curr.getNext().setPrev(dummyHead);
					dummyHead.setNext(curr.getNext());
					return data;
					/* case2 : delete at last. */
				} else if (curr.getNext() == null) {
					curr.getPrev().setNext(null);
					return data;
					/* case3 : delete between. */
				} else {
					curr.getPrev().setNext(curr.getNext());
					curr.getNext().setPrev(curr.getPrev());
					return data;
				}
			}
		}
		return null;
	}

	/* serch method */
	public DNode<T> search(T data) {
		DNode<T> curr = dummyHead.getNext();
		for (; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
			;
		if (curr != null && curr.getData().compareTo(data) == 0) {
			return curr;
		} else
			return null;

	}

	/* traverse method. */
	public void traverse() {
		DNode<T> curr = dummyHead.getNext();
		System.out.print("Head--->");
		while (curr != null) {
			System.out.print(curr + "--->");
			// ((District) curr.getData()).getLocationLinkedList().traverse();
			curr = curr.getNext();
		}
		System.out.print("Null");
	}
}
