package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;

public class Exit implements GameState {

	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		System.out.println("Bye Bye :'(");
		System.exit(0);
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(gc.getImageManager().getImage("/Backgrounds/Background.png"), 0, 0);

	}

}
