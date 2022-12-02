package game;
import java.lang.String;

public class App {
    public static void main(String[] args)
    {
        try
        {
            int idMatch = 1;
            SocketServer server = new SocketServer(12345,1);
            System.out.println("Servidor 'Me' concluido!");

            SocketClient client = new SocketClient("127.0.0.1", 12346);
            System.out.println("Cliente 'Me' concluido!");
            Match match = new Match(1, 5, client, server);
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



