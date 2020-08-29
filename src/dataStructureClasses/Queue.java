package dataStructureClasses;

import interfaces.QueueInterface;

@SuppressWarnings("unchecked")
public class Queue<T> implements QueueInterface {
	int front;
	int rear;
	int size;
	int capacity;
	
	T[] arr;
	
	public Queue(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		
		this.rear = -1;
		this.front = -1;
		
		arr = (T[]) new Object[capacity];
	}
	
	@Override
	public <E> boolean enQueue(E data) {
		if (size < capacity) {
			if (rear == -1) {
				rear = 0;
				front = 0;
			} else if (rear >= capacity - 1) {
				rear = 0;
			} else {
				rear++;
			}
			
			arr[rear] = (T) data;
			size++;
			return true;
		}
		
		return false;
	}
	@Override
	public <E> E deQueue() {
		if (front != -1) {
			E val = (E) arr[front];
			arr[front] = null;
			front++;
			
			if (front > capacity - 1) {
				front = 0;
			}
			
			size--;
			
			if (front > rear) {
				front = -1;
				rear = -1;
			}
			
			return val;
		}
		return null;
	}
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	@Override
	public <E> E peek() {
		if (front != -1) {
			return (E) arr[front];
		}
		return null;
	}
	
	public void printQueue() {
		int i = front;
		int j = 0;
		
		while (j < size) {
			if (arr[i] != null)
				System.out.print(arr[i] + " ");
			
			j++;
			i++;
			
			if (i > capacity - 1) {
				i = 0;
			}
		}
	}
	
}