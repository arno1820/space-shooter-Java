package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.ImageManager;

public class Exit extends GameState {

	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		System.out.println("Bye Bye :'(");
		System.exit(0);
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(gc.getImageManager().getImage("/Backgrounds/Background_MENU.png"), 0, 0);

	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {}

}
