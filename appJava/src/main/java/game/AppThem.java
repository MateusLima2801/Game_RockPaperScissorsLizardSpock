package game;

public class AppThem {
    public static void main(String[] args) throws Exception 
    {
        try
        {
            SocketClient client = new SocketClient("127.0.0.1", 12345);
            System.out.println("Cliente 'Them' concluido!");
            SocketServer server = new SocketServer(12346, 1);
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
