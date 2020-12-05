package statistics;

import java.util.LinkedList;

import statistics.matcher.Matcher;

public class Query {

    private LinkedList<Matcher> alkiot;

    public Query() {
        alkiot = new LinkedList<Matcher>();
    }

    public void push(Matcher alkio){
        alkiot.addFirst(alkio);
    }

    public Matcher pop(){
        return alkiot.remove();
    }

    public boolean empty(){
        return alkiot.isEmpty();
    }
	
}
