package me.choco.ignite.model;

import java.util.Arrays;

public class Mesh {
	
	private Vertex[] vertices;
	private int[] indices;
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}
	
	public Vertex[] getVertices() {
		return vertices;
	}
	
	public void setIndices(int[] indices) {
		this.indices = indices;
	}
	
	public int[] getIndices() {
		return indices;
	}

	@Override
	public int hashCode() {
		int result = 31 * Arrays.hashCode(indices);
		result = 31 * result + Arrays.hashCode(vertices);
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Mesh)) return false;
		
		Mesh other = (Mesh) obj;
		return Arrays.equals(indices, other.indices) && Arrays.equals(vertices, other.vertices);
	}
	
}