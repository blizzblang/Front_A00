package nate.master.com.VBOS;

import nate.master.com.Texture;

public class RectTex extends VBO {
		public RectTex(String path) {
			super(new Texture(path.trim()),new float[] {			       
					-0.5f,  0.5f, 0.0f,
			        -0.5f, -0.5f, 0.0f,
			         0.5f, -0.5f, 0.0f,
			         0.5f,  0.5f, 0.0f,
			         },
					new int[] {0, 1, 3, 3, 1, 2,} ,
					new float[] {
							/*
							0f,0f,
							1f,0f,
							1f,1f,
							0f,1f,
							*/
							
							
							0f,0f,
							
							0f,1f,
							1f,1f,
							1f,0f,
					});
		}

}
