package com.C_Elegans.heptagon.shader;


import java.awt.Color;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class GuiShader extends ShaderProgram{
	private static final String VERTEX_FILE = "shaders/guiVertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/guiFragmentShader.txt";
	private int location_textureSampler;
	private int location_transformationMatrix;
	public GuiShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void bindAttributes(){
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texCoords");
	}
	@Override
	protected void getAllUniformLocations() {
		location_textureSampler = super.getUniformLocation("textureSampler");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	public void uploadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	

}
