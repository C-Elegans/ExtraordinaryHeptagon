package com.C_Elegans.heptagon.objects;

public class FullScreenTexture extends GameObject{
	static float vertices[] = {
		
		-1f,1f,0f,
		1f,1f,0f,
		-1f,-1f,0f,
		1f,1f,0f,
		1f,-1f,0f,
		-1f,-1f,0f
	};
	static float texCoords[] = {
		0,1,
		1,1,
		0,0,
		1,1,
		1,0,
		0,0,
	};
	public FullScreenTexture() {
		super(vertices, texCoords);
		// TODO Auto-generated constructor stub
	}

}
