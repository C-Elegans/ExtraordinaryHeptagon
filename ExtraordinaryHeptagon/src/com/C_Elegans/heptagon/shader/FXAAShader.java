package com.C_Elegans.heptagon.shader;


public class FXAAShader extends ShaderProgram {
	private static final String VERTEX_FILE = "shaders/fxaaVertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/fxaaFragmentShader.txt";
	public FXAAShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texCoords");


	}

}
