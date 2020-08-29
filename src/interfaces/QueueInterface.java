package interfaces;

public interface QueueInterface {
	public <E> boolean enQueue(E data);

	public <E> E deQueue();

	public boolean isEmpty();
	
	public <E> E peek();

}
