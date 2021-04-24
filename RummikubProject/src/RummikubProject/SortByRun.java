package RummikubProject;
import java.util.Comparator;

public class SortByRun implements Comparator<Tile> {

	public int compare(Tile a, Tile b)
	{
		if(a.getColor().compareTo(b.getColor())==0)
			if(a.getNumber()== b.getNumber()-1 || a.getNumber()-1 == b.getNumber())
				return 0;
		return (int)(a.getNumber()-b.getNumber());
	}
}
