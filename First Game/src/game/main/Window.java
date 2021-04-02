package game.main;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas
{
	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window(int width, int height, String title, Game game)
	{
		//Creating a JFrame (Window Frame)
		JFrame f = new JFrame (title);
		
		//Setting up the size of the window
		f.setPreferredSize(new Dimension(width,height));
		f.setMaximumSize(new Dimension(width,height));
		f.setMinimumSize(new Dimension(width,height));
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes the 'X' button on top work
		f.setResizable(false); //Can't resize the window
		f.setLocationRelativeTo(null); //Start location is in the middle of the screen, as opposed to the default of top left
		f.add(game); //Adding game class to frame
		f.setVisible(true); //Setting frame to visible (to actually see it)
		game.start(); //Starts game
		
	}
	

}
