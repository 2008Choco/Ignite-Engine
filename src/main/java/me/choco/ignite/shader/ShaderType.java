package me.choco.ignite.shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public enum ShaderType {
	
	FRAGMENT(GL_FRAGMENT_SHADER),
	VERTEX(GL_VERTEX_SHADER);
	
	
	private final int glId;
	
	private ShaderType(int glId) {
		this.glId = glId;
	}
	
	public int getGLId() {
		return glId;
	}
	
}