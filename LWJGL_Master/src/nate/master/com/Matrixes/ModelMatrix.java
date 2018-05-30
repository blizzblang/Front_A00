package nate.master.com.Matrixes;

import java.nio.FloatBuffer;

import org.joml.Matrix3fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Vector4fc;

public class ModelMatrix extends Matrix4f {
	
	public ModelMatrix(float[] pos, float[] rot) {
		this.rotate(rot[0],1,0,0);
		this.rotate(rot[1],0,1,0);
		this.rotate(rot[2],0,0,1);
		this.translate(pos[0], pos[1], pos[2]);
	}
	public ModelMatrix(float x, float y,float z,float a,float b,float c) {
		this(new float[] {x,y,z},new float[] {a,b,c});
	}
	public ModelMatrix() {
		this(0, 0, 0, 0, 0, 0);
	}

}
