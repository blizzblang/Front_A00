package nate.master.com.Entity.Bullets;

import org.joml.Vector3f;

import nate.master.com.VBOS.RectTex;
import nate.master.com.VBOS.VBO;
import nate.master.com.abstracts.Bullet;

public class Bullet_8mm extends Bullet {

	public Bullet_8mm(float[] pos, float angle,Vector3f v) {
		super(pos, angle, new RectTex("icon.png"),v,new Vector3f(0.1f,0.0250f,1f));
		startTTL();
		setTTL(10);
	}

	@Override
	public void ltick() {
		
		
	}

}
