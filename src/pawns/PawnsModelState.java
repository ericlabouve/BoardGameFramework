package pawns;

import framework.GameState;
import framework.Renderable;

/**
 * Represents the state of the underlying game model at a singular state
 * of time. 
 * PawnsModelState contains the configuration of the board.
 *                     all pluggin implementation specific information.
 * 
 * @author Eric LaBouve
 */
public class PawnsModelState 
{
    /** Copy of the previous state of the game table */
    private Renderable[][] table;
    /** Copy of the winning player */
    public boolean whiteWon;
    /** Copy of the black pawn score */
    public int blackScore;
    /** Copy of the white pawn score */
    public int whiteScore;
    /** Copy of the illegalMove parameter */
    public boolean illegalMove;
    /** Copy of PawnsGameState */
    public GameState state;
          
    /**
     * Constructor for PawnsModelState
     * 
     * @param saveTable Copy of the previous state of the game table.
     */
    public PawnsModelState(Renderable[][] saveTable)
    {
        table = saveTable;
    }

    /**
     * Constructor for PawnsModelState
     * 
     * @param myTable Copy of the previous state of the game table
     * @param whiteWon Copy of the winning player
     * @param blackScore Copy of the black pawn score
     * @param whiteScore Copy of the white pawn score
     * @param illegalMove Copy of the illegalMove parameter
     * @param gameState The state that the game is currently in.
     */
    public PawnsModelState(Renderable[][] myTable, boolean whiteWon, int blackScore, 
        int whiteScore, boolean illegalMove, GameState gameState) 
    {        
        table = deepCopy(myTable);                
        this.whiteWon = whiteWon;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.illegalMove = illegalMove;
        this.state = gameState;
    }
    
    /**
     * Getter for table state
     * 
     * @return Copy of the table
     */
    public Renderable[][] getTable()
    {
        return table;
    }
    
    /**
     * Generates an exact copy of the game board table.
     * @param tab Reference to the table to copy.
     * 
     * @return An exact copy of the game board table
     */
    public Renderable[][] deepCopy(Renderable[][] tab)
    {
        Renderable[][] newTable = new Pawn[tab.length][tab[0].length];
        // For each row
        for (int row = 0; row < tab.length; row++)
        {
            // For each column
            for (int col = 0; col < tab[0].length; col++)
            {
                newTable[row][col] = tab[row][col];
            }                
        }
        return newTable;
    }    
}
