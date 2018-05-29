package nate.master.com;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

//TODO Clean this up and prepare for 3D / 2D drawing 
public class VBO {

    private int vboId; // Verticies
    private int indId; // Indices
    private int vaoId;
    float[] vertices;
    int[] indices;
    FloatBuffer verticesBuffer; // Buffer for Vertexes
    IntBuffer indicesBuffer; // Buffer for Indices
	public VBO() {

	        
			vertices = new float[]{
			        -0.5f,  0.5f, 0.0f,
			        -0.5f, -0.5f, 0.0f,
			         0.5f, -0.5f, 0.0f,
			         0.5f,  0.5f, 0.0f,
	            };
			indices = new int[] {
				       //0, 1, 3, 3, 1, 2,
			};
	        
	        /*
	         * 1. Create the VAO and bind to it
	         * 2. Create the VBO and bind to it
	         * 3. Define structure of the data
	         * 4. Unbind the VBO
	         * 
	         */
	        try {
	            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
	            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
	            verticesBuffer.put(vertices).flip();
	            indicesBuffer.put(indices).flip();
	            vaoId = glGenVertexArrays();  
		        vboId = glGenBuffers();
		        indId = glGenBuffers();
	            glBindVertexArray(vaoId);	//VAO bound
			        glBindBuffer(GL_ARRAY_BUFFER, vboId);
				    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
				    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); 
			        
			        
			        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,indId);
			        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
			        
			        glBindBuffer(GL_ARRAY_BUFFER, 0);
	            glBindVertexArray(0);  // VAO unbound
	        } finally {
	            if (verticesBuffer != null ) {
	                MemoryUtil.memFree(verticesBuffer);
	            }
	            if(indicesBuffer != null) {
	            	MemoryUtil.memFree(indicesBuffer);
	            }
	        }
	}


	 public void cleanup() {


	        glDisableVertexAttribArray(0);

	        // Delete the VBO
	        glBindBuffer(GL_ARRAY_BUFFER, 0);
	        glDeleteBuffers(vboId);
	        glDeleteBuffers(indId);
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
