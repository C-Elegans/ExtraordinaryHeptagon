package com.C_Elegans.heptagon.objects;

import org.lwjgl.util.vector.Vector2f;

import com.C_Elegans.heptagon.render.Loader;
import com.C_Elegans.heptagon.render.RawModel;

public class LetterBox{
	private RawModel model;
	private float x;
	private float y;
	private char letter;
	static float vertices[] = {
		0f,0.05f,-1f,
		0.04f,0.05f,-1f,
		0f,0f,-1f,
		0.04f,0.05f,-1f,
		0.04f,0f,-1f,
		0f,0f,-1f
		
	};
	static float texCoords[] = {
		0,0,
		.9f,0,
		0,1,
		.9f,0,
		.9f,1,
		0,1,
	};
	public LetterBox(float x, float y, char letter) {
		model = Loader.getLoader().loadToVAOT(vertices, texCoords);
		this.x = x;
		this.y = y;
		this.letter = letter;
		// TODO Auto-generated constructor stub
	}
	public RawModel getRawModel(){
		return model;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public Vector2f getPosition(){
		return new Vector2f(x,y);
	}
	public char getLetter() {
		return letter;
	}
	
	

}
