package nate.master.com.Managers;

import nate.master.com.Runtime;
import nate.master.com.abstracts.Bullet;
import nate.master.com.abstracts.ObjectStack;
import nate.master.com.abstracts.gEntity;

public class BulletManager extends ObjectStack<Bullet>{

	@Override
	public void update() {
		sync();
		
	}
	public void tickBullets() {
		sync();
		if(getArray() !=null)
		for( Bullet ent : getArray()) {
			Tick(ent);
			ent.getLRS().addPos(ent.getVelocity());

		}
	}
	public void renderBullets() {
		
		if(getArray() !=null)
		for( Bullet ent : getArray()) {
			Render(ent);
		}
	}
	private void Render(Bullet a) {
		Runtime.rending.ren(a.getVBO(),a.getLRS());
	}
	private void Tick(Bullet a) {
		a.ltick();
	}
}
