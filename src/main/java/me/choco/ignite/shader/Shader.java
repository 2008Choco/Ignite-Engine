package me.choco.ignite.shader;

public interface Shader {
	
	public int getProgramId();
	
	public void loadShader(ShaderType type, String path);
	
	public void link();
	
	public void createUniform(String name);
	
	public void setUniformValue(String name, int value);
	
	public void setUniformValue(String name, float value);
	
	public void setUniformValue(String name, boolean value);
	
	public void bind();
	
	public void unbind();
	
	public void delete();
	
}