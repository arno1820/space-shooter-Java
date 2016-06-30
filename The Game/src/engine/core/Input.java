package engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private GameController gc;
	
	//don't need to be static
	private static boolean[] keys = new boolean[256];
	private static boolean[] keysLast = new boolean[256];
	
	private static boolean[] Buttons = new boolean[5];
	private static boolean[] ButtonsLast = new boolean[5];
	
	private static int mousex, mousey;
	
	public Input(GameController gc) {
		this.gc = gc;
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}

	public void update(){
		keysLast = keys.clone();
		ButtonsLast = Buttons.clone();
	}
	
	public static boolean isKey(int keyCode){
		return keys[keyCode];
	}
	
	//is this the first frame that keyCode has been pressed?
	public static boolean isKeyPressed(int keyCode){
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public static boolean isKeyReleased(int keyCode){
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public static boolean isButton(int ButtonCode){
		System.out.println(ButtonCode);
		return Buttons[ButtonCode];
	}
	
	public static boolean isButtonPressed(int ButtonCode){
		return Buttons[ButtonCode] && !ButtonsLast[ButtonCode];
	}
	
	public static boolean isButtonReleased(int ButtonCode){
		return !Buttons[ButtonCode] && ButtonsLast[ButtonCode];
	}
	
	public static int getMouseX(){
		return mousex;
	}
	
	public static int getMouseY(){
		return mousey;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mousex = (int) (e.getX()/gc.getScale());
		this.mousey = (int) (e.getY() / gc.getScale());
				
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Buttons[e.getButton()] = true;		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Buttons[e.getButton()] = true;		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
