package project_Beta;

import engine.core.GameController;

public class Position {

	private GameController gc;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Position(double x, double y, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
	}
	
	public Position(double width, double height, double x, double y, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.height = height;
		this.width = width;
	}
	
	public void setPosition(double x, double y){
		
		this.setX(x);
		this.setY(y);
	}

	public void setX(double x) {
		if(!(x > gc.getWidth()-width/2 || x < -width/4)){
			this.x = x;
		}
	}

	public void setY(double y) {
		if(!(y > gc.getHeight()-height/2 || y < -height/2)){
			this.y = y;
		}
	}
	
	public void setPositionOutside(double x, double y){
		
		this.setXOutside(x);
		this.setYOutside(y);
	}

	public void setXOutside(double x) {
		this.x = x;
	}

	public void setYOutside(double y) {
		this.y = y;
	}
	

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
}
