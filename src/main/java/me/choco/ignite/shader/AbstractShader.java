package me.choco.ignite.shader;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import me.choco.ignite.IgniteGame;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

public abstract class AbstractShader implements Shader {
	
	private final int programId;
	private final Map<String, Integer> uniforms = new HashMap<>();
	private final Map<ShaderType, Integer> shaderIds = new EnumMap<>(ShaderType.class);
	
	public AbstractShader() {
		this.programId = glCreateProgram();
		
		if (programId == 0) {
			throw new IllegalStateException("Could not create shader program");
		}
	}
	
	@Override
	public int getProgramId() {
		return programId;
	}
	
	@Override
	public void loadShader(ShaderType type, String path) {
		Preconditions.checkNotNull(type, "type");
		Preconditions.checkArgument(path != null && !path.trim().isEmpty(), "path");
		
		int shaderId = glCreateShader(type.getGLId());
		Preconditions.checkState(shaderId != 0, "Shader could not be created");
		
		this.shaderIds.put(type, shaderId);
		
		StringBuilder source = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(IgniteGame.class.getResourceAsStream(path)))) {
			reader.lines().forEach(l -> source.append(l).append('\n'));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		glShaderSource(shaderId, source);
		glCompileShader(shaderId);
		
		Preconditions.checkState(glGetShaderi(shaderId, GL_COMPILE_STATUS) != 0, "Shader could not be compiled (type: %s). Stacktrace:\n%s", type, glGetShaderInfoLog(shaderId));
		glAttachShader(programId, shaderId);
	}
	
	@Override
	public void link() {
		glLinkProgram(programId);
		Preconditions.checkState(glGetProgrami(programId, GL_LINK_STATUS) != 0, "Could not link shader program");
		
		for (int shaderId : shaderIds.values()) {
			if (shaderId == 0) continue;
			glDetachShader(programId, shaderId);
		}
		
		glValidateProgram(programId);
		Preconditions.checkState(glGetProgrami(programId, GL_VALIDATE_STATUS) != 0, "Could not validate shader program");
	}
	
	@Override
	public void createUniform(String name) {
		int location = glGetUniformLocation(programId, name);
		Preconditions.checkState(location >= 0, "Could not find uniform variable with name %s", name);
		
		this.uniforms.put(name, location);
	}
	
	@Override
	public void setUniformValue(String name, Matrix4f matrix) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			FloatBuffer buffer = stack.mallocFloat(16);
			glUniformMatrix4fv(uniforms.get(name), false, matrix.get(buffer));
		}
	}
	
	@Override
	public void setUniformValue(String name, Matrix3f matrix) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			FloatBuffer buffer = stack.mallocFloat(9);
			glUniformMatrix3fv(uniforms.get(name), false, matrix.get(buffer));
		}
	}
	
	@Override
	public void setUniformValue(String name, Vector4f vector) {
		glUniform4f(uniforms.get(name), vector.x, vector.y, vector.z, vector.w);
	}
	
	@Override
	public void setUniformValue(String name, Vector3f vector) {
		glUniform3f(uniforms.get(name), vector.x, vector.y, vector.z);
	}
	
	@Override
	public void setUniformValue(String name, int value) {
		glUniform1i(uniforms.get(name), value);
	}
	
	@Override
	public void setUniformValue(String name, float value) {
		glUniform1f(uniforms.get(name), value);
	}
	
	@Override
	public void setUniformValue(String name, boolean value) {
		this.setUniformValue(name, value ? 1 : 0);
	}
	
	@Override
	public void bind() {
		glUseProgram(programId);
	}
	
	@Override
	public void unbind() {
		glUseProgram(0);
	}
	
	@Override
	public void delete() {
		this.unbind();
		this.shaderIds.clear();
		this.uniforms.clear();
		
		if (programId != 0) {
			glDeleteProgram(programId);
		}
	}
	
}