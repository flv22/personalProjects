package org.contracts;

public interface QueueContract {
	public void add(int element);
	public void remove();
	public int get(int position);
	public int getSize();
}
