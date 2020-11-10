/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        Gson mapper = new Gson();
        List<Player> players = Arrays.asList(mapper.fromJson(bodyText, Player[].class));
        
        players.stream()
        	   .filter(player -> player.getNationality().equals("FIN"))
        	   .sorted(new PlayerComparator())
        	   .forEach(System.out::println);
    }
}
