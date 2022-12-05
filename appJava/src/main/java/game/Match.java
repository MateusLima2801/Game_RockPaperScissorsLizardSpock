package game;

import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

public class Match {
    int idMatch; 
    int turn = 1;
    int maxTurn;
    ArrayList<ePiece> myPlays = new ArrayList<ePiece>();
    ArrayList<ePiece> theirPlays = new ArrayList<ePiece>();
    ArrayList<Integer> results = new ArrayList<Integer>();
    static Board board = new Board();
    SocketClient client;
    SocketServer server;

    public Match(int _idMatch, int _maxTurn, SocketClient _client, SocketServer _server)
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
        int rand = 1+geneRandom.nextInt(4);
        return ePiece.getByValue(rand);
    }

    public static int comparePlay(ePiece me, ePiece them)
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
            winner = me.getScreenName();
            loser = them.getScreenName();
        }
        else
        {
            verb = board.getVerb(them, me);
            winner = them.getScreenName();
            loser = me.getScreenName();
        }

        System.out.println(String.format("%s X %s: %s %s %s", me.getScreenName(), them.getScreenName(), winner, verb, loser));
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
