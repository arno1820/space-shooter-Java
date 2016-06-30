package project_Beta;

import java.awt.event.KeyEvent;

import com.sun.glass.ui.Application;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import javafx.scene.input.MouseEvent;

public class TitleScreen implements GameState {
	
	Image background = new Image("/Backgrounds/background.png");
	Image title = new Image("/Menu/Title.png");
	Image buttonStart = new Image("/Menu/Play_Button.png");
	Image buttonOptions = new Image("/Menu/Options_Button.png");
	Image buttonIndex = new Image("/Menu/Index_Button.png");
	Image buttonExit = new Image("/Menu/Exit_Button.png");
	
	SoundClip sound = new SoundClip("/select.wav");
	boolean playedS = false;
	boolean playedO = false;
	boolean playedI = false;
	boolean playedE = false;
	
	public void TileScreen(){}
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 38 && Input.getMouseY() < 47)){
			
			buttonStart = new Image("/Menu/Play_ButtonHOVER.png");
			
			if(Input.isButtonPressed(java.awt.event.MouseEvent.BUTTON1)) {
				buttonStart = new Image("/Menu/Play_ButtonPRESS.png");
				System.out.println("EY EY");
			}
			
			if(!playedS){
				sound.play();
				playedS = true;
		}
		}else{
			buttonStart = new Image("/Menu/Play_Button.png");
			 playedS = false;
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 53 && Input.getMouseY() < 62)){
			buttonOptions = new Image("/Menu/Options_ButtonHOVER.png");
			if(!playedO){
				sound.play();
				playedO = true;
			}
		}else{
			buttonOptions = new Image("/Menu/Options_Button.png");
			playedO = false;
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 68 && Input.getMouseY() < 77)){
			buttonIndex = new Image("/Menu/Index_ButtonHOVER.png");
			if(!playedI){ 
				sound.play();
				playedI = true;
			}
		}else{
			buttonIndex = new Image("/Menu/Index_Button.png");
			playedI = false;
		}
		
		if((Input.getMouseX() > 5 && Input.getMouseX() < 91) && (Input.getMouseY() > 83 && Input.getMouseY() < 93)){
			buttonExit = new Image("/Menu/Exit_ButtonHOVER.png");
			if(!playedE){
				sound.play();
				playedE = true;
			}
		}else{
			buttonExit = new Image("/Menu/Exit_Button.png");
			playedE = false;
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
