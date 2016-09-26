package project_Beta;

import java.util.ArrayList;

import com.sun.prism.Image;

import engine.core.GameController;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;
import project_Beta.Unit.Unit;

public class Position {

	private GameController gc;
	private double x;
	private double y;
	private double width = 0;
	private double height = 0;

	private boolean useDefaultBoundaries;
	private ArrayList<Double> Boundaries =  new ArrayList<Double>();

	public Position(double x, double y, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
		useDefaultBoundaries = true;
	}

	public Position(double width, double height, double x, double y, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.height = height;
		this.width = width;
		useDefaultBoundaries = true;
	}
	
	public Position(double x, double y, Double minX, Double maxX, Double minY, Double maxY, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
		Boundaries.add(0, minX);
		Boundaries.add(1, maxX);
		Boundaries.add(2, minY);
		Boundaries.add(3, maxY);
		useDefaultBoundaries = false;
	}

	public Position(double width, double height, double x, double y, Double minX, Double maxX, Double minY, Double maxY, GameController gc){
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.height = height;
		this.width = width;
		Boundaries.add(0, minX);
		Boundaries.add(1, maxX);
		Boundaries.add(2, minY);
		Boundaries.add(3, maxY);
		useDefaultBoundaries = false;
	}


	public void setPosition(double x, double y){

		this.setX(x);
		this.setY(y);
	}

	public void setX(double x) {
		if(useDefaultBoundaries && (x < gc.getWidth()-width && x > 0)){
			this.x = x;
		}else if(!useDefaultBoundaries && (x < Boundaries.get(1)-width && x > Boundaries.get(0)-width/4)){
			this.x = x;
		}
	}

	public void setY(double y) {
		if(useDefaultBoundaries && (y < gc.getHeight()-height && y > 0)){
			this.y = y;
		}else if(!useDefaultBoundaries && (y < Boundaries.get(3)-height/2 && y > Boundaries.get(2)-height/2)){
			this.y = y;
		}
	}

	//TODO: kleine afwijking op de hitbox, reden in nog niet duidelijk!
	//a hitBox is:
	//	 (1)+------------
	//		|			|
	//		|	    	|
	//		|			|
	//		------------+(2)
	// where the + is a 'hitbox-point' 
	//the return value == [X-location of (1)+, X-location of (2)+, Y-location of (1)+, Y-location of (2)+]
	public static double[] createHitbox(double x, double y, double width, double height){
		double[] hitbox = {x,x+width,y,y+height};
		return hitbox;
	}

	public double[] getHitbox(){
		return createHitbox(this.x, this.y, this.width, this.height);
	}

	public boolean hitboxCollision(double[] hitbox){

		double[] thisHitbox = this.getHitbox();
		int faults = 0;
		
		if(!(hitbox[0] > thisHitbox[1] || hitbox[1] < thisHitbox[0])) faults +=1;
		if(!(hitbox[2] > thisHitbox[3] || hitbox[3] < thisHitbox[2])) faults +=1;
		if(faults == 2) {
			return true;
		}
		else return false;
		
	}

	public boolean checkHitBoxCollision(Level level, Object thisUnit){

		ArrayList<Unit> units = level.getUnitList();

		for(Unit unit: units){
			if(unit == thisUnit){

			}else if(this.hitboxCollision(unit.getPosition().getHitbox())){
				return true;
			}
		}
		return false;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isUseDefaultBoundaries() {
		return useDefaultBoundaries;
	}

	public void setUseDefaultBoundaries(boolean useDefaultBoundaries) {
		this.useDefaultBoundaries = useDefaultBoundaries;
	}

	public ArrayList<Double> getBoundaries() {
		return Boundaries;
	}

	public void setBoundaries(ArrayList<Double> boundaries) {
		Boundaries = boundaries;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
