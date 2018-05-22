package me.choco.ignite.shader;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public interface Shader {
	
	public int getProgramId();
	
	public void loadShader(ShaderType type, String path);
	
	public void link();
	
	public void createUniform(String name);
	
	public void setUniformValue(String name, Matrix4f matrix);
	
	public void setUniformValue(String name, Matrix3f matrix);
	
	public void setUniformValue(String name, Vector4f vector);
	
	public void setUniformValue(String name, Vector3f vector);
	
	public void setUniformValue(String name, int value);
	
	public void setUniformValue(String name, float value);
	
	public void setUniformValue(String name, boolean value);
	
	public void bind();
	
	public void unbind();
	
	public void delete();
	
}