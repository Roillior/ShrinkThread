/**
 * This class represent a pixel in the picture
 * extendsJButton
 * 
 * @author Lior Maimon 
 * mmn15 , Question 1
 */


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{
	private Color buttonColor = Color.white;
	private boolean flag = false;
	/**
	 * empty constructor
	 */
	public MyButton(){
		this(null);
	}
	/**
	 * construct a new button with a given string
	 */
	public MyButton(String str){
		super(str);
		super.setContentAreaFilled(false);
	}
	/**
	 * set the Button color
	 * @param col - contain the color we want to change to
	 */
	public void setButtonColor(Color col){
		buttonColor = col;
	}
	/**
	 * method that return the button color
	 * @return the button color
	 */
	public Color getButtonColor(){
		return buttonColor;
	}
	/**
	 * set a flag on the button - flag used as mark if button color need to be change
	 * @param bol - true if need to be change false otherwise
	 */
	public void setFlag(boolean bol){
		flag = bol;
	}
	/**
	 * method that return the flag
	 * @return flag boolean value
	 */
	public boolean getFlag(){
		return flag;
	}
	/**
	 * method paintComponent
	 */
	@Override
	 protected void paintComponent(Graphics g) {
         g.setColor(buttonColor);
         g.fillRect(0, 0, getWidth(), getHeight());
         super.paintComponent(g);
     }
}
