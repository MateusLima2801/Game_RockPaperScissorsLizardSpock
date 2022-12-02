from enum import Enum
import string

class ePiece(Enum):
    SCISSORS = 0
    PAPER = 1
    STONE = 2
    LIZARD = 3
    SPOCK = 4

    names = ["TESOURA", "PAPEL", "PEDRA", "LAGARTO", "SPOCK"]

    def getName(self):
        return self.names[self.value]
    
    def getValue(self):
        return self.value

    def getByValue(self, value):
        return self(value)
    
    #TODO: test if it works
    def getByName(self, name):
        return self[string.strip(name)]
