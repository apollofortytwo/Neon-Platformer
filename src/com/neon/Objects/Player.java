package com.neon.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.neon.framework.GameObject;
import com.neon.framework.ObjectId;

public class Player extends GameObject {

	private float width = 32, height = 64;
	private float gravity = 0.4f;
	private float MAX_SPEED = 10;

	public Player(float x, float y, ObjectId id) {
		super(x, y, id);

	}

	public void tick(LinkedList<GameObject> object) {
		//apply velocities to position
		x += velX;
		y += velY;

		if (falling || jumping) { // applying gravity to y-position
			velY += gravity;

			if (velY >= MAX_SPEED) { //setting maximum gravitational velocity 
				velY = MAX_SPEED;
			}
		}
		
		collision(object);//checking for any collisions with player object
		System.out.println(jumping);
	}

	private void collision(LinkedList<GameObject> object) {
		for (GameObject temp : object) {
			if (temp.getId() == ObjectId.Block) {
				if (getBottomBounds().intersects(temp.getBounds())) { //Checks collision with bottom 
					setY(temp.getY() - height);
					setVelY(0);
					jumping = false;
				}
				if (getTopBounds().intersects(temp.getBounds())) {//Checks collision with top
					setY(temp.getY() + height / 2);
					this.setVelY(0);
				}
				if (getLeftBounds().intersects(temp.getBounds())) {//Checks collision with left
					setX(temp.getX() + width);
					
				}
				if (getRightBounds().intersects(temp.getBounds())) {//Checks collision with right 
					setX(temp.getX() - width);
					
				}
			}
		}

	}

	//draw graphics
	public void render(Graphics g) {
		//Player graphic
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, (int) width, (int) height);

		//Convert
		Graphics2D g2d = (Graphics2D) g;

		//Detection Borders
		g.setColor(Color.RED);
		g2d.draw(getBottomBounds());
		g2d.draw(getLeftBounds());
		g2d.draw(getTopBounds());
		g2d.draw(getRightBounds());
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) ((int) x + (width / 5)), (int) ((int) y + (height / 2)),
				(int) ((int) width - (width / 5) * 2), (int) height / 2);
	}

	public Rectangle getTopBounds() {
		return new Rectangle((int) ((int) x + (width / 5)), (int) y, (int) ((int) width - (width / 5) * 2),
				(int) height / 2);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) ((int) y + (width / 5)), (int) width / 5,
				(int) ((int) height - (height / 10) * 2));
	}

	public Rectangle getRightBounds() {
		return new Rectangle((int) ((int) x + (width - (width / 5))), (int) ((int) y + (width / 5)), (int) width / 5,
				(int) ((int) height - (height / 10) * 2));
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

}
