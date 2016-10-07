package project_Beta.Unit;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.gfx.Image;
import project_Beta.Position;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.Level;

public class  TinyUnit extends Enemy {

	private Image looks;
	private int cooldown = 20;
	// array, showing attack state: [goingForwards, PrepareTeleportsStillToGo, NeedsToBackUp, prevX, prevY]
	// 0 = false; 1 = true;
	private int[] attackArray = {0, 30, 0, 0};
	private boolean needsToBackUp = false;

	public TinyUnit(Image looks, double x, double y, double width, double height, GameController gc, Level level){
		
		super.level = level;
		this.looks = looks;
		
		super.health = 1;
		
		super.position = new Position(width, height, x, y,(double) 0,(double) gc.getWidth() + 20, (double) 0, (double)gc.getHeight(), gc);
		super.frontalSpeed = 80;
		super.sidewiseSpeed = 70;
		super.backwiseSpeed = 40;
		this.randomDestiantion();
		
	}
	
	private boolean moveToLocation(double dt){
		
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

		if(XPos == super.position.getX() && YPos == super.position.getY()) {
			return true;
		}else{return false;}
		
	}
	
	@Override
	public void updateUnit(double dt) {

		if (super.behaviour == Behaviour.IDLE) {

			if (cooldown <= 0) {
				cooldown = 20;

				Random rand = new Random();
				int next = rand.nextInt(10);
				if (next < 7) randomDestiantion();
				if (next >= 7 && !level.getGameOver()) super.behaviour = Behaviour.SHOOT;
			}
			cooldown--;

		}

		
		if(super.behaviour == Behaviour.SHOOT){
			//attackArray default: [0,  >0 ,0,x]
			if(attackArray[1] > 0){
				Random rand = new Random();
				int negativeX = 1;
				int negativeY = 1;

				if(rand.nextBoolean()) negativeX = -1;
				if(rand.nextBoolean()) negativeY = -1;

				super.position.setPosition(super.position.getX() + negativeX*rand.nextInt(4), super.position.getY() + negativeY*rand.nextInt(4));

				attackArray[1]--;
			}

			//make ready for attack
			//[0,0,0,x]
			if(attackArray[1] <= 0 && attackArray[0] == 0){
				super.destination[0] = super.level.getPlayer().getPosition().getX();
				super.destination[1] = super.position.getY();
				attackArray[3] = (int) super.position.getX();
				attackArray[0] = 1;
				super.frontalSpeed = 120;
			}

			//Attack
			//[1, 0, 0, XPOS]
			if(attackArray[0] == 1){

				this.moveToLocation(dt);

				if(arrived()) {
					attackArray[2] = 1;
					super.destination[0] = attackArray[3];
				}
				//backing off (if nothing hit)
				//[1,0,1,XPOS]
				if(attackArray[2] == 1 && arrived()){
					attackArray[0] = 0;
					attackArray[1] = 30;
					attackArray[2] = 0;
					super.behaviour = Behaviour.MOVE;
				}
				//hit the player
				if(moveToLocation(dt) && attackArray[0] == 1 && attackArray[2] != 1){
					super.level.getPlayer().hit(10);
					super.level.deleteUnit(this);
				}

			}



		}
		
		if(super.behaviour == Behaviour.MOVE){

			if(arrived()){
				super.behaviour = Behaviour.IDLE;
			}else{
				boolean isBlocked = moveToLocation(dt);
				if(isBlocked) super.behaviour = Behaviour.IDLE;
			}
		}
				
	}

	@Override
	public void renderUnit(Renderer r) {
		r.drawImage(looks, (int)super.position.getX(), (int)super.position.getY());
	}

	@Override
	public void hit(int amount) {
		// TODO hit van tinyunit
		// dit is een voorlopige implementatie!
		
		super.health -= amount;
		if(super.health <=0){
			super.level.deleteUnit(this);
			System.out.println("DEATH");
			super.behaviour = Behaviour.MOVE;
		}
		
	}
	
	private void randomDestiantion(){
		Random rand = new Random();
		this.SetDestination(100 + rand.nextInt(220), rand.nextInt(180));
	}

	private boolean arrived(){
		if(super.position.getX()> super.destination[0] - 5 && super.position.getX() < super.destination[0] + 5){
			super.destination[0] = super.position.getX();
		}
		if(super.position.getY() > super.destination[1] - 5 && super.position.getY() < super.destination[1] + 5){
			super.destination[1] = super.position.getY();
		}
		if(super.position.getX() == super.destination[0] && super.position.getY() == super.destination[1]) {
			return true;
		}else{ return false;}
	}
		
}
