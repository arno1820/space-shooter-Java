package project_Beta;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.MouseEvent;

import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.Light;
import engine.core.gfx.PixelAid;
import engine.core.gfx.ShadowType;


public class game extends AbstractGame{

	private GameState gameState = new TitleScreen();
	
	
	public static void main(String args[]){
		GameController gc = new GameController(new game(), true, PixelAid.getPixelColor(1, 0.2f, 0.2f, 0.2f));
		gc.setTitle("Project Beta v0.1");
		gc.setLighting(false);
		gc.start();
	}
	
	public game() {
		
	}

	@Override
	public void update(GameController gc, float dt) {
		this.gameState = gameState.updateGameState(gc, dt);
	}

	@Override
	public void render(GameController gc, Renderer r) {
		gameState.renderGamestate(gc, r);
	}

}
