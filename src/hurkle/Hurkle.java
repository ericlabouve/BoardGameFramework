package hurkle;

import framework.Renderable;

/**
 * Represents all the different images that can be rendered in the board game.
 * The game board is filled with EMPTY_SOLID objects to start the game. Once 
 * clicked, EMPTY_SOLID object can either be replaced by EMPTY_CLEAR objects 
 * which allow the background the show through, or in the case that the player
 * chooses the correct location, the EMPTY_SOLID gets replaced by HURKLE.
 * @author Eric LaBouve
 */
public enum Hurkle implements Renderable 
{
    /** Represents a White Pawn */
    HURKLE ("Hurkle"),
    /** Represents an empty space that does not obfuscate the background */
    EMPTY_CLEAR ("Empty Clear"),
    /** Represents an empty space that obfuscates the background */
    EMPTY_SOLID ("Empty Solid");
    /** The text value for the Renderable object */
    private String textValue;
    
    /**
     * Constructor for an Hurkle.
     * 
     * @param type The name of the renderable object state
     */
    Hurkle(String type)
    {
        textValue = type;
    }
    
    /**
     * Getter for |textValue|.
     * 
     * @return String representation of the object
     */
    @Override
    public String getText() 
    {
        return textValue;
    }

    /**
     * Getter for the console string value.
     * 
     * @return String representation of the object for the console
     */
    @Override
    public String getConsoleSymbol() 
    {
        String symbol = "";
        // HURKLE
        if (textValue.equals("Hurkle"))
        {
            symbol += "H";
        }
        // EMPTY_CLEAR
        else if (textValue.equals("Empty Clear"))
        {
            symbol += "O";
        }
        // EMPTY_SOLID
        else
        {
            symbol += "+";
        }
        return symbol;
    }
}
