package RummikubProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ButtonsPanel extends JPanel {

	private JLabel opnumOftileslbl,stklbl;
	private JButton startBtn,takeBtn,endTurnBtn,undoBtn,sortByGroupBtn,sortByRunBtn;
	private TextArea infoText;
	Rack the_pool, players_rack;
	int poolSize,aiNumOfTiles;
	Rummikub gameFr;
	int checker;
	
	public ButtonsPanel(Rack pool, Rack playersRack,int poolS,int num,int aiNumOfTiles) {
		
		super();
		
		this.checker=num;
		System.out.println("Buttons Panel");

		the_pool = pool;
		players_rack = playersRack;
		this.poolSize=poolS;
		this.aiNumOfTiles=aiNumOfTiles;
		
		this.setBackground(Color.GRAY);
		setPreferredSize(new Dimension(240,625));

		this.setLayout(null);
		
		opnumOftileslbl = new JLabel("AI's number Of tiles:"+aiNumOfTiles);
		opnumOftileslbl.setBounds(0, 11, 200, 56);
		opnumOftileslbl.setForeground(Color.RED);
		opnumOftileslbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.add(opnumOftileslbl);

		stklbl = new JLabel("Num of Cards in stack:" + poolSize);
		stklbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		stklbl.setBounds(0, 120, 217, 71);
		this.add(stklbl);
		
		
		takeBtn = new JButton("Take tile");
		takeBtn.setBackground(Color.RED);
		takeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		takeBtn.setBounds(10, 372, 109, 39);
		this.add(takeBtn);
		
		endTurnBtn = new JButton("End turn");
		endTurnBtn.setBackground(Color.CYAN);
		endTurnBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		endTurnBtn.setBounds(128, 372, 109, 39);
		this.add(endTurnBtn);
		
		undoBtn = new JButton("Undo");
		undoBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		undoBtn.setBackground(Color.ORANGE);
		undoBtn.setBounds(56, 426, 109, 39);
		this.add(undoBtn);
		
		sortByGroupBtn = new JButton("Sort by group");
		sortByGroupBtn.setBackground(Color.BLUE);
		sortByGroupBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sortByGroupBtn.setBounds(33, 485, 171, 39);
		this.add(sortByGroupBtn);
		
		sortByRunBtn = new JButton("Sort by run");
		sortByRunBtn.setBackground(Color.YELLOW);
		sortByRunBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sortByRunBtn.setBounds(33, 535, 171, 39);
		this.add(sortByRunBtn);		
		
		Font f = new Font("Courier", Font.BOLD, 16);
		infoText = new TextArea();
		infoText.setBounds(10, 219, 227, 135);
		this.add(infoText);
		infoText.setFont(f);
			
		ActionEventHandler handler = new ActionEventHandler();

		takeBtn.addActionListener(handler);
		endTurnBtn.addActionListener(handler);
		undoBtn.addActionListener(handler);
		sortByGroupBtn.addActionListener(handler);
		sortByRunBtn.addActionListener(handler);

		this.setVisible(true);
	}
	
	private class ActionEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource()==takeBtn)
			{
				takeTile();
			}
			else if(event.getSource()==endTurnBtn)
			{
				if(Human.isTilePlaced() || Human.isTileTaken())
				{
					infoText.setText("Checking board");
					boolean valid = checkBoard();

					if(valid)
					{
						if(Rummikub.getPlayersRack().sizeOfRack()==0)
						{
							JOptionPane.showMessageDialog(null, "Well done! You won!", "Rummikub", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						else
						{
							Rummikub.getPlayersRack().setNewRack();
							infoText.setText("Computer's turn");
							Human.setTurn(false);
							Human.setTilePlaced(false);
							Human.setTileTaken(false);
							Human.setTurn(true);
							if(checker==1)
								Computer.takeTurn();
						}
					}
				}
				else if(Human.isTurn())
				{
					JOptionPane.showMessageDialog(null, "You may not end you turn. You have not placed a tile, or taken one.", "Rummiub", JOptionPane.ERROR_MESSAGE);
					infoText.setText("You must place a\ntile or take one.");
				}
				else if(!Human.isTurn())
				{
					JOptionPane.showMessageDialog(null, "It is not your turn to end!", "Rummikub", JOptionPane.ERROR_MESSAGE);
				}
				
			}

			else if(event.getSource()==sortByGroupBtn)
			{
				infoText.setText("Tiles sorted.");
				sortTilesByGroup();
			}
			else if(event.getSource()==sortByRunBtn)
			{
				infoText.setText("Tiles sorted.");
				sortTilesByRun();
			}
			
			else if(event.getSource()==undoBtn)
			{
				if(Human.isTurn())
				{
					CanvasPanel.undoBoard();
					infoText.setText("Previous Move");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "It is not your turn", "Rummikub", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}//END of class ActionEventHandler
	
	void incAInumOfTiles()
	{		
		aiNumOfTiles=Rummikub.getCompsRack().sizeOfRack();
		opnumOftileslbl.setText("AI's number Of tiles:"+aiNumOfTiles);

	}
	
	void decSizeOfPool()
	{		
		poolSize=the_pool.sizeOfRack();
		stklbl.setText("Num of Cards in stack:" + poolSize);		
	}
	void output(String outStr)
	{
		infoText.setText(outStr);
	}
	//--------------------------Taking tile button------------------------------------------
	private void takeTile()
	{


		if(Human.isTilePlaced())
		{
			JOptionPane.showMessageDialog(null, "You have placed a tile, you may not take one.", "Rummikub", JOptionPane.ERROR_MESSAGE);
		}
		else if(Human.isTileTaken())
		{
			JOptionPane.showMessageDialog(null, "You have already taken a tile. You may take another one.", "Rummikub", JOptionPane.ERROR_MESSAGE);
		}
		else if(!Human.isTurn())
		{
			JOptionPane.showMessageDialog(null, "It is not your turn","Rummikub",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
		
			Human.setTileTaken(true);
			Tile newTile = the_pool.removeTile();
			if(newTile!=null)
			{
				players_rack.addtile(newTile);
				gameFr.showPlayersTiles(players_rack);
				//System.out.println(players_rack.toString());
				infoText.setText("Tile taken.");
				decSizeOfPool();
				//stklbl.setText("Num of Cards in stack:" + poolSize);
			}
			else
			{
				// pop up dialog box to quit...
				JOptionPane.showMessageDialog(null, "No more tiles in pool.\nQuiting...", "Rummikub", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}
	//--------------------------Sort Tiles by group button------------------------------------------

	public void sortTilesByGroup()
	{
		players_rack.sortedByGroup();
		System.out.println(players_rack.toString());
		gameFr.showPlayersTiles(players_rack);
	}
	//--------------------------Sort Tiles by Run button------------------------------------------
	public void sortTilesByRun()
	{
		players_rack.sortedByRun();
		System.out.println(players_rack.toString());
		gameFr.showPlayersTiles(players_rack);

	}
	
	/*
	* sets the Rummikub object associated with this object
	*/
	public void setGameFrame(Rummikub gf)
	{
		gameFr = gf;
		return;
	}
	
	//--------------------------Checking the board------------------------------------------
	private boolean checkBoard()
	{
		return CanvasPanel.checkBoardValidity();
	}

}
