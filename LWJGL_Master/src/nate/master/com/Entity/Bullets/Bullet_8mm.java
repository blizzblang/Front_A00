package nate.master.com.Entity.Bullets;

import org.joml.Vector3f;

import nate.master.com.Entity.Bullet;
import nate.master.com.VBOS.RectTex;
import nate.master.com.VBOS.VBO;

public class Bullet_8mm extends Bullet {

	public Bullet_8mm(float[] pos, float angle,Vector3f v) {
		super(pos, angle, new RectTex("icon.png"),v);
		
	}

	@Override
	public void ltick() {
		
		
	}

}
