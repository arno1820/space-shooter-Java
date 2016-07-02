package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;

public interface  GameState {
	
	public abstract GameState updateGameState(GameController gc, float dt);
	
	public abstract void renderGamestate(GameController gc, Renderer r);
	
}
