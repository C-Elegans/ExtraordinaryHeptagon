package com.C_Elegans.heptagon.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public final class DisplayManager {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	private static long lastFrameTime;
	private static float delta;
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setTitle("Extraordinary Heptagon");
			Display.create(new PixelFormat(), attribs);
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDepthFunc(GL11.GL_LEQUAL);
		lastFrameTime = getCurrentTime();

	}
	public static void updateDisplay(){
		Display.sync(200);
		Display.update();
		long  currentFrameTime= getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000.0f;
		lastFrameTime = currentFrameTime;
	}
	public static void closeDisplay(){
		Display.destroy();
	}
	public static float getFrameTimeSeconds(){
		return delta;
	}
	public static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
}
