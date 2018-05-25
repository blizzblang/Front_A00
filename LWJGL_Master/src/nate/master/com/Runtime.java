package nate.master.com;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.Callback;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Window;
//TODO Clean up warnings.
public class Runtime {
	GameWindow game = new GameWindow(720,480);
	Renderer rending;
	Callback debugProc;
	public static void main(String[] args) {
	System.out.println("VER: " + Version.getVersion());
	Runtime run = new Runtime();
	run.begin();

	}
	public Runtime() {
		
		game.create();
	}
	private void begin() {
	
		try {
	       
	      
	        VBO triangle = new VBO();
	        rending = new Renderer();
	        debugProc = GLUtil.setupDebugMessageCallback();
	        
	        while (!glfwWindowShouldClose(game.getWindow())) {
	        	game.tick();
	        	//MAIN GAME LOOP
	        	
	        	
	        	rending.ren(triangle);
	        	
	       
	        	
	        	game.tock();
	        }
	        triangle.cleanup();
	        rending.cleanup();
			game.destroy();
		} finally {
	         glfwTerminate();
	         glfwSetErrorCallback(null).free();
		}
	}
}
