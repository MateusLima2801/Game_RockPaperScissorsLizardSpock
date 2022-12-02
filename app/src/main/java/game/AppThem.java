package  app.src.main.java.game;
import java.io.IOException;

public class AppThem {
    public static void main(String[] args) throws Exception 
    {
        try
        {
            WSClient client = new WSClient("127.0.0.1", 12345);
            System.out.println("Cliente 'Them' concluido!");
            WSServer server = new WSServer(12346, 1);
            System.out.println("Servidor 'Them' concluido!");
            
            Match match = new Match(1, 5, client, server);
            int res = match.playMatch();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
