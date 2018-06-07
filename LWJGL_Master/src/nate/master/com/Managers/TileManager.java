package nate.master.com.Managers;

import nate.master.com.Camera;
import nate.master.com.Runtime;
import nate.master.com.abstracts.TileMap;
import nate.master.com.abstracts.ObjectStack;
import nate.master.com.abstracts.gEntity;


public class TileManager extends ObjectStack<TileMap>{
	final float[] startPos;
	final boolean centered;
	final int xLimit;
	final int yLimit;
	TileMap[][] tileMap;
	public TileManager(int a,int b,boolean c,float[] sp) {
		centered=c;
		xLimit=a;
		yLimit=b;
		if(!centered)
		startPos=sp;
		else
		startPos=new float[] {sp[0]-xLimit/2,sp[1]-yLimit/2,sp[2]};
		tileMap= new TileMap[xLimit][yLimit];
		
	}
	public void generateTileMap() {
		for(int x=0;x<xLimit;x++)
			for(int y=0;y<yLimit;y++) {
				float[] pR = new float[3];
				if(centered) {
					pR[0] = startPos[0]+x;
					pR[1] = startPos[1]+y;
					pR[2] = startPos[2];
				}
				else {
					pR[0] = startPos[0]+x;
					pR[1] = startPos[1]+y;
					pR[2] = startPos[2];
				}
				//System.out.println(x+" "+y);
				tileMap[x][y] = new TileMap(pR);
				
			}
		
	}
	@Override
	public void update() {
		sync();
		
	}
	public void tickTiles() {
		sync();
		if(getArray() !=null)
		for( TileMap ent : getArray()) {
			Tick(ent);
		

		}
	}
	public void renderTiles(Camera focus) {
		int radius=7;

		float cNTx = Math.abs(startPos[0]+focus.getPos()[0]); // Camera nearest Tile X
		int icNTx=(int) Math.round(cNTx);
		float cNTy = Math.abs(startPos[1]+focus.getPos()[1]);  // Camera nearest Tile Y
		int icNTy=(int) Math.round(cNTy);
		for(int x=0;x<radius;x++)
			for(int y=0;y<radius;y++) {
				Render(getWorldTile(icNTx+x-radius/2,icNTy+y-radius/2));
			}
		
	}
	private TileMap getWorldTile(int x,int y) {
		if(x < 0 || x >= xLimit || y < 0 || y >= yLimit) return null;
		return tileMap[x][y];
	}
	private void Render(TileMap a) {
		if(a!=null)
		Runtime.rending.ren(a.getVBO(),a.getLRS());
	}
	private void Tick(TileMap a) {
		a.ltick();
	}
}
