package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.lang.Exception;
import javax.websocket.OnMessage;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import java.io.DataInputStream;
public class WSServer {
    ServerSocket server;
    Socket client;
    DataInputStream dIn;
    ArrayList<Message> buffer = new ArrayList<Message>();
    int idMatch;
    boolean canClose = false;

    public WSServer(int port, int _idMatch) throws IOException {
        server = new ServerSocket(port);
        client = server.accept();
        idMatch = _idMatch;

        System.out.println("Cliente " + 
                        client.getInetAddress().getHostAddress() + " adicionado.");
        
        dIn = new DataInputStream(client.getInputStream());  
    }

    public void waitMessage() throws Exception
    {
        while(!canClose)
        {
            
            // byte[] packetData = new byte[dIn.readString()];
            // dIn.readFully(packetData);
            readMessage(dIn.readUTF(););

        }
    }

    public void readMessage(String message) throws IOException, Exception
    {
        JSONObject json= new JSONObject(message);
        if(idMatch == (int) json.get("idMatch"))
        {
            int idMatch = (int) json.get("idMatch");
            int turn = (int) json.get("turn");
            ePiece adversaryPlay = ePiece.getByName(json.get("adversaryPlay").toString());
            Message message = new Message(idMatch, turn, adversaryPlay);
            buffer.add(message);
        } else throw new Exception("Wrong idMatch received");
    }

    public ePiece checkBuffer(int turn) throws InterruptedException, Exception
    {
        while(buffer.isEmpty())
        {
            TimeUnit.MILLISECONDS.sleep(100);
        }

        for (int i = 0; i< buffer.size(); i++) {
            if(buffer.get(i).turn == turn)
            {
                ePiece them = buffer.get(i).adversaryPlay;
                buffer.remove(i);
                return them;
            }
        }

        throw new Exception("Some message was lost on the way");
    }

    public void toggleServerClosure()
    {
        canClose = true;
    }
    
    protected void finalize() throws IOException
    {
        dIn.close();
        server.close();
        client.close();
    }



}
