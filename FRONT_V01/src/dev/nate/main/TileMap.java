package dev.nate.main;

import org.joml.Vector3f;

import dev.nate.main.GameObjects.ETile;
import dev.nate.main.meta.pEntity;



public class TileMap {
	ETile[][] Map;
	int Height=0;
	int Width=0;
	Vector3f startPos = new Vector3f(0,0,0);
	boolean centered=true;
	public TileMap(int w,int h) {
		Map = new ETile[w][h];
		Height=h;
		Width=w;
		if(centered)
			startPos = new Vector3f(startPos.x-Width/2,startPos.y-Height/2,startPos.z);
	}
	public void generateMap() {
		for(int x=0;x<Width;x++)
			for(int y=0;y<Height;y++) {
				Map[x][y] = new ETile();
				Map[x][y].setPosition(new Vector3f(startPos.x + x,startPos.y+y,startPos.z-0.1f));
			}
	}
	public void renderAll(Camera a ) {
		int radius=7;

		float cNTx = Math.abs(startPos.x+a.getPos()[0]); // Camera nearest Tile X
		int icNTx=(int) Math.round(cNTx);
		float cNTy = Math.abs(startPos.y+a.getPos()[1]);  // Camera nearest Tile Y
		int icNTy=(int) Math.round(cNTy);
		
		for(int x=0;x<radius;x++)
			for(int y=0;y<radius;y++) {
				ETile t = getWorldTile(icNTx+x-radius/2,icNTy+y-radius/2);
				if(t!=null) {
					pEntity.renderiRenderable(t);
					//t.render();
				}
				
			}
	}
	private ETile getWorldTile(int x,int y) {
		
		if(x < 0) return null;
		if(y < 0) return null;
	
		return Map[x][y];
	}
}
