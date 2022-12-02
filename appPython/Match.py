import Board
import ePiece
import Message
import random

class Match():
    idMatch = 1
    turn = 1
    maxTurn = 1
    myPlays = []
    theirPlays = []
    results = []
    board = Board()
    # client = WSClient()
    # server = WSServer()

    def __init__(self, _idMatch, _maxTurn, _client, _server):
        self.idMatch = _idMatch
        self.client = _client
        self.server = _server
        self.maxTurn = _maxTurn

    def playMatch(self): 
        while not self.isFinished():
            self.executePlay(self)
        return self.getWinner()

    def isFinished(self):
        return self.turn > self.maxTurn

    def getWinner(self):
        result = 0;
        for r in self.results: 
            result += r

        if result == 0: 
            return 0
        elif result > 0:
            return 1
        else: return -1
    
    def choosePlay(self):
        rand = random.randrange(0,5)
        return ePiece.getByValue(rand)

    def comparePlay(self, me, them):
        return self.board.comparePlay(me, them)

    def sendPlay(self, me):
        mess = Message(self.idMatch, self.turn, me);
        # client.sendMessage(mess);
        a = 1

    def receivePlay(self):
        #return server.checkBuffer(turn);
        return ePiece.SPOCK;

    def executePlay(self):
        me = self.choosePlay();
        self.sendPlay(me);
        #self.server.waitMessage();
        them = self.receivePlay();
        self.myPlays.add(me);
        self.theirPlays.add(them);
        self.processResults(me, them);
        self.turn = self.turn + 1

    def processResults(self, me, them):
        res = self.comparePlay(me, them);
        self.results.add(res);
        verb = "";
        winner = ""
        loser = "";
        
        if res >= 0:
            verb = self.board.getVerb(me, them);
            winner = me.getName();
            loser = them.getName();
        else:
            verb = self.board.getVerb(them, me);
            winner = them.getName();
            loser = me.getName();

        print(f"{me.getName()} X {them.getName()}: {winner} {verb} {loser}\n")