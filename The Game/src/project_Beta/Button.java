package project_Beta;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.GameStates.GameState;

public class Button {
	
	private String buttonPath;
	private String buttonHoverPath;
	private String buttonClickPath;
	private GameState nextGameState;
	private int x,y;
	private int width, height;
	private ImageManager iM;
	private Image current;
	
	private SoundClip sound;
	private boolean sPlayed = false;
	
	private int timeToWait = 10;
	
	public Button(String button, String buttonHover, String buttonClick, SoundClip sound, GameState nextGameState, int Xlocation, int Ylocation, ImageManager iM){
		
		this.buttonPath = button;
		if(buttonHover == null) {this.buttonHoverPath = button;}
		else{this.buttonHoverPath = buttonHover;}
		if(buttonClick == null) {this.buttonClickPath = button;}
		else{this.buttonClickPath = buttonClick;}
		
		this.sound = sound;
		
		this.nextGameState = nextGameState;
		this.x = Xlocation;
		this.y = Ylocation;
		this.iM = iM;
		
		current = iM.getImage(button);
		this.width = current.getWidth();
		this.height = current.getHeight();
	}
	
	public GameState updateButton(GameController gc, float dt, GameState gs) {
		
		//hovering
		if((Input.getMouseX() > x && Input.getMouseX() < x + width) && (Input.getMouseY() > y && Input.getMouseY() < y + height)){
			
			current = iM.getImage(buttonHoverPath);
			
			//Click
			if(Input.isButtonPressed(java.awt.event.MouseEvent.BUTTON1)) {
				current = iM.getImage(buttonClickPath);
				System.out.println("EY EY");
				timeToWait -= dt;
			}
			
			if(!sPlayed && sound != null){
				sound.play();
				sPlayed = true;
			}
		}else{
			current = iM.getImage(buttonPath);
			sPlayed = false;
		}
		
		if(timeToWait != 10){
			current = iM.getImage(buttonClickPath);
			if(timeToWait <= 0){
				timeToWait = 10;
				nextGameState.make(iM, gs);
				return nextGameState;
			}else{
				timeToWait -= dt;
			}
		}
		
		return null;
		
	}
	
	public void renderButton(GameController gc, Renderer r) {
		
		r.drawImage(current, x, y);
		
	}
	
}
