package nate.master.com;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

import nate.master.com.Matrixes.ProjectionMatrix;
import nate.master.com.VBOS.VBO;


public class Renderer {
	private ShaderProgram shaderProgram;
	private ProjectionMatrix cP; // Current Projection
	private Camera cam = new Camera(new float[] {0,0,-1.5f},new float[] {0,0,0});
	public Renderer() {
	 	shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Util.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Util.loadResource("/fragment.fs"));
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("viewMatrix");
        shaderProgram.createUniform("modelMatrix");
        shaderProgram.createUniform("texture_sampler");
	}
	public void update() {
		cam.update();
	}
	public void ren(VBO mesh,Matrix4f a){
		
		shaderProgram.bind();
		if(cP != null) {shaderProgram.setUniform("projectionMatrix", cP);}
		shaderProgram.setUniform("modelMatrix",a);
		shaderProgram.setUniform("viewMatrix", cam.getViewMatrix()); //TODO This isn't a good way to do this. Make a checking method to reduce calls to the Camera
		shaderProgram.setUniform("texture_sampler", 0);
		mesh.render();
	    shaderProgram.unbind();
	
	}
	public void cleanup() {
		
	    if (shaderProgram != null) {
	        shaderProgram.cleanup();
	    }
	}
	public void setPm(ProjectionMatrix a) {
		cP = a;
		
	}
}

