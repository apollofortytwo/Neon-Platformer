package com.neon.window;

import com.neon.framework.GameObject;

public class Camera {
	private float x, y;

	public Camera(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	public void tick(GameObject player) {
		//centers camera on player 
		x = player.getX() - Game.WIDTH/2;
		y = player.getY() - Game.HEIGHT/2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
