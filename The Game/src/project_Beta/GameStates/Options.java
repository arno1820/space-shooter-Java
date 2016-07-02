package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Button;

public class Options implements GameState { 

	private Button Xbutton;
	private SoundClip sound = new SoundClip("/select.wav");
	private ImageManager iM;
	
	public Options(ImageManager iM, GameState prev) {
		
		this.iM = iM;
		Image temp = iM.getImage("/Menu/Cross.png");
		int width = temp.getWidth();
		this.Xbutton = new Button("/Menu/Cross.png", "/Menu/CrossHOVER.png", "/Menu/CrossPRESS.png", sound, prev, 315 - width, 5, iM);
		
	}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {

		Xbutton.updateButton(gc, dt);
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(gc.getImageManager().getImage("/Backgrounds/Background_MENU.png"), 0, 0);
		Xbutton.renderButton(gc, r);
	}

}
