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
	private int wave = 0;
	
	
	@Override
	public GameState updateGameState(GameController gc, float dt) {
		
		player.updateUnit(dt);
		updateWave(gc);
		if(!enemies.isEmpty()){
		
			for(Enemy enemy : enemies){
				enemy.updateUnit(dt);
			}
		}
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {
		
		r.drawImage(background,0,0);
		player.renderUnit(r);
		
		if(!enemies.isEmpty()){
			
			for(Enemy enemy : enemies){
				enemy.renderUnit(r);
			}
		}
	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		this.player = new Player(iM.getImage("/PlayerShips/Blue.png"), 20, 90, iM.getGc(), this);

	}	
	
	private void updateWave(GameController gc){
		
		Random rand = new Random();
		
		//TODO: Still very basic, needs more!
		if(enemies.isEmpty()){
			for(int i = wave; i > 0; i--){
				enemies.add(new TinyUnit(iM.getImage("/Enemies/EM_Fighter.png"), 320 + rand.nextInt(20), rand.nextInt(180), 4, 3, gc, this));
				enemies.get(i-1).SetDestination(100 + rand.nextInt(220), rand.nextInt(180));
			}
			wave++;
		}
		
	}
	
	public ArrayList<Unit> getUnitList(){
		ArrayList<Unit> unitList = (ArrayList<Unit>) enemies.clone();
		unitList.add(player);
		return unitList;
	}
	
}