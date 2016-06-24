package test;

import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Renderer;

public class game extends AbstractGame{

	public static void main(String args[]){
		GameController gc = new GameController(new game());
		gc.start();
	}

	@Override
	public void update(GameController gc, float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameController gc, Renderer r) {
		// TODO Auto-generated method stub
		
	}

}
