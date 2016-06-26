package test;

import com.sun.glass.events.KeyEvent;

import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;

public class game extends AbstractGame{

	public static void main(String args[]){
		GameController gc = new GameController(new game());
		gc.start();
	}

	@Override
	public void update(GameController gc, float dt) {
		if(Input.isKey(KeyEvent.VK_1)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_2)) System.out.println("heey!");
		if(Input.isKeyReleased(KeyEvent.VK_3)) System.out.println("hey!");
	}

	@Override
	public void render(GameController gc, Renderer r) {
		// TODO Auto-generated method stub
		
	}

}
