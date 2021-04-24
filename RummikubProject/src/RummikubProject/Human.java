package RummikubProject;


/**
* represents the human player
*/

public class Human
{
	boolean turn, tileTaken, tilePlaced;
	static Human THE_PLAYER;

	/**
	* Human Constructor to represent the human player
	*/
	public Human()
	{
		turn = true;
		tileTaken = false;
		tilePlaced = false;
		THE_PLAYER = this;
	}


	/**
	* sets whether it is the player's turn or not
	* @param isTurn whether it is or is not the player's turn
	*/
	public static void setTurn(boolean isTurn)
	{
		THE_PLAYER.setTileTurn(isTurn);
	}

	private void setTileTurn(boolean isTurn)
	{
		turn = isTurn;
	}

	/**
	* sets whether the player has placed a tile or not
	* @param tilePl whether the player has placed a tile or not
	*/
	public static void setTilePlaced(boolean tilePl)
	{
		THE_PLAYER.settingTilePlaced(tilePl);
	}

	private void settingTilePlaced(boolean tilePl)
	{
		tilePlaced = tilePl;
	}

	/**
	* sets whether the player has taken a tile or not
	* @param tilePl whether the player has taken a tile or not
	*/
	public static void setTileTaken(boolean tileTak)
	{
		THE_PLAYER.settingTileTaken(tileTak);
	}

	private void settingTileTaken(boolean tileTak)
	{
		tileTaken = tileTak;
	}

	/**
	* Determines whether it is the player's turn or not
	* @return <code>boolean</code> whether it is the player's turn or not
	*/
	public static boolean isTurn()
	{
		return THE_PLAYER.turn;
	}

	/**
	* Determines whether the player has placed a tile or not
	* @return <code>boolean</code> whether the player has placed a tile or not
	*/
	public static boolean isTilePlaced()
	{
		return THE_PLAYER.tilePlaced;
	}

	/**
	* Determines whether the player has taken a tile or not
	* @return <code>boolean</code> whether the player has taken a tile or not
	*/
	public static boolean isTileTaken()
	{
		return THE_PLAYER.tileTaken;
	}


}// END of Human class
