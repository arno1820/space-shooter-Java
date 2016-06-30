package project_Beta;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;

public class TitleScreen implements GameState {
	
	Image background = new Image("/Backgrounds/background.png");
	Image title = new Image("/Menu/Title.png");
	Image buttonStart = new Image("/Menu/Play_Button.png");
	Image buttonOptions = new Image("/Menu/Options_Button.png");
	Image buttonIndex = new Image("/Menu/Index_Button.png");
	Image buttonExit = new Image("/Menu/Exit_Button.png");
	
	
	public void TileScreen(){}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		return this;
		
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background, 0, 0);
		r.drawImage(title, 5, 5);
		r.drawImage(buttonStart, 5, 38);
		r.drawImage(buttonOptions, 5, 53);
		r.drawImage(buttonIndex, 5, 68);
		r.drawImage(buttonExit, 5, 83);
	}

}
