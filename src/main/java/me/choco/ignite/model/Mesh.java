package me.choco.ignite.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

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
	
	public FloatBuffer getVerticesAsBuffer(boolean flip) {
		FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length * Vertex.BYTES);
		
		for (Vertex vertex : vertices) {
			Vector3f position = vertex.getPosition();
			buffer.put(position.x);
			buffer.put(position.y);
			buffer.put(position.z);
			
			Vector3f normal = vertex.getNormal();
			buffer.put(normal.x);
			buffer.put(normal.y);
			buffer.put(normal.z);
			
			Vector2f textureCoordinate = vertex.getTextureCoordinates();
			buffer.put(textureCoordinate.x);
			buffer.put(textureCoordinate.y);
		}
		
		if (flip) {
			buffer.flip();
		}
		
		return buffer;
	}
	
	public IntBuffer getIndicesAsBuffer(boolean flip) {
		IntBuffer buffer = MemoryUtil.memAllocInt(indices.length).put(indices);
		
		if (flip) {
			buffer.flip();
		}
		
		return buffer;
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