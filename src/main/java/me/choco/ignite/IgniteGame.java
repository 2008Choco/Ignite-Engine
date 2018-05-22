package me.choco.ignite;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;

public class IgniteGame {
	
	private long window;
	
	public final void init(String title, int width, int height) {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit()) {
			throw new IllegalStateException("GLFW could not be initialized");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		this.window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL) {
			throw new IllegalStateException("Window could not be created");
		}
		
		try (MemoryStack stack = stackPush()) {
			IntBuffer wBuffer = stack.mallocInt(1), hBuffer = stack.mallocInt(1);
			
			glfwGetWindowSize(window, wBuffer, hBuffer);
			
			GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (vidMode.width() - wBuffer.get(0)) / 2, (vidMode.height() - hBuffer.get(0)) / 2);
		}
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1); // V-Sync
		glfwShowWindow(window);
	}
	
	public final void run() {
		GL.createCapabilities();
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
	
}