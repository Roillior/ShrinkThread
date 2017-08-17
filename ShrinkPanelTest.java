/**
 * This class represent the main method
 * 
 * @author Lior Maimon 
 * mmn15 , Question 1
 */


import javax.swing.JFrame;
import javax.swing.UIManager;

public class ShrinkPanelTest {
	//main method of the program
		public static void main(String[] args) {
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e){}
			UserInput inp = new UserInput();
			JFrame frame = new JFrame("Shrink");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400,450);
			frame.add(new ShrinkPanel(inp.getSize(), inp.getThreadNum() ,inp.getPasses()));
			frame.setVisible(true);
		}


}

