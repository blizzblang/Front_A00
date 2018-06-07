package dev.nate.main;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.Version;

import dev.nate.main.GameObjects.ESoldier;

public class gRuntime {
	public static GameWindow game;
	public static ComponentManager CM;
	public static Renderer mainRender;
	public static void main(String[] args) {
		gRuntime localGame = new gRuntime();
		localGame.begin();
		
	}
	public gRuntime() {
		game = new GameWindow(1280, 720);
		game.create();
		mainRender = new Renderer(game.getPm());
		CM = new ComponentManager();
		System.out.println("VER: " + Version.getVersion());
		System.out.println("Tan 90 = "+Math.tan(90));
	}
	private void begin() {

		
		TileMap maps = new TileMap(10, 10);maps.generateMap();
		
		ESoldier player = new ESoldier();player.addToManager();
		
        while (!glfwWindowShouldClose(game.getWindow())) {
        	game.tick();
        	CM.iEntityTick();
        	maps.renderAll(mainRender.getCamera());
        	CM.drawiRenderable();
        	game.tock();
        }
	
	}
	
}
