package game;

public class Board{

    //a_{i,j} = 0 - withdraw
    // a_{i,j} = 1 - i wins from j
    // a_{i,j} = -1 - i loses for j
    private static int[][] adjacencyMatrix = {{0,1,-1, 1,-1},
                                       {-1,0,1,-1,1},
                                       {1,-1,0,1,-1},
                                       {-1,1,-1,0,1},
                                       {1,-1,1,-1,0}};

    //only access the fields with value 1
    private static String[][] playVerbs = {{"EMPATA COM","CORTA", "", "DECAPTA",""},
                                {"", "EMPATA COM", "COBRE", "", "REFUTA"},
                                {"AMASSA", "", "EMPATA COM", "ESMAGA", ""},
                                {"", "COME", "", "EMPATA COM", "ENVENENA"},
                                {"DERRETE", "", "VAPORIZA", "", "EMPATA COM"}}; 
    
    public static int comparePlay(ePiece me, ePiece them)
    {
        return adjacencyMatrix[me.getValue()][them.getValue()];
    }

    public static String getVerb(ePiece winner, ePiece loser)
    {
        return playVerbs[winner.getValue()][loser.getValue()];
    } 
    
}