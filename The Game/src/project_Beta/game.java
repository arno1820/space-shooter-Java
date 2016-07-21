package project_Beta;



import engine.core.AbstractGame;
import engine.core.GameController;
import engine.core.Renderer;
import engine.core.gfx.RGBCalc;
import project_Beta.GameStates.GameState;
import project_Beta.GameStates.TitleScreen;


public class game extends AbstractGame{

	private static GameState gameState;
	private static GameController gc;
	private double height;
	private double width;
	
	public static void main(String args[]){
		GameController gc = new GameController(new game(), true, RGBCalc.getPixelColor(1, 0.2f, 0.2f, 0.2f));
		gc.setTitle("Project Beta v0.1");
		gc.setLighting(false);
		gameState = new TitleScreen();
		gameState.make(gc.getImageManager(), null);
		gc.start();
		game.gc = gc;
		
	}
	
	public game() {}

	@Override
	public void update(GameController gc, float dt) {
		height = gc.getHeight();
		width = gc.getWidth();
		game.gameState = gameState.updateGameState(gc, dt);
	}

	@Override
	public void render(GameController gc, Renderer r) {
		gameState.renderGamestate(gc, r);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	

}