package me.choco.ignite;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import me.choco.ignite.render.Renderer;

import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;

public class IgniteGame {
	
	private final List<Renderer> renderers = new LinkedList<>();
	
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
		
		this.renderers.forEach(Renderer::init);
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			this.render();
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
		
		this.renderers.clear();
		
		Callbacks.glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public void render() {
		this.renderers.forEach(Renderer::render);
	}
	
	public void addRenderer(Renderer renderer) {
		this.renderers.add(renderer);
	}
	
	public void addRenderer(int index, Renderer renderer) {
		this.renderers.add(index, renderer);
	}
	
	public void removeRenderer(Renderer renderer) {
		this.renderers.remove(renderer);
	}
	
}