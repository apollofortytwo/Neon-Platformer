package com.neon.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.neon.window.Handler;

public class KeyInput implements KeyListener {
	Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		for (GameObject temp : handler.objectL) {
			if (temp.getId() == ObjectId.Player) {
				if (code == KeyEvent.VK_LEFT)temp.setVelX(-5);
				if (code == KeyEvent.VK_RIGHT)temp.setVelX(+5);
				if(!temp.jumping){
					if (code == KeyEvent.VK_UP)temp.setVelY(-15);
					temp.jumping = true;
					return;
				}
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		for (GameObject temp : handler.objectL) {
			if (temp.getId() == ObjectId.Player) {
				if (code == KeyEvent.VK_LEFT) {
					temp.setVelX(0);
				}
				if (code == KeyEvent.VK_RIGHT) {
					temp.setVelX(0);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
