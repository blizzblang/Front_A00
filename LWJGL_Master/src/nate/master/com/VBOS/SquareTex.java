package nate.master.com.VBOS;

import nate.master.com.Texture;
import nate.master.com.abstracts.VBO;

public class SquareTex extends VBO {
		public SquareTex() {
			super(new Texture("blueMan32x32.png"),new float[] {			       
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
