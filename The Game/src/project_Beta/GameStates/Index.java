package project_Beta.GameStates;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Button;

public class Index extends GameState {

	private Image background;
	private Image title;
	
	private Button Xbutton;
	private Button enemies;
	private Button items;
	private Button locations;
	private Button ships;
	private SoundClip sound = new SoundClip("/select.wav");
	private ImageManager iM;
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		GameState next = updateButtons(super.buttonList, gc, dt);
		
		if(next != null) return next;
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background, 0, 0);
		renderButtons(buttonList, gc, r);
	}

	@Override
	public void make(ImageManager iM, GameState prev) {
		
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		
		Image temp = iM.getImage("/Menu/Cross.png");
		int width = temp.getWidth();
		super.buttonList.add(new Button("/Menu/Cross.png", "/Menu/CrossHOVER.png", "/Menu/CrossPRESS.png", sound, prev, 315 - width, 5, iM));
		super.buttonList.add(new Button("/Menu/Enemies_Button.png", "/Menu/Enemies_ButtonHOVER.png", "/Menu/Enemies_ButtonPRESS.png", sound, null, 5, 38, iM));
		super.buttonList.add(new Button("/Menu/Items_Button.png", "/Menu/Items_ButtonHOVER.png", "/Menu/Items_ButtonPRESS.png", sound, null, 5, 53, iM));
		super.buttonList.add(new Button("/Menu/Locations_Button.png", "/Menu/Locations_ButtonHOVER.png", "/Menu/Locations_ButtonPRESS.png", sound, null, 5, 68, iM));
		super.buttonList.add(new Button("/Menu/Ships_Button.png", "/Menu/Ships_ButtonHOVER.png", "/Menu/Ships_ButtonPRESS.png", sound, null, 5, 83, iM));
	}

}
