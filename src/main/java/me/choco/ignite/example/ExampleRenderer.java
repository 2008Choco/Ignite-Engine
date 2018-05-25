package me.choco.ignite.example;

import org.lwjgl.opengl.GL11;

import me.choco.ignite.buffer.MeshVBO;
import me.choco.ignite.buffer.VBO;
import me.choco.ignite.camera.Camera;
import me.choco.ignite.model.Mesh;
import me.choco.ignite.model.Model;
import me.choco.ignite.render.Renderer;
import me.choco.ignite.shader.Shader;
import me.choco.ignite.util.OBJLoader;

public class ExampleRenderer extends Renderer {

	private Model model;
	private VBO<Mesh> vbo;
	
	private Shader shader;
	
	@Override
	public void init() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		this.model = OBJLoader.loadModel("/cube.obj");
		this.vbo = new MeshVBO().allocate(model);
		
		this.shader = new ExampleShader();
	}
	
	@Override
	public void render() {
		GL11.glClearColor(0, 0, 0, 0);
		
		this.shader.bind();
		
		this.shader.setUniformValue("projectionMatrix", Camera.PROJECTION_MATRIX);
		this.shader.setUniformValue("viewMatrix", Camera.get().getViewMatrix());
		this.shader.setUniformValue("modelMatrix", model.getTransformation().getAsMatrix());
		
		this.vbo.draw();
		this.shader.unbind();
	}
	
	@Override
	public void dispose() {
		this.vbo.dispose();
	}
	
}