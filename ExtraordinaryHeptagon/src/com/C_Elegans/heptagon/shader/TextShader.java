package com.C_Elegans.heptagon.shader;

import java.awt.Color;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


public class TextShader extends ShaderProgram {
	private static final String VERTEX_FILE = "shaders/textVertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/textFragmentShader.txt";
	int location_letterOffset;
	private int location_colorInput;
	private int location_colorChangeVector;
	String letters[] = {
			"1234567890abc",
			"defghijklmnop",
			"qrstuvwxyzABC",
			"DEFGHIJKLMNOP",
			"QRSTUVWXYZ!;%",
			":?*()–+-=.,/|",
			"\"\'@#$^&{}[] `",
	};
	private int location_positionOffset;
	public TextShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		this.location_letterOffset = super.getUniformLocation("letterOffset");
		location_colorInput = super.getUniformLocation("colorInput");
		location_colorChangeVector = super.getUniformLocation("colorChangeVector");
		location_positionOffset = super.getUniformLocation("positionOffset");
	}	
	public void letterOffset(Vector2f offset){
		super.loadVector2f(location_letterOffset, offset);
	}
	public void loadLetter(char letter){
		super.loadVector2f(location_letterOffset, this.getLocationOfLetter(letter));
	}
	public void changeColor(Color color){
		
		super.loadVector(location_colorInput, new Vector3f(color.getRed()/255.0f,color.getGreen()/255.0f,color.getBlue()/255.0f));
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texCoords");


	}
	public void uploadColorChangeVector(Vector3f vector){
		super.loadVector(location_colorChangeVector, vector);
	}
	public void loadPosition(Vector2f position){
		super.loadVector2f(location_positionOffset, position);
	}
	private Vector2f getLocationOfLetter(char letter){
		letter = (char) (letter - ' ');
		return new Vector2f(letter%14,letter/14);
	}

}
