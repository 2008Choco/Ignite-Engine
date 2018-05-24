package me.choco.ignite.render;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;

public abstract class Renderer {
	
	private static int currentRenderId = 1;
	
	private Renderer parent = null;
	
	private final int id;
	private final Set<Renderer> children = new HashSet<>();
	
	public Renderer() {
		this.id = currentRenderId++;
	}
	
	public final int getId() {
		return id;
	}
	
	public final Renderer getParent() {
		return parent;
	}
	
	public final void renderWithChildren() {
		this.render();
		this.children.forEach(Renderer::render);
	}
	
	public final void clearChildren() {
		this.children.clear();
	}
	
	protected final void addChild(Renderer renderer) {
		Preconditions.checkState(!children.contains(renderer) && renderer.parent != this, "Cannot register child to parent twice");
		Preconditions.checkState(!renderer.children.contains(this) && parent != renderer, "Cannot register parent to child");
		
		if (children.add(renderer)) {
			renderer.parent = this;
		}
	}
	
	protected final Set<Renderer> getChildren() {
		return Collections.unmodifiableSet(children);
	}
	
	public abstract void init();
	
	public abstract void render();
	
	public abstract void dispose();
	
	@Override
	public final int hashCode() {
		return 31 * Integer.hashCode(id);
	}
	
	@Override
	public final boolean equals(Object object) {
		return object == this || (object instanceof Renderer && id == ((Renderer) object).id);
	}
	
}