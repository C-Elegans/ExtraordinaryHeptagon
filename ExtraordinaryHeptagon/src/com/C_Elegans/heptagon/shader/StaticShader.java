package com.C_Elegans.heptagon.shader;


import java.awt.Color;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class StaticShader extends ShaderProgram{
	private static final String VERTEX_FILE = "shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/fragmentShader.txt";
	private int location_rotationMatrix;
	private int location_colorInput;
	private int location_transformationVector;
	private int location_colorChangeVector;
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void bindAttributes(){
		super.bindAttribute(0, "position");
	}
	@Override
	protected void getAllUniformLocations() {
		location_rotationMatrix = super.getUniformLocation("rotationMatrix");
		location_colorInput = super.getUniformLocation("colorInput");
		location_transformationVector = super.getUniformLocation("transformationVector");
		location_colorChangeVector = super.getUniformLocation("colorChangeVector");
	}
	public void uploadRotationMatrix(float rotationAmount){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		matrix.rotate((float) Math.toRadians(rotationAmount), new Vector3f(0,0,1));
		
		super.loadMatrix(location_rotationMatrix, matrix);
	}
	public void changeColor(Color color){
		
		super.loadVector(location_colorInput, new Vector3f(color.getRed()/255.0f,color.getGreen()/255.0f,color.getBlue()/255.0f));
	}
	public void uploadTransformationVector(Vector2f vector){
		super.loadVector2f(location_transformationVector, vector);
	}
	public void uploadColorChangeVector(Vector3f vector){
		super.loadVector(location_colorChangeVector, vector);
	}
	

}
