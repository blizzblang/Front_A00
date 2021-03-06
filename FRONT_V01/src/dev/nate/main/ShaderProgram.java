package dev.nate.main;


import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import dev.nate.main.meta.DebugMng;

public class ShaderProgram {

    private final int programId;

    private int vertexShaderId;

    private int fragmentShaderId;
    
    private final Map<String, Integer> uniforms;
    
    public ShaderProgram()  {
        programId = glCreateProgram();
        uniforms = new HashMap<>();
        if (programId == 0) {
            try {
				throw new Exception("Could not create Shader");
			} catch (Exception e) {e.printStackTrace();}
        }
    }

    public void createVertexShader(String shaderCode){
        try {
			vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void createFragmentShader(String shaderCode) {
        try {
			fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
		} catch (Exception e) {e.printStackTrace();}
    }

    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }
    public void createUniform(String uniformName){
        int uniformLocation = glGetUniformLocation(programId, uniformName);
        if (uniformLocation < 0) {
            try {
				throw new Exception("Could not find uniform:" + uniformName);
			} catch (Exception e) {e.printStackTrace();}
        }
        uniforms.put(uniformName, uniformLocation);
    }
    public void setUniform(String uniformName, Matrix4f value) {
    	if(!uniforms.containsKey(uniformName)) {
    		DebugMng.closeApp(2);
    		System.exit(-1); //TODO Error Codes PLZ
    	}
        try (MemoryStack stack = MemoryStack.stackPush()) {
            // Dump the matrix into a float buffer
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(uniforms.get(uniformName), false, fb);
        }
    }
    //TODO Expand uniform variables and such
    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }
    public void link() {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            try {
				throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
			} catch (Exception e) {e.printStackTrace();}
        }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }
}