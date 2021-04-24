package RummikubProject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class mainRum {
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		FirstScreen gameView = new FirstScreen();

		gameView.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}
