package engine.core.gfx;

public class PixelAid {
	
	
	//what this(getRed) does for example: 01100101 00101010 01101111 0101101 (maybe incorrect color, but who cares... :p)
	//0xAARRGGBB				        	  A		  R			G		B
	//(color >> 16)                       01100101 00101010    -         -    
	// 0xff & ...				          00000000 00000000	00000000 00101010(R)
	public static float getRed(int color){
		return (0xff &(color >> 16)) /255f;
	}
	public static float getGreen(int color){
		return (0xff &(color >> 8)) /255f;
	}
	public static float getBlue(int color){
		return (0xff & color) /255f;
	}
	
	public static float getAlpha(int color){
		return (0xff &(color >> 24)) /255f;
	}
	
	public static int getPixelColor(float a, float r, float g, float b){
		return ((int)(r * 255f + 0.5f) << 16 |
				(int)(g * 255f + 0.5f) << 8 |
				(int)(b * 255f + 0.5f));
		//(int)(a * 255f + 0.5f) << 24 |
	}
	
	public static int getColorPower(int color, float power){
		
		//'getAlpha(color) * power' ipv '1'?
		
		return getPixelColor(1,
						getRed(color) * power,
						getGreen(color) * power,
						getBlue(color) * power);
		
	}
	
	public static int getLightBlend(int color, int light, int ambientLight){
		
		//float a = getAlpha(light);
		float r = getRed(light);
		float g = getGreen(light);
		float b = getBlue(light);
		
		//if(a < getAlpha(ambientLight)) a = getAlpha(ambientLight);
		if(r < getRed(ambientLight)) r = getRed(ambientLight);
		if(g < getGreen(ambientLight)) g = getGreen(ambientLight);
		if(b < getBlue(ambientLight)) b = getBlue(ambientLight);
		
		return getPixelColor(1, r * getRed(color), g*getGreen(color), b*getBlue(color));
	}
	
	public static int getMax(int color0, int color1){
		return getPixelColor(Math.max(getAlpha(color0),getAlpha(color1)),
				Math.max(getRed(color0),getRed(color1)),
				Math.max(getGreen(color0),getGreen(color1)),
				Math.max(getBlue(color0),getBlue(color1)));
	}
	
}
