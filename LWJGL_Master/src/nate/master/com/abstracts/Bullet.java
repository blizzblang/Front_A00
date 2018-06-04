package nate.master.com.abstracts;

import org.joml.Vector3f;

import nate.master.com.VBOS.VBO;

public abstract class Bullet extends gEntity{
	
	public Bullet(float[] pos, float angle, VBO a, Vector3f vel,Vector3f Scale) {
		super(pos, angle, a);
		Velocity=vel;
		getLRS().setScale(Scale);
	}
	
	
	
}
