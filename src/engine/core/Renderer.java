package engine.core;

import java.awt.image.DataBufferByte;

import engine.core.gfx.Color;
import engine.core.gfx.Image;

public class Renderer {
	
	private int width, height;
	private byte[] pixels;
	
	public Renderer(GameController gc) {
		this.width = gc.getWidth();
		this.height = gc.getHeight();
		pixels = ((DataBufferByte) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	//totaal geprogrameerd!
	public void setPixel(int x, int y, Color c){
		
		if((x < 0 || x >= width || y < 0 || y >= height) || c.a == 0) return;
		
		int index = (x + y *width)*4;
		pixels[index] = (byte) ((c.a * 255f) + 0.5f);
		pixels[index + 1] = (byte) ((c.b * 255f) + 0.5f);
		pixels[index + 2] = (byte) ((c.g * 255f) + 0.5f);
		pixels[index + 3] = (byte) ((c.r * 255f) + 0.5f);
	}

	public void drawImage(Image image, int offX, int offY){
		
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				setPixel(x + offX, y + offY, image.getPixels()[x+y*image.getWidth()]);
			}
		}
		
	}
	
	public void clear(){
		//TODO RGB INVERSED??
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++ ){
				// DO NOT! setPixel(x,y, new Color(a,r,g,b) !!!!!
				setPixel(x, y, Color.Black);
			}
		}
	}
	
}

