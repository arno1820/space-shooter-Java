package engine.core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private int width, height;
	private int[] pixels;
	private ShadowType shadowType = ShadowType.NONE;
	private String path;

	public String getPath() {
		return path;
	}

	protected Image(String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, this.width, this.height, null, 0, width);
		
		//to make shure its out of our RAM
		image.flush();
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public ShadowType getShadowType() {
		return shadowType;
	}

	public void setShadowType(ShadowType shadowType) {
		this.shadowType = shadowType;
	}
	

}
