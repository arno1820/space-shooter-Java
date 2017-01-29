package engine.core.gfx;

public class Light {
	
	private int color, radius, diameter;
	private int[] lightMap;
	
	public Light(int color, int radius) {

		this.color = color;
		this.radius = radius;
		this.diameter = radius * 2;
		
		lightMap = new int[diameter * diameter];
		
		for(int x = 0; x < diameter; x++){
			for(int y = 0; y < diameter; y++){
				float distance = (float)
								Math.sqrt( (x - radius) * (x - radius) + (y - radius) * (y-radius) );
				
				if(distance < radius){
					lightMap[x+y*diameter] = RGBCalc.getColorPower(color, 1 - distance/radius);
				}
				else{
					lightMap[x+y*diameter] = 0xff000000;
				}
			}
		}
	}
	
	public int getLightValue(int x, int y){
		
		if( x < 0 || x >= diameter || y < 0 ||  y >= diameter) return 0xff000000;
		else return lightMap[x+y*diameter];
		
	}

	public int getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}

	public int getDiameter() {
		return diameter;
	}

	public int[] getLightMap() {
		return lightMap;
	}

}
