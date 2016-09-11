package pawns;

import framework.Renderable;


/**
 * Represents an individual piece in the game of pawns
 * 
 * @author Eric LaBouve
 */
public enum Pawn implements Renderable
{
    /** Represents a White Pawn */
    WHITE ("White"),
    /** Represents a Black Pawn*/
    BLACK ("Black"),
    /** Represents an empty space */
    EMPTY ("Empty");
    /** The text value for the Renderable object */
    private String textValue;
    
    /**
     * Constructor for an Pawn
     * @param type The name of the renderable object state
     */
    Pawn(String type)
    {
        textValue = type;
    }
    
    /**
     * Getter for |textValue|
     * @return String representation of the object
     */
    @Override
    public String getText() 
    {
        return textValue;
    }

    /**
     * Getter for the console string value.
     * @return String representation of the object for the console
     */
    @Override
    public String getConsoleSymbol() 
    {
        String symbol = "";
        // White
        if (textValue.equals("White"))
        {
            symbol += "W";
        }
        // Black
        else if (textValue.equals("Black"))
        {
            symbol += "B";
        }
        // Empty
        else
        {
            symbol += "+";
        }
        return symbol;
    }
}