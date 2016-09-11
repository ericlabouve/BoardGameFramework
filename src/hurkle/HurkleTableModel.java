package hurkle;

import framework.GameBoardTableModel;
import framework.GameState;
import framework.Renderable;

/**
 * Underlying data model for the Hurkle game.
 * 
 * @author Eric LaBouve
 */
public class HurkleTableModel extends GameBoardTableModel
{
    /** Whether or not the player has beat the game */
    private boolean playerWon = false;
    /** Current number of guesses the player has taken */
    private int playerGuesses = 0;
    /** The maximum number of guesses the player can take before he/she loses */
    private int kMaxPlayerGuesses = 5;
    /** Row location of the Hurkle */
    private int hurkleRow = 0;
    /** Column location of the Hurkle */
    private int hurkleColumn = 0;
    /** Direction that the user needs to search in order to find Hurkle */
    private Direction direction = null;    
    
    /**
     * Default constructor for HurkleTableModel
     */
    public HurkleTableModel()
    {
        super();
    }
    
    /**
     * Constructor for HurkleTableModel
     * @param rowSizep Number of rows in the board
     * @param colSizep Number of columns in the board
     */    
    public HurkleTableModel(int rowSizep, int colSizep)
    {
        super(rowSizep, colSizep);
    }
    
    /**
     * What should be done on a click by click basis.
     * 
     * @param row Row at which click occurred
     * @param col Column at which click occurred
     * @param lClick Left Click (Not used)
     * @param rClick Right Click (Not used)
     */    
    @Override
    public void makeMove(int row, int col, boolean lClick, boolean rClick) 
    {
        Hurkle piece = (Hurkle) getValueAt(row, col);      
        
        // Clicked on an Empty-Solid/Hurkle location (Valid Move)
        if (!piece.equals(Hurkle.EMPTY_CLEAR))
        {
            direction = Direction.calculateDirection(row, col,
                hurkleRow, hurkleColumn);
            // If this location is the location of the hurkle
            if (direction.equals(Direction.NONE))
            {
                playerWon = true;
                revealBackground();
                setValueAt(Hurkle.HURKLE, row, col);
            }
            // If this location is not the location of the hurkle
            else
            {
                setValueAt(Hurkle.EMPTY_CLEAR, row, col);
            }
            playerGuesses++;
        }
        // Clicked on an Empty-Clear location (Illegal Move)
        else
        {
            illegalMove = true;            
        }       
        fireTableDataChanged();        
    }

    /**
     * Check if the game is over (for example, if there is a winner)
     * Winner is declared in makeMove().
     * 
     * @return true if the game is over
     */    
    @Override
    public boolean isGameOver() 
    {
        boolean isGameOver = false;
        // Check if the player has lost
        if (playerGuesses >= kMaxPlayerGuesses)
        {
            isGameOver = true;
        }
        return isGameOver;
    }

    /**
     * Determines if the game has ended in a draw. 
     *
     * @return true if the game has ended in a draw.
     */     
    @Override
    public boolean isDraw() 
    {
        return false;
    }

    /**
     * Start a new game by putting new Renderable objects in the board 
     * 
     * Call fireTableDataChanged() at the end of the method.
     */    
    @Override
    public void newGameBoard() 
    {
        hurkleRow = (int) (Math.random() * getRowCount());
        hurkleColumn = (int) (Math.random() * getColumnCount());
        
        // Initialize every row
        for (int row = 0; row < getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < getColumnCount(); col++)
            {
                setValueAt(Hurkle.EMPTY_SOLID, row, col);
            }
        }
        gameState = GameState.GAMENOTOVER;
        illegalMove = false;
        playerGuesses = 0;
        playerWon = false;
        direction = null;
        fireTableDataChanged();        
    }

    /**
     * Save any essential data, if needed, before moving to the next move
     */    
    @Override
    public void save() 
    {
        // Do nothing
    }
    
    /**
     * Fills the board with EMPTY_CLEAR hurkle pieces.
     * EMPTY_CLEAR hurkle pieces don't get rendered so they will allow
     * the background to show through.
     */
    private void revealBackground()
    {
        // Loop through every row
        for (int row = 0; row < getRowCount(); row++)
        {
            // Loop through every column
            for (int col = 0; col < getColumnCount(); col++)
            {
                setValueAt(Hurkle.EMPTY_CLEAR, row, col);
            }
        }
    }  
    
    /**
     * Getter for the number of times the player has guessed this game
     * @return |playerGuesses|
     */
    public int getPlayerGuesses()
    {
        return playerGuesses;
    }
    
    /**
     * Getter for whether or not the player has beat the game
     * @return True if the player beat the game
     */
    public boolean getPlayerWon()
    {
        return playerWon;
    }
    
    /**
     * Getter for the direction of the hurkle
     * @return |direction|
     */
    public Direction getHint()
    {
        return direction;
    }  
    
    /**
     * Start a new game board layout by putting new Renderable 
     * objects in the board and the hurkle 1 square in from the upper
     * left hand corner of the screen.
     * 
     * Call fireTableDataChanged() at the end of the method.
     */    
    public void cheatLayout() 
    {
        hurkleRow = 1;
        hurkleColumn = 1;
        
        // Initialize every row
        for (int row = 0; row < getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < getColumnCount(); col++)
            {
                setValueAt(Hurkle.EMPTY_SOLID, row, col);
            }
        }
        gameState = GameState.GAMENOTOVER;
        illegalMove = false;
        playerGuesses = 0;
        playerWon = false;
        direction = null;
        fireTableDataChanged();        
    }    
/*    
    public void printBoard()
    {
        for (int row = 0; row < getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < getColumnCount(); col++)
            {
                if (row == hurkleRow && col == hurkleColumn)
                {
                    System.out.print("H");
                }
                else
                {
                    Renderable object = (Renderable) this.getValueAt(row, col);
                    System.out.print(object.getConsoleSymbol());
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }
*/ 
}
