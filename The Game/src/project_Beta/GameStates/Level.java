package project_Beta.GameStates;

import java.util.ArrayList;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Unit.*;

public class Level extends GameState {
	
	private ImageManager iM;
	private Image background;
	private Player player;
	private ArrayList<Enemy> enemies;
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		player.updateUnit(dt);
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background,0,0);
		player.renderUnit(r);
	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		
		this.player = new Player(iM.getImage("/PlayerShips/Blue.png"), 20, 90, iM.getGc());

	}

}
