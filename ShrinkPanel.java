/**
 * This class represent the Panel
 * extend JPanel
 * 
 * @author Lior Maimon
 * mmn15 , Question 1
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ShrinkPanel extends JPanel{
	private int size;
	private int threadNumber;
	private int passNumber;
	private JPanel picturePanel;
	private JPanel buttonsPanel;
	private MyButton[][] pictureButtons; //n,m,t come from main or in constructor?
	private JButton goButton;
	private JButton clearButton;
	/**
	 * construct a new Panel represent the shrink program
	 */
	public ShrinkPanel(int n, int m, int t){
		size = n;
		threadNumber = m;
		passNumber = t;
		pictureButtons = new MyButton[size][size];
		setLayout(new BorderLayout());
		picturePanel = new JPanel(new GridLayout(size,size));
		buttonsPanel = new JPanel();
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				pictureButtons[i][j] = new MyButton();
				pictureButtons[i][j].addActionListener(new ButtonListener());
				picturePanel.add(pictureButtons[i][j]);
			}
		}
		
		
		add(picturePanel, BorderLayout.CENTER);
		goButton = new JButton("Go");
		clearButton = new JButton("Clear");
		
		goButton.addActionListener(new ButtonListener());
		clearButton.addActionListener(new ButtonListener());
		buttonsPanel.add(goButton);
		buttonsPanel.add(clearButton);
		
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	/**
	 * method paintComponent
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	//private method ButtomListener - bottoms performers implements ActionListener
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			Object src = event.getSource();
			
			if(src == goButton){ //go button
				goButton.setEnabled(false);
				new ShrinkThread(); //restart the lock , condition , and counter
				for(int i = 0; i < threadNumber ; i++){
					new ShrinkThread(pictureButtons, i , threadNumber, passNumber, picturePanel).start();
				}
			}
			else if(src == clearButton){ //clear button
				for(int i = 0; i < size; i++){
					for(int j = 0; j < size; j++){
						pictureButtons[i][j].setButtonColor(Color.white);
					}
					goButton.setEnabled(true);
				}
				repaint();	
			}
			else{ //picture buttons
				if(((MyButton)src).getButtonColor() == Color.white){
					((MyButton)src).setButtonColor(Color.black);
				}
				else{
					((MyButton)src).setButtonColor(Color.white);
				}
			}
		}
	}
	
}

