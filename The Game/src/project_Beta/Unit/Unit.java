package project_Beta.Unit;

import engine.core.Renderer;
import project_Beta.Position;

public abstract class Unit {
	
	int health;
	Position position;

	public abstract void updateUnit(double dt);
	
	public abstract void renderUnit(Renderer r);
	
}
