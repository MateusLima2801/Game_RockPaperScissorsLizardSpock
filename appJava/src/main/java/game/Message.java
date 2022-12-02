package game;

import org.json.simple.JSONObject;

public class Message {
    int idMatch;
    int turn;
    ePiece adversaryPlay;

    public Message(int _idMatch, int _turn, ePiece _adversaryPlay)
    {
        idMatch = _idMatch;
        turn = _turn;
        adversaryPlay = _adversaryPlay;
    }
    
    public JSONObject createJsonMessage()
    {
        JSONObject json = new JSONObject();
        json.put("idMatch", idMatch);
        json.put("turn", turn);
        json.put("adversaryPlay", adversaryPlay.toString());
        return json;
    }

    

    
}
