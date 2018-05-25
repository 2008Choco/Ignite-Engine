package me.choco.ignite.example;

import me.choco.ignite.shader.AbstractShader;
import me.choco.ignite.shader.ShaderType;

public class ExampleShader extends AbstractShader {
	
	public ExampleShader() {
		this.loadShader(ShaderType.VERTEX, "/example_vertex.glsl");
//		this.loadShader(ShaderType.FRAGMENT, "/example_fragment.glsl");
		
		this.createUniform("projectionMatrix");
		this.createUniform("viewMatrix");
		this.createUniform("modelMatrix");
		
		this.link();
	}
	
}