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

public class game extends AbstractGame{

	private Image image = new Image("/Backgrounds/Background FLATcolor TEST.png");
	private Image image2 = new Image("/Backgrounds/Stars See-Trough TEST.png");
	private SoundClip sound = new SoundClip("/song.wav");
	private boolean background = true;
	private Light light = new Light(0xffffffff, 100);
	
	public static void main(String args[]){
		GameController gc = new GameController(new game());
		gc.start();
		gc.setLighting(false);
	}

	@Override
	public void update(GameController gc, float dt) {
		if(Input.isKey(KeyEvent.VK_1)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_2)){
			System.out.println("heey!");
			sound.play();
		}
		if(Input.isKeyReleased(KeyEvent.VK_3)) System.out.println("hey!");
		if(Input.isKeyPressed(KeyEvent.VK_F)) background = false;
	}

	@Override
	public void render(GameController gc, Renderer r) {
		if(background){
			r.drawImage(image, 0, 0);
			r.drawImage(image2, 0, 0);
			//r.setAmbientLight(0xffffffff);
			//r.drawLight(light, 0, 0);
		}
		else{
			r.clear();
		}
	}

}
