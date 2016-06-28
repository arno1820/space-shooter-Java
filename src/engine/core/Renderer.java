package engine.core;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import engine.core.gfx.PixelAid;
import engine.core.gfx.Image;
import engine.core.gfx.ImageTile;
import engine.core.gfx.Light;

public class Renderer {
	
	private int width, height;
	private int[] pixels;
	private int[] lightMap;
	private int ambientLight = 0xffffffff;
	
	public Renderer(GameController gc) {
		this.width = gc.getWidth();
		this.height = gc.getHeight();
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		lightMap = new int[pixels.length];
	}
	
	//totaal geprogrameerd!
	public void setPixel(int x, int y, int color){
		
		if((x < 0 || x >= width || y < 0 || y >= height) || color == 16777215) return;
		
		pixels[x+y*width] = color;
	}
	
	public void setLightMap(int x, int y, int color){
	
		if(x < 0 || x >= width || y < 0 || y >= height) return;
	
		lightMap[x+y*width] = PixelAid.getMax(color, lightMap[x+y*width]);
	}

	public void drawImage(Image image, int offX, int offY){
		
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				setPixel(x + offX, y + offY, image.getPixels()[x+y*image.getWidth()]);
			}
		}
		
	}
	
	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
		
		for(int x = 0; x < image.getTileWidth(); x++){
			for(int y = 0; y < image.getTileHeight(); y++){
				setPixel(x + offX, y + offY, image.getPixels()
						[(x+(tileX*image.getTileWidth())) + (y + (tileY *image.getTileHeight())*image.getWidth())]);
			}
		}
		
	}
	
	public void clear(){
		//TODO RGB INVERSED??
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++ ){
				//Hexadecimaal voor zwart: 0xAARRGGBB
				pixels[x+y*width] = 0xff000000;
				lightMap[x+y*width] = ambientLight;
			}
		}
	}
	
	public void combineMaps(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++ ){
				setPixel(x, y, PixelAid.getLightBlend(pixels[x+y*width], lightMap[x+y*width], ambientLight));
			}
		}
	}
	
	public void drawLight(Light light, int offX, int offY){
		
		for(int x = 0; x < light.getDiameter(); x++){
			for(int y = 0; y < light.getDiameter(); y++){
				setLightMap(x + offX, y + offY, light.getLightMap()[x+y*light.getDiameter()]);
			}
		}
	}
	
	public int getAmbientLight() {
		return ambientLight;
	}
	
	public void setAmbientLight(int ambientLight) {
		this.ambientLight = ambientLight;
	}
	
}

