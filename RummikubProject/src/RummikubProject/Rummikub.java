package RummikubProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import RummikubProject.Tile.color;


public class Rummikub extends JFrame {
	static Rummikub GAME_FRAME;
	
	BoardPane board;
	UserPane userPane,compPane;
	ButtonsPane btnsPanel;
	JPanel top;

	final int startRackSize = 14;
	int poolSize=0, aiNumOfTiles=0;
	Rack pool;
	Rack players_rack;
	Rack comps_rack;

	Computer compPlayer;
	Human humanPlayer;
	boolean addTile = false;
	
	public Rummikub(int num) {
		
		super();
				

		pool = new Rack();
		players_rack = new Rack(pool, startRackSize);
		players_rack.setNewRack();
		
		if(num ==1) 
		{
			comps_rack = new Rack(pool, startRackSize);
			compPlayer=new Computer(comps_rack);
			compPane=new UserPane(comps_rack);
			aiNumOfTiles=comps_rack.sizeOfRack();
		}
		poolSize=pool.sizeOfRack();
		humanPlayer = new Human();
		
		board = new BoardPane();
		userPane = new UserPane(players_rack);
		
	//	btnsPanel = new ButtonsPanel(pool, players_rack,poolSize,num,aiNumOfTiles);
		btnsPanel=new ButtonsPane(pool, players_rack,poolSize,num,aiNumOfTiles);
		
		top = new JPanel();
		

		setBounds(0, 0, 1300, 750);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		top.setLayout(new BorderLayout());
		top.add(board, BorderLayout.EAST);
		top.add(btnsPanel, BorderLayout.WEST);
		top.setPreferredSize(new Dimension(1300, 600));
		
		if(num==1) {
			getContentPane().add(compPane, BorderLayout.NORTH);
			getContentPane().add(top, BorderLayout.CENTER);
			getContentPane().add(userPane, BorderLayout.SOUTH);
		}
		else {
			getContentPane().add(top, BorderLayout.NORTH);
			getContentPane().add(userPane, BorderLayout.SOUTH);
		}
	
		pack();
		setVisible(true);
		btnsPanel.getPanel().setGameFrame(this);
		GAME_FRAME = this;


		showPlayersTiles(players_rack);
		showCompsTiles(comps_rack);

		
	}
	

	/*
	* checks if a tile is currently being placed on the board
	* @return <code>boolean</code> whether there is a tile being placed or not
	*/
	public static boolean adding()
	{
		return GAME_FRAME.addTile;
	}

	/*
	* Determines the <code>Rack</code> belonging to the human player
	*/
	public static Rack getPlayersRack()
	{
		return GAME_FRAME.players_rack;
	}

	/**
	* Determines the <code>Rack</code> corresponding to the pool of tiles
	* @return the <code>Rack</code> object of the pool
	*/
	public static Rack getPool()
	{
		return GAME_FRAME.pool;
	}
	
	

	public static ButtonsPanel getButtonsPanel()
	{
		return GAME_FRAME.btnsPanel.getPanel();
	}
	
	
	static CardsPanel getCompPanel()
	{
		 //GAME_FRAME.showCompsTiles(getCompsRack());
		return GAME_FRAME.compPane.getPanel();
	}
	/**
	* Determines the computer's <code>Rack</code>
	* @return the <code>Rack</code> corresponding to the tiles in the computer's hand
	*/
	public static Rack getCompsRack()
	{
		return GAME_FRAME.comps_rack;
	}

	/**
	* set whether a tile is being added or not
	* @param isAdding whether a tile is being added or not
	*/
	public static void setAdding(boolean isAdding)
	{
		GAME_FRAME.setInternalAdd(isAdding);
	}

	private void setInternalAdd(boolean isIt)
	{
		addTile = isIt;
	}


	/*
	* places a given tile on the board
	* @param tileIndex the index of the tile on the player's <code>Rack</code> to put down
	* @param who <code>String</code> ("Comp" if computer) is placing the tile
	*/
	public static void putTileDown(Tile tile,int tileIndex, String who)

	{
		GAME_FRAME.internalPutTile(tile,tileIndex, who);
	}

	private void internalPutTile(Tile tile,int tileIndex, String who)
	{
		
		players_rack.removeSpecificTile(tileIndex);
		players_rack.display(userPane.getPanel());
		int blankIndex = players_rack.sizeOfRack();

		userPane.getPanel().drawTile(null,blankIndex);
		userPane.getPanel().repaint();
		board.setWhichTile(tile);
		
	}
	
	/**
	* outputs a message to the user in the info area of the <code>InfoPanel</code>
	* @param infoStr the <code>String</code> to be outputted
	*/
	public static void tellUser(String infoStr)
	{
		GAME_FRAME.btnsPanel.getPanel().output(infoStr);
	}

	
	/*
	* displays the players tiles on the <code>UserPanel</code> associated with it
	* @param playersRack the <code>Rack</code> corresponding to the tiles in the player's hand
	*/
	public void showPlayersTiles(Rack playersRack)
	{
		playersRack.display(userPane.getPanel());
	}
	public void showCompsTiles(Rack compRack)
	{
		compRack.display(compPane.getPanel());
	}	

}
