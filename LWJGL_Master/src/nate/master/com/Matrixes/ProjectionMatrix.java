package nate.master.com.Matrixes;

import java.nio.FloatBuffer;

import org.joml.Matrix3fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Vector4fc;

public class ProjectionMatrix extends Matrix4f {

	public ProjectionMatrix(float a, int FOV, float zNear,float zFar) {
		this.perspective((float) Math.toRadians(60), a, zNear,zFar);
	}
	public ProjectionMatrix(int w,int h, int FOV, float zNear,float zFar) {
		this( (float) w / h,FOV,zNear,zFar);
	}

}
