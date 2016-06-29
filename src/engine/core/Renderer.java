package engine.core;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import engine.core.gfx.PixelAid;
import engine.core.gfx.ShadowType;
import engine.core.gfx.Image;
import engine.core.gfx.ImageTile;
import engine.core.gfx.Light;

public class Renderer {
	
	private int width, height;
	private int[] pixels;
	private int[] lightMap;
<<<<<<< HEAD
	private ShadowType[] shadowMap;
	private int ambientLight = 0xffffffff;
=======
	private int ambientLight = PixelAid.getPixelColor(1, 0.1f, 0.2f, 0.3f);
>>>>>>> origin/master
	
	public Renderer(GameController gc) {
		this.width = gc.getWidth();
		this.height = gc.getHeight();
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		lightMap = new int[pixels.length];
		shadowMap = new ShadowType[pixels.length];
	}
	
	//totaal geprogrameerd!
	public void setPixel(int x, int y, int color, ShadowType shadow){
		
		if((x < 0 || x >= width || y < 0 || y >= height) || color == 16777215) return;
		
		pixels[x+y*width] = color;
		shadowMap[x + y * width] = shadow;
	}
	
	public ShadowType getShadowMap(int x, int y){
		
		if(x<0 || x >= width || y < 0 || y >= height) return ShadowType.TOTAL;
		
		return shadowMap[x + y*width];
		
	}
	
	public void setLightMap(int x, int y, int color){
		
		if(x < 0 || x >= width || y < 0 || y >= height) return;
	
		lightMap[x+y*width] = color; //PixelAid.getMax(color, lightMap[x+y*width]);
	}

	public void drawImage(Image image, int offX, int offY){
		
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				setPixel(x + offX, y + offY, image.getPixels()[x+y*image.getWidth()], image.getShadowType());
			}
		}
		
	}
	
	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
		
		for(int x = 0; x < image.getTileWidth(); x++){
			for(int y = 0; y < image.getTileHeight(); y++){
				setPixel(x + offX, y + offY, image.getPixels()
						[(x+(tileX*image.getTileWidth())) + (y + (tileY *image.getTileHeight())*image.getWidth())],image.getShadowType());
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
				setPixel(x, y, PixelAid.getLightBlend(pixels[x+y*width], lightMap[x+y*width], ambientLight), shadowMap[x + y *width]);
			}
		}
	}
	
	public void drawLight(Light light, int offX, int offY){
		
		for(int i = 0; i <= light.getDiameter(); i++){
			drawLightLine(light.getRadius(), light.getRadius(), i, 0, light, offX, offY);
			drawLightLine(light.getRadius(), light.getRadius(), i, light.getDiameter(), light, offX, offY);
			drawLightLine(light.getRadius(), light.getRadius(), 0, i, light, offX, offY);
			drawLightLine(light.getRadius(), light.getRadius(), light.getDiameter(), i, light, offX, offY);
		}
	}
	
	//brenhensen line algorithm
	public void drawLightLine(int x0, int y0, int x1, int y1, Light light, int offX, int offY){
		
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		
		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;
		
		int err = dx - dy;
		int e2;
		
		while(true){
			
			
			if(light.getLightValue(x0, y0) == 0xff000000) break;

			int sx2 = x0 - light.getRadius() + offX;
			int sy2 = y0 - light.getRadius() + offY;
			
			setLightMap( sx2 , sy2, light.getLightValue(x0, y0));
			
			if(x0 == x1 && y0 == y1) break;
			if(getShadowMap(sx2, sy2) == ShadowType.TOTAL) break;
			
			
			e2 = 2*err;
			
			if(e2 > -1*dy){
				err -= dy;
				x0 += sx;
			}
			
			if(e2 < dx){
				err += dx;
				y0 += sy;
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

