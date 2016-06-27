package test;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.MouseEvent;

import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.gfx.Image;

public class game extends AbstractGame{

	private Image image = new Image("/Background.png");
	private boolean background = true;
	
	public static void main(String args[]){
		GameController gc = new GameController(new game());
		gc.start();
	}

	@Override
	public void update(GameController gc, float dt) {
		if(Input.isKey(KeyEvent.VK_1)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_2)) System.out.println("heey!");
		if(Input.isKeyReleased(KeyEvent.VK_3)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_F)) background = false;
	}

	@Override
	public void render(GameController gc, Renderer r) {
		if(background){
			r.drawImage(image, 0, 0);
		}
		else{
			r.clear();
		}
	}

}
