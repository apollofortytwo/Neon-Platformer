package com.neon.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.neon.Objects.Block;
import com.neon.framework.GameObject;
import com.neon.framework.ObjectId;

public class Handler {

	public LinkedList<GameObject> objectL = new LinkedList<GameObject>();

	private GameObject tempObject;

	public void tick() {
		for (int i = 0; i < objectL.size(); i++) {
			tempObject = objectL.get(i);

			tempObject.tick(objectL);
		}
	}
	

	public void render(Graphics g) {
		for (GameObject temp: objectL) {
			temp.render(g);
		}
	}

	public void addObject(GameObject object) {
		objectL.add(object);
	}

	public void removeObject(GameObject object) {
		objectL.remove(object);
	}

	public void createLevel() {
		for (int xx = 0; xx < Game.WIDTH * 2 ; xx += 32) {
			addObject(new Block(xx, Game.HEIGHT - 32, ObjectId.Block));
		}
		
		for (int xx = 200; xx < 600 ; xx += 32) {
			addObject(new Block(xx, 400, ObjectId.Block));
		}
		
		for (int yy = 0; yy < Game.HEIGHT + 32 ; yy += 32) {
			addObject(new Block(0,yy, ObjectId.Block));
		}
		
		
		
		
	}

}
