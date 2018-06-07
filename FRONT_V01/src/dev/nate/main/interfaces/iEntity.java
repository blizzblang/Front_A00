package dev.nate.main.interfaces;

import org.joml.Vector3f;

public interface iEntity {
	Vector3f getPosition(); // Get position of Entity 
	Vector3f setPosition(Vector3f nPos); // Set Position of Entity
	public void tick(); // Called once per loop 
	public int getEntityId();
	public void setEntityId(int a);
	

}
