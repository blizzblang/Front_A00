package nate.master.com.Matrixes;

import java.nio.FloatBuffer;

import org.joml.Matrix3fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Vector4fc;

public class ProjectionMatrix extends Matrix4f {

	public ProjectionMatrix(float aspectRatio, int FOV, float zNear,float zFar) {
		float s = (float) (1 / Math.tan( (FOV/2) * (Math.PI/180) ));
		this._m00(s);
		this._m11(s);
		this._m22(-(zFar/(zFar-zNear)));
		this._m32(this.m22()*zNear);
		this._m23(-1);
	}
	public ProjectionMatrix(int w,int h, int FOV, float zNear,float zFar) {
		this( (float) w / h,FOV,zNear,zFar);
	}

}
