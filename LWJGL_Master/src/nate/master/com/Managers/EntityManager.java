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
	}
	public void renderAll() {
		renderEnts();
		bullets.renderBullets();
	}

	
}
