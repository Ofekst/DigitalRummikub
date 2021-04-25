package RummikubProject;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class ButtonsPane extends JScrollPane {
	ButtonsPanel btns;
	
	public ButtonsPane(Rack pool, Rack players_rack, int poolSize,int num,int aiNumOfTiles ) {
	super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	setPreferredSize(new Dimension(1040, 50));
	btns = new ButtonsPanel(pool, players_rack,poolSize,num,aiNumOfTiles);
	setViewportView(btns);
}
	
	public ButtonsPanel getPanel() {
		return btns;
	}
}
	