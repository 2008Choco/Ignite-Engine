package me.choco.ignite.shader;

public interface Shader {

	public int getProgramId();

	public void loadShader(ShaderType type, String path);

	public void link();

	public <T> void setUniformValue(UniformShaderVariable<T> variable, T value);

	public void bind();

	public void unbind();

	public void delete();

    public static <T> UniformShaderVariable<T> createUniform(String name, UniformShaderVariableType<T> type) {
        return new UniformShaderVariable<>(name, type);
    }

}