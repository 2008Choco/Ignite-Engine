package me.choco.ignite.shader;

import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

public interface Shader {
	
	public int getProgramId();
	
	public void loadShader(ShaderType type, String path);
	
	public void link();
	
	public void createUniform(String name);
	
	public void setUniformValue(String name, Matrix4fc matrix);
	
	public void setUniformValue(String name, Matrix3fc matrix);
	
	public void setUniformValue(String name, Vector4fc vector);
	
	public void setUniformValue(String name, Vector3fc vector);
	
	public void setUniformValue(String name, int value);
	
	public void setUniformValue(String name, float value);
	
	public void setUniformValue(String name, boolean value);
	
	public void bind();
	
	public void unbind();
	
	public void delete();
	
}