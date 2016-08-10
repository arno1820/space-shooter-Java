package project_Beta.Unit;

import engine.core.Renderer;
import project_Beta.Position;

public abstract class Unit {
	
	int frontalSpeed = 0;
	int sidewiseSpeed = 0;
	int backwiseSpeed = 0;
	int health;
	Position position;

	public abstract void updateUnit(double dt);
	
	public abstract void renderUnit(Renderer r);
	
}
