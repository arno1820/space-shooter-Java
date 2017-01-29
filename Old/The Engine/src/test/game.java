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
import engine.core.gfx.ShadowType;


public class game extends AbstractGame{

	private Image image = new Image("/Backgrounds/Background FLATcolor TEST.png");
	private Image image2 = new Image("/Backgrounds/Stars See-Trough TEST.png");
	private Image image3 = new Image("/Backgrounds/Background FLATcolor01 TEST.png");
	private Image image4 = new Image("/Backgrounds/Bg Starfield.png");
	private Image currentBack = image;
	private SoundClip sound = new SoundClip("/song.wav");
	private boolean background = true;
	private Light light = new Light(0xffffffff, 200);

	
	public static void main(String args[]){
		GameController gc = new GameController(new game(), true, PixelAid.getPixelColor(1, 0.2f, 0.2f, 0.2f));
		gc.start();

	}
	
	public game() {
		image2.setShadowType(ShadowType.FADE);
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

	}

	@Override
	public void render(GameController gc, Renderer r) {
		if(background){
			
			r.drawImage(image, 0, 0);
			r.drawImage(image2, 0, 0);
			r.drawLight(light, Input.getMouseX(), Input.getMouseY());
			

		}
		else{
			r.clear();
		}
		
	}

}
