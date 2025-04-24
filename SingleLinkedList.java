package application;

public class SingleLinkedList<T extends Comparable<T>> {
	Node<T> dummyHead = new Node<>(null);
	Node<T> head = dummyHead;

	/* insert method that added data sorted */
	public void insert(T data) {
		/* case1 : if we have only dummy head */
		Node<T> newNode = new Node<>(data);
		if (dummyHead.getNext() == null) {
			dummyHead.setNext(newNode);

		} else {
			Node<T> curr = dummyHead.getNext();
			Node<T> prev = dummyHead;
			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
				;
			/* case2: insert at first */
			if (prev == null) {
				newNode.setNext(curr);
				dummyHead.setNext(newNode);
				/* case3 : insert at last. */
			} else if (curr == null) {
				prev.setNext(newNode);
				/* and case4 : insert between */
			} else {
				newNode.setNext(curr);
				prev.setNext(newNode);
			}
		}
	}

	/* delete method */
	public T delete(T data) {
		if (dummyHead.getNext() != null) {
			Node<T> curr = dummyHead.getNext();
			Node<T> prev = dummyHead;
			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
				;

			if (curr.getData().compareTo(data) == 0) {
				/* case 1 : delete at first */
				if (prev == null) {
					dummyHead.setNext(curr.getNext());
					return data;
					/* case2 : delete at last */
				} else if (curr.getNext() == null) {
					prev.setNext(null);
					return data;
					/* case3 : delete between */
				} else {
					prev.setNext(curr.getNext());
					return data;
				}
			}
		} else
			System.out.println("The list is Empty. ");
		return null;
	}

	/* Search method */
	public Node<T> search(T data) {
		Node<T> curr = dummyHead.getNext();
		for (; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext())
			;
		if (curr != null && curr.getData().compareTo(data) == 0) {
			return curr;
		} else
			return null;
	}

	/* traverse method */
	public void traverse() {
		Node<T> curr = dummyHead.getNext();
		System.out.print("Head --->");
		while (curr != null) {
			System.out.print(curr + "--->");
			// if (curr.getData() instanceof Location)
			// ((Location) curr.getData()).getMartyrLinkedList().traverse();
			curr = curr.getNext();
		}
		System.out.print("null");

	}
}
