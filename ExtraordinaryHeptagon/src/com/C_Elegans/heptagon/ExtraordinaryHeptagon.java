package com.C_Elegans.heptagon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.C_Elegans.heptagon.level.LevelCreater;
import com.C_Elegans.heptagon.objects.Background;
import com.C_Elegans.heptagon.objects.GameObject;
import com.C_Elegans.heptagon.objects.GuiObject;
import com.C_Elegans.heptagon.objects.Heptagon;
import com.C_Elegans.heptagon.objects.PlayerSquare;
import com.C_Elegans.heptagon.objects.Wall;
import com.C_Elegans.heptagon.render.DisplayManager;
import com.C_Elegans.heptagon.render.Loader;
import com.C_Elegans.heptagon.render.Renderer;

public class ExtraordinaryHeptagon {
	public static List<GameObject> objects;
	public static List<GuiObject> guiObjects;
	public static boolean gameOver = false;
	static float timeSinceLastUpdate = 0;
	public static long startTime;
	static Random r;
	static float levelChangeTime = 0;
	public static float timeSinceStart = 0;
	public static int gameMode = 2;
	private static int gameModeTemp = gameMode;
	static float accelSpeed;
	static GuiObject splashScreen;
	public static void main(String[] args) {
		
		timeSinceStart = 0;
		startTime = DisplayManager.getCurrentTime();
		r = new Random();
		accelSpeed = r.nextFloat() *.3f + 0.3f;
		System.out.println("accel: "+ accelSpeed);
		objects = new ArrayList<GameObject>();
		guiObjects = new ArrayList<GuiObject>();
		Loader loader = new Loader();
		LevelCreater levelCreater = new LevelCreater();
		
		DisplayManager.createDisplay();
		Renderer renderer = new Renderer();
		//splashScreen = new GuiObject(-.75f,-1,1.5f,2f,"splashScreen");
		
		
		Background background = new Background();
		
		Heptagon heptagon  = new Heptagon();
		PlayerSquare square = new PlayerSquare(.3f,0);
		
		guiObjects.add(new GuiObject(.75f,.85f,.25f,.15f,"numberBackground"));
		//guiObjects.add(splashScreen);
		objects.add(square);
		objects.add(heptagon);
		System.out.println(objects.size());
		
		while(!Display.isCloseRequested()){
			DisplayManager.updateDisplay();
			renderer.prepare();
			if(!gameOver){
				if(gameMode == 3){
					renderer.drawText(String.format("%.3f",accelSpeed), -.05f, -.05f);
				}
				renderer.render(objects, guiObjects, background);
				Iterator<GameObject> it = objects.iterator();
				while(it.hasNext()){
					GameObject object = it.next();
					if(object instanceof Wall){
						object.changeRadius(-.8f*DisplayManager.getFrameTimeSeconds());
						if(gameMode != 0){
							if(object.checkCollision(square)){

								gameMode = 0;
							}
						}
					}
					if(object.isDestroyed()){
						it.remove();
					}
				}
				if(gameMode != 0){
					renderer.drawText(String.format("%.1f", (DisplayManager.getCurrentTime() - startTime)/1000.0f),.8f,.9f);
					
				}
				checkInputs(square, heptagon);
				
				//heptagon.changeRadius((float) Math.pow((float) Math.sin(timeSinceStart*20),7)*.007f);
				heptagon.setRadius((float) ((Math.sin(timeSinceStart*5) + Math.sin(timeSinceStart*15)/3 + Math.sin(timeSinceStart*9*5)/9)*.02f));
				if(gameMode != 0) updateLevel(objects, levelCreater, background);
				timeSinceStart += DisplayManager.getFrameTimeSeconds();
				
			}
			
		}
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}
	private static void checkInputs(PlayerSquare square, Heptagon heptagon){
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			if(gameMode == 3){
				
				square.changeSpeed(DisplayManager.getFrameTimeSeconds()*accelSpeed);
			}
			else{
				square.changeTheta(DisplayManager.getFrameTimeSeconds()*360);
			}
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			if(gameMode == 3){
				
				square.changeSpeed(-DisplayManager.getFrameTimeSeconds()*accelSpeed);
			}
			else{
				square.changeTheta(-DisplayManager.getFrameTimeSeconds()*360);
			}
		}
		if(gameMode == 0&&Keyboard.isKeyDown(Keyboard.KEY_R)){
			gameMode = gameModeTemp;
			startTime = DisplayManager.getCurrentTime();
			objects.clear();
			objects.add(square);
			objects.add(heptagon);
			levelChangeTime = 0;
			System.out.println(gameMode);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			System.exit(1);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			gameMode = 1;
			gameModeTemp = 1;
			startTime = DisplayManager.getCurrentTime();
			objects.clear();
			objects.add(square);
			objects.add(heptagon);
			levelChangeTime = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			gameMode = 2;
			gameModeTemp = 2;
			startTime = DisplayManager.getCurrentTime();
			objects.clear();
			objects.add(square);
			objects.add(heptagon);
			levelChangeTime = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3)){
			gameMode = 3;
			gameModeTemp = 3;
			startTime = DisplayManager.getCurrentTime();
			objects.clear();
			objects.add(square);
			objects.add(heptagon);
			levelChangeTime = 0;
		}
		square.update();
		
	}
	private static void updateLevel(List<GameObject> objects, LevelCreater levelCreater, Background background){
		timeSinceLastUpdate += DisplayManager.getFrameTimeSeconds();
		if(timeSinceLastUpdate > levelChangeTime){
			
			levelChangeTime = levelCreater.nextLevel(objects,1);
			
			background.changeTheta(-1);
			timeSinceLastUpdate = 0;
			accelSpeed = r.nextFloat() *.2f + 0.2f;
		}
	}
	

}
