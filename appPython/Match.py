from Board import Board
from ePiece import ePiece
from Message import Message
import random
from SocketClient import SocketClient
from SocketServer import SocketServer

class Match():

    def __init__(self, _idMatch: int, _maxTurn: int, _client: SocketClient, _server: SocketServer):
        self.turn = 1
        self.myPlays = []
        self.theirPlays = []
        self.results = []
        self.board = Board()
        self.idMatch = _idMatch
        self.client = _client
        self.server = _server
        self.maxTurn = _maxTurn

    def playMatch(self) -> int: 
        while not self.isFinished():
            self.executePlay()
        return self.getWinner()

    def isFinished(self) -> bool:
        return self.turn > self.maxTurn

    def getWinner(self) -> int:
        result = 0;
        for r in self.results: 
            result += r

        if result == 0: 
            return 0
        elif result > 0:
            return 1
        else: return -1
    
    def choosePlay(self) -> ePiece:
        rand = random.randrange(0,4)
        return ePiece(rand)

    def comparePlay(self, me: ePiece, them: ePiece) -> int:
        return self.board.comparePlay(me, them)

    def sendPlay(self, me):
        mess = Message(self.idMatch, self.turn, me);
        self.client.sendMessage(mess);
        a = 1

    def receivePlay(self):
        return self.server.checkBuffer(self.turn);
        #return ePiece.SPOCK;

    def executePlay(self):
        me = self.choosePlay();
        self.sendPlay(me);
        self.server.waitMessage();
        them = self.receivePlay();
        self.myPlays.append(me);
        self.theirPlays.append(them);
        self.processResults(me = me, them = them);
        self.turn = self.turn + 1

    def processResults(self, me: ePiece, them: ePiece):
        res = self.comparePlay(me, them);
        self.results.append(res);
        verb: str
        winner: str
        loser: str
        
        if res >= 0:
            verb = self.board.getVerb(me, them);
            winner = me.getScreenName();
            loser = them.getScreenName();
        else:
            verb = self.board.getVerb(them, me);
            winner = them.getScreenName();
            loser = me.getScreenName();

        print(f"{me.getScreenName()} X {them.getScreenName()}: {winner} {verb} {loser}")