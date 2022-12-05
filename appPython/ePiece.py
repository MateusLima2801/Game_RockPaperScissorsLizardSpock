from enum import Enum
import string

screenNames = ["TESOURA", "PAPEL", "PEDRA", "LAGARTO", "SPOCK"]

class ePiece(Enum):
    SCISSORS = 0
    PAPER = 1
    STONE = 2
    LIZARD = 3
    SPOCK = 4

    

    def getScreenName(self) -> str:
        return screenNames[self.value]
    
    def getValue(self) -> int:
        return self.value

    def getByValue(self, value: int) -> str:
        return self(value)

    def getByName(name: str):
        return ePiece[name.strip()]
