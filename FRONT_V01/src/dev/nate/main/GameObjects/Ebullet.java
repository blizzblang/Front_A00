package dev.nate.main.GameObjects;

import org.joml.Vector3f;

import dev.nate.main.ModelMatrix;
import dev.nate.main.VBO;
import dev.nate.main.VBOrect;
import dev.nate.main.compEntity;
import dev.nate.main.gRuntime;
import dev.nate.main.interfaces.iEntity;
import dev.nate.main.interfaces.iManaged;
import dev.nate.main.interfaces.iRenderable;

public class Ebullet implements iEntity, iRenderable, iManaged{
	int entityId=-1;
	int renderId=-1;
	compEntity localEntity;
	Vector3f Vel = new Vector3f(0,0,0);
	VBOrect sprite;
	
	public Ebullet(Vector3f Pos, Vector3f vel) {
		localEntity = new compEntity(3);
		Vel=vel;
		init();
	}
	public VBO getVBO() {
		return sprite.getVBO();
	}

	@Override
	public void init() {
		sprite = new VBOrect("/bulletBase.png",1.0f,1.0f);
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
		getPosition().add(Vel);
		
	}
	@Override
	public int addToManager() {
		gRuntime.CM.Ents.add(this);
		entityId = gRuntime.CM.Ents.lastIndexOf(this);
		gRuntime.CM.Renderable.add(this);
		renderId = gRuntime.CM.Renderable.lastIndexOf(this);
		return 0;
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
