package nate.master.com;


import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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
    	if(TEX.containsKey(fileName))return TEX.get(fileName);
        // Load Texture file
        PNGDecoder decoder = null;
		try {
			decoder = new PNGDecoder(Texture.class.getResourceAsStream(fileName));
		} catch (IOException e1) {System.err.println("1.loadTexture(String fileName) has failed: ");e1.printStackTrace();}

        // Load texture contents into a byte buffer
        ByteBuffer buf = ByteBuffer.allocateDirect(
                4 * decoder.getWidth() * decoder.getHeight());
        try {
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
		} catch (IOException e) {
			System.err.println("2.loadTexture(String fileName) has failed: ");
			e.printStackTrace();
		}
        buf.flip();

        // Create a new OpenGL texture 
        int textureId = glGenTextures();
        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Upload the texture data
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0,
                GL_RGBA, GL_UNSIGNED_BYTE, buf);
        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);
        TEX.put(fileName, textureId);
        return textureId;
    }

    public void cleanup() {
        glDeleteTextures(id);
    }
}
