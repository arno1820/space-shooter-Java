package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.ImageManager;

public class Play extends GameState {

	@Override
	public GameState updateGameState(GameController gc, float dt) {
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {

		r.drawImage(gc.getImageManager().getImage("/Backgrounds/Background_MENU.png"), 0, 0);

	}

	@Override
	public void make(ImageManager iM, GameState prev) {
		// TODO Auto-generated method stub
		
	}

}
