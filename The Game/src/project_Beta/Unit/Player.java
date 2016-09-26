package project_Beta.Unit;

import java.awt.event.KeyEvent;
import java.util.Random;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Position;
import project_Beta.Bullets.BasicBullet;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;

public class Player extends Unit {
	
	private Image player;
	private double cooldown = 0;
	private boolean leftGun = true;
	private SoundClip sound1; 
	private SoundClip sound2; 
	private SoundClip sound3; 
	public Player(Image image, double x, double y, GameController gc, Level level){
		
		sound1 = new SoundClip("/Randomize.wav");
		sound2 = new SoundClip("/Randomize2.wav");
		sound3 = new SoundClip("/Randomize3.wav");
		this.level = level;
		this.player = image;
		super.health = 3;
		super.position = new Position(image.getWidth(), image.getHeight(),x, y, gc);
		
	}
	@Override
	public void updateUnit(double dt){
		
		double XPos = super.position.getX();
		double YPos = super.position.getY();
		
		if(Input.isKey(KeyEvent.VK_Z)){
			position.setY(position.getY() - 50*dt);
		}
		if(Input.isKey(KeyEvent.VK_S)){
			position.setY(position.getY() + 50*dt);
		}
		if(Input.isKey(KeyEvent.VK_Q)){
			position.setX(position.getX() - 40*dt);
		}
		if(Input.isKey(KeyEvent.VK_D)){
			position.setX(position.getX() + 75*dt);
		}
		if(Input.isKey(KeyEvent.VK_SPACE)){
			System.out.println("pew pew");
			if(cooldown < 0){
				if(leftGun){
					level.addBullet(new BasicBullet(position.getX() + position.getWidth()/2, position.getY(), this, level, level.getGc()));
					cooldown = 0.20;
					leftGun = false;
				}else{
					level.addBullet(new BasicBullet(position.getX() + position.getWidth()/2, position.getY() + position.getHeight(), this, level, level.getGc()));
					leftGun = true;
					cooldown = 0.20;
				}
			}else cooldown -= dt;
		}
		
		if(super.position.checkHitBoxCollision(super.level, this)){
			super.position.setPosition(XPos, YPos);
		}
		//System.out.println(position.getX() +"y:" + position.getY());
		
	}
	
	@Override
	public void renderUnit(Renderer r) {
		r.drawImage(player, (int)super.position.getX(), (int)super.position.getY());
		
	}
	@Override
	public void hit() {
		// TODO Hit van player
		
	}
	
}
