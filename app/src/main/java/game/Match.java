package  app.src.main.java.game;

import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.util.Random.*;

public class Match {
    int idMatch; 
    int turn = 1;
    int maxTurn;
    ArrayList<ePiece> myPlays = new ArrayList<ePiece>();
    ArrayList<ePiece> theirPlays = new ArrayList<ePiece>();
    ArrayList<Integer> results = new ArrayList<Integer>();
    Board board = new Board();
    WSClient client;
    WSServer server;

    public Match(int _idMatch, int _maxTurn, WSClient _client, WSServer _server)
    {
        idMatch = _idMatch;
        client = _client;
        server = _server;
        maxTurn = _maxTurn;
    }

    public int playMatch() throws Exception
    {
        while(!isFinished())
        {
            executePlay();
        }

        return getWinner();
    }

    public ePiece choosePlay()
    {
        Random geneRandom = new Random();
        int rand = geneRandom.nextInt(5);
        return ePiece.getByValue(rand);
    }

    public int comparePlay(ePiece me, ePiece them)
    {
        return board.comparePlay(me, them);
    }

    public void sendPlay(ePiece me) throws IOException
    {
        Message mess = new Message(idMatch, turn, me);
        client.sendMessage(mess);
    };

    public ePiece receivePlay() throws Exception
    {
        return server.checkBuffer(turn);
        //return ePiece.SPOCK;
    };

    public void executePlay() throws IOException, Exception
    {
        ePiece me = choosePlay();
        sendPlay(me);
        server.waitMessage();
        ePiece them = receivePlay();
        myPlays.add(me);
        theirPlays.add(them);
        processResults(me, them);
        turn++;
    }

    public void processResults(ePiece me, ePiece them)
    {
        int res = comparePlay(me, them);
        results.add(res);
        String verb;
        String winner;
        String loser;
        if(res >= 0 )
        {
            verb = board.getVerb(me, them);
            winner = me.getName();
            loser = them.getName();
        }
        else
        {
            verb = board.getVerb(them, me);
            winner = them.getName();
            loser = me.getName();
        }

        System.out.println(String.format("%s X %s: %s %s %s", me.getName(), them.getName(), winner, verb, loser));
    }
    public boolean isFinished()
    {
        return turn > maxTurn;
    }

    public int getWinner()
    {
        int result = 0;
        for (int r : results) {
            result += r;
        }

        if(result == 0) return 0;
        else if(result > 0) return 1;
        else return -1;
    }



}
