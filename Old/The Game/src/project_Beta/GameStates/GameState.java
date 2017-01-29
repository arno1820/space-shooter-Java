package project_Beta.GameStates;

import java.util.ArrayList;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.ImageManager;
import project_Beta.Button;
import project_Beta.Unit.Unit;

public abstract class GameState {

	protected ArrayList<Button> buttonList = new ArrayList<Button>();
	private boolean hasBeenMade = false;
	
	public abstract GameState updateGameState(GameController gc, float dt);
	
	public abstract void renderGamestate(GameController gc, Renderer r);
	
	public void make(ImageManager iM, GameState prev){
		if(this.hasBeenMade) return;
		this.hasBeenMade = true;
		this.makeSub(iM, prev);
	}
	
	protected abstract void makeSub(ImageManager iM, GameState prev);
	
	protected GameState updateButtons(ArrayList<Button> buttonList, GameController gc, float dt){
		
		for(Button button : buttonList){
			GameState nextState = null;
			nextState = button.updateButton(gc, dt, this);
			if(nextState != null) return nextState;
		}
		return null;
		
	}
	
	protected void renderButtons(ArrayList<Button> buttonList, GameController gc, Renderer r){
		for(Button button : buttonList){
			button.renderButton(gc, r);
		}
	}
	
}
