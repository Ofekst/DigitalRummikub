package RummikubProject;
import javax.swing.*;
import java.awt.*;

/**
* This class is the scroll pane that holds the UserPanel on which the player's <code>Rack</code> is displayed
*/
public class UserPane extends JScrollPane
{
	CardsPanel rackPanel;
	RackListener rackListener;

	/**
	* UserPane Constructor
	* @param playersRack the <code>Rack</code> corresponding to the tiles in the player's hand
	*/
	public UserPane(Rack playersRack)
	{
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(1000,120));
		rackPanel = new CardsPanel(playersRack);
		rackPanel.setPreferredSize(new Dimension(3000,90));
		//rackPanel.setBounds(245, 547, 1038, 192);
		setViewportView(rackPanel);
		rackListener = new RackListener(playersRack);
		rackPanel.addMouseListener(rackListener);
	}

	/**
	* Determines the parent panel of the pane
	* @return the parent <code>UserPanel</code>
	*/
	public CardsPanel getPanel()
	{
		return rackPanel;
	}
}// END of class UserPane