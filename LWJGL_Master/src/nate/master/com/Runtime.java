package nate.master.com;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.Callback;

import nate.master.com.Entities.Soldier;
import nate.master.com.Entity.Bullets.Bullet_8mm;
import nate.master.com.Managers.EntityManager;
import nate.master.com.VBOS.VBO;

import org.joml.Vector3f;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Window;
//TODO Clean up warnings.
public class Runtime {
	static GameWindow game = new GameWindow(1920,1080);
	EntityManager EntMng = new EntityManager();
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
	      //  debugProc = GLUtil.setupDebugMessageCallback();
	        EntMng.add(new Soldier());
	        EntMng.add(new Bullet_8mm(new float[] {10,0,0}, 0, new Vector3f(-1.2f,0,0)));
	        while (!glfwWindowShouldClose(game.getWindow())) {
	        	rending.update();
	        	game.tick();
	    
	        	
	        	//rending.ren(unit);
	        	
	        	EntMng.tickAll();
	        	EntMng.renderAll();
	       
	        	
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
