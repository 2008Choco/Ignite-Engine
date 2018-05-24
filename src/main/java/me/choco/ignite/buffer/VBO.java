package me.choco.ignite.buffer;

public interface VBO<T> {
	
	public VBO<T> allocate(T data);
	
	public void draw();
	
	public void dispose();
	
}