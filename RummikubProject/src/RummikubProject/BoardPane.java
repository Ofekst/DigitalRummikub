package RummikubProject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the scroll pane that holds the canvas of the board
 */
public class BoardPane extends JScrollPane {
	CanvasPanel canvasPanel;

	/*
	 * BoardPane Constructor
	 */
	public BoardPane() {
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setPreferredSize(new Dimension(1040, 50));
		canvasPanel = new CanvasPanel(this);
		setViewportView(canvasPanel);
	}

	/*
	 * sets a tile which may be added to the canvas
	 */
	public void setWhichTile(Tile which) {
		canvasPanel.setWhichTile(which);
	}

	/*
	 * gets the parent panel of this pane
	 */

	public CanvasPanel getPanel() {
		return canvasPanel;
	}

}// END of class BoardPane