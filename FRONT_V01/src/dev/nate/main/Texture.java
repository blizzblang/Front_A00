package dev.nate.main;


import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {
static HashMap<String,Integer> TEX = new HashMap<String,Integer>();
    private final int id;

    public Texture(String fileName) {
        this(loadTexture(fileName));
    }

    public Texture(int id) {
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }

    private static int loadTexture(String fileName){
    	
    	if(TEX.containsKey(fileName))return TEX.get(fileName); // Don't load texture we already have.  
        PNGDecoder decoder = null;  
		try {
			decoder = new PNGDecoder(Texture.class.getResourceAsStream(fileName));
		} catch (IOException e1) {System.err.println("1.loadTexture(String fileName) has failed: ");e1.printStackTrace();} // Load Texture file
        ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight()); // Load texture contents into a byte buffer
        try {
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
		} catch (IOException e) {System.err.println("2.loadTexture(String fileName) has failed: ");e.printStackTrace();}
        buf.flip(); 
        int textureId = glGenTextures();  // Create a new OpenGL texture       
        glBindTexture(GL_TEXTURE_2D, textureId); // Bind the texture
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1); // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);   // Upload the texture data
        glGenerateMipmap(GL_TEXTURE_2D);  // Generate Mip Map
        TEX.put(fileName, textureId);
        return textureId;
        
    }

    public void cleanup() {
        glDeleteTextures(id);
    }
}
