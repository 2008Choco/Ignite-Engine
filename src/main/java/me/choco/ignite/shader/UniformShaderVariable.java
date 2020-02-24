package me.choco.ignite.shader;

public class UniformShaderVariable<T> {

    private final String name;
    private final UniformShaderVariableType<T> type;

    public UniformShaderVariable(String name, UniformShaderVariableType<T> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public UniformShaderVariableType<T> getType() {
        return type;
    }

}
