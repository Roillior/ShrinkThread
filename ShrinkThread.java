/**
 * This class represent the shrink threads 
 * extends Thread
 * 
 * @author Lior Maimon 
 * mmn15 , Question 1
 */


import java.awt.Color;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

public class ShrinkThread extends Thread{
	private MyButton[][] matrix;
	static Lock lock;
	static Condition cond ;
	static int counter ;
	private int threadNumber;
	private int totalThreads;
	private int numberOfPass;
	private JPanel picturePanel;
	private final int DELAY = 300; //const delay 300 milisec
	/**
	 * empty constructor restart the static values
	 */
	public ShrinkThread(){
		lock = new ReentrantLock();
		cond = lock.newCondition();
		counter = 0;
	}
	/**
	 * construct a new shrinkThread
	 */
	public ShrinkThread(MyButton[][] mat, int threadNum, int totalThreadsNumber, int passNumber, JPanel picPanel){
		matrix = mat;
		threadNumber = threadNum;
		totalThreads = totalThreadsNumber;
		numberOfPass = passNumber;
		picturePanel = picPanel;

	}
	/**
	 * Method run 
	 */
	@Override
	public void run(){
		for(int count = 0; count < numberOfPass ; count++){ //number of passes need to do
			for(int i = 0; i < matrix.length ; i++){  
				if((i % totalThreads) == threadNumber){  //work is shared by the thread each thread get his on lines from the picture
					for(int j = 0; j < matrix[i].length; j++){
						if(matrix[i][j].getButtonColor().equals(Color.black)){
							checkToChange(i , j);
						}
					}
				}
			}
			lock.lock(); //enter critical section
			try{
				if(counter != (totalThreads - 1)){
					counter++;
					cond.await();
				}
				else{
					counter = 0;
					cond.signalAll();
					
				}

			}catch(InterruptedException e){System.out.println("Interrupted while waiting, Thread number "+threadNumber);}
			for(int i = 0; i < matrix.length ; i++){
				if((i % totalThreads) == threadNumber){
					for(int j = 0; j < matrix[i].length; j++){
						if(matrix[i][j].getFlag()){
							matrix[i][j].setButtonColor(Color.white);
							matrix[i][j].setFlag(false);
							picturePanel.repaint();	
						}
					}
				}
			}
			lock.lock(); //enter critical section
			try{
				if(counter != (totalThreads - 1)){
					counter++;
					cond.await();
				}
				else{
					counter = 0;
					cond.signalAll();
					
				}
			}catch(InterruptedException e){System.out.println("Interrupted while waiting, Thread number "+threadNumber);}
			try{
				sleep(DELAY); //delay the picture to see the change
			}catch(InterruptedException e){}
		}
	}
	//private method to check the neigbour of each pixel and check if need to change the pixel color
	private void checkToChange(int row, int col){
		if(row == 0 || row == (matrix.length - 1) || col == 0 || col == (matrix.length - 1)){
			if(row == 0){
				if(col == 0 ){
					if(matrix[row + 1][col].getButtonColor().equals(Color.white) || matrix[row][col + 1].getButtonColor().equals(Color.white)){
						matrix[row][col].setFlag(true);
					}
				}
				else if(col == (matrix.length -1)){
					if(matrix[row + 1][col].getButtonColor().equals(Color.white) || matrix[row][col - 1].getButtonColor().equals(Color.white)){
						matrix[row][col].setFlag(true);
					}
				}
				else{
					if(matrix[row + 1][col].getButtonColor().equals(Color.white) || matrix[row][col - 1].getButtonColor().equals(Color.white) || 
							matrix[row][col + 1].getButtonColor().equals(Color.white)){
						matrix[row][col].setFlag(true);
					}
				}
			}
			else if(row == (matrix.length - 1)){
				if(col == 0 ){
					if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row][col + 1].getButtonColor().equals(Color.white)){
						matrix[row][col].setFlag(true);
					}
				}
				else if(col == (matrix.length -1)){
					if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row][col - 1].getButtonColor().equals(Color.white)){
						matrix[row][col].setFlag(true);
					}
				}
				else{
					if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row][col - 1].getButtonColor().equals(Color.white) || 
							matrix[row][col + 1].getButtonColor().equals(Color.white)){
						
						matrix[row][col].setFlag(true);
					}
				}
			}
			else if(col == 0){
				if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row + 1][col].getButtonColor().equals(Color.white) || 
						matrix[row][col + 1].getButtonColor().equals(Color.white)){
					matrix[row][col].setFlag(true);
				}
			}
			else{ //last col
				if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row + 1][col].getButtonColor().equals(Color.white) ||
						matrix[row][col - 1].getButtonColor().equals(Color.white) ){
					matrix[row][col].setFlag(true);
				}
			}
		}
		else{
			if(matrix[row - 1][col].getButtonColor().equals(Color.white) || matrix[row + 1][col].getButtonColor().equals(Color.white)||
					matrix[row][col - 1].getButtonColor().equals(Color.white) || matrix[row][col + 1].getButtonColor().equals(Color.white)){
				matrix[row][col].setFlag(true);
			}
		}
	}
}
