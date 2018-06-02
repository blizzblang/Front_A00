package nate.master.com.VBOS;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

import nate.master.com.Texture;
import nate.master.com.Matrixes.ModelMatrix;

//TODO Clean this up and prepare for 3D / 2D drawing  
public  abstract class VBO {
	private Texture texture=null;
    private int vboId; // Verticies
    private int indId; // Indices
    private int vaoId;
    private int texId;
    float[] vertices;
    int[] indices;
    float[] texCoords;
    FloatBuffer verticesBuffer; // Buffer for Vertexes
    IntBuffer indicesBuffer; // Buffer for Indices
    FloatBuffer textCoordsBuffer;
  
   

    public VBO(Texture t,float[] verts,int[] inds, float[] texs) {
    		texture=t;
    		vertices = verts;
    		indices = inds;
    		texCoords = texs;
	        try {
	        	//TODO Generalize these calls 
	        	vaoId = glGenVertexArrays();  
	        	
	            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
	            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
	            textCoordsBuffer = MemoryUtil.memAllocFloat(texCoords.length);
	            
	            verticesBuffer.put(vertices).flip();
	            indicesBuffer.put(indices).flip();
	            textCoordsBuffer.put(texCoords).flip();
	            
		        vboId = glGenBuffers();
		        indId = glGenBuffers();
		        texId = glGenBuffers();
	           
	            glBindVertexArray(vaoId);	//VAO bound
	            
			    glBindBuffer(GL_ARRAY_BUFFER, vboId);
				glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
				glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); 
				
	          
	            glBindBuffer(GL_ARRAY_BUFFER, texId);
	            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
	            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
	            
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
    public VBO() {
        this(new Texture("icon.png"), 
        		new float[] {
	            // V0
	            -0.5f, 0.5f, 0.5f,
	            // V1
	            -0.5f, -0.5f, 0.5f,
	            // V2
	            0.5f, -0.5f, 0.5f,
	            // V3
	            0.5f, 0.5f, 0.5f,
	            // V4
	            -0.5f, 0.5f, -0.5f,
	            // V5
	            0.5f, 0.5f, -0.5f,
	            // V6
	            -0.5f, -0.5f, -0.5f,
	            // V7
	            0.5f, -0.5f, -0.5f,
	            
	            // For text coords in top face
	            // V8: V4 repeated
	            -0.5f, 0.5f, -0.5f,
	            // V9: V5 repeated
	            0.5f, 0.5f, -0.5f,
	            // V10: V0 repeated
	            -0.5f, 0.5f, 0.5f,
	            // V11: V3 repeated
	            0.5f, 0.5f, 0.5f,

	            // For text coords in right face
	            // V12: V3 repeated
	            0.5f, 0.5f, 0.5f,
	            // V13: V2 repeated
	            0.5f, -0.5f, 0.5f,

	            // For text coords in left face
	            // V14: V0 repeated
	            -0.5f, 0.5f, 0.5f,
	            // V15: V1 repeated
	            -0.5f, -0.5f, 0.5f,

	            // For text coords in bottom face
	            // V16: V6 repeated
	            -0.5f, -0.5f, -0.5f,
	            // V17: V7 repeated
	            0.5f, -0.5f, -0.5f,
	            // V18: V1 repeated
	            -0.5f, -0.5f, 0.5f,
	            // V19: V2 repeated
	            0.5f, -0.5f, 0.5f,
	        },
        	new int[]{
	            // Front face
	            0, 1, 3, 3, 1, 2,
	            // Top Face
	            8, 10, 11, 9, 8, 11,
	            // Right face
	            12, 13, 7, 5, 12, 7,
	            // Left face
	            14, 15, 6, 4, 14, 6,
	            // Bottom face
	            16, 18, 19, 17, 16, 19,
	            // Back face
	            4, 6, 7, 5, 4, 7,},
        	new float[]{
	            0.0f, 0.0f,
	            0.0f, 0.5f,
	            0.5f, 0.5f,
	            0.5f, 0.0f,
	            
	            0.0f, 0.0f,
	            0.5f, 0.0f,
	            0.0f, 0.5f,
	            0.5f, 0.5f,
	            
	            // For text coords in top face
	            0.0f, 0.5f,
	            0.5f, 0.5f,
	            0.0f, 1.0f,
	            0.5f, 1.0f,

	            // For text coords in right face
	            0.0f, 0.0f,
	            0.0f, 0.5f,

	            // For text coords in left face
	            0.5f, 0.0f,
	            0.5f, 0.5f,

	            // For text coords in bottom face
	            0.5f, 0.0f,
	            1.0f, 0.0f,
	            0.5f, 0.5f,
	            1.0f, 0.5f,
	        });
    }

	 public void cleanup() {


	        glDisableVertexAttribArray(0);

	        // Delete the VBO
	        glBindBuffer(GL_ARRAY_BUFFER, 0);
	        
	        glDeleteBuffers(vboId);
	        glDeleteBuffers(indId);
	        glDeleteBuffers(texId);
	        // Delete the VAO
	        glBindVertexArray(0);
	        glDeleteVertexArrays(vaoId);
	    }
	 public void render() {
		  glEnable(GL_BLEND);
		    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	        glActiveTexture(GL_TEXTURE0);
	        glBindTexture(GL_TEXTURE_2D, texture.getId());
	        glBindVertexArray(getVaoId());
	        glEnableVertexAttribArray(0);
	        glEnableVertexAttribArray(1);
	        glDrawElements(GL_TRIANGLES, getIndicesCount(), GL_UNSIGNED_INT, 0);
	        glDisableVertexAttribArray(0);
	        glDisableVertexAttribArray(1);
	        glBindVertexArray(0);
	        glDisable(GL_BLEND);
	        
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


	public int getIndicesCount() {
		return indices.length;
	}

}
