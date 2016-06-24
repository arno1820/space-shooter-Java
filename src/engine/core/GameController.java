package engine.core;

public class GameController implements Runnable{

	private Thread thread;
	private AbstractGame game;
	private Window window;
	
	//to be changed! not alot of pixels -> small resolution (because cpu is going to render all)
	private int width = 320, height = 240;
	//we need to enlarge our with an height to a nice window size.
	private float scale = 2.0f;
	private String title = "AEngine by Arno Rondou v1.0";
	
	private boolean isRunning = false;
	
	//updates for every second
	private double frameCap = 1.0/60.0;
	
	public GameController(AbstractGame game) {
		this.game = game;
	}
	
	public void start(){
		
		if(isRunning) return;
		
		window = new Window(this);
		
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
		
		
		while(isRunning){
			
			//render only when updated
			boolean render = false;
			
			//Records how long this while takes 
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			
			while(unprocessedTime >= frameCap){
				
				game.update(this, (float) frameCap);
				unprocessedTime -= frameCap;
				render = true;
			}
			
			if(render){
				//clear screen
				//game.render(this, r);
				window.update();
			}else{
				try {
					//no render, just sleep a bit. easier on the cpu.
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
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
	
}
