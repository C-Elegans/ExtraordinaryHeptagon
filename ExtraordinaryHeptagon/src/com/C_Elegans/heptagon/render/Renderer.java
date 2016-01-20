package com.C_Elegans.heptagon.render;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import org.lwjgl.util.vector.Vector3f;

import com.C_Elegans.heptagon.ExtraordinaryHeptagon;
import com.C_Elegans.heptagon.objects.Background;
import com.C_Elegans.heptagon.objects.FullScreenTexture;
import com.C_Elegans.heptagon.objects.GameObject;
import com.C_Elegans.heptagon.objects.GuiObject;
import com.C_Elegans.heptagon.objects.Heptagon;
import com.C_Elegans.heptagon.objects.LetterBox;
import com.C_Elegans.heptagon.objects.Wall;
import com.C_Elegans.heptagon.shader.BackgroundShader;
import com.C_Elegans.heptagon.shader.FXAAShader;
import com.C_Elegans.heptagon.shader.GuiShader;
import com.C_Elegans.heptagon.shader.StaticShader;
import com.C_Elegans.heptagon.shader.TextShader;

public class Renderer {
	StaticShader shader;
	private static float degreesPerSecond =50.0f;
	private float degrees = 0;
	private Vector3f hsvColor = new Vector3f(0,1,1);
	Random r;
	BackgroundShader bgShader;
	float timeSinceStart = 0;
	private int frameBuffer;
	private int frameBufferTexture;
	
