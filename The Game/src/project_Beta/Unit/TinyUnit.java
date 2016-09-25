package project_Beta.Unit;

import java.awt.event.KeyEvent;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.gfx.Image;
import project_Beta.Position;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;

public class  TinyUnit extends Enemy {

	private Image looks;
	
	public TinyUnit(Image looks, double x, double y, double width, double height, GameController gc, Level level){
		
		super.level = level;
		this.looks = looks;
		
		super.health = 2;
		
		super.position = new Position(width, height, x, y,(double) 0,(double) gc.getWidth() + 20, (double) 0, (double)gc.getHeight(), gc);
		super.frontalSpeed = 80;
		super.sidewiseSpeed = 70;
		super.backwiseSpeed = 40;
		
	}
	
	private void moveToLocation(double dt){
		
		double XPos = super.position.getX();
		double YPos = super.position.getY();
		
		if(super.destination[0] > XPos && super.destination[1] > YPos){
			double speed = Math.sqrt(Math.pow(super.backwiseSpeed, 2) + Math.pow(super.sidewiseSpeed, 2));
			super.position.setPosition(XPos + speed*dt, YPos + speed*dt);
		}
		else if(super.destination[0] > XPos && super.destination[1] == YPos){
			double speed = super.backwiseSpeed;
			super.position.setX(XPos + speed*dt);
		}
		else if(super.destination[0] > XPos && super.destination[1] < YPos){
			double speed = Math.sqrt(Math.pow(super.backwiseSpeed, 2) + Math.pow(super.sidewiseSpeed, 2));
			super.position.setPosition(XPos + speed*dt, YPos - speed*dt);
		}
		else if(super.destination[0] == XPos && super.destination[1] > YPos){
			double speed = super.sidewiseSpeed;
			super.position.setY(YPos + speed*dt);
		}
		else if(super.destination[0] == XPos && super.destination[1] == YPos){
			super.behaviour = Behaviour.IDLE;
		}
		else if(super.destination[0] == XPos && super.destination[1] < YPos){
			double speed = super.sidewiseSpeed;
			super.position.setY(YPos - speed*dt);
		}
		else if(super.destination[0] < XPos && super.destination[1] > YPos){
			double speed = Math.sqrt(Math.pow(super.frontalSpeed, 2) - Math.pow(super.sidewiseSpeed, 2));
			super.position.setPosition(XPos - speed*dt, YPos + speed*dt);
		}
		else if(super.destination[0] < XPos && super.destination[1] == YPos){
			double speed = super.frontalSpeed;
			super.position.setX(XPos - speed*dt);
		}
		else if(super.destination[0] < XPos && super.destination[1] < YPos){
			double speed = Math.sqrt(Math.pow(super.frontalSpeed, 2) - Math.pow(super.sidewiseSpeed, 2));
			super.position.setPosition(XPos - speed*dt, YPos - speed*dt);
		}
		
		if(super.position.checkHitBoxCollision(super.level, this)){
			super.position.setPosition(XPos, YPos);
		}
		
	}
	
	@Override
	public void updateUnit(double dt) {
		
		if(super.behaviour == Behaviour.IDLE){
			
		}
		
		if(super.behaviour == Behaviour.SHOOT){
			
		}
		
		if(super.behaviour == Behaviour.MOVE){
			
			if(super.position.getX()> super.destination[0] - 5 && super.position.getX() < super.destination[0] + 5){
				super.destination[0] = super.position.getX();
			}	
			if(super.position.getY() > super.destination[1] - 5 && super.position.getY() < super.destination[1] + 5){
				super.destination[1] = super.position.getY();
			}
			if(super.position.getX() == super.destination[0] && super.position.getY() == super.destination[1]){
				super.behaviour = Behaviour.IDLE;
			}else{
				moveToLocation(dt);
			}
		}
				
	}

	@Override
	public void renderUnit(Renderer r) {
		r.drawImage(looks, (int)super.position.getX(), (int)super.position.getY());
	}

	@Override
	public void hit() {
		// TODO hit van tinyunit
		// dit is een voorlopige implementatie!
		
		super.health -= 1;
		if(super.health <=0){
			super.level.deleteUnit(this);
			System.out.println("DEATH");
		}
		
	}
	
	
		
}
