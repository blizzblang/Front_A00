package dev.nate.main;



public class VBOrect {
	VBO localVBO;
	
	public VBOrect(String path,float w, float h) {
		float[] verts = new float[] {			       
		-0.5f*w,  0.5f*h, 0.0f,
		-0.5f*w, -0.5f*h, 0.0f,
		 0.5f*w, -0.5f*h, 0.0f,
		 0.5f*w,  0.5f*h, 0.0f};
		int[] indices = new int[] {0, 1, 3, 3, 1, 2} ;
		float[] texC = new float[] { 0f, 0f, 1f, 0f, 1f, 1f, 0f, 1f };
		Texture image = new Texture(path);
		localVBO = new VBO(image,verts,indices,texC);
	}
	public VBO getVBO() {
		return localVBO;
	}
}