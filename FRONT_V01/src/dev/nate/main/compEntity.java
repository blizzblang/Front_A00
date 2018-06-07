package dev.nate.main;

import org.joml.Vector3f;

import dev.nate.main.interfaces.iEntity;

public class compEntity implements iEntity{
	Vector3f Position = new Vector3f();
	final int entityID;
	public compEntity(int a) {
		entityID=a;
	}
	@Override
	public Vector3f getPosition() {
		
		return Position;
	}

	@Override
	public Vector3f setPosition(Vector3f nPos) {
		Position = nPos;
		return Position;
	}
	@Override
	public void tick() {
		
	}
	@Override
	public int getEntityId() {
		return entityID;
	}
	@Override
	public void setEntityId(int a) {
		return;
	}

	
}
