package engine.core.gfx;

import java.util.HashMap;

import engine.core.GameController;

public class ImageManager {
	
	private HashMap<String, Image> imageMap = new HashMap<String, Image>();
	private GameController gc;
	
	public ImageManager(GameController gc){this.gc = gc;}

	public Image getImage(String path){
		
		if(imageMap.containsKey(path)) return imageMap.get(path);
		
		Image temp = new Image(path);
		imageMap.put(path, temp);
		return temp;
		
	}
	
	public void removeImage(String path){
		
		if(!imageMap.containsKey(path)) return;
		
		imageMap.remove(path);
		
	}
	
	public GameController getGc() {
		return gc;
	}
	
	
	
}
