package project_Beta.Bullets;

import engine.core.GameController;
import engine.core.Renderer;
import project_Beta.Position;
import project_Beta.GameStates.Level;
import project_Beta.Unit.Unit;

public abstract class Bullet {
	
	Position position;
	Level level;
	GameController gc;
	Unit firedBy;
	
	public Bullet(int width, int height, double x, double y, Unit firedBy, Level level, GameController gc) {
		
		position = new Position(width, height, x, y,(double)-50, (double)gc.getWidth() + 50,(double)-50, (double)gc.getHeight()+50, gc);
		this.gc = gc;
		this.level = level;
		this.firedBy = firedBy;
	}
	
	public abstract void impact();
	
	/*
	 * Updates the bullet 
	 */
	public abstract void updateBullet(double dt);
	public abstract void renderBullet(Renderer r);
	public abstract void killBullet();

}
