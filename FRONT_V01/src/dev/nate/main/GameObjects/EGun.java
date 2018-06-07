package dev.nate.main.GameObjects;

import org.joml.Vector3f;

import dev.nate.main.ModelMatrix;
import dev.nate.main.VBO;
import dev.nate.main.VBOrect;
import dev.nate.main.compEntity;
import dev.nate.main.interfaces.iEntity;
import dev.nate.main.interfaces.iRenderable;

public class EGun implements iEntity, iRenderable{
	int entityId=-1;
	int renderId=-1;
	compEntity localEntity;
	VBOrect sprite;
	
	public EGun() {
		localEntity = new compEntity(3);
		init();
	}
	public VBO getVBO() {
		return sprite.getVBO();
	}

	@Override
	public void init() {
		sprite = new VBOrect("/PPSH.png",1.0f,1.0f);
	}

	@Override
	public Vector3f getPosition() {
		return localEntity.getPosition();
	}

	@Override
	public Vector3f setPosition(Vector3f nPos) {
		return localEntity.setPosition(nPos);
	}

	

	@Override
	public void tick() {
	
		
	}
	@Override
	public int getRenderId() {
		return renderId;
	}
	@Override
	public void setRenderId(int a) {
		renderId=a;
		
	}
	@Override
	public int getEntityId() {
	return entityId;
		
	}
	@Override
	public void setEntityId(int a) {
		entityId=a;
		
	}
	@Override
	public ModelMatrix getModelMatrix() {
		Vector3f Pos = localEntity.getPosition();
		return new ModelMatrix(Pos.x, Pos.y, Pos.z, 0, 0, 0);
	}

}
