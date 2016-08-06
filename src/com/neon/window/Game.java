package com.neon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.neon.Objects.Block;
import com.neon.Objects.Player;
import com.neon.framework.GameObject;
import com.neon.framework.KeyInput;
import com.neon.framework.ObjectId;

public class Game extends Canvas implements Runnable {
	private boolean running = false;
	private Thread thread;

	public static int WIDTH, HEIGHT;

	Handler handler;
	Camera cam;
	Player player;
	BufferedImage level;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		
		
		//player = new Player(64, 64, ObjectId.Player);
		//handler.addObject(player);
		//handler.createLevel();
		 
		loadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
		cam = new Camera(0,0 );
	}
	
	private void loadImageLevel(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int x = 0; x < width; x++){ //loops through each pixel
			for(int y = 0; y < height; y++){
				int pixel = image.getRGB(x, y);
				//Some Binary shit
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 & blue == 255){ //white pixel
					handler.addObject(new Block(x*32, y*32, ObjectId.Block));
				}
				if(red == 0 && green == 38 & blue == 255){
					System.out.println("player found");
					handler.addObject(new Player(x*32,y*32,ObjectId.Player));
				}
			}
		}
		
	}

	public synchronized void start() {
		if (running == true) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void run() {
		init();
		this.requestFocus();
		
		System.out.println("running");
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() { // repeatedly runs commands
		handler.tick();
		
		for(GameObject temp: handler.objectL){ // camera search and obtain for camera
			if(temp.getId() == ObjectId.Player){
				cam.tick(temp);
			}
		}
		
	}

	private void render() { // draws images onto the screen
		BufferStrategy bs = this.getBufferStrategy(); 
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////// Draw Here
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g2d.translate(-cam.getX(), -cam.getY());
		
		handler.render(g);
		
		g2d.translate(cam.getX(), cam.getY());
		///////////////////////////
		g.dispose();
		
		bs.show();
	}

	public static void main(String args[]) {
		new Window(800, 600, "Neon Platformer", new Game());
	}
}
