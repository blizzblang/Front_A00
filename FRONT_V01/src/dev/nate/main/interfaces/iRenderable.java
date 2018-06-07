package dev.nate.main.interfaces;

import dev.nate.main.ModelMatrix;
import dev.nate.main.VBO;

public interface iRenderable {
	public VBO getVBO();
	public ModelMatrix getModelMatrix();
	public void init();
	//public void render(); // Render function to draw something. 
	public int getRenderId();
	public void setRenderId(int a);
}
