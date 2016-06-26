package engine.core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private int width, height;
	private Color[] pixels;
	
	public Image(String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		int[] p = image.getRGB(0, 0, this.width, this.height, null, 0, width);
		pixels = new Color[p.length];
		
		for(int i = 0; i < p.length; i++){
			//Bit shifting, stored as: 0xAARRGGBB. 0xff -> store it as an int from 0 to 255, so devide it by 255 as a float
			pixels[i] = new Color((0xff & (p[i] >> 24)) / 255f,
								   (0xff & (p[i] >> 16)) / 255f,
								   (0xff & (p[i] >> 8)) / 255f,
								   (0xff & (p[i])) / 255f);
		}
		
		//to make shure its out of our RAM
		image.flush();
		
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

	public Color[] getPixels() {
		return pixels;
	}

	public void setPixels(Color[] pixels) {
		this.pixels = pixels;
	}
	

}
