package nate.master.com.Managers;

import org.joml.Vector3f;

import nate.master.com.Runtime;
import nate.master.com.abstracts.TileMap;
import nate.master.com.abstracts.Bullet;
import nate.master.com.abstracts.ObjectStack;
import nate.master.com.abstracts.gEntity;

public class EntityManager extends ObjectStack<gEntity>{
	private BulletManager bullets = new  BulletManager();
	private TileManager tiles = new TileManager(64,64,true,new float[] {0,0,-0.1f});
	public EntityManager() {
	}
	public void init() {
		tiles.generateTileMap();
	}
	public void tickEnts() {
		sync();
		if(getArray() !=null)
		for( gEntity ent : getArray()) {
			Tick(ent);
		}
	}
	public void renderEnts() {
		
		if(getArray() !=null)
		for( gEntity ent : getArray()) {
			Render(ent);
		}
	}
	private void Tick(gEntity a) {
		a.tick();
		Vector3f p = a.handleMotion();
		float dx=p.x;
		float dy=p.y;
		float dz=p.z;
		a.getLRS().addPos(dx, dy, dz);
	}
	private void Render(gEntity a) {
		Runtime.rending.ren(a.getVBO(),a.getLRS());
	}

	@Override
	public void update() {
		sync();
		
	}
	@Override
	public void sync() {
		if(ad != null) {
			//store.addAll(ad);
			for(int i=0;i<ad.size();i++) {
				if(ad.get(i) instanceof Bullet) {
					bullets.add((Bullet)ad.get(i));
				}
				else if(ad.get(i) instanceof TileMap) {
					tiles.add((TileMap) ad.get(i));
				}
				else {
					store.add(ad.get(i));
				}
			}
			ad.clear();
		}
			if(re!=null) {
				//store.removeAll(re);
				for(int i=0;i<ad.size();i++) {
					if(re.get(i) instanceof Bullet) {
						bullets.rem((Bullet)re.get(i));
					}
					else {
						store.remove(ad.get(i));
					}
				}
			re.clear();
		}
		bullets.sync();
	}
	public void tickAll() {
		tickEnts();
		bullets.tickBullets();
		tiles.tickTiles();
		//handleBulletCollisions();
	}
	public void renderAll() {
		
		tiles.renderTiles(Runtime.rending.getCamera());
		renderEnts();
		bullets.renderBullets();
	
	}
	public void checkForRemoval(gEntity a) {
		if(a.ttlEnabled()) {
			a.setTTL((float) (a.getTTl() - Runtime.game.getLastLoopTime()/1000));
			if(a.getTTl() <= 0) {
				rem(a);
				a.setTTL(0);
			}
		}
	}
	public void handleBulletCollisions() {
		for(int i=0;i<this.store.size();i++) {
			gEntity tempEntity = store.get(i);
			
			for(int j=0;j<bullets.getArray().size();j++) {
				Bullet b = bullets.getArray().get(i);
				float dX =(float) tempEntity.getLRS().getPos()[0]-b.getLRS().getPos()[0];
			//	System.out.println("dx: "+tempEntity.getLRS().getPos()[0]+" : "+b.getLRS().getPos()[0]);
				float dY =(float) tempEntity.getLRS().getPos()[1]-b.getLRS().getPos()[1];
				float dZ =(float) tempEntity.getLRS().getPos()[2]-b.getLRS().getPos()[2];
		//		System.out.println(dX +", "+dY+", "+ dZ);
				float dist = (float)( Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2));
				float check = (float) Math.pow(tempEntity.getRadius()+b.getRadius(),2) ;
				if(check > dist) {
					//TODO Fix bullet collision.
				}
				
					
			}
		}
	}
}
