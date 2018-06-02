package nate.master.com.abstracts;

import org.joml.Vector2f;
import org.joml.Vector3f;

import nate.master.com.Matrixes.ModelMatrix;
import nate.master.com.VBOS.VBO;

public abstract class gEntity {
	protected ModelMatrix LRS = new ModelMatrix();
	protected Vector3f Velocity; //Continuous motion
	protected Vector3f move; // Resets every tick. set per loop.
	protected Vector3f pointing = new Vector3f(0,0,(float) Math.toRadians(-90)); // Direction to draw. 
	protected float mass=1;
	final VBO sprite; //TODO Enable multiple sprite usage and control. 

	public gEntity(float[] pos,float angle,VBO a) {
		// 2D entity
		sprite=a;
		LRS.setPos(pos);
		LRS.setRot(0,angle,0);
		
	}
	public gEntity(VBO a) {
		this(new float[] {0,0,0},0,a);
	}
	public ModelMatrix getLRS() {return this.LRS;}
	public void tick() {
		
		ltick();
		move = null;
		LRS.setRot(pointing.x, pointing.y, pointing.z);
	}
	public abstract void ltick();
	public VBO getVBO() {return sprite;}
	public Vector3f handleMotion() {
		Vector3f ret = new Vector3f(0,0,0);
		if(Velocity!=null)
		ret.add(Velocity);
		if(move!=null)
		ret.add(move);
		if(pointing!=null)
		ret.add(pointing);
		return ret;
	}
	public Vector3f getVelocity() {return Velocity;}
}
