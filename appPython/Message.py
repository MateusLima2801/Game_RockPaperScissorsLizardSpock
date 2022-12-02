import ePiece
import json

class Message():
    idMatch = 1;
    turn = 1;
    adversaryPlay = ePiece()

    def __init__(self,_idMatch,_turn,_adversaryPlay):
        self.idMatch = _idMatch
        self.turn = _turn
        self.adversaryPlay = _adversaryPlay
    
    def createJsonMessage(self):
        dict = {"idMatch": self.idMatch,
                "turn": self.turn,
                "adversaryPlay": self.adversaryPlay}
        return json.dumps(dict)