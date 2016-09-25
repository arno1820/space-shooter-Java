package project_Beta;

import java.util.ArrayList;

import engine.core.GameController;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;
import project_Beta.Unit.Unit;

public class Position {

	private GameController gc;
	private double x;
	private double y;
	private double width;
	private double height;

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
		if(useDefaultBoundaries && (x < gc.getWidth()-width/4 && x > -width/4)){
			this.x = x;
		}else if(!useDefaultBoundaries && (x < Boundaries.get(1)-width/4 && x > Boundaries.get(0)-width/4)){
			this.x = x;
		}
	}

	public void setY(double y) {
		if(useDefaultBoundaries && (y < gc.getHeight()-height/2 && y > -height/2)){
			this.y = y;
		}else if(!useDefaultBoundaries && (y < Boundaries.get(3)-height/2 && y > Boundaries.get(2)-height/2)){
			this.y = y;
		}
	}

//	public void setPosition(double x, double y, Level level, Unit thisUnit){
//
//		this.setX(x, level, thisUnit);
//		this.setY(y, level, thisUnit);
//	}
//
//	public void setX(double x, Level level, Unit thisUnit) {
//		if(!(x > gc.getWidth()-width/4 || x < -width/4) || !useDefaultBoundaries){
//			this.x = x;
//		}else if(!useDefaultBoundaries && !(x > Boundaries.get(1)-width/4 || x < Boundaries.get(0)-width/4)){
//			this.x = x;
//		}
//	}
//
//	public void setY(double y, Level level, Unit thisUnit) {
//		if(!(y > gc.getHeight()-height/2 || y < -height/2) || !useDefaultBoundaries){
//			this.y = y;
//		}else if(!useDefaultBoundaries && !(x > Boundaries.get(3)-height/2 || y < Boundaries.get(2)-height/2)){
//			this.y = y;
//		}
//	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	//a hitBox is:
	//	 (1)+------------
	//		|			|
	//		|			|
	//		|			|
	//		------------+(2)
	// where the + is a 'hitbox-point' 
	//the return value == [X-location of (1)+, X-location of (2)+, Y-location of (1)+, Y-location of (2)+]
	public static double[] createHitbox(double x, double y, double width, double height){
		double[] hitbox = {x-width/2,x+width/2,y-height/2,y+height/2};
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
		if(faults == 2) return true;
		else return false;
		

	}

	public boolean checkHitBoxCollision(Level level, Unit thisUnit){

		ArrayList<Unit> units = level.getUnitList();

		for(Unit unit: units){
			if(unit == thisUnit){

			}else if(this.hitboxCollision(unit.getPosition().getHitbox())){
				return true;
			}
		}
		return false;

	}

}
