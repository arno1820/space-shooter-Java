package project_Beta.Unit;

import java.awt.event.KeyEvent;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.gfx.Image;
import engine.core.gfx.ImageManager;
import project_Beta.Position;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;

public class Player extends Unit {
	
	private Image player;
	
	public Player(Image image, double x, double y, GameController gc, Level level){
		
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
	
}