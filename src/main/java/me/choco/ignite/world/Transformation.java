package me.choco.ignite.world;

import java.util.Objects;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class Transformation {
	
	private final Vector3f position = new Vector3f();
	private final Vector3f rotation = new Vector3f();
	private final Vector3f scale = new Vector3f();
	
	public void setPosition(float x, float y, float z) {
		this.position.set(x, y, z);
	}
	
	public void setPosition(Vector3f position) {
		this.setPosition(position.x, position.y, position.z);
	}
	
	public Vector3fc getPosition() {
		return position;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getZ() {
		return position.z;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation.set(x % 360, y % 360, z % 360);
	}
	
	public void setRotation(Vector3f rotation) {
		this.setRotation(rotation.x, rotation.y, rotation.z);
	}
	
	public void rotate(float x, float y, float z) {
		this.rotation.x = (rotation.x + x) % 360;
		this.rotation.y = (rotation.y + y) % 360;
		this.rotation.z = (rotation.z + z) % 360;
	}
	
	public Vector3fc getRotation() {
		return rotation;
	}
	
	public float getRotationX() {
		return rotation.x;
	}
	
	public float getRotationY() {
		return rotation.y;
	}
	
	public float getRotationZ() {
		return rotation.z;
	}
	
	public void setScale(float x, float y, float z) {
		this.scale.set(x, y, z);
	}
	
	public void setScale(Vector3f scale) {
		this.setScale(scale.x, scale.y, scale.z);
	}
	
	public Vector3fc getScale() {
		return scale;
	}
	
	public float getScaleX() {
		return scale.x;
	}
	
	public float getScaleY() {
		return scale.y;
	}
	
	public float getScaleZ() {
		return scale.z;
	}
	
	public Matrix4f getAsMatrix() {
		return new Matrix4f().scale(scale).translate(position).rotateXYZ(getRotationInRadians());
	}

	@Override
	public int hashCode() {
		int result = 31 * (position == null ? 0 : position.hashCode());
		
		result = 31 * result + (rotation == null ? 0 : rotation.hashCode());
		result = 31 * result + (scale == null ? 0 : scale.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Transformation)) return false;
		
		Transformation other = (Transformation) obj;
		return Objects.equals(position, other.position) && Objects.equals(rotation, other.rotation)
			&& Objects.equals(scale, other.scale);
	}
	
	private Vector3f getRotationInRadians() {
		Vector3f result = new Vector3f(rotation);
		
		result.x = (float) Math.toRadians(rotation.x);
		result.y = (float) Math.toRadians(rotation.y);
		result.z = (float) Math.toRadians(rotation.z);
		
		return result;
	}
	
}