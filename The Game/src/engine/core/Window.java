package engine.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	
	//the actual window
	private JFrame frame;
	//what we are going to render to
	private Canvas canvas;
	//Image of the game, we will manipulate theze pixels
	private BufferedImage image;
	//what we use to draw the buffered image on our canvas
	private Graphics g;
	//Dubble buffering (against flickering)
	private BufferStrategy bs;
	
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
		canvas.createBufferStrategy(1);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public void update(){
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
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
}
