package me.choco.ignite.example;

import me.choco.ignite.shader.AbstractShader;
import me.choco.ignite.shader.ShaderType;
import me.choco.ignite.shader.UniformShaderVariable;
import me.choco.ignite.shader.UniformShaderVariableType;

import org.joml.Matrix4fc;

public class ExampleShader extends AbstractShader {

    public static final UniformShaderVariable<Matrix4fc> VARIABLE_PROJECTION_MATRIX = UniformShaderVariableType.MATRIX_4F.create("projectionMatrix");
    public static final UniformShaderVariable<Matrix4fc> VARIABLE_VIEW_MATRIX = UniformShaderVariableType.MATRIX_4F.create("viewMatrix");
    public static final UniformShaderVariable<Matrix4fc> VARIABLE_MODEL_MATRIX = UniformShaderVariableType.MATRIX_4F.create("modelMatrix");

	public ExampleShader() {
		this.loadShader(ShaderType.VERTEX, "/example_vertex.glsl");
//		this.loadShader(ShaderType.FRAGMENT, "/example_fragment.glsl");

		this.link();
	}

}