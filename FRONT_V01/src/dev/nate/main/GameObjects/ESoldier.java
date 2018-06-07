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
import dev.nate.main.meta.pEntity;

import static org.lwjgl.glfw.GLFW.*;

public class ESoldier implements iEntity, iRenderable, iManaged {
	int entityId=-1;
	int renderId=-1;
	compEntity localEntity;
	EGun PPSH;
	VBOrect sprite;
	
	public ESoldier() {
		localEntity = new compEntity(1);
		PPSH = new EGun();
		init();
	}
	public VBO getVBO() {
		return sprite.getVBO();
	}

	@Override
	public void init() {
		sprite = new VBOrect("/blueMan32x32.png",1.0f,1.0f);
		pEntity.addiRenderableToCm(PPSH);
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
		Vector3f Pos = localEntity.getPosition();
		Vector3f gunPosition =new Vector3f(Pos.x-0.5f, Pos.y, Pos.z);
		PPSH.setPosition(gunPosition);
		
		boolean kw = gRuntime.game.getKeyUp(GLFW_KEY_W);
		if(kw) {
			Ebullet t = new Ebullet(getPosition(), new Vector3f(0,-0.05f,0));
			t.addToManager();
		}
			
	}
	@Override
	public int addToManager() {
		entityId = pEntity.addiEntityToCm(this);
		renderId = pEntity.addiRenderableToCm(this);
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
