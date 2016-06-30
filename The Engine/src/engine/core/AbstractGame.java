package engine.core;

public abstract class AbstractGame {
	
	//static input, there are other ways, LOOK INTO THIS!
	public abstract void update(GameController gc, float dt);
	public abstract void render(GameController gc, Renderer r);
}
