package engine.core;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import engine.core.gfx.PixelAid;
import engine.core.gfx.Image;

public class Renderer {
	
	private int width, height;
	private int[] pixels;
	
	public Renderer(GameController gc) {
		this.width = gc.getWidth();
		this.height = gc.getHeight();
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	//totaal geprogrameerd!
	public void setPixel(int x, int y, int color){
		
		if(x < 0 || x >= width || y < 0 || y >= height) return;
		
		pixels[x+y*width] = color;
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
				//Hexadecimaal voor zwart: 0xAARRGGBB
				setPixel(x, y, 0xff000000);
			}
		}
	}
	
}

