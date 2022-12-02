package game;
import java.lang.String;


public enum ePiece {
    SCISSORS("TESOURA",0), PAPER("PAPEL",1),
    STONE("PEDRA",2), LIZARD("LAGARTO",3),
    SPOCK("SPOCK",4);

    
    private final String name;
    private final int value;

    private ePiece(String _name, int _value)
    {
        name = _name;
        value = _value;
    }

    public String getName()
    {
        return name;
    }

    public int getValue()
    {
        return value;
    }

    public static ePiece getByValue(int val)
    {
        return ePiece.values()[val];
    }

    public static ePiece getByName(String name) throws Exception
    {
        ePiece[] values = ePiece.values();
        for(int i = 0; i<5; i++)
        {
            if(values[i].toString().contains(name))
            {
                return ePiece.values()[i];
            }
        }
        
        throw new Exception("unreachable");
    }
}
//use ordinal when u want to get the index