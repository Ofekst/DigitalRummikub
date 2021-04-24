package RummikubProject;
import java.util.Comparator;

public class SortByGroup implements Comparator<Tile> {

	public int compare(Tile a, Tile b)
	{
		return a.getColor().compareTo(b.getColor());
	}


	
	
}