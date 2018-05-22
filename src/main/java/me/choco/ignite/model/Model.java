package me.choco.ignite.model;

import java.util.Objects;

public class Model {
	
	private Mesh mesh;
	
	public Model(Mesh mesh) {
		this.mesh = mesh;
	}
	
	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	@Override
	public int hashCode() {
		return 31 * mesh.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Model)) return false;
		
		Model other = (Model) obj;
		return Objects.equals(mesh, other.mesh);
	}
	
}