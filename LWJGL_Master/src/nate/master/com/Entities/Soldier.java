package nate.master.com.Entities;

import org.joml.Vector3f;

import nate.master.com.Matrixes.ModelMatrix;
import nate.master.com.VBOS.RectTex;
import nate.master.com.VBOS.VBO;
import nate.master.com.abstracts.gEntity;

public class Soldier extends gEntity {
	
	public Soldier() {
		super(new RectTex("blueMan32x32.png") );
	}

	@Override
	public void ltick() {
	Velocity = new Vector3f(0.02f,0.0f,0.0f);
		
	}


	
		
}
