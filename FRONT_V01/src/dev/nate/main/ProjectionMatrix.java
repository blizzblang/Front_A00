package dev.nate.main;

import org.joml.Matrix4f;

public class ProjectionMatrix extends Matrix4f {

	public ProjectionMatrix(float a, int FOV, float zNear,float zFar) {
		this.perspective((float) Math.toRadians(60), a, zNear,zFar);
	}
	public ProjectionMatrix(int w,int h, int FOV, float zNear,float zFar) {
		this( (float) w / h,FOV,zNear,zFar);
	}

}
