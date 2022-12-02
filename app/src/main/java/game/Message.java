package game;

import org.json.simple.JSONObject;
import java.io.FileWriter;

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

    public Message(JSONObject obj) {
        
    }
    
    public JSONObject createJsonMessage()
    {
        JSONObject json = new JSONObject();
        json.put("idMatch", idMatch);
        json.put("turn", turn);
        json.put("adversaryPlay", adversaryPlay.getName());
        return json;
    }

    

    
}
