package com.C_Elegans.heptagon.objects;

import org.lwjgl.util.vector.Vector2f;

public class Wall extends GameObject{
	private static final float TWOPI = (float) 2.0f *3.14159265f;
	
	
	static float[] vertices = {
			
			
			.1f,0.0f,-0.5f,
			.1f,TWOPI/7,-0.5f,
			0f,0.0f,-0.5f,
			.1f,TWOPI/7,-0.5f,
			0f,TWOPI/7,-0.5f,
			0f,0.0f,-0.5f,
	};
	public Wall(float radius,float theta){
		super(vertices, radius,theta * TWOPI/7f);
		 
	}
	@Override
	public Vector2f getTransformationVector(){
		return new Vector2f(super.radius,super.theta);
	}
	@Override
	public void changeRadius(float delta){
		super.radius += delta;
		
		if (radius < 0){
			destroy();
		}
	}
	public void destroy(){
		super.destroyed = true;
	}
	@Override
	public boolean checkCollision(PlayerSquare square){
		if(square.getTheta()>=this.theta && square.getTheta()<(this.theta + TWOPI/7)){
			if(this.getRadius() >=square.getRadius() && this.getRadius()<(square.getRadius() + PlayerSquare.HEIGHT)){
				System.out.println("Collision: " +this);
				return true;
			}
			
		}
		if((square.getTheta()+PlayerSquare.THETA_OFFSET)>=this.theta && (square.getTheta()+PlayerSquare.THETA_OFFSET)<(this.theta + TWOPI/7)){
			if(this.getRadius() >=square.getRadius() && this.getRadius()<(square.getRadius() + PlayerSquare.HEIGHT)){
				System.out.println("Collision: " +this);
				return true;
			}
			
		}
		return false;
	}
	
}
