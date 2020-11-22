package ohtu;

public class TennisGame {

	private int m_score1 = 0;
	private int m_score2 = 0;
	private String player1Name;
	private String player2Name;
	private final String[] tieScores = { "Love-All", "Fifteen-All", "Thirty-All", "Forty-All", "Deuce" };
	private final String[] scores = { "Love", "Fifteen", "Thirty", "Forty" };

	public TennisGame(String player1Name, String player2Name) {
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	public void wonPoint(String playerName) {
		if (playerName == player1Name)
			m_score1 += 1;
		else
			m_score2 += 1;
	}

	public String getScore() {
		String score;
		if (m_score1 == m_score2) {
			score = tieScores[m_score1];
		} else if (m_score1 >= 4 || m_score2 >= 4) {
			int scoreDifference = m_score1 - m_score2;
			if (scoreDifference == 1)
				score = String.format("Advantage %s", player1Name);
			else if (scoreDifference == -1)
				score = String.format("Advantage %s", player2Name);
			else if (scoreDifference >= 2)
				score = String.format("Win for %s", player1Name);
			else
				score = String.format("Win for %s", player2Name);
		} else {
			score = String.format("%s-%s", scores[m_score1], scores[m_score2]);
		}
		return score;
	}
}