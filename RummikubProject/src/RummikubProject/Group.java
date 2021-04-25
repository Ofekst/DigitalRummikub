package RummikubProject;

import java.util.ArrayList;

import RummikubProject.Tile.color;

/**
* Corresponds to a set or sequence
*/

public class Group
{
	int size;
	ArrayList<Tile> tiles= new ArrayList<Tile>(50);// 50 is the max tiles that could be in a line

	/*
	* Creates an empty Group 
	*/
	public Group()
	{
		size = 0;
	}


	/*
	* Determines the number of tiles in the group
	*/
	public int getSize()
	{
		return size;
	}

	/*
	* Checks if the group is a valid set or sequence
	*/
	public boolean isValidGroup()
	{
		boolean validGroup = (isSet() || isSequence());
		return validGroup;
	}

	/*
	* Adds a tile to a group
	*/
	public void addItem(Tile t)
	{
		tiles.add(t);
		size++;
	}

	/*
	* Determines a tile
	*/
	public int getItem(int num)
	{
		return tiles.get(num).getIndex();
	}
	/* */
	
	public Tile getItemTile(int num)
	{
		return tiles.get(num);
	}
	

	/*
	* Determines if a given tile is present in the group or not
	*/
	/*
	boolean isMember(Tile tile)
	{
		for(int i=0; i<size; i++)
		{
			if(tiles.get(i).getNumber() == tile.getNumber() && tiles.get(i).getColor() == tile.getColor() && 
				tiles.get(i).getNumber() == tile.getNumber()	)
				return true;
		}
		return false;
	}
*/
	boolean isMember(int tileNum)
	{
		for(int i=0; i<size; i++)
		{
			if(tiles.get(i).getIndex() == tileNum)
				return true;
		}
		return false;
	}
	
	/*
	* Prints the group information to the screen
	*/
	public void print()
	{
		for(int i=0; i<size; i++)
		{
			System.out.println( tiles.get(i).getColor()+ " "
						+ tiles.get(i).getNumber());
		}
		System.out.println("");
	}
	
	
	// Checks if the group is a valid set of tiles
	private boolean isSet()
	{
		boolean validSet = true;

		// Test size of set
		if(size>4 || size<3)
		{
			validSet = false;
		}

		// Check all tiles are the same value
		int val = 0;
		int setVal = 0;
		boolean setValueSet = false;
		boolean isJoker = false;
		Tile currTile;

		for(int i=0; i<size; i++)
		{
			currTile = tiles.get(i);
			val = currTile.getNumber();
			isJoker = currTile.getColor().equals(color.JOKER);

			if(!isJoker)
			{
				if(setValueSet)
				{
					if(val!=setVal)
					{
						validSet = false;
					}
				}
				else
				{
					setVal = val;
					setValueSet = true;
				}
			}
		}

		// Test tiles are different colors
		boolean redFlag = false;
		boolean blueFlag = false;
		boolean yellowFlag = false;
		boolean blackFlag = false;

		for(int i=0; i<size; i++)
		{
			color c = tiles.get(i).getColor();

			if(c.equals(color.R))
			{
				if(redFlag==true)
				{
					validSet = false;
				}
				redFlag = true;
			}

			if(c.equals(color.BLUE))
			{
				if(blueFlag==true)
				{
					validSet = false;
				}
				blueFlag = true;
			}

			if(c.equals(color.Y))
			{
				if(yellowFlag==true)
				{
					validSet = false;
				}
				yellowFlag = true;
			}

			if(c.equals(color.B))
			{
				if(blackFlag==true)
				{
					validSet = false;
				}
				blackFlag = true;
			}
		}

		return validSet;
	}


	// checks if the group is a valid sequence
	private boolean isSequence()
	{
		boolean validSequence = true;

		// check sequence size
		if(size<3 || size>13)
		{
			validSequence = false;
		}

		// check all one color 
		color col;
		color sequenceCol = color.R;
		boolean isJoker = false;
		boolean colorSet = false;
		Tile currTile;

		for(int i=0; i<size; i++)
		{
			currTile = tiles.get(i);
			col = currTile.getColor();
			isJoker = col.equals(color.JOKER);
			
			if(!isJoker)
			{
				if(colorSet)
				{
					if(!col.equals(sequenceCol))
					{
						validSequence = false;
					}
				}
				else
				{
					sequenceCol = col;
					colorSet = true;
				}
			}
		}
		
		// test tiles are sequential 
		int vals[] = new int[size];
		for(int i=0; i<size; i++)
		{
			currTile = tiles.get(i);
			vals[i] = currTile.getNumber();

			if(currTile.getColor().equals(color.JOKER))
			{
				vals[i] = -1;
			}
		}
		
		// test vals[]
		int joker_count = 0;
		boolean sequenceStarted = false;

		for(int i=0; i<size; i++)
		{
			if(vals[i] == -1)
			{
				if(sequenceStarted==true)
				{
					joker_count++;
				}
			}
			else
			{
				if(sequenceStarted==false)
				{
					sequenceStarted = true;
					joker_count = 0;
				}
				else
				{
					if(vals[i-joker_count-1]==vals[i]-joker_count-1)
					{
						joker_count++;
					}
					else
					{
						validSequence = false;
					}
				}
			}

		}

		// only test if everything else fits
		if(validSequence)
		{
			int upper_bound = 0;
			int lower_bound = 0;
			// check bounds of sequence (<13 and >1)
			for(int i=0; i<size; i++)
			{
				upper_bound = vals[i] + size - 1 - i;

				if(upper_bound > 13)
				{
					validSequence = false;
				}
				lower_bound = vals[i] - i;

				if(lower_bound < 1 && vals[i] != -1)
				{
					validSequence = false;
				}
			}
		}
		return validSequence;
	}


	/*
	* tests one Group against another to see if they contain one or more of the same tiles
	*/
	public boolean clashesWith(Group otherGroup)
	{
		boolean clash = false;

		for(int i=0; i<size; i++)
		{
			for(int j=0; j<otherGroup.size; j++)
			{
				if(getItem(i)==otherGroup.getItem(j))
				{
					clash = true;
				}
			}
		}
		return clash;
	}


	@Override
	public String toString() {
		return "Group [size=" + size + ", tiles=" + tiles + "]";
	}
	
}// END of class Group
