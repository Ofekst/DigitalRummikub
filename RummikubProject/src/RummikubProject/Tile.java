package RummikubProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile implements Comparable<Tile> {
	public enum color {
		R, B, Y, BLUE, JOKER
	}

	public static final int MAX_VALUE = 13;
	public static final int MIN_VALUE = 1;
	private final int JOKER_POINTS = 20;
	private final color c;
	private int number;
	private int tileIndex;

	ImageIcon img;

	public Tile(color c, int number,int index) {
		this.c = c;
		this.tileIndex=index;
		if (c.equals(color.JOKER)) {
			this.number = JOKER_POINTS;
		} else {
			this.number = number;
		}
		img = new ImageIcon("images\\" + this.getNumber() + this.getColor() + ".png");
	}

	public Tile(int index) {
		c = color.JOKER;
		tileIndex=index;
		number = JOKER_POINTS;
		img = new ImageIcon("images\\" + this.getNumber() + this.getColor() + ".png");
	}

	public color getColor() {
		return c;
	}
	
	public int getIndex() {
		return tileIndex;
	}

	public int getNumber() {
		return number;
	}

	public ImageIcon getImageIcon() {
		return img;
	}

	@Override
	public int compareTo(Tile otherObject) {
		Tile other = (Tile) otherObject;
		if (this.number < other.number)
			return -1;
		if (this.number > other.number)
			return 1;
		return 0;
	}

	// Testmethods
	@Override
	public String toString() {
		return "(Color: " + c + ", " + "Number: " + " " + number + ")";
	}

}
