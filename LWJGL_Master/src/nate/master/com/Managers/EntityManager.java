package nate.master.com.Managers;

import nate.master.com.Runtime;
import nate.master.com.Entity.Bullet;
import nate.master.com.abstracts.ObjectStack;
import nate.master.com.abstracts.gEntity;

public class EntityManager extends ObjectStack<gEntity>{
	private BulletManager bullets = new  BulletManager();
	public EntityManager() {
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
		handleBulletCollisions();
	}
	public void renderAll() {
		renderEnts();
		bullets.renderBullets();
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
				
				}
				
					
			}
		}
	}
}
