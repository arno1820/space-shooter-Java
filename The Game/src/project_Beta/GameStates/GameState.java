package project_Beta.GameStates;

import java.util.ArrayList;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Button;

public abstract class GameState {

	protected ArrayList<Button> buttonList = new ArrayList<Button>();
	
	public abstract GameState updateGameState(GameController gc, float dt);
	
	public abstract void renderGamestate(GameController gc, Renderer r);
	
	public abstract void make(ImageManager iM, GameState prev);
	
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
