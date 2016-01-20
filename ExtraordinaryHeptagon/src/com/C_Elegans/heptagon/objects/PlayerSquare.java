package com.C_Elegans.heptagon.objects;

import org.lwjgl.util.vector.Vector2f;

public class PlayerSquare extends GameObject{
	
	private static final float TWOPI = (float) 2.0f *3.14159265f;
	public static final float THETA_OFFSET = TWOPI/42;
	public static final float HEIGHT = 0.04f;
	private float speed = 0;
	static float[] vertices = {
		0,0,0,
		HEIGHT,0,0,
		HEIGHT,THETA_OFFSET,0,
		0,0,0,
		0,THETA_OFFSET,0,
		HEIGHT,THETA_OFFSET,0,
	};
	public PlayerSquare(float radius,float theta){
		super(vertices,radius, theta * TWOPI/7f);
		
		
	}
	@Override
	public Vector2f getTransformationVector(){
		return new Vector2f(radius,theta);
	}
	@Override
	public void changeTheta(float thetaDelta){
		thetaDelta = (float) Math.toRadians(thetaDelta);
		super.theta += thetaDelta;
		if(theta <0){
			super.theta = super.theta + TWOPI;
		}
		if(theta >= TWOPI){
			super.theta -= TWOPI;
		}
	}
	public void changeSpeed(float delta){
		speed += delta;
	}
	public void update(){
		theta += speed;
		speed = speed *0.95f;
		if(theta <0){
			super.theta = super.theta + TWOPI;
		}
		if(theta >= TWOPI){
			super.theta -= TWOPI;
		}

	}
	
}
