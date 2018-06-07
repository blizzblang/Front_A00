package dev.nate.main;

import java.util.ArrayList;

import dev.nate.main.interfaces.iEntity;
import dev.nate.main.interfaces.iRenderable;
import dev.nate.main.meta.pEntity;


public class ComponentManager{
	/*TODO Make these object stacks for adding entites in cycles
	 * For now they'll be public
	 * 
	 */
	public ArrayList<iEntity> Ents = new ArrayList<iEntity>();
	public ArrayList<iRenderable> Renderable = new ArrayList<iRenderable>();
	

	public void iEntityTick() {
		for(int i=0;i<Ents.size();i++) {
			iEntity t = Ents.get(i);
			t.tick();
		}
	}
	
	public void drawiRenderable() {
		for(int i=0;i<Renderable.size();i++) {
			iRenderable t = Renderable.get(i);
			pEntity.renderiRenderable(t);
		}
	}
}
