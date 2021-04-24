package RummikubProject;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JOptionPane;

import RummikubProject.Tile.color;

/**
* This class Listens for clicks and drags on the board
*/
public class BoardListener implements MouseListener, MouseMotionListener
{
	CanvasPanel board;
	boolean dragging = false;
	int fromWidthIndex = 0;
	int fromHeightIndex = 0;
	int toWidthIndex = 0;
	int toHeightIndex = 0;


	/*
	* BoardListen Constructor
	*/
	public BoardListener(CanvasPanel brd)
	{
		board = brd;
	}

	/*
	* detects when the mouse is pressed on the board
	*/
	public void mousePressed(MouseEvent event)
	{
		int x_pos = event.getX();
		int y_pos = event.getY();
		int widthIndex = x_pos/45;
		int heightIndex = y_pos/60;

		//test should it be added first
		if(Rummikub.adding())
		{
			if(widthIndex<board.boardWidth || heightIndex<board.boardHeight)
			{
				if(CanvasPanel.placeEmpty(heightIndex, widthIndex))
				{
					CanvasPanel.addaTile(heightIndex, widthIndex);
					Human.setTilePlaced(true);
					Rummikub.setAdding(false);
					Rummikub.tellUser("Tile placed");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You chose a place that already contains a tile.", "Rummikub", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
		{

			if(!CanvasPanel.placeEmpty(heightIndex, widthIndex))
			{
				Tile tile = CanvasPanel.getTileVal(heightIndex, widthIndex);
				int value = tile.getNumber();
				color tileColor = tile.getColor();
				Rummikub.tellUser(tileColor + " " + value);
			}
			else
			{
				Rummikub.tellUser("No tile in that position");
			}
		}
	}

	/*
	* detects when the mouse is released on the board
	*/
	public void mouseReleased(MouseEvent event)
	{
		// test if its their go first
		if(dragging && Human.isTurn() && Human.isTilePlaced())
		{
			toWidthIndex = event.getX()/45;
			toHeightIndex = event.getY()/60;

			if(toWidthIndex<20 && toHeightIndex<10)
			{
				dragging = false;
				CanvasPanel.moveTile(fromWidthIndex, toWidthIndex, fromHeightIndex, toHeightIndex);
				
			}
		}
		else if(!Human.isTilePlaced())
		{
			JOptionPane.showMessageDialog(null, "You must place a tile before you manipulate the board.", "Rummikub", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	* detects when the mouse is dragged on the board
	*/
	public void mouseDragged(MouseEvent event)
	{

		//test if you're in the middle of placing tile, and that your not already dragging
		if(!Rummikub.adding() && !dragging)
		{
			fromWidthIndex = event.getX()/45;
			fromHeightIndex = event.getY()/60;
			dragging = true;
		}
	}

	public void mouseMoved(MouseEvent event)
	{
	}

	public void mouseClicked(MouseEvent event)
	{
	}


	public void mouseEntered(MouseEvent event)
	{
	}

	public void mouseExited(MouseEvent event)
	{
	}
}// END of class BoardListener
