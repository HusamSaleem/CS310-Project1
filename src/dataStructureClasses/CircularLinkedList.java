package dataStructureClasses;

import interfaces.LinkedListInterface;
import other_Data_Classes.Process;

@SuppressWarnings("unchecked")
public class CircularLinkedList<T> implements LinkedListInterface {

	public int size;
	public Node root;
	Node tail;

	public CircularLinkedList() {
		this.root = null;
		this.size = 0;
	}

	public class Node {
		public T data;
		public Node next;

		public Node() {
			this.data = null;
			this.next = null;
		}

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	@Override
	public <E extends Comparable<E>> boolean insert(Object data) {
		if (size == 0) {
			root = new Node((T) data);
			tail = root;
			root.next = tail;
			tail.next = root;

			size++;
			return true;
		} else {
			Node curNode = root;

			for (int i = 0; i < size; i++) {
				if (((Process) data).compareTo(curNode.data) > 0) {
					if (curNode.next != root) {
						curNode = curNode.next;
					}
				} else {
					break;
				}
			}

			if (root == curNode) {
				insertAtHead(data);
				return true;
			} else if (tail == curNode) {
				insertAtEnd(data);
				return true;
			}

			// In between the nodes
			Node newNode = new Node((T) data);
			newNode.next = curNode.next;
			curNode.next = newNode;
			size++;

			return true;
		}
	}

	// Not used independently when needed to sort the linked list by priority /
	// other data...
	@Override
	public <E> boolean insertAtEnd(E data) {
		// The tail's next will become the new node
		// The new node's next will become the tails..
		Node newNode = new Node((T) data);

		tail.next = newNode;
		newNode.next = root;
		tail = newNode;
		size++;
		return true;
	}

	// Not used independently when needed to sort the linked list by priority /
	// other data...
	@Override
	public <E> boolean insertAtHead(E data) {
		Node newNode = new Node((T) data);

		newNode.next = root;
		root = newNode;
		tail.next = root;
		size++;
		return true;
	}

	@Override
	public <E> E peek() {
		if (size != 0)
			return (E) root.data;

		return null;
	}

	public <E> boolean remove(E data) {
		if (size == 0) {
			return false;
		} else {
			Node curNode = root;
			Node prev = null;

			for (int i = 0; i < size; i++) {
				if (curNode.data == data) {
					if (curNode == root) {
						removeHead();
						return true;
					} else if (curNode == tail) {
						removeEnd();
						return true;
					} else {
						prev.next = curNode.next;
						size--;
						return true;
					}
				}

				prev = curNode;
				curNode = curNode.next;
			}

		}
		return false;
	}

	@Override
	public <E> E removeHead() {
		if (root != null) {
			Node curRoot = root;
			root = root.next;
			tail.next = root;

			size--;
			return (E) curRoot;
		}
		return null;
	}

	@Override
	public <E> E removeEnd() {
		if (size != 0) {
			Node curNode = root;

			while (curNode.next != tail) {
				curNode = curNode.next;
			}

			curNode.next = root;
			tail = curNode;
			size--;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public String toString() {
		Node curNode = root;
		String s = "";

		for (int i = 0; i < this.size - 1; i++) {
			s += curNode.data + ", ";
			curNode = curNode.next;
		}

		s += curNode.data;

		return s;
	}
}