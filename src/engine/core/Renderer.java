package engine.core;

import java.awt.image.DataBufferByte;

public class Renderer {
	
	private int width, height;
	private byte[] pixels;
	
	public Renderer(GameController gc) {
		this.width = gc.getWidth();
		this.height = gc.getHeight();
		pixels = ((DataBufferByte) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	//totaal geprogrameerd!
	public void setPixel(int x, int y, float a, float r, float g, float b){
		
		if((x < 0 || x >= width || y < 0 || y >= height) || a == 0) return;
		
		int index = (x + y *width)*4;
		pixels[index] = (byte) ((a * 255f) + 0.5f);
		pixels[index + 1] = (byte) ((b * 255f) + 0.5f);
		pixels[index + 2] = (byte) ((g * 255f) + 0.5f);
		pixels[index + 3] = (byte) ((r * 255f) + 0.5f);
	}

	public void clear(){
		//TODO RGB INVERSED??
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++ ){
				setPixel(x, y, 1, 40, 238, 238);
			}
		}
	}
	
}

