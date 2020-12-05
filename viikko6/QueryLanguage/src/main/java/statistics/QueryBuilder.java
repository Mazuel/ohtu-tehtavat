package statistics;

import java.util.LinkedList;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {

	private LinkedList<Matcher> matchers;

	public QueryBuilder() {
		this.matchers = new LinkedList<>();
	}

	public Matcher build() {
		Matcher query = new And(matchers.toArray(new Matcher[0]));
		matchers.clear();
		return query;
	}

	public QueryBuilder playsIn(String team) {
		this.matchers.add(new All(new PlaysIn(team)));
		return this;
	}

	public QueryBuilder hasAtLeast(int value, String category) {
		this.matchers.add(new HasAtLeast(value, category));
		return this;
	}

	public QueryBuilder hasFewerThan(int value, String category) {
		this.matchers.add(new HasFewerThan(value, category));
		return this;
	}

	public QueryBuilder oneOf(Matcher... matchers) {
		this.matchers.add(new Or(matchers));
		return this;
	}

}
