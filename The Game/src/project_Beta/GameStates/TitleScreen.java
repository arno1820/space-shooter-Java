package project_Beta.GameStates;



import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Button;

public class TitleScreen implements GameState {
	
	private Image background;
	private Image title;
	private Button buttonStart;
	private Button buttonOptions;
	private Button buttonIndex;
	private Button buttonExit;
	private ImageManager iM;
	
	private SoundClip sound = new SoundClip("/select.wav");
	
	public TitleScreen(ImageManager iM){
		
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background.png");
		this.title = iM.getImage("/Menu/Title.png");
		
		this.buttonStart = new Button("/Menu/Play_Button.png", "/Menu/Play_ButtonHOVER.png", "/Menu/Play_ButtonPRESS.png", sound, new Play(), 5, 38, iM);
		this.buttonOptions = new Button("/Menu/Options_Button.png", "/Menu/Options_ButtonHOVER.png", "/Menu/Options_ButtonPRESS.png", sound, new Options(), 5, 53, iM);
		this.buttonIndex = new Button("/Menu/Index_Button.png", "/Menu/Index_ButtonHOVER.png", "/Menu/Index_ButtonPRESS.png", sound, new Index(), 5, 68, iM);
		this.buttonExit = new Button("/Menu/Exit_Button.png", "/Menu/Exit_ButtonHOVER.png", "/Menu/Exit_ButtonPRESS.png", sound, new Exit(), 5, 83, iM);
		
	}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		GameState nextState = null;
		
		nextState = buttonStart.updateButton(gc, dt);
		if(nextState != null) return nextState;
		
		nextState = buttonOptions.updateButton(gc, dt);
		if(nextState != null) return nextState;
		
		nextState = buttonIndex.updateButton(gc, dt);
		if(nextState != null) return nextState;
		
		nextState = buttonExit.updateButton(gc, dt);
		if(nextState != null) return nextState;
		
		return this;
		
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background, 0, 0);
		r.drawImage(title, 5, 5);
		buttonStart.renderButton(gc, r);
		buttonOptions.renderButton(gc, r);
		buttonIndex.renderButton(gc, r);
		buttonExit.renderButton(gc, r);
	}

}
