package dev.nate.main;



import static org.lwjgl.glfw.GLFW.*;

public class Camera {
	private float Pos[] = new float[3];
	private float Rot[] = new float[3];

	boolean Active = true;
	private Object follow = null;
	float speed=0.01f;
	public Camera(float[] pos,float[] rot) {
	
		this.Pos = pos;
		this.Rot = rot;
	}
	public Camera() {
		this(new float[] {0,0,0},new float[] {0,0,0});
	}
	public ViewMatrix getViewMatrix() {
		return new ViewMatrix(Pos, Rot);
	}
	public void setActive() {Active=true;}
	public void setFollow(Object a) {
		follow = a;
	}

	public void update() {
		/*
		if(Active)
		if(follow==null) {
			boolean key_w = Runtime.game.getKey(GLFW_KEY_W);
			boolean key_a = Runtime.game.getKey(GLFW_KEY_A);
			boolean key_s = Runtime.game.getKey(GLFW_KEY_S);
			boolean key_d = Runtime.game.getKey(GLFW_KEY_D);
			boolean key_q = Runtime.game.getKey(GLFW_KEY_Q);
			boolean key_e = Runtime.game.getKey(GLFW_KEY_E);
			
			if(key_q) {
				Pos[2]+=speed;
			}
			if(key_e) {
				Pos[2]-=speed;
			}
			if(key_a) {
				Pos[0]+=speed;
			}
			if(key_d) {
				Pos[0]-=speed;
			}
			if(key_w) {
				Pos[1]-=speed;
			}
			if(key_s) {
				Pos[1]+=speed;
			}
		}
		*/
	}
	public float[] getPos() {
		return Pos;
	}
}
