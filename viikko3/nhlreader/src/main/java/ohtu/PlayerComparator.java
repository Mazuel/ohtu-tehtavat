package ohtu;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return Integer.compare(o2.getGoals() + o2.getAssists(), o1.getGoals() + o1.getAssists());
	}


}
