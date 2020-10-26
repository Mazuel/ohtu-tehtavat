package ohtuesimerkki;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
	 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void shouldFindPlayer() {
    	Player player = stats.search("Lemieux");
    	assertEquals(player.getName(), "Lemieux");
    }
    
    @Test
    public void shouldNotFindPlayer() {
    	Player player = stats.search("BaronVonGoalie");
    	assertEquals(player, null);
    }
    
    @Test
    public void shouldFindCorrectAmountOfPlayers() {
    	assertEquals(stats.team("EDM").size(), 3);
    }
    
    @Test
    public void shouldFindTwoTopScorers() {
    	assertEquals(stats.topScorers(1).size(), 2);
    }
}

