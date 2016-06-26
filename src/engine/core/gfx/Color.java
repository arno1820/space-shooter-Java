package engine.core.gfx;

public class Color {

	public float a, r, g, b;
	
	public static Color Black = new Color(1, 0, 0, 0);
	
	public Color(float a, float r, float g, float b) {
		this.a = a;
		this.b = b;
		this.r = r;
		this.g = g;
	}

}
