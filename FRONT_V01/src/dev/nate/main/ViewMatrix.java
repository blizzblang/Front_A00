package dev.nate.main;

import org.joml.Matrix4f;

public class ViewMatrix extends Matrix4f {
	
	public ViewMatrix(float[] pos, float[] rot) {
		this.rotate(rot[0],1,0,0);
		this.rotate(rot[1],0,1,0);
		this.rotate(rot[2],0,0,1);
		this.translateLocal(pos[0], pos[1], pos[2]);
	}
	public ViewMatrix(float x, float y,float z,float a,float b,float c) {
		this(new float[] {x,y,z},new float[] {a,b,c});
	}
}
