package me.choco.ignite.example;

import me.choco.ignite.IgniteGame;

public class ExampleGame extends IgniteGame {
	
	public ExampleGame() {
		this.init("Example Game", 980, 720);
		this.addRenderer(new ExampleRenderer());
		this.run();
	}
	
	public static void main(String[] args) {
		new ExampleGame();
	}
	
}