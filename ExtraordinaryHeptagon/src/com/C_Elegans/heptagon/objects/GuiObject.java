package com.C_Elegans.heptagon.objects;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.C_Elegans.heptagon.render.Loader;

public class GuiObject extends GameObject{
	float x,y,width,height;
	int texture;
	static float[] vertices = {
			0,1,0,
			1,1,0,
			1,0,0,
			0,1,0,
			0,0,0,
			1,0,0,
			
	};
	static float texCoords[] = {
		0,0,
		1,0,
		1,1,
		0,0,
		0,1,
		1,1,
	};
	public GuiObject(float x, float y, float width, float height, String textureFile) {
		super(vertices,texCoords);
		//super(vertices, radius, theta);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = Loader.getLoader().loadTexture(textureFile);
				
		// TODO Auto-generated constructor stub
	}
	public Matrix4f getTransformationMatrix(){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		//matrix.translate(new Vector3f(.5f,.5f,0));
		matrix.scale(new Vector3f(width,height,1));
		matrix.m03 = x;
		matrix.m13 = y;
		return matrix;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public int getTexture() {
		return texture;
	}
	
}
