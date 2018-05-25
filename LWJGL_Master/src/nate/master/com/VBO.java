package nate.master.com;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

//TODO Clean this up and prepare for 3D / 2D drawing
public class VBO {

    private int vboId;
    private int vaoId;
    float[] vertices;
	public VBO() {

	        
			vertices = new float[]{
	                -0.5f,  0.5f, -0.5f,
	                -0.5f, -0.5f, 0.5f,
	                 0.5f,  0.5f, 0.0f,
	                 0.5f,  0.5f, 0.0f,
	                -0.5f, -0.5f, 0.0f,
	                 0.5f, -0.5f, 0.0f,
	            };

	        FloatBuffer verticesBuffer = null;
	        try {
	            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
	            verticesBuffer.put(vertices).flip();

	            // Create the VAO and bind to it
	            vaoId = glGenVertexArrays();
	            glBindVertexArray(vaoId);

	            // Create the VBO and bint to it
	            vboId = glGenBuffers();
	            glBindBuffer(GL_ARRAY_BUFFER, vboId);
	            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
	            // Define structure of the data
	            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

	            // Unbind the VBO
	            glBindBuffer(GL_ARRAY_BUFFER, 0);

	            // Unbind the VAO
	            glBindVertexArray(0);
	        } finally {
	            if (verticesBuffer != null) {
	                MemoryUtil.memFree(verticesBuffer);
	            }
	        }
	}


	 public void cleanup() {


	        glDisableVertexAttribArray(0);

	        // Delete the VBO
	        glBindBuffer(GL_ARRAY_BUFFER, 0);
	        glDeleteBuffers(vboId);

	        // Delete the VAO
	        glBindVertexArray(0);
	        glDeleteVertexArrays(vaoId);
	    }

	public int getVaoId() {
		return vaoId;
	}
	public int getVboId() {
		return vboId;
	}
	//TODO Would need 3d/2d specification.
	public int getVertexCount() {
		return vertices.length/3;
	}
}
