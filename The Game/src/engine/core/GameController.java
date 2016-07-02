package engine.core;

import engine.core.gfx.PixelAid;
import javafx.scene.AmbientLight;

public class GameController implements Runnable{

	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer renderer;
	private Input input;
	
	//to be changed! not alot of pixels -> small resolution (because cpu is going to render all)
	private int width = 320, height = 180;
	//we need to enlarge our with an height to a nice window size.
	private float scale = 3.0f;
	private String title = "AEngine by Arno Rondou v1.0";
	
	private boolean isRunning = false;
	
	//updates for every second
	private double frameCap = 1.0/60.0;
	
	private boolean lighting = false;
	private int ambientLight;
	
	public GameController(AbstractGame game) {
		this.game = game;
	}
	
	public GameController(AbstractGame game, boolean lighting, int ambientLight) {
		this.game = game;
		this.lighting = lighting;
		this.ambientLight = ambientLight;
	}
	
	
	public void start(){
		
		if(isRunning) return;
		
		window = new Window(this);
		renderer = new Renderer(this);
		if(lighting) renderer.setAmbientLight(ambientLight);
		input = new Input(this);
		thread = new Thread(this);
		thread.run();
		
	}

	public void stop(){
		if(!isRunning) return;
		
		isRunning = false;
	}
	
	public void run(){
		
		//control framerate and how much we update (framerate matches updates)
		
		isRunning = true;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double frameTime = 0;
		int frameRate = 0;	
		
		while(isRunning){
			
			//render only when updated
			boolean render = true;
			
			//Records how long this while takes 
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= frameCap){
				
				game.update(this, (float) frameCap);
				input.update();
				unprocessedTime -= frameCap;
				render = true;
				
				if(frameTime >= 1){
					frameTime = 0;
					System.out.println(frameRate);
					frameRate = 0;
				}
				
			}
			
			if(render){
				//renderer.clear();
				game.render(this, renderer);
				if(lighting) renderer.combineMaps();
				window.update();
				frameRate++;
				
			}else{
				try {
					//no render, just sleep a bit. easier on the cpu.
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	public void sleep(){
		try {
			thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cleanUp(){
		window.cleanUp();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}

	public boolean isLighting() {
		return lighting;
	}

	public void setLighting(boolean lighting) {
		this.lighting = lighting;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
}