package project_Beta.Unit;

import engine.core.Renderer;

public abstract class Enemy extends Unit {
	
	SquadManager Squad;
	double[] destination = null;
	Behaviour behaviour = Behaviour.IDLE;
	int busyTime = 0;
	Behaviour nextBehaviour = Behaviour.IDLE;
		
	public void SetDestination(double x, double y){
		if(this.behaviour == Behaviour.SHOOT) return;
		
		this.destination = new double[2];
		this.destination[0] = x;
		this.destination[1] = y;
		this.behaviour = Behaviour.MOVE;
		
	}

}

