package game;

public class AppThem {
    public static void main(String[] args) throws Exception 
    {
        try
        {
            SocketClient client = new SocketClient("localhost", 12002);
            System.out.println("Cliente 'Them' concluido!");
            SocketServer server = new SocketServer(12003, 1);
            System.out.println("Servidor 'Them' concluido!");
            
            Match match = new Match(1, 5, client, server);
            int res = match.playMatch();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
