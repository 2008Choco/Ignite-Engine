package me.choco.ignite.example;

import org.lwjgl.opengl.GL11;

import me.choco.ignite.render.Renderer;

public class ExampleRenderer implements Renderer {
	
	@Override
	public void render() {
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
	}
	
}