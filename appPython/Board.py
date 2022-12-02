from numpy import *

class Board():
    # a_{i, j} = 0 - withdraw
    # a_{i, j} = 1 - i wins from j
    # a_{i, j} = -1 - i loses for j
    _adjacencyMatrix = [[0, 1, -1, 1, -1],
                        [-1, 0, 1, -1, 1],
                        [1, -1, 0, 1, -1],
                        [-1, 1, -1, 0, 1],
                        [1, -1, 1, -1, 0]]

    #only access the fields with value 1
    playVerbs = [["EMPATA COM", "CORTA", "", "DECAPTA", ""],
                ["", "EMPATA COM", "COBRE", "", "REFUTA"],
                ["AMASSA", "", "EMPATA COM","ESMAGA", ""],
                ["", "COME", "", "EMPATA COM", "ENVENENA"],
                ["DERRETE", "", "VAPORIZA", "", "EMPATA COM"]]

    def comparePlay(self,me, them):
        return self.adjacencyMatrix[me.getValue()][them.getValue()]
    

    def getVerb(self,winner,loser):
        return self.playVerbs[winner.getValue()][loser.getValue()]


