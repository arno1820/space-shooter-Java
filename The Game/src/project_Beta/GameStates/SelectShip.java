package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.ImageManager;

public class SelectShip extends GameState {

	private ImageManager iM;
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		GameState ngs = new Level();
		ngs.make(iM, this);
		return ngs;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {

		r.drawImage(gc.getImageManager().getImage("/Backgrounds/Background_MENU.png"), 0, 0);

	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {
		this.iM = iM;
		
	}

}
