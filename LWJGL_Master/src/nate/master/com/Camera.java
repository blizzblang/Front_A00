package nate.master.com;

import nate.master.com.Matrixes.ViewMatrix;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {
	private float Pos[] = new float[3];
	private float Rot[] = new float[3];
	private ViewMatrix view;
	boolean Active = true;
	public Camera(float[] pos,float[] rot) {
		view = new ViewMatrix(pos, rot);
		this.Pos = pos;
		this.Rot = rot;
	}
	public Camera() {
		this(new float[] {0,0,0},new float[] {0,0,0});
	}
	public ViewMatrix getViewMatrix() {
		view = new ViewMatrix(this.Pos, this.Rot);
		return view;
	}
	public void setActive() {Active=true;}
	public void update() {
		if(Active) {
			boolean key_w = Runtime.game.getKey(GLFW_KEY_W);
			boolean key_a = Runtime.game.getKey(GLFW_KEY_A);
			boolean key_s = Runtime.game.getKey(GLFW_KEY_S);
			boolean key_d = Runtime.game.getKey(GLFW_KEY_D);
			boolean key_q = Runtime.game.getKey(GLFW_KEY_Q);
			boolean key_e = Runtime.game.getKey(GLFW_KEY_E);
			if(key_w) {
				Pos[2]+=0.1;
			}
			if(key_s) {
				Pos[2]-=0.1;
			}
			if(key_a) {
				Pos[0]+=0.1;
			}
			if(key_d) {
				Pos[0]-=0.1;
			}
			if(key_q) {
				Rot[1]-=0.1;
			}
			if(key_e) {
				Rot[1]+=0.1;
			}
		}
		
	}
}
