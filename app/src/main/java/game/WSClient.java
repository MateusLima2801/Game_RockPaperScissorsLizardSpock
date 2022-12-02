package game;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import org.json.simple.JSONObject;
import javax.print.DocPrintJob;

import java.io.DataOutputStream;

public class WSClient {
    Socket client;
    DataOutputStream dOut;

    public WSClient(String ip ,int port) throws IOException {
        client = new Socket(ip,port);
        dOut = new DataOutputStream(client.getOutputStream());
        // Message mess = new Message(1, 1, ePiece.PAPER);
        // sendMessage(mess);
    }

    public void sendMessage(Message message) throws IOException
    {
        JSONObject json = message.createJsonMessage();
        dOut.writeUTF(json.toJSONString());
        dOut.flush();
    }

    protected void finalize() throws IOException
    {
        dOut.close();
        client.close();
    }
}
