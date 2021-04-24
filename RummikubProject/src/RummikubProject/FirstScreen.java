package RummikubProject;
import javax.swing.JFrame;

public class FirstScreen extends JFrame {

	MenuPanel menu;
	
	public FirstScreen() {
		setBounds(0, 0, 1000, 500);

		menu= new MenuPanel(this);
		this.getContentPane().add(menu);
		pack();
		this.setVisible(true);
	}
	
}
