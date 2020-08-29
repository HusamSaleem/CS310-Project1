package interfaces;

public interface StackInterface {
	public <T> boolean insert(T data);

	public <T> T pop();

	public boolean isEmpty();

	public <T> T peek();

}
