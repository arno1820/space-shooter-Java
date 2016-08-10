package project_Beta.GameStates;

import java.util.ArrayList;
import java.util.Random;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Unit.*;

public class Level extends GameState {
	
	private ImageManager iM;
	private Image background;
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int TIMER = 50;
	
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		player.updateUnit(dt);
		if(!enemies.isEmpty()) enemies.get(0).updateUnit(dt);;
		if(TIMER == 0){
			
			Random rand = new Random();
			
			int randomX = 320 + rand.nextInt(20);
			int randomY = rand.nextInt(180);
			
			enemies.add(new TinyUnit(iM.getImage("/Enemies/EM_Fighter.png"), randomX, randomY, 3, 4, gc));
			randomX = 100 + rand.nextInt(220);
			randomY = rand.nextInt(180);
			enemies.get(0).SetDestination(randomX, randomY);
			TIMER = -1;
		}
		
		TIMER--;
		
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		r.drawImage(background,0,0);
		player.renderUnit(r);
		if(!enemies.isEmpty()) enemies.get(0).renderUnit(r);
	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		
		this.player = new Player(iM.getImage("/PlayerShips/Blue.png"), 20, 90, iM.getGc());

	}

}
