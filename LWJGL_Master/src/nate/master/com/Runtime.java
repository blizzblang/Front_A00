package nate.master.com;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.Callback;

import nate.master.com.Entities.Soldier;
import nate.master.com.Entity.Bullets.Bullet_8mm;
import nate.master.com.Managers.EntityManager;
import nate.master.com.Managers.SuperManager;
import nate.master.com.VBOS.VBO;
import nate.master.com.abstracts.TileMap;

import org.joml.Vector3f;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Window;
//TODO Clean up warnings.
public class Runtime {
	public static GameWindow game = new GameWindow(1920,1080);
	public static SuperManager GameManager = new SuperManager();
	
	
	public static Renderer rending;
	Callback debugProc;
	public static void main(String[] args) {
	System.out.println("VER: " + Version.getVersion());
	System.out.println("Tan 90 = "+Math.tan(90));
	Runtime run = new Runtime();
	run.begin();

	}
	public Runtime() {
		
		game.create();
	}
	private void begin() {
	
		try {
	        rending = new Renderer();
	        rending.setPm(game.getPm());
	        GameManager.init();
	   
	        Soldier aS0 = new Soldier();
	      
	        GameManager.add(aS0);
	        GameManager.add(new Bullet_8mm(new float[] {10,0,0}, 0, new Vector3f(-0.25f,0,0)));
	        int size=10;
	        for(int x=0;x<size;x++)
	        	for(int y=0;y<size;y++) {
	        		GameManager.add(new TileMap(new float[] {x-size/2,y-size/2,-.01f}));
	        	}
	        while (!glfwWindowShouldClose(game.getWindow())) {
	        	rending.update();
	        	game.tick();
	    
	        	
	        	//rending.ren(unit);
	        	
	        	GameManager.tickAll();
	        	GameManager.renderAll();
	       
	        	
	        	game.tock();
	        }
	        
	        rending.cleanup();
			game.destroy();
		} finally {
	         glfwTerminate();
	         glfwSetErrorCallback(null).free();
		}
	}
}
