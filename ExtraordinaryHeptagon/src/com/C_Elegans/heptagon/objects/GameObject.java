package com.C_Elegans.heptagon.objects;
import org.lwjgl.util.vector.Vector2f;

import com.C_Elegans.heptagon.render.Loader;
import com.C_Elegans.heptagon.render.RawModel;
public class GameObject {
	private RawModel model;
	protected boolean destroyed = false;
	protected float radius;
	protected float theta;
	public GameObject(float[] vertices, float radius, float theta){
		model = Loader.getLoader().loadToVAO(vertices);
		this.theta = theta;
		this.radius = radius;
	}
	public GameObject(float[] vertices, float[] colors, float radius, float theta){
		model = Loader.getLoader().loadToVAO(vertices,colors);
		this.theta = theta;
		this.radius = radius;
	}
	public GameObject(float[] vertices, float[] texCoords){
		model = Loader.getLoader().loadToVAOT(vertices, texCoords);
		this.theta = 0;
		this.radius = 0;
	}
	public float getRadius() {
		return radius;
	}
	public float getTheta() {
		return theta;
	}
	public RawModel getRawModel(){
		return model;
	}
	public void update(){
		
	}
	public void changeRadius(float delta){
		
	}
	public Vector2f getTransformationVector(){
		return new Vector2f(radius,theta);
	}
	public boolean isDestroyed(){
		return destroyed;
	}
	public void changeTheta(float theta){
		
	}
	public boolean checkCollision(PlayerSquare square){
		return false;
	}
	
}
