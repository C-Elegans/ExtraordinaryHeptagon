package com.C_Elegans.heptagon.level;

import java.util.List;
import java.util.Random;

import com.C_Elegans.heptagon.objects.GameObject;
import com.C_Elegans.heptagon.objects.Wall;

public class LevelCreater {
	private float[][][] levels = {
			{{1},{1,0},{1,1},{1,2}},
			{{1},{1,0},{1,2},{1,4},{1,6}},
			{{1},{1,0},{1,3}},
			{{1},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5}},
			{{1},{1,0},{1,1},{1,3},{1,4},{1,5}},
			
			{{4.5f},{1,0},{1,1},{1,3},{1,4},{1,5},//spiral
				 {1.5f,1},{1.5f,2},{1.5f,4},{1.5f,5},{1.5f,6},
				 {2,2},{2,3},{2,5},{2,6},{2,0},
				 {2.5f,3},{2.5f,4},{2.5f,6},{2.5f,0},{2.5f,1},
				 {3,4},{3,5},{3,0},{3,1},{3,2},
				 {3.5f,5},{3.5f,6},{3.5f,1},{3.5f,2},{3.5f,3},
				 {4,6},{4,0},{4,2},{4,3},{4,4},
				},
			{{2.0f}, {1,0},{1.1f,0},{1.2f,0},{1.3f,0},{1.4f,0},{1.5f,0}},
			{{2.0f},{1,0},{1,2},{1,4},{1,6},{1.5f,0},{1.5f,2},{1.5f,4},{1.5f,6},{2,0},{2,2},{2,4},{2,6}},
			{{4.0f},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{2,0},{2,1},{2,2},{2,4},{2,5},{2,6},{3,0},{3,1},{3,2},{3,3},{3,4},{3,5}},
			{{2.0f}, {1,0},{1.1f,0},{1.2f,0},{1.3f,0},{1.4f,0},{1.5f,0},
			{1,2},{1.1f,2},{1.2f,2},{1.3f,2},{1.4f,2},{1.5f,2},
			{1,4},{1.1f,4},{1.2f,4},{1.3f,4},{1.4f,4},{1.5f,4}},
	};
			
	
	
	Random r = new Random();
	public float nextLevel(List<GameObject> objects, float levelOffset){
		float[][] level = levels[r.nextInt(levels.length)];
		int offset = r.nextInt(7);
		for(int i = 1; i<level.length; i++){
			objects.add(new Wall(levelOffset+ level[i][0], (float)((int)level[i][1] + offset)%7));
		}
		return level[0][0];
	}
	
	
}
