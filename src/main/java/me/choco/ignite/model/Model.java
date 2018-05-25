package me.choco.ignite.model;

import java.util.Objects;

import com.google.common.base.Preconditions;

import me.choco.ignite.world.Transformation;

public class Model {
	
	private final Mesh mesh;
	private final Transformation transformation;
	
	public Model(Mesh mesh) {
		Preconditions.checkNotNull(mesh, "mesh");
		
		this.mesh = mesh;
		this.transformation = new Transformation();
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Transformation getTransformation() {
		return transformation;
	}
	
	@Override
	public int hashCode() {
		int result = 31 * (mesh == null ? 0 : mesh.hashCode());
		result = 31 * result + (transformation == null ? 0 : transformation.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Model)) return false;
		
		Model other = (Model) obj;
		return Objects.equals(mesh, other.mesh) && Objects.equals(transformation, other.transformation);
	}
	
}