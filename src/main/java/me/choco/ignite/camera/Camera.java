package me.choco.ignite.camera;

import me.choco.ignite.world.Transformation;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class Camera {

	public static final float ASPECT_RATIO = 16.0F / 10.0F;
	public static final float FIELD_OF_VIEW = 60.0F;
	public static final float Z_NEAR = 0.1F, Z_FAR = 100F;

	public static final Matrix4fc PROJECTION_MATRIX = new Matrix4f().perspective(FIELD_OF_VIEW, ASPECT_RATIO, Z_NEAR, Z_FAR);

	private static Camera instance;

	private final Transformation transformation;

	private Camera() {
		this.transformation = new CameraTransformation();
	}

	public Transformation getTransformation() {
		return transformation;
	}

	public Matrix4f getViewMatrix() {
		return transformation.asMatrix();
	}

	public static Camera get() {
		return (instance == null) ? (instance = new Camera()) : instance;
	}


	public static final class CameraTransformation extends Transformation {

		@Override
		public void setScale(float x, float y, float z) {
			throw new UnsupportedOperationException("Camera has no scale");
		}

		@Override
		public void setScale(Vector3f scale) {
			throw new UnsupportedOperationException("Camera has no scale");
		}

		@Override
		public Vector3fc getScale() {
			throw new UnsupportedOperationException("Camera has no scale");
		}

		@Override
		public Matrix4f asMatrix() {
			return new Matrix4f().translate(position).rotateXYZ(rotation);
		}

	}

}