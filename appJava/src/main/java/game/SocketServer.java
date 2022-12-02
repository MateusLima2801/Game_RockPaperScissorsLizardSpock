package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.lang.Exception;

import org.json.JSONObject;

import java.io.DataInputStream;
public class SocketServer {
    ServerSocket server;
    Socket client;
    DataInputStream dIn;
    ArrayList<Message> buffer = new ArrayList<Message>();
    int idMatch;

    public SocketServer(int port, int _idMatch) throws Exception {
        server = new ServerSocket(port);
        client = server.accept();
        idMatch = _idMatch;

        System.out.println("Cliente " + 
                        client.getInetAddress().getHostAddress() + " adicionado.");
        
        dIn = new DataInputStream(client.getInputStream());  
    }

    public void waitMessage() throws Exception
    {
        String message = dIn.readUTF();
        readMessage(message);
    }

    public void readMessage(String message) throws IOException, Exception
    {
        JSONObject json= new JSONObject(message);
        if(idMatch == (int) json.get("idMatch"))
        {
            int idMatch = (int) json.get("idMatch");
            int turn = (int) json.get("turn");
            ePiece adversaryPlay = ePiece.getByName(json.get("adversaryPlay").toString());
            Message mess = new Message(idMatch, turn, adversaryPlay);
            buffer.add(mess);
        } else throw new Exception("Wrong idMatch received");
    }

    public ePiece checkBuffer(int turn) throws InterruptedException, Exception
    {
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
    
    protected void finalize() throws IOException
    {
        dIn.close();
        server.close();
        client.close();
    }



}
