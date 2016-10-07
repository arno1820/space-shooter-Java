package project_Beta.Bullets;

import java.util.ArrayList;
import java.util.Random;

import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.Image;
import project_Beta.GameStates.Level;
import project_Beta.Unit.Unit;

public class BasicBullet extends Bullet {

	private Image bullet;
	private int speed;
	
	public BasicBullet(double x, double y, Unit firedBy, Level level, GameController gc) {
		super(4, 4, x, y,firedBy, level, gc);
		bullet = gc.getImageManager().getImage("/PlayerShips/Bullet.png");
		Random rand = new Random();
		speed = 3 + rand.nextInt(1);
	}
	public BasicBullet(double x, double y, int speedness, Unit firedBy, Level level, GameController gc) {
		super(4, 4, x, y,firedBy, level, gc);
		bullet = gc.getImageManager().getImage("/PlayerShips/Bullet.png");
		Random rand = new Random();
		speed = speedness;
	}
	
	@Override
	public void impact() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateBullet(double dt) {
		
		if(super.position.getX() > 320){
			killBullet();
		}
		
		super.position.setPosition(super.position.getX()+speed, super.position.getY());
		
		//TODO Helemaal niet efficient! miss met een map? hashtabel, op basis van hun positie?
		ArrayList<Unit> units = level.getUnitList();
		for(Unit unit: units){
			//TODO hier zit een kleine fout, soms hitten de bullets niet terwijl ze dit wel moeten doen!
			if(unit != super.firedBy && super.position.hitboxCollision(unit.getPosition().getHitbox())){
				unit.hit();
				this.killBullet();
			}
		}
	}

	@Override
	public void killBullet() {
		
		super.level.deleteBullet(this);
		
	}

	@Override
	public void renderBullet(Renderer r) {
		r.drawImage(bullet, (int)super.position.getX(), (int)super.position.getY());
		
	}

}
