package com.neon.window;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Loads images
public class BufferedImageLoader {
	private BufferedImage image;
	
	//uses a path to find an image and assigns it to an image variable 
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
}
