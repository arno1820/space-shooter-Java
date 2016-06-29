package test;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.MouseEvent;

import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Input;
import engine.core.Renderer;
import engine.core.audio.SoundClip;
import engine.core.gfx.Image;
import engine.core.gfx.Light;
import engine.core.gfx.PixelAid;
<<<<<<< HEAD
import engine.core.gfx.ShadowType;
=======
>>>>>>> origin/master

public class game extends AbstractGame{

	private Image image = new Image("/Backgrounds/Background FLATcolor TEST.png");
	private Image image2 = new Image("/Backgrounds/Stars See-Trough TEST.png");
	private Image image3 = new Image("/Backgrounds/Background FLATcolor01 TEST.png");
	private Image image4 = new Image("/Backgrounds/Bg Starfield.png");
	private Image currentBack = image;
	private SoundClip sound = new SoundClip("/song.wav");
	private boolean background = true;
	private Light light = new Light(0xffff0000, 100);
<<<<<<< HEAD
	
	private boolean lightbool = true ;
=======
>>>>>>> origin/master
	
	public static void main(String args[]){
		GameController gc = new GameController(new game(), true, PixelAid.getPixelColor(1, 0.1f, 0.2f, 0.3f));
		gc.start();
<<<<<<< HEAD
		gc.setLighting(true);
	}
	
	public game() {
		image2.setShadowType(ShadowType.TOTAL);
=======
		
>>>>>>> origin/master
	}

	@Override
	public void update(GameController gc, float dt) {
		if(Input.isKey(KeyEvent.VK_1)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_2)){
			System.out.println("heey!");
			if(currentBack != image){
				currentBack = image;
			}else currentBack = image3;
			//sound.play();
		}
		if(Input.isKeyReleased(KeyEvent.VK_3)){
			System.out.println("hey!");
			currentBack = image4;
		}
		if(Input.isKeyPressed(KeyEvent.VK_F)) lightbool = false;
		if(Input.isKeyReleased(KeyEvent.VK_F)) lightbool = true;
				
	}

	@Override
	public void render(GameController gc, Renderer r) {
		if(background){
<<<<<<< HEAD
			r.drawImage(currentBack, 0, 0);
			r.drawImage(image2, 0, 0);
			r.setAmbientLight(PixelAid.getPixelColor(1, 0.5f, 0.5f, 0.5f));
			r.drawLight(light, 50, 50);
=======
			
			//r.drawImage(image, 0, 0);
			r.drawImage(image2, 0, 0);
			r.drawLight(light, 50, 50);
			//r.setAmbientLight(0xffffffff);
>>>>>>> origin/master
		}
		else{
			r.clear();
		}
		
	}

}
