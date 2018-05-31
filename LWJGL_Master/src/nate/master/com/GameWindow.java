package nate.master.com;


import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import nate.master.com.Matrixes.ProjectionMatrix;

import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameWindow {
	private long window;public long getWindow() {return window;}
	private int targetFPS=60;
	private int targetUPS=0;
	private int WIDTH;
	private int HEIGHT;
	private double lastLoopTime;
	private float elapsedTime;
	private boolean vsync=true;
	private boolean resized = false;
	public ProjectionMatrix ProMatrix;
	public GameWindow(int w,int h) {
	
	     // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        
        WIDTH = w;
        HEIGHT = h;
        ProMatrix = new ProjectionMatrix(WIDTH, HEIGHT, 60, 0.01f, 1000f);
      
	}
	public void create() {
		  // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "5252018", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            this.WIDTH = width;
            this.HEIGHT = height;
            this.setResized(true);
        });
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
        GL.createCapabilities();
        glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
        glEnable(GL_DEPTH_TEST);
        lastLoopTime = getTime();
	}

	   public boolean isResized() {
	        return resized;
	    }

	    public void setResized(boolean resized) {
	        this.resized = resized;
	    }
	public void destroy() {
		 glfwFreeCallbacks(window);
         glfwDestroyWindow(window);
	}
	public void tick() {
		elapsedTime = getElapsedTime();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
      
        glfwPollEvents();
        glfwSetWindowTitle(window, ""+getFPS());
        if (isResized()) {
            glViewport(0, 0, WIDTH, HEIGHT);
           setResized(false);}
        

	}
	public void tock() {
		  glfwSwapBuffers(window); // swap the color buffers
		 
        if (isvSync()) {
            sync();
        }
	}
    private boolean isvSync() {
		return vsync;
	}
    private void sync() {
        float loopSlot = 1f / targetFPS;
        double endTime = getLastLoopTime() + loopSlot;
        while (getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }
	//TODO Move this to a Timer class for general use.
    public double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }
    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }
    public int getFPS() {
    	
    	return (int)(1.0/elapsedTime);
    }
    public double getLastLoopTime() {
        return lastLoopTime;
    }
	public ProjectionMatrix getPm() {
		return ProMatrix;
	}
	public boolean getKey(int a) {
		return glfwGetKey(window, a) == GLFW_PRESS;
	}
}
