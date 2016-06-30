package project_Beta;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;

public class TitleScreen implements GameState {
	
	Image background = new Image("/Backgrounds/background.png");
	Image title = new Image("/Menu/Title.png");
	Image buttonStart = new Image("/Menu/Play_Button.png");
	Image buttonOptions = new Image("/Menu/Options_Button.png");
	Image buttonIndex = new Image("/Menu/Index_Button.png");
	Image buttonExit = new Image("/Menu/Exit_Button.png");
	SoundClip sound = new SoundClip("/");
	
	
	public void TileScreen(){}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 38 && Input.getMouseY() < 47)){
			buttonStart = new Image("/Menu/Play_ButtonHOVER.png");
			sound.play();
		}else{
			buttonStart = new Image("/Menu/Play_Button.png");
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 53 && Input.getMouseY() < 62)){
			buttonOptions = new Image("/Menu/Options_ButtonHOVER.png");
			sound.play();
		}else{
			buttonOptions = new Image("/Menu/Options_Button.png");
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 68 && Input.getMouseY() < 77)){
			buttonIndex = new Image("/Menu/Index_ButtonHOVER.png");
			sound.play();
		}else{
			buttonIndex = new Image("/Menu/Index_Button.png");
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 83 && Input.getMouseY() < 93)){
			buttonExit = new Image("/Menu/Exit_ButtonHOVER.png");
			sound.play();
		}else{
			buttonExit = new Image("/Menu/Exit_Button.png");
		}
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
