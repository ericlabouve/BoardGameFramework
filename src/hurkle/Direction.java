package hurkle;

/**
 * Represents Compass directions for giving Hurkle hints.
 * 
 * @author Eric LaBouve
 */
public enum Direction 
{
    /** North */
    NORTH ("North"),
    /** NORTH WEST */
    NORTH_WEST ("North West"),
    /** NORTH EAST */
    NORTH_EAST ("North East"),
    /** SOUTH */
    SOUTH ("South"),
    /** SOUTH WEST */
    SOUTH_WEST ("South West"),
    /** SOUTH EAST */
    SOUTH_EAST ("South East"),
    /** EAST */
    EAST ("East"),
    /** WEST */
    WEST ("West"), 
    /** Special case for when the (row,col)==(Hurkle row,Hurkle col) */
    NONE ("None"); 
    
    /** String representation of the direction */
    private String direction;
    
    /**
     * Constructor for Direction
     * @param dir String of the current direction
     */
    Direction(String dir)
    {
        direction = dir;
    }
    
    /**
     * Getter for |direction|
     * @return String representation of the object
     */
    public String getText() 
    {
        return direction;
    }
    
    /**
     * Calculates the direction the user needs to search according to the
     * location that was clicked and the location of the hurkle.
     * @param rowClick The row the player clicked
     * @param colClick The column the player clicked
     * @param rowHurkle The row the hurkle is hidden in.
     * @param colHurkle The column the hurkle is hidden in.
     * @return The direciton that the Hurkle is located relative to the click
     */
    public static Direction calculateDirection(int rowClick, int colClick,
        int rowHurkle, int colHurkle)
    {
        Direction dir = Direction.NONE;
        // Same Y-Axis and Hurkle is to the West
        if (rowHurkle == rowClick && colHurkle < colClick)
        {
            dir = Direction.WEST;
        }
        // Same Y-Axis and Hurkle is to the East
        else if (rowHurkle == rowClick && colHurkle > colClick)
        {
            dir = Direction.EAST;
        }        
        // Same X-Axis and Hurkle is North
        else if (colHurkle == colClick && rowHurkle < rowClick)
        {
            dir = Direction.NORTH;
        }
        // Same X-Axis and Hurkle is South
        else if (colHurkle == colClick && rowHurkle > rowClick)
        {
            dir = Direction.SOUTH;
        }
        // User has chosen the hurkle's location
        else
        {
            dir = checkDiagonals(dir, rowClick, colClick, rowHurkle, colHurkle);
        }

        return dir;
    }

    /**
     * Helper fucntion for calculateDirection().
     * Calculates the direction the user needs to search according to the
     * location that was clicked and the location of the hurkle.
     * @param dir The current direction from calculateDirection()
     * @param rowClick The row the player clicked
     * @param colClick The column the player clicked
     * @param rowHurkle The row the hurkle is hidden in.
     * @param colHurkle The column the hurkle is hidden in.
     * @return The direciton that the Hurkle is located relative to the click
     */    
    private static Direction checkDiagonals(Direction dir, int rowClick, 
        int colClick, int rowHurkle, int colHurkle)
    {
        // Hurkle is located in the upper right of the user's click
        if(colHurkle > colClick && rowHurkle < rowClick)
        {
            dir = Direction.NORTH_EAST;
        }
        // Hurkle is located in the upper left of the user's click
        else if(colHurkle < colClick && rowHurkle < rowClick)
        {
            dir = Direction.NORTH_WEST;
        }
        // Hurkle is located in the lower right of the user's click
        else if(colHurkle > colClick && rowHurkle > rowClick)
        {
            dir = Direction.SOUTH_EAST;
        }
        // Hurkle is located in the lower left of the user's click
        else if(colHurkle < colClick && rowHurkle > rowClick)
        {
            dir = Direction.SOUTH_WEST;
        }        
        // User has chosen the hurkle's location
        else
        {
            dir = Direction.NONE;
        }
        return dir;
    }

}
