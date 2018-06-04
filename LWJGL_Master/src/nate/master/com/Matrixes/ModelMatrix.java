package nate.master.com.Matrixes;

import java.nio.FloatBuffer;

import org.joml.Matrix3fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Vector3f;
import org.joml.Vector4fc;

import nate.master.com.Util;

public class ModelMatrix extends Matrix4f {
	float[] p = new float[] {0,0,0};
	float[] r = new float[] {0,0,0};;
	float[] s = new float[] {1,1,1};
	public ModelMatrix(float[] pos, float[] rot) {
		p=Util.arrayCopy(pos, 3);
		r=Util.arrayCopy(rot, 3);
		refresh();
	}
	public ModelMatrix(float x, float y,float z,float a,float b,float c) {
		this(new float[] {x,y,z},new float[] {a,b,c});
	}
	private void refresh() {
		this.identity();
		r[0]%=360;
		r[1]%=360;
		r[2]%=360;
		
		this.rotate(r[0],1,0,0);
		this.rotate(r[1],0,1,0);
		this.rotate(r[2],0,0,1);
		this.translateLocal(p[0], p[1], p[2]);
		this.scale(s[0], s[1], s[2]);
	}
	public ModelMatrix() {
		this(0, 0, 0, 0, 0, 0);
	}
	public void addPos(Vector3f a) {
		addPos(a.x,a.y,a.z);
		
	}
	public void addPos(float a[]) 
	{
		addPos(a[0],a[1],a[2]);
	}
	public void addPos(float x,float y,float z) {
		p[0]+=x;
		p[1]+=y;
		p[2]+=z;
		
		refresh();
	}
	public void setPos(float x,float y,float z) {setPos(new float[]{x,y,z});}
	public void setPos(float[] pos) {
		p=Util.arrayCopy(pos, 3);
		
		refresh();
	}
	public void addRot(float[] a) {
		addRot(a[0],a[1],a[2]);
	}
	public void addRot(float x,float y,float z) {
		r[0]+=x;
		r[1]+=y;
		r[2]+=z;
		
		refresh();
	}
	public void setRot(float x,float y,float z) {
		r=new float[] {x,y,z};
		refresh();
	}
	public float[] getPos() {
		return p;
	}
	public float[] getRot() {
		return r;
	}
	public void setScale(Vector3f a) {
		s[0]=a.x;
		s[1]=a.y;
		s[2]=a.z;
	}

}
