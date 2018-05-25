#version 330

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

in vec3 in_position;
in vec3 in_normal;
in vec3 in_textureCoord;

void main() {
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(in_position, 0);
}