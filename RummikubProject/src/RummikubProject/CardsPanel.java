package RummikubProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class CardsPanel extends JPanel {
	
	  Rack playersRack;
	  Image img,originalRack, blankImage;
	  
	  Graphics tilesGraphics;
	
	public CardsPanel(Rack r) 
	{
		super();
		this.playersRack=r;
		System.out.println("CardsPanel");
	}

	/**
	* draw a given tile on the panel
	* @param i the index of the tile in the <code>Rack</code>
	*/
	public void drawTile(Tile tileId,int i)
	{
			
		if(originalRack == null)
		{
			originalRack = createImage(getWidth(), getHeight());
			tilesGraphics = originalRack.getGraphics();
			tilesGraphics.setColor(Color.GRAY);
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}
		
		if(i<playersRack.sizeOfRack()) 
		{
		
			System.out.println("The card that supposed to be is: "+tileId.getNumber()+" in color: "+tileId.getColor());
			if(tileId.getNumber()==20)
				img = new ImageIcon("images\\JOKER.png").getImage();
			else
				img = new ImageIcon("images\\"+tileId.getNumber()+tileId.getColor()+".png").getImage();

		}
		else
		{
			img = new ImageIcon("images\\0blank.png").getImage();
		}	
		
		tilesGraphics = originalRack.getGraphics();
		tilesGraphics.drawImage(img,i*45,0,50,100,this);
		
	}
	public void paint(Graphics g)
	{
	
		if(originalRack == null)
		{
			originalRack = createImage(getWidth(), getHeight());
			tilesGraphics = originalRack.getGraphics();
			tilesGraphics.setColor(Color.GRAY);
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}
		g.drawImage(originalRack,0,0,this);
		 
		 
	}	
}// END of class CardsPanel
