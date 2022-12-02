package game;

import java.io.IOException;
import java.io.Console;
import java.lang.String;

public class App {
    public static void main(String[] args)
    {
        try
        {
            int idMatch = 1;
            WSServer server = new WSServer(12345,1);
            System.out.println("Servidor 'Me' concluido!");

            WSClient client = new WSClient("127.0.0.1", 12346);
            System.out.println("Cliente 'Me' concluido!");
            Match match = new Match(5, 1, client, server);
            int res = match.playMatch();
            String winner;
            if(res>0) winner = "Me";
            else if(res==0) winner = "No one";
            else winner = "Them";
            System.out.println(winner + " has won the match " + idMatch);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        
    }
}



