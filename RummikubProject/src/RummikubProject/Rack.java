package RummikubProject;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import RummikubProject.Tile.color;


public class Rack
{
	private static final int MAX_BAG_SIZE = 106;

	int previousSize = 0,size=0, index=0;
	ArrayList<Tile> tiles = new ArrayList<Tile>(MAX_BAG_SIZE);
	ArrayList<Tile> previousRack = new ArrayList<Tile>(MAX_BAG_SIZE);

	private Random randomGenerator;

	CardsPanel displayPlace;

	  /*
	   * Initializes all tiles (2 x all different type of tiles)
	   * and a randomGenerator in order to remove tiles randomly.
	   */
	public Rack()
	  {
		this.size=106;

		tiles = new ArrayList<>(MAX_BAG_SIZE);
	    for (color color : color.values()) 
	    {
	      if (color != color.JOKER) {
	        for (int i = Tile.MIN_VALUE; i <= Tile.MAX_VALUE; i++) {
	          tiles.add(new Tile(color, i,index));
	          index++;
	          tiles.add(new Tile(color, i,index));
	          index++;
	        }
	      }
	    }
	    tiles.add(new Tile(index));
	    index++;
	    tiles.add(new Tile(index));
	    for(int i=0; i<tiles.size();i++)
	    {
	    	
	    	System.out.println("["+tiles.get(i).getNumber()+" , "+tiles.get(i).getColor()+" , "+tiles.get(i).getIndex()+"]");
	    }
	    randomGenerator = new Random();
	    
	  }
	

	/**
	* Rack Constructor which sets up a player's rack
	* @param the_pool the pool from which the tiles are to be taken
	* @param the initial size the <code>Rack</code> is to be
	*/
	public Rack(Rack the_pool, int initialSize)
	{
		size = 0;
		for(int i=0; i<initialSize; i++)
		{
			Tile addingTile = the_pool.removeTile();
			this.addtile(addingTile);
		}
		
	}
	
	/*
	* sets the <code>Rack</code> as what it currently is
	*/
	public void setNewRack()
	{
		System.out.println("setNewRack size: "+size);
		
		for(int i=0; i<size; i++)
		{
			previousRack.set(i, tiles.get(i));
		}
		previousSize = size;

	}
	
	  //Removes a Tile randomly.
	  Tile removeTile() {
		 if(size == 0)
			 return null;
		 Tile t = tiles.remove(randomGenerator.nextInt(sizeOfRack()));
	     this.size--;
	     return t;
	  }
	  
	  void removeSpecificTile(int index) {
		  tiles.remove(index);
		  this.size--;
		  }
	  
		/*
		* removes a given tile by tile number
		* @param tileIndex the index within the <code>Rack</code> of the tile to remove
		*/
		public void removeTileNumber(int tileNum)
		{
			System.out.println("Rack before remove "+sizeOfRack());

			for(int i=0; i<size; i++)//size
			{
				if(getTileIndex(i)==tileNum)
				{
					removeSpecificTile(i);
				}

			}
			System.out.println("Rack after remove "+sizeOfRack());
		}
	  

	  int sizeOfRack() {
	    return tiles.size();
	 }
	  
	  void addtile(Tile t) {
		  this.size++;
		  this.tiles.add(t);
		  this.previousRack.add(null);
		  return;
	  }
	  

	  ArrayList<Tile> gettiles() {
	    return tiles;
	  }
	  
	  public int getTileNumber(int tileIndex)
	  {
		return tiles.get(tileIndex).getNumber();
	  }
	  public int getTileIndex(int tileIndex)
	  {
		return tiles.get(tileIndex).getIndex();
	  }
	  public color getTileColor(int tileIndex)
	  {
		  return tiles.get(tileIndex).getColor();
	  }
  
	/*
	* displays the player's <code>Rack</code> on the given <code>CardsPanel</code>
	* @param dispPlace the <code>CardsPanel</code> object to display the <code>Rack</code> on
	*/
	public void display(CardsPanel dispPlace)
	{
		displayPlace = dispPlace;
		
		for(int i=0; i<this.tiles.size(); i++)
		{
			displayPlace.drawTile(tiles.get(i),i);	
			
		}
		
		displayPlace.paint(displayPlace.getGraphics());
		System.out.println("length: "+this.tiles.size());

	}		
		/**
		* sorts the <code>Rack</code> by same number and different color 
		*/
		public void sortedByGroup()
		{
			Collections.sort(tiles, new SortByRun());
			
			Tile tempTile;
			for (int j=tiles.size()-1; j>0; j--)
			{
				for (int i=0; i<j; i++)
				{
					if(tiles.get(i).getNumber()==tiles.get(i+1).getNumber())
						if(!tiles.get(i).getColor().equals(tiles.get(i+1).getColor()))
						{
							tempTile = tiles.get(i);
							tiles.set(i, tiles.get(i+1));
							tiles.set(i+1, tempTile);

						}
				}
			}
		}   	
		
		/**
		* sorts the <code>Rack</code> by same color and series of numbers
		*/
		public void sortedByRun()
		{		
			Collections.sort(tiles, new SortByGroup());

			Tile tempTile;
			for (int j=tiles.size()-1; j>0; j--)
			{
				for (int i=0; i<j; i++)
				{
					if(tiles.get(i).getColor().equals(tiles.get(i+1).getColor()))
						if(tiles.get(i).getNumber()>tiles.get(i+1).getNumber())
						{
							tempTile = tiles.get(i);
							tiles.set(i, tiles.get(i+1));
							tiles.set(i+1, tempTile);

						}
				}
			}
		}
		
	  // for test
	  @Override
	  public String toString() {
	    StringBuilder stringBuilder = new StringBuilder();
	    stringBuilder.append("bag size: ").append(sizeOfRack()).append('\n');
	    for (Tile tile : tiles) {
	      stringBuilder.append('(').append(tile.getColor()).append(", ").append(tile.getNumber()).append(", ").append(tile.getIndex()).append(")\n");
	    }
	    return stringBuilder.toString();
	  }


	  public void reset()
		{
			int i;

			System.out.println("Previous size: "+previousSize);

			for(i=0; i<previousSize; i++)
			{
				
				System.out.println(previousRack.get(i).getNumber()+" , "+ previousRack.get(i).getColor());
			}
			addItemsToArray(tiles);
			for(i=0; i<previousSize; i++)
			{
				tiles.set(i, previousRack.get(i));
			}
			size =previousSize;
			display(displayPlace);
		}
	  
	  public void addItemsToArray(ArrayList<Tile> t)
	  {
		  for(int i=t.size();i<previousSize;i++)
		  {
			  t.add(null);
		  }
	  }

}
