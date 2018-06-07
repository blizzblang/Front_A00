package dev.nate.main.meta;

import dev.nate.main.gRuntime;
import dev.nate.main.interfaces.iEntity;
import dev.nate.main.interfaces.iRenderable;

/*
 * For code re-use
 */
public final class pEntity {
	public static int addiEntityToCm(iEntity a) {
		if(	gRuntime.CM.Ents.add(a)) {
			return gRuntime.CM.Ents.lastIndexOf(a);
		}
		return -1;
	}
	public static int addiRenderableToCm(iRenderable a) {
		if(	gRuntime.CM.Renderable.add(a)) {
			return gRuntime.CM.Renderable.lastIndexOf(a);
		}
		return -1;
	}
	public static void renderiRenderable(iRenderable a ) {
		gRuntime.mainRender.ren(a.getVBO(), a.getModelMatrix());
	}
}
