from ePiece import ePiece
import json

class Message():

    def __init__(self,idMatch: int, turn: int,adversaryPlay: ePiece):
        self.idMatch = idMatch
        self.turn = turn
        self.adversaryPlay = adversaryPlay
    
    def createJsonMessage(self):
        dict = {"idMatch": self.idMatch,
                "turn": self.turn,
                "adversaryPlay": self.adversaryPlay.name}
        return json.dumps(dict)