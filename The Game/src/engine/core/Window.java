package engine.core;

import engine.core.gfx.Text;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window {
	
	//the actual window
	private JFrame frame;
	//what we are going to render to
	private Canvas canvas;
	//Image of the game, we will manipulate these pixels
	private BufferedImage image;
	//what we use to draw the buffered image on our canvas
	private Graphics g;
	//Dubble buffering (against flickering)
	private BufferStrategy bs;

	private ArrayList<Text> text = new ArrayList<Text>();
	
	//gameController for with and height
	public Window(GameController gc) {
		//last parameter is how we are going to give the data to render the pixels (RRGGBB -> as integer(faster if manipulation is small) 
		//or 255,255,255 as bit array)
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		canvas = new Canvas();
		Dimension s = new Dimension((int)(gc.getWidth()* gc.getScale()), (int)(gc.getHeight()* gc.getScale()));
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setPreferredSize(s);
		
		frame = new JFrame(gc.getTitle());
		//makes that the program stop if you hit the close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		//zet de canvas in het midden van de frame
		frame.add(canvas, BorderLayout.CENTER);
		//to make frame Undecorated
		//frame.setUndecorated(true);
		//frame will become the size of the canvas
		frame.pack();
		//X = 0 and Y = 0, in het midden ipv links bovenaan
		////frame.setLocationRelativeTo(null);
		//with this you can't rescale the window... LOOK INTO THIS!
		frame.setVisible(true);
		frame.setResizable(false);
		
		//sets 1 buffer, 
		//bufferstategy -> when an image is rendered, it draws it peace by peace, which make it flicker.
		//g will draw to the bs.
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public void update(){
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		if(!text.isEmpty()){
			for(Text text1 : text){
				g.setColor(Color.MAGENTA);
				g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, g.getFont().getSize()));
				g.drawString(text1.getString(), (int) text1.getPosition().getX(), (int) text1.getPosition().getY());
			}
		}
		bs.show();
	}
	
	public void cleanUp(){
		g.dispose();
		bs.dispose();
		image.flush();
		frame.dispose();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void addText(Text text1){
		text.add(text1);
	}

	public ArrayList<Text> getAllTextNOCLONE(){
		return text;
	}

	public ArrayList<Text> getAllText(){
		return (ArrayList<Text>) text.clone();
	}

}
