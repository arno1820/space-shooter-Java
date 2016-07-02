package engine.core.gfx;

import java.util.HashMap;

public class ImageManager {
	
	//TODO check correct capacity
	private HashMap<String, Image> imageMap = new HashMap<String, Image>();
	
	public ImageManager(){}
	
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
	
	
	
}
