/**
 * This class represent the User Input
 * 
 * @author Lior Maimon  
 * mmn15 , Question 1
 */


import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
	private int size;
	private int threadNumber;
	private int passNumber;
	private int check = -1;
	Scanner scan;
	/**
	 * empty constructor
	 */
	public UserInput(){
		scan = new Scanner(System.in);
		while(check == -1){
			try{
				System.out.println("Please enter the picture size (n) ");
				size = scan.nextInt();
				check = 0;
			}catch(InputMismatchException e){
				System.out.println("n have to be an Integer, please try again. ");
				scan.nextLine();
			}
		}
		check = -1;
		while(check == -1){
			try{
				System.out.println("Please enter the Number of Threads, (m) ");
				threadNumber = scan.nextInt();
				check = 0;
			}catch(InputMismatchException e){
				System.out.println("m have to be an Integer, please try again. ");
				scan.nextLine();
			}
		}
		check = -1;
		while(check == -1){
			try{
				System.out.println("Please enter the Number of passes, (t) ");
				passNumber = scan.nextInt();
				check = 0;
			}catch(InputMismatchException e){
				System.out.println("t have to be an Integer, please try again. ");
				scan.nextLine();
			}
		}
	}
	//method return the size the user entered
	public int getSize(){
		return size;
	}
	//method return the thread number the user entered
	public int getThreadNum(){
		return threadNumber;
	}
	//method return the pass number the user entered
	public int getPasses(){
		return passNumber;
	}
}