	private int frameBufferDepthBuffer;
	private FXAAShader fxaaShader;
	private GuiShader guiShader;
	private FullScreenTexture fullScreen;
	private List<LetterBox> boxes;
	private TextShader textShader;
	private int fontTexture;
	private float timeToUpdate = 0;
	static float fps = 60;
	public Renderer(){
		 boxes = new ArrayList<LetterBox>();
		 bgShader = new BackgroundShader();
		 shader = new StaticShader();
		 r = new Random();
		 System.out.println("bgShader: " +bgShader.getProgramID()+" shader: "+ shader.getProgramID());
		 frameBuffer = this.createFrameBuffer();
		 frameBufferTexture = this.createTextureAttachment(Display.getWidth(), Display.getHeight());
		 System.out.println(Display.getHeight());
		 frameBufferDepthBuffer = this.createDepthBufferAttachment(Display.getWidth(), Display.getHeight());
		 GL11.glEnable(GL11.GL_DEPTH_TEST);
		 GL11.glDepthFunc(GL11.GL_LEQUAL);
		 this.unbindCurrentFrameBuffer();
		 fontTexture = Loader.getLoader().loadTexture("font");
		 fullScreen = new FullScreenTexture();
		 fxaaShader = new FXAAShader();
		 guiShader = new GuiShader();
		 textShader = new TextShader();
		 
	}
	public void prepare(){
		GL11.glClearColor(0, .1f, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		
	}
	public void render(List<GameObject> objects,List<GuiObject> guiObjects, Background background){
		degrees = degrees + degreesPerSecond * DisplayManager.getFrameTimeSeconds();
		Vector3f.add(hsvColor, new Vector3f(.1f*DisplayManager.getFrameTimeSeconds(),0,0), hsvColor);
		if(hsvColor.x >1){
			hsvColor.x = 0;
		}
		renderFPS();
		float change = (float) Math.abs(Math.sin(timeSinceStart*4))*0.2f;
		this.bindFrameBuffer(frameBuffer, Display.getWidth(), Display.getHeight());
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDepthFunc(GL11.GL_LEQUAL);
		this.renderBackground(background, change);
		this.renderGameObjects(objects, change);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		this.renderGui(guiObjects,change);
		
		this.renderText(change);
		
		this.bindFrameBuffer(0, Display.getWidth(), Display.getHeight());
		this.renderFXAA();
		this.timeSinceStart += DisplayManager.getFrameTimeSeconds();
		this.timeToUpdate += DisplayManager.getFrameTimeSeconds();
		boxes.clear();
	}
	private void renderBackground(Background background, float change){
		bgShader.start();
		bgShader.uploadRotationMatrix(degrees);
		bgShader.changeColor(new Color(Color.HSBtoRGB(hsvColor.x, hsvColor.y, hsvColor.z)));
		
		bgShader.uploadColorChangeVector(new Vector3f(change,change,change));
		GL30.glBindVertexArray(background.getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		shader.uploadTransformationVector(background.getTransformationVector());
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, background.getRawModel().getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		bgShader.stop();
		
	}
	private void renderGameObjects(List<GameObject> objects, float change){
		shader.start();
		shader.uploadRotationMatrix(degrees);
		shader.changeColor(new Color(  Color.HSBtoRGB(hsvColor.x, hsvColor.y, hsvColor.z)));
		shader.uploadColorChangeVector(new Vector3f(change*1.5f,change*1.5f,change*1.5f));
		for(GameObject object: objects){
			switch(ExtraordinaryHeptagon.gameMode){
			case 0:
				if(object instanceof Heptagon){
					renderObject(object);
				}
				break;
			case 1:
				renderObject(object);
			case 2:
				
				//System.out.println(object.getRadius());
				if((object.getRadius() >0.7f) || !(object instanceof Wall)){
					renderObject(object);
				}
				break;
			default:
				renderObject(object);
			}

			
			
		}
		shader.stop();
		
	}
	private void renderObject(GameObject object){
		RawModel model = object.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		
		shader.uploadTransformationVector(object.getTransformationVector());
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	private void renderGui(List<GuiObject> guis,  float change){
		guiShader.start();
		for(GuiObject object:guis){
			RawModel model = object.getRawModel();
			GL30.glBindVertexArray(model.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			guiShader.uploadTransformationMatrix(object.getTransformationMatrix());
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, object.getTexture());
			
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL30.glBindVertexArray(0);
		}
		guiShader.stop();
	}
	private void renderFXAA(){
		fxaaShader.start();
		
		GL30.glBindVertexArray(fullScreen.getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, frameBufferTexture);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, fullScreen.getRawModel().getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		fxaaShader.stop();
	}
	private void renderText(float change){
		textShader.start();
		if(boxes.isEmpty())return;
		for(LetterBox box:boxes){
		GL30.glBindVertexArray(box.getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fontTexture);
		textShader.loadLetter(box.getLetter());
		textShader.loadPosition(box.getPosition());
		textShader.changeColor(new Color(  Color.HSBtoRGB(hsvColor.x, hsvColor.y, hsvColor.z)));
		textShader.uploadColorChangeVector(new Vector3f(change*1.5f,change*1.5f,change*1.5f));
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, box.getRawModel().getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		}
		textShader.stop();
	}
	private int createFrameBuffer() {
        int frameBuffer = GL30.glGenFramebuffers();
        //generate name for frame buffer
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
        //create the framebuffer
        GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
        //indicate that we will always render to color attachment 0
        return frameBuffer;
    }
 
    private int createTextureAttachment( int width, int height) {
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height,
                0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0,
                texture, 0);
        return texture;
    }
     
    private int createDepthTextureAttachment(int width, int height){
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, width, height,
                0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT,
                texture, 0);
        return texture;
    }
 
    private int createDepthBufferAttachment(int width, int height) {
        int depthBuffer = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, width,
                height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT,
                GL30.GL_RENDERBUFFER, depthBuffer);
        return depthBuffer;
    }
    public void unbindCurrentFrameBuffer() {//call to switch to default frame buffer
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
    }
    private void bindFrameBuffer(int frameBuffer, int width, int height){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);//To make sure the texture isn't bound
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
        GL11.glViewport(0, 0, width, height);
    }
    public void drawText(String text,float x, float y){
    	
    	if(text.isEmpty()){
    		
    	}
    	for(int i=0;i<text.length();i++){
    		boxes.add(new LetterBox(x,y,text.charAt(i)));
    		x += 0.04f;
    	}
    	
    }
    private void renderFPS(){
    	
    	if(timeToUpdate >0.5f){
    		fps = 1/DisplayManager.getFrameTimeSeconds();
    		timeToUpdate = 0;
    	}
    	drawText(String.format("%.1f fps", fps),-.9f,.9f);
    }
    
}
