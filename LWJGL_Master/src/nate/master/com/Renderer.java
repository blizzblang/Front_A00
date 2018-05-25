package nate.master.com;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;


public class Renderer {
	private ShaderProgram shaderProgram;
	
	public Renderer() {
	 	shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Util.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Util.loadResource("/fragment.fs"));
        shaderProgram.link();
	}
	public void ren(VBO mesh){
		
		shaderProgram.bind();// Draw the mesh
		glBindVertexArray(mesh.getVaoId());
	    glEnableVertexAttribArray(0);
	    glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
	    // Restore state
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);

	    shaderProgram.unbind();
	
	}
	public void cleanup() {
		
	    if (shaderProgram != null) {
	        shaderProgram.cleanup();
	    }
	}
}

