package interfaces;

public interface LinkedListInterface {
	public <E extends Comparable<E>> boolean insert(Object data);
	
	public <E> boolean insertAtEnd(E data);
	
	public <E> boolean insertAtHead(E data);
	
	public <E> E peek();
	
	public <E> E removeEnd();
	
	public <E> E removeHead();
	
	public boolean isEmpty();
}
