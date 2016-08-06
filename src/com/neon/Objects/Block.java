package com.neon.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.neon.framework.GameObject;
import com.neon.framework.ObjectId;

public class Block extends GameObject {
	private int width = 32, height = 32;
	
	public Block(float x, float y, ObjectId id) {
		super(x, y, id);

	}
	
	//Draw graphic
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, width, height);
	}

	//used for collision detection with player
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

}
