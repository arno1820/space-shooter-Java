package project_Beta.GameStates;

import java.util.ArrayList;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Button;

public class TitleScreen extends GameState {
	
	private Image background;
	private Image title;
	private ImageManager iM;
	
	private SoundClip sound = new SoundClip("/select.wav");
	
	public TitleScreen(){}
	
	@Override
	public void make(ImageManager iM, GameState prev) {
		
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		this.title = iM.getImage("/Menu/Title.png");
		
		super.buttonList.add(new Button("/Menu/Play_Button.png", "/Menu/Play_ButtonHOVER.png", "/Menu/Play_ButtonPRESS.png", sound, new Play(), 5, 38, iM));
		super.buttonList.add(new Button("/Menu/Options_Button.png", "/Menu/Options_ButtonHOVER.png", "/Menu/Options_ButtonPRESS.png", sound, new Options(), 5, 53, iM));
		super.buttonList.add(new Button("/Menu/Index_Button.png", "/Menu/Index_ButtonHOVER.png", "/Menu/Index_ButtonPRESS.png", sound, new Index(), 5, 68, iM));
		super.buttonList.add(new Button("/Menu/Exit_Button.png", "/Menu/Exit_ButtonHOVER.png", "/Menu/Exit_ButtonPRESS.png", sound, new Exit(), 5, 83, iM));
		
	}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		GameState next = updateButtons(super.buttonList, gc, dt);
		
		if(next != null) return next;
		
		return this;
		
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background, 0, 0);
		r.drawImage(title, 5, 5);
		renderButtons(buttonList, gc, r);
	}

}
