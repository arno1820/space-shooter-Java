package project_Beta.Unit;

import java.awt.event.KeyEvent;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.gfx.Image;
import project_Beta.Position;

public class  TinyUnit extends Enemy {

	private Image looks;
	
	public TinyUnit(Image looks, double x, double y, double width, double height, GameController gc){
		
		this.looks = looks;
		
		super.position = new Position(width, height, x, y, gc);
		super.frontalSpeed = 75;
		super.sidewiseSpeed = 50;
		super.backwiseSpeed = 40;
		
	}
	
	private void moveToLocation(double dt){
		
		double XPos = super.position.getX();
		double YPos = super.position.getY();
		
		
		if(super.destination[0] > XPos){
			if(super.destination[1] > YPos){
				double speed = Math.sqrt(Math.pow(super.backwiseSpeed, 2) + Math.pow(super.sidewiseSpeed, 2));
				super.position.setPositionOutside(XPos + speed*dt, YPos + speed*dt);
			}else{
				double speed = Math.sqrt(Math.pow(super.backwiseSpeed, 2) + Math.pow(super.sidewiseSpeed, 2));
				super.position.setPositionOutside(XPos + speed*dt, YPos - speed*dt);
			}
		}else{
			if(super.destination[1] > YPos){
				double speed = Math.sqrt(Math.pow(super.frontalSpeed, 2) - Math.pow(super.sidewiseSpeed, 2));
				super.position.setPositionOutside(XPos - speed*dt, YPos + speed*dt);
			}else{
				double speed = Math.sqrt(Math.pow(super.frontalSpeed, 2) - Math.pow(super.sidewiseSpeed, 2));
				super.position.setPositionOutside(XPos - speed*dt, YPos - speed*dt);
			}
		}
		
	}
	
	@Override
	public void updateUnit(double dt) {
		
		if(super.behaviour == Behaviour.IDLE){
			
		}
		
		if(super.behaviour == Behaviour.SHOOT){
			
		}
		
		if(super.behaviour == Behaviour.MOVE){
			if(super.position.getX()> super.destination[0] - 5 && super.position.getX() < super.destination[0] + 5 &&
					super.position.getY() > super.destination[1] - 5 && super.position.getY() < super.destination[1] + 5){
				super.behaviour = Behaviour.IDLE;
			}else{
				moveToLocation(dt);
			}
		}
		
		System.out.println(position.getX() +"y:" + position.getY());
		
	}

	@Override
	public void renderUnit(Renderer r) {
		r.drawImage(looks, (int)super.position.getX(), (int)super.position.getY());
	}
	
	
		
}
