package RummikubProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	private JButton humanbut,aibut;
	private Image img, btn1Image,btn2Image;
    public Rummikub gamePanel;
    JPanel panel;
    JFrame fr;

	
	public MenuPanel(JFrame frame)
	{
		super();
		this.fr=frame;
		this.setPreferredSize(new Dimension(1300,750));
		this.setLayout(null);
		
		img = new ImageIcon("images\\openbg.png").getImage();
		humanbut = new JButton(new ImageIcon("images\\1v1but.png"));
		aibut = new JButton(new ImageIcon("images\\AIbut.png"));

		
		humanbut.setBounds(400, 270, 375, 100);
		aibut.setBounds(400, 470, 375, 100);



		humanbut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	gamePanel = new Rummikub(0);
	        	fr.dispose();
            }
        });
		
		
		aibut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	gamePanel = new Rummikub(1);
	        	fr.dispose();
            }
        });
    
		
		this.add(humanbut);
		this.add(aibut);


	}
	   public void paintComponent(Graphics g) {
	        g.drawImage(img, 0, 0, null);
	    }
}

