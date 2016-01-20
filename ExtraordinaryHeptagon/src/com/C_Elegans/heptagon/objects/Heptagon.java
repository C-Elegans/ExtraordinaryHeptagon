package com.C_Elegans.heptagon.objects;


public class Heptagon extends GameObject{
	private static final float TWOPI = (float) 2.0f *3.14159265f;
	/*static float[] vertices = {
			.25f,0.0f,0.0f,
			0.0f,0.0f,0.0f,
			.25f,TWOPI/7,0.0f,
			.25f,TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,2*TWOPI/7,0.0f,
			.25f,2*TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,3*TWOPI/7,0.0f,
			.25f,3*TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,4*TWOPI/7,0.0f,
			.25f,4*TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,5*TWOPI/7,0.0f,
			.25f,5*TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,6*TWOPI/7,0.0f,
			.25f,6*TWOPI/7,0.0f,
			0.0f,0.0f,0.0f,
			.25f,0.0f,0.0f,
			
	};*/
	static float[] vertices = {
		.25f,0*TWOPI/7,-1.0f,
		.25f,TWOPI/7,-1.0f,
		0.2f,0*TWOPI/7,-1.0f,
		.25f,TWOPI/7,-1.0f,
		.2f,TWOPI/7,-1.0f,
		.2f,0*TWOPI/7,-1.0f,
		
		.25f,TWOPI/7,-1.0f,
		.25f,2*TWOPI/7,-1.0f,
		0.2f,TWOPI/7,-1.0f,
		.25f,2*TWOPI/7,-1.0f,
		.2f,2*TWOPI/7,-1.0f,
		.2f,TWOPI/7,-1.0f,
		
		.25f,2*TWOPI/7,-1.0f,
		.25f,3*TWOPI/7,-1.0f,
		0.2f,2*TWOPI/7,-1.0f,
		.25f,3*TWOPI/7,-1.0f,
		.2f,3*TWOPI/7,-1.0f,
		.2f,2*TWOPI/7,-1.0f,
		
		.25f,3*TWOPI/7,-1.0f,
		.25f,4*TWOPI/7,-1.0f,
		0.2f,3*TWOPI/7,-1.0f,
		.25f,4*TWOPI/7,-1.0f,
		.2f,4*TWOPI/7,-1.0f,
		.2f,3*TWOPI/7,-1.0f,
		
		.25f,4*TWOPI/7,-1.0f,
		.25f,5*TWOPI/7,-1.0f,
		0.2f,4*TWOPI/7,-1.0f,
		.25f,5*TWOPI/7,-1.0f,
		.2f,5*TWOPI/7,-1.0f,
		.2f,4*TWOPI/7,-1.0f,
		
		.25f,5*TWOPI/7,-1.0f,
		.25f,6*TWOPI/7,-1.0f,
		0.2f,5*TWOPI/7,-1.0f,
		.25f,6*TWOPI/7,-1.0f,
		.2f,6*TWOPI/7,-1.0f,
		.2f,5*TWOPI/7,-1.0f,

		.25f,6*TWOPI/7,-1.0f,
		.25f,0*TWOPI/7,-1.0f,
		0.2f,6*TWOPI/7,-1.0f,
		.25f,0*TWOPI/7,-1.0f,
		.2f,0*TWOPI/7,-1.0f,
		.2f,6*TWOPI/7,-1.0f,
	};
	public Heptagon(){
		super(vertices,0,0);
		
		
	}
	public void changeRadius(float delta){
		this.radius += delta;
	}
	public void setRadius(float radius){
		this.radius = radius;
	}
}
