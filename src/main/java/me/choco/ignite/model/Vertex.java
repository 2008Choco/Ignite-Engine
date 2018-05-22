package me.choco.ignite.model;

import java.util.Objects;

import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Vertex {
	
	private static final int FLOATS = 8;
	public static final int BYTES = FLOATS * Float.BYTES;
	
	private Vector3f position, normal;
	private Vector2f textureCoordinates;
	
	public Vertex() {
		this.position = new Vector3f();
		this.normal = new Vector3f();
		this.textureCoordinates = new Vector2f();
	}
	
	public Vertex position(Vector3f position) {
		this.position = position;
		return this;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vertex normal(Vector3f normal) {
		this.normal = normal;
		return this;
	}
	
	public Vector3f getNormal() {
		return normal;
	}
	
	public Vertex textureCoordinates(Vector2f textureCoordinates) {
		this.textureCoordinates = textureCoordinates;
		return this;
	}
	
	public Vector2f getTextureCoordinates() {
		return textureCoordinates;
	}
	
	public Vertex reset() {
		this.position = new Vector3f();
		this.normal = new Vector3f();
		this.textureCoordinates = new Vector2f();
		return this;
	}

	@Override
	public int hashCode() {
		int result = 31 * (normal == null ? 0 : normal.hashCode());
		
		result = 31 * result + (position == null ? 0 : position.hashCode());
		result = 31 * result + (textureCoordinates == null ? 0 : textureCoordinates.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Vertex)) return false;
		
		Vertex other = (Vertex) obj;
		return Objects.equals(position, other.position) && Objects.equals(normal, other.normal)
			&& Objects.equals(textureCoordinates, other.textureCoordinates);
	}
	
}