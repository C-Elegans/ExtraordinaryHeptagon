package com.C_Elegans.heptagon.objects;


public class Background extends GameObject{
	private static final float TWOPI = (float) 2.0f *3.14159265f;
	private static float[] vertices = {
		
		
		2.0f,0.0f,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,TWOPI/7,0.0f,
		2.0f,TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,2*TWOPI/7,0.0f,
		2.0f,2*TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,3*TWOPI/7,0.0f,
		2.0f,3*TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,4*TWOPI/7,0.0f,
		2.0f,4*TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,5*TWOPI/7,0.0f,
		2.0f,5*TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,6*TWOPI/7,0.0f,
		2.0f,6*TWOPI/7,0.0f,
		0.0f,0.0f,0.0f,
		2.0f,0.0f,0.0f,
		
		.22f,0.0f,-1f,
		0.0f,0.0f,-1f,
		.22f,TWOPI/7,-1f,
		.22f,TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,2*TWOPI/7,-1f,
		.22f,2*TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,3*TWOPI/7,-1f,
		.22f,3*TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,4*TWOPI/7,-1f,
		.22f,4*TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,5*TWOPI/7,-1f,
		.22f,5*TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,6*TWOPI/7,-1f,
		.22f,6*TWOPI/7,-1f,
		0.0f,0.0f,-1f,
		.22f,0.0f,-1f,
		
		
	};
	private static float[] colors = {
		
		0.2f,0.2f,0.2f,
		0.2f,0.2f,0.2f,
		0.2f,0.2f,0.2f,
		0.1f,0.1f,0.1f,
		0.1f,0.1f,0.1f,
		0.1f,0.1f,0.1f,
		0.05f,0.05f,0.05f,
		0.05f,0.05f,0.05f,
		0.05f,0.05f,0.05f,
		0.25f,0.25f,0.25f,
		0.25f,0.25f,0.25f,
		0.25f,0.25f,0.25f,
		0.15f,0.15f,0.15f,
		0.15f,0.15f,0.15f,
		0.15f,0.15f,0.15f,
		0.35f,0.35f,0.35f,
		0.35f,0.35f,0.35f,
		0.35f,0.35f,0.35f,
		0.3f,0.3f,0.3f,
		0.3f,0.3f,0.3f,
		0.3f,0.3f,0.3f,
		
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		0,0,0,
		
			
			
			
	};
	public Background(){
		super(vertices,colors, 0, 0);
		System.out.println("Background Initialized");
	}
	@Override 
	public void changeTheta(float delta){
		delta = delta * TWOPI/7;
		theta += delta;
	}
}
