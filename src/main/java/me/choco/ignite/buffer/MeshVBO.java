package me.choco.ignite.buffer;

import me.choco.ignite.model.Mesh;
import me.choco.ignite.model.Model;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class MeshVBO implements VBO<Mesh> {
	
	private static final int POINTER_STRIDE = Float.BYTES * 8;
	
	private final int vertexBufferId, indicesBufferId, vaoId;
	private int size;
	
	public MeshVBO() {
		this.vertexBufferId = glGenBuffers();
		this.indicesBufferId = glGenBuffers();
		this.vaoId = glGenVertexArrays();
	}
	
	public VBO<Mesh> allocate(Mesh mesh) {
		this.size = mesh.getIndices().length;
		glBindVertexArray(vaoId);
		
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferId);
		glBufferData(GL_ARRAY_BUFFER, mesh.getVerticesAsBuffer(true), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, mesh.getIndicesAsBuffer(true), GL_STATIC_DRAW);
		
		glVertexAttribPointer(0, 3, GL_FLOAT, false, POINTER_STRIDE, 0);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, POINTER_STRIDE, Float.BYTES * 3);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, POINTER_STRIDE, Float.BYTES * 6);
		
		glBindVertexArray(0);
		return this;
	}
	
	public VBO<Mesh> allocate(Model model) {
		return allocate(model.getMesh());
	}
	
	public void draw() {
		glBindVertexArray(vaoId);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
		
		glBindVertexArray(0);
	}
	
	public void dispose() {
		glBindVertexArray(vaoId);
		
		glDeleteBuffers(vertexBufferId);
		glDeleteBuffers(indicesBufferId);
		glDeleteVertexArrays(vaoId);
		
		glBindVertexArray(0);
	}
	
}