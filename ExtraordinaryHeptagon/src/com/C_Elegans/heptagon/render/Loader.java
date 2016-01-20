package com.C_Elegans.heptagon.render;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Loader {
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	private static Loader loader;
	public Loader(){
		if(loader != null){
			System.err.println("CREATED MORE THAN ONE LOADER");
			System.exit(1);
		}
		loader = this;
	}
	public RawModel loadToVAO(float[] positions){
		int vaoID = createVAO();
		storeDataInAttributeList(0,3,positions);
		unbindVAO();
		return new RawModel(vaoID,positions.length);
		
	}
	public RawModel loadToVAOT(float[] positions,float[] texCoords){
		int vaoID = createVAO();
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,texCoords);
		unbindVAO();
		return new RawModel(vaoID,positions.length);
		
	}
	
	public RawModel loadToVAO(float[] positions, float[] colors){
		System.out.println(colors.length);
		System.out.println(positions.length);
		
		int vaoID = createVAO();
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,3,colors);
		unbindVAO();
		return new RawModel(vaoID,positions.length);
		
	}
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int textureId = texture.getTextureID();
		textures.add(textureId);
		return textureId;
	}
	public void cleanUp(){
		for(int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
	private int createVAO(){
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		vaos.add(vaoID);
		return vaoID;
	}
	private void storeDataInAttributeList(int attributeNumber,int dataSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dataSize, GL11.GL_FLOAT,  false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	public static Loader getLoader(){
		return loader;
	}
}

