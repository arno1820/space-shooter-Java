package project_Beta.Unit;

import engine.core.Renderer;
import project_Beta.Position;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;

public abstract class Unit {
	
	int frontalSpeed = 0;
	int sidewiseSpeed = 0;
	int backwiseSpeed = 0;
	int health;
	Position position;
	Level level;

	public abstract void updateUnit(double dt);
	
	public abstract void renderUnit(Renderer r);
	
	public abstract void hit();
	
	public Position getPosition(){
		return this.position;
	}
	
}
