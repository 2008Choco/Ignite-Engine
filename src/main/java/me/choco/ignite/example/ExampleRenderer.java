package me.choco.ignite.example;

import me.choco.ignite.buffer.MeshVBO;
import me.choco.ignite.buffer.VBO;
import me.choco.ignite.model.Mesh;
import me.choco.ignite.model.Model;
import me.choco.ignite.render.Renderer;
import me.choco.ignite.util.OBJLoader;

import org.lwjgl.opengl.GL11;

public class ExampleRenderer implements Renderer {

	private Model model;
	private VBO<Mesh> vbo;
	
	@Override
	public void init() {
		this.model = OBJLoader.loadModel("/cube.obj");
		this.vbo = new MeshVBO().allocate(model);
	}
	
	@Override
	public void render() {
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
		this.vbo.draw();
	}
	
}