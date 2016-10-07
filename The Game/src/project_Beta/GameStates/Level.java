package project_Beta.GameStates;

import java.util.ArrayList;
import java.util.Random;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import engine.core.gfx.Text;
import project_Beta.Bullets.Bullet;
import project_Beta.Button;
import project_Beta.Position;
import project_Beta.Unit.*;

public class Level extends GameState {
	
	private ImageManager iM;
	private Image background;
	private Button playAgain;
	private Button menu;

	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int wave = 0;
	private int realWave = 0;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private GameController gc;
	private Text killsText = null;
	private int kills = 0;
	private Text waveText = null;
	private Text enemiesOnScreen = null;

	private boolean previousBulletWasDeleted = false;
	private boolean previousEnemyWasDeleted = false;
	private boolean gameOver = false;

	@Override
	public GameState updateGameState(GameController gc, float dt) {

		killsText.setString("Kills: " + kills);
		waveText.setString("Wave: " + realWave);
		enemiesOnScreen.setString("Enemies on screen: " + enemies.size());
		GameState next = null;

		if(gameOver){
			next = updateButtons(super.buttonList, gc, dt);
		}
		player.updateUnit(dt);
		updateWave(gc);
		if(!enemies.isEmpty()){
			for(int i = 0; i < enemies.size(); i++){
				
				Enemy enemy = enemies.get(i);
				enemy.updateUnit(dt);
				if(previousEnemyWasDeleted){
					i--;
					previousEnemyWasDeleted = false;
				}
			}
		}
		if(!bullets.isEmpty()){
			for(int i = 0; i < bullets.size(); i++){
				
				Bullet bullet = bullets.get(i);
				bullet.updateBullet(dt);
				if(previousBulletWasDeleted){
					i--;
					previousBulletWasDeleted = false;
				}
			}
		}
		this.gc = gc;

		if(next != null){
			gc.deleteText(killsText);
			gc.deleteText(enemiesOnScreen);
			gc.deleteText(waveText);
			return next;
		}
		return this;
	}

	@Override
	public void renderGamestate(GameController gc, Renderer r) {

		r.drawImage(background,0,0);
		if(!gameOver)player.renderUnit(r);
		
		if(!enemies.isEmpty()){
			
			for(Enemy enemy : enemies){
				enemy.renderUnit(r);
			}
		}
		
		if(!bullets.isEmpty()){
			for(Bullet bullet: bullets){
				bullet.renderBullet(r);
			}
		}

		if(gameOver){
			renderButtons(buttonList, gc, r);
		}

	}

	@Override
	protected void makeSub(ImageManager iM, GameState prev) {
		this.iM = iM;
		this.background = iM.getImage("/Backgrounds/Background_MENU.png");
		this.player = new Player(iM.getImage("/PlayerShips/Blue.png"), 20, 90, iM.getGc(), this);

		super.buttonList.add(new Button("/Menu/PlayAgain_Button.png", "/Menu/PlayAgain_ButtonHOVER.png", "/Menu/PlayAgain_ButtonPRESS.png", new SoundClip("/select.wav"), new SelectShip(), 5, 38, iM));
		super.buttonList.add(new Button("/Menu/Menu_Button.png", "/Menu/Menu_ButtonHOVER.png", "/Menu/Menu_ButtonPRESS.png", new SoundClip("/select.wav"), new TitleScreen(), 5, 53, iM));
		killsText = new Text("Kills: " + kills, new Position(80, 10, iM.getGc()));
		waveText = new Text("Wave: " + realWave, new Position(230, 10, iM.getGc()));
		enemiesOnScreen = new Text("Enemies On Screen: " + enemies.size(), new Position(300, 10, iM.getGc()));
		iM.getGc().addText(enemiesOnScreen);
		iM.getGc().addText(waveText);
		iM.getGc().addText(killsText);
	}
	
	private void updateWave(GameController gc){

		Random rand = new Random();
		//TODO: spawnen alle enemies? location wrong?
		//TODO: Still very basic, needs more!
		if(enemies.isEmpty()){
			int n = 0;
			for(int i = wave; i > 0; i--){
				enemies.add(new TinyUnit(iM.getImage("/Enemies/EM_Fighter.png"), 320 + rand.nextInt(20), rand.nextInt(180), 4, 3, gc, this));
				enemies.get(n).SetDestination(100 + rand.nextInt(220), rand.nextInt(180));
				n *= 10;
			}
			wave += 5;
			realWave++;

		}
		
	}
	
	public ArrayList<Unit> getUnitList(){
		ArrayList<Unit> unitList = (ArrayList<Unit>) enemies.clone();
		if(!gameOver)unitList.add(player);
		return unitList;
	}
	
	public void addBullet(Bullet bullet){
		bullets.add(bullet);
	}
	
	public void deleteBullet(Bullet bullet){
		bullets.remove(bullet);
		previousBulletWasDeleted = true;
	}
	
	public void deleteUnit(Unit unit){
		if(unit instanceof Enemy){
			enemies.remove(unit);
			previousEnemyWasDeleted = true;
			if(!gameOver)kills++;
		}else if(unit instanceof Player){
			gameOver = true;
		}
	}
	
	public GameController getGc() {
		return gc;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean getGameOver(){
		return gameOver;
	}
}