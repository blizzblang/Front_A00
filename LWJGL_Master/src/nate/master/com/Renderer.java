package nate.master.com;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

import nate.master.com.Matrixes.ProjectionMatrix;


public class Renderer {
	private ShaderProgram shaderProgram;
	private ProjectionMatrix cP; // Current Projection
	private Camera cam = new Camera(new float[] {0,0,0},new float[] {0,0,0});
	public Renderer() {
	 	shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Util.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Util.loadResource("/fragment.fs"));
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("viewMatrix");
        shaderProgram.createUniform("modelMatrix");
	}
	public void update() {
		cam.update();
	}
	public void ren(VBO mesh){
		
		shaderProgram.bind();
		if(cP != null) {shaderProgram.setUniform("projectionMatrix", cP);}
		shaderProgram.setUniform("modelMatrix", mesh.getModelMatrix());
		shaderProgram.setUniform("viewMatrix", cam.getViewMatrix()); //TODO This isn't a good way to do this. Make a checking method to reduce calls to the Camera
		glBindVertexArray(mesh.getVaoId());
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);
	    glDrawElements(GL_TRIANGLES, mesh.getIndicesCount(),GL_UNSIGNED_INT,0);
	    glDisableVertexAttribArray(1);
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);

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

