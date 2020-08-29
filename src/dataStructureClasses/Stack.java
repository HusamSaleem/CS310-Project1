package dataStructureClasses;

import interfaces.StackInterface;

@SuppressWarnings("unchecked")
public class Stack<T> implements StackInterface {

	T[] arr;
	int size;
	int capacity;
	int top;

	public Stack(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		top = -1;

		arr = (T[]) new Object[capacity];
	}

	@Override
	public <E> boolean insert(E data) {
		if (size == 0) {
			top = 0;

			arr[top] = (T) data;
			size++;
			return true;
		} else if (size < capacity) {
			arr[++top] = (T) data;
			size++;

			return true;
		}

		return false;
	}

	@Override
	public <E> E pop() {
		if (size > 0) {
			E elementPopped = (E) arr[top];

			arr[top] = null;
			top--;
			size--;

			return elementPopped;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public <E> E peek() {
		if (size > 0)
			return (E) arr[top];
		return null;
	}

}