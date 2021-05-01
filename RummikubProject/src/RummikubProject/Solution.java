package RummikubProject;

/**
* This class is a possible solution for the computer on a turn
*/
public class Solution
{
	Group[] contentGroups = new Group[50];
	int size;

	/*
	* Solution Constructor initialises a <code>Solution</code> with a size of zero Groups
	*/
	public Solution()
	{
		size=0;

		for(int i=0; i<35; i++)
		{
			contentGroups[i] = new Group();
		}
	}

	/*
	* adds a <code>Group</code>
	* @param toAdd the <code>Group</code> to be added
	*/
	public void addGroup(Group toAdd)
	{
		for(int i=0; i<toAdd.size; i++)
		{
			contentGroups[size].addItem(toAdd.getItemTile(i));
		}
		size++;
	}


	public int getNumTiles()
	{
		int numTiles = 0;

		for(int i=0; i<size; i++)
		{
			numTiles += contentGroups[i].size;
		}
		
		return numTiles;
	}
	
	
	/**
	* Determines if a given tile is present
	* @param tileNum the tile number
	* @return <code>boolean</code> whether the tile is a member or not
	*/
	public boolean isMember(Tile tile)
	{
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<contentGroups[i].size; j++)
			{
				if(contentGroups[j].isMember(tile.getIndex()))
					return true;
			}
		}
		return false;
	}

	/**
	* Determines the size (number of Groups)
	* @return size
	*/
	public int getSize()
	{
		return size;
	}

	/**
	* Determines the Groups
	* @return an array of <code>Group</code> objects corresponding to all Groups in this Solution instance
	*/
	public Group[] getGroups()
	{
		return contentGroups;
	}
}// END of class Solution