package me.choco.ignite.shader;

import java.nio.FloatBuffer;

import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

@FunctionalInterface
public interface UniformShaderVariableType<T> {

    public static final UniformShaderVariableType<Integer> INT = (variable, value) -> GL20.glUniform1i(variable, value.intValue());
    public static final UniformShaderVariableType<Float> FLOAT = (variable, value) -> GL20.glUniform1f(variable, value.floatValue());
    public static final UniformShaderVariableType<Boolean> BOOLEAN = (variable, value) -> GL20.glUniform1i(variable, value.booleanValue() ? 1 : 0);

    public static final UniformShaderVariableType<Vector2fc> VECTOR_2F = (variable, value) -> GL20.glUniform2f(variable, value.x(), value.y());
    public static final UniformShaderVariableType<Vector3fc> VECTOR_3F = (variable, value) -> GL20.glUniform3f(variable, value.x(), value.y(), value.z());
    public static final UniformShaderVariableType<Vector4fc> VECTOR_4F = (variable, value) -> GL20.glUniform4f(variable, value.x(), value.y(), value.z(), value.w());

    public static final UniformShaderVariableType<Matrix3fc> MATRIX_3F = (variable, value) -> {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(9);
            GL20.glUniformMatrix3fv(variable, false, value.get(buffer));
        }
    };
    public static final UniformShaderVariableType<Matrix4fc> MATRIX_4F = (location, value) -> {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            GL20.glUniformMatrix3fv(location, false, value.get(buffer));
        }
    };

    public void set(int location, T value);

    public default UniformShaderVariable<T> create(String name) {
        return new UniformShaderVariable<>(name, this);
    }

}
