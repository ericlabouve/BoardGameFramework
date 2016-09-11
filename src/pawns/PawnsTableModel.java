package pawns;

import framework.GameBoardTableModel;
import framework.GameState;
import java.util.Stack;

/**
 * Underlying data model for Pawns
 * 
 * @author Eric LaBouve
 */
public class PawnsTableModel extends GameBoardTableModel
{
    /** A history of game models for Undo Action */
    private Stack<PawnsModelState> cache;
    /** If there is a winner, this variable keeps track of which winner */
    private boolean whiteWon = false;
    /** Score for team white */
    private int whiteScore = 0;
    /** Score for team black */
    private int blackScore = 0;    

    /**
     * Default constructor for PawnsTableModel
     */
    public PawnsTableModel()
    {
        super();
        cache = new Stack<PawnsModelState>();
    }
    
    /**
     * Constructor for GameBoardTableModel
     * @param rowSizep Number of rows in the board
     * @param colSizep Number of columns in the board
     */
    public PawnsTableModel(int rowSizep, int colSizep)
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
        Pawn pawn = (Pawn) getValueAt(row, col);
        boolean attacking = lClick || rClick;        
        
        // Pawn is attacking Left
        if (lClick && isValidAttack(pawn, true, row, col))
        {
            setValueAt(Pawn.EMPTY, row, col);
            // Get new row and col position
            int newRow = pawn.equals(Pawn.WHITE) ? row - 1 : row + 1;
            int newCol = col - 1;
            // Update pawn
            setValueAt(pawn, newRow, newCol);
            editScore(pawn);
        }
        // Pawn is attacking right
        else if (rClick && isValidAttack(pawn, false, row, col))
        {
            setValueAt(Pawn.EMPTY, row, col);
            // Get new row and col position
            int newRow = pawn.equals(Pawn.WHITE) ? row - 1 : row + 1;
            int newCol = col + 1;
            // Update pawn
            setValueAt(pawn, newRow, newCol);
            editScore(pawn);
        }
        // Check other movements: (Checkstyle made me do this...)
        else
        {
            checkMove(row, col, pawn, attacking);
        }
    }
    
    /**
     * What should be done on a click by click basis.
     * 
     * @param row Row at which click occurred
     * @param col Column at which click occurred
     * @param pawn The value at row, col
     * @param attacking Whether or not the user is attacking
     */
    private void checkMove(int row, int col, Pawn pawn, boolean attacking)
    {
        // White Pawn is moving
        if (!attacking && pawn.equals(Pawn.WHITE) && 
            isValidMove(pawn, row, col))
        {
            setValueAt(Pawn.EMPTY, row, col);
            setValueAt(Pawn.WHITE, row - 1, col);
        }
        // Black Pawn in moving
        else if (!attacking && pawn.equals(Pawn.BLACK) && 
            isValidMove(pawn, row, col))
        {
            setValueAt(Pawn.EMPTY, row, col);
            setValueAt(Pawn.BLACK, row + 1, col);
        }
        // Illegal move
        else
        {
            illegalMove = true;
        } 
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
        boolean isWinner = false;
        // Check if black has won
        if (checkForHasCapturedAllOpponentsPawns(Pawn.WHITE) ||
                reachedLastRank(Pawn.BLACK))
        {
            whiteWon = false;
            isWinner = true;
        }
        // Check if white has won
        else if (checkForHasCapturedAllOpponentsPawns(Pawn.BLACK) ||
            reachedLastRank(Pawn.WHITE))
        {
            whiteWon = true;
            isWinner = true;
        }
        return isWinner;
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
        // Initialize every row
        for (int row = 0; row < getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < getColumnCount(); col++)
            {
                //The second row from the bottom
                if (row == 1)
                {
                    setValueAt(Pawn.BLACK, row, col);
                }
                //The second row from the top
                else if (row == getRowCount() - 2)
                {
                    setValueAt(Pawn.WHITE, row, col);
                }
                //All other spots
                else
                {
                    setValueAt(Pawn.EMPTY, row, col);
                }
            }
        }
        gameState = GameState.GAMENOTOVER;
        illegalMove = false;
        whiteScore = 0;
        blackScore = 0;
        cache.clear();
        
        fireTableDataChanged();
    }

    /**
     * Save any essential data, if needed, before moving to the next move
     */
    @Override
    public void save() 
    {
        cache.push(getModelState());
    }
    
    /**
     * Returns a copy of all essential model information
     * 
     * @return the state of the model in a PawnsModelState
     */
    public PawnsModelState getModelState()
    {
        return new PawnsModelState(myTable, whiteWon, 
            blackScore, whiteScore, illegalMove, gameState);
    }
    
    /**
     * An attack is a diagonal change in position where a pawn of the opposite
     * team resides.
     * Checks if there exists an enemy pawn in the correct position to attack.
     * Left attacks have priority over right attacks if both the left and
     * right clicks are true.
     * 
     * @param type Either a White Pawn or a Black Pawn
     * @param direction Either a left=true or right=false attack
     * @param row The current pawn position's row
     * @param col The current pawn position's column
     * @return If the attack is a valid move.
     */
    private boolean isValidAttack(Pawn type, boolean direction, 
        int row, int col)
    {
        boolean answer = false;
        // Get potentially new row and col position
        int newRow = type.equals(Pawn.WHITE) ? row - 1 : row + 1;
        int newCol = direction ? col - 1 : col + 1;
        // If valid move and attacking an enemy
        if (isValidCoordinate(newRow, newCol) && isEnemyPawn(
            type, newRow, newCol))
        {
            answer = true;
        }

        return answer;
    }
    
    /**
     * Pawns may not move off the board or vertically into another pawn.
     * A move is a vertical change in position.
     * 
     * @param row The current pawn's row position
     * @param col The current pawn's column position
     * @return true if the pawn's move is valid
     */
    private boolean isValidMove(Pawn type, int row, int col)
    {
        boolean answer = false;
        // Get potentially new vertical position
        int newRow = type.equals(Pawn.WHITE) ? row - 1 : row + 1;
        // Moves must be in the board and empty
        if (isValidCoordinate(newRow, col) && 
            getValueAt(newRow, col).equals(Pawn.EMPTY))
        {
            answer = true;
        }
        return answer;
    }
    
    /**
     * Increase the score of White or Black.
     * 
     * @param myPawn Represents a Pawn on the board
     */
    public void editScore(Pawn myPawn)
    {
        // White piece score increases
        if (myPawn.equals(Pawn.WHITE))
        {
            whiteScore++;
        }
        // Black piece score increases
        else
        {
            blackScore++;
        }
    }
    
    /**
     * Checks if the coordinate contains a pawn of the opposite team.
     * 
     * @param myPawn Represents the current player's pawn type
     * @param row Is the board row number for the comparison Pawn
     * @param col Is the board column number for the comparison Pawn
     * @return boolean True if location contains an enemy pawn
     * and false if the location contains anything else.
     */
    public boolean isEnemyPawn(Pawn myPawn, int row, int col)
    {
        boolean answer = false;
        // If the spot is not empy and the spot is filled with a pawn 
        // from the oppsoite team
        if (!getValueAt(row, col).equals(Pawn.EMPTY) && 
            !myPawn.equals(getValueAt(row, col)))
        {
            answer = true;
        }        
        return answer;
    }
    
    /**
     * Getter for |whiteScore|
     * 
     * @return |whiteScore|
     */
    public int getWhiteScore()
    {
        return whiteScore;
    }
    /**
     * Getter for |blackScore|
     * 
     * @return |blackScore|
     */
    public int getBlackScore()
    {
        return blackScore;
    }
    
    /**
     * Getter for |whiteWon|
     * 
     * @return |whiteWon|
     */
    public boolean hasWhiteWon()
    {
        return whiteWon;
    }
    
    /**
     * Determine if the player is unable to move any of his/her pawns
     * @param pawn The current player's pawn 
     * @return true if the player cannot make any more moves
     *
     *      NOT IMPLEMENTED
     * 
    private boolean isBlocked(Pawn pawn)
    {
        //Loop through all the spaces on the board and determine if there
        // is a pawn of the opposite color in front of all this player's pawns
        int rowAdjust = pawn.equals(Pawn.WHITE) ? -1 : 1;
        //String type = pawn.equals(Pawn.WHITE) ? "W" : "B";
        
        boolean answer = true;
        // Loop through all the rows
        for (int row = 0; row < rowSize; row++)
        {
            // Loop through all the columns
            for (int col = 0; col < colSize; col++)
            {
                // Are we at a pawn of type |pawn|?
                // Is there a space on the board in front of this pawn?
                // Is this space empty?
                if (getValueAt(row, col).equals(pawn) &&
                        isValidCoordinate(row + rowAdjust, col) &&
                        getValueAt(row + rowAdjust, col).equals(Pawn.EMPTY))
                {
                    answer = false;
                }
            }
        }
        return false;
    }
    */
    
    /**
     * Checks if there are any pawns of type |pawn| left on the board
     * @param pawn the current player's pawn
     * @return true if there are no more pawns on the board of the player's pawn
     */
    private boolean checkForHasCapturedAllOpponentsPawns(Pawn pawn)
    {
        boolean answer = true;
        // Loop through all the rows
        for (int row = 0; row < rowSize; row++)
        {
            // Loop through all the columns
            for (int col = 0; col < colSize; col++)
            {
                // If there exists an empty space in front of a pawn
                if (getValueAt(row, col).equals(pawn))
                {
                    answer = false;
                }
            }
        }
        return answer;
    }
    
    /**
     * White pawns move towards row=0; black pawns move towards row=rowSize-1 
     * @param pawn The type of pawn of the current player's turn
     * @return true if the current player's pawn has reached the last rank.
     */
    private boolean reachedLastRank(Pawn pawn)
    {
        boolean hasReachedLastRank = false;
        int lastRow = pawn.equals(Pawn.WHITE) ? 0 : rowSize - 1;
        // Check every last row column position
        for (int col = 0; col < colSize; col++)
        {
            // Check for a pawn of type |pawn|
            if (getValueAt(lastRow, col).equals(pawn))
            {
                hasReachedLastRank = true;
            }
        }
        return hasReachedLastRank;
    }
    
    /**
     * Pops a game state off the cache and restores the state of the game to
     * one previous move in the past. If there are no saved game states,
     * restoreGameBoard does nothing.
     */
    public final void restoreGameBoard()
    {
        // Only restore the state of the game if the cache is not empty
        if (!cache.isEmpty())
        {
            PawnsModelState modelState = cache.pop();
            
            this.myTable = (Pawn[][]) modelState.getTable();
            
            this.whiteWon = modelState.whiteWon;
            this.blackScore = modelState.blackScore;
            this.whiteScore = modelState.whiteScore;
            this.illegalMove = modelState.illegalMove;
            this.gameState = modelState.state;
            fireTableDataChanged();
            fireTableStructureChanged();
        }
    }  
    
    /**
     * Resizes the underlying game board and adjusts the JTable column widths
     * @param row The new number of rows
     * @param col The new number of columns
     */
    public void resizeBoard(int row, int col)
    {
        myTable = new Pawn[row][col];
        rowSize = row;
        colSize = col;
        newGameBoard();
        fireTableStructureChanged();
        view.adjustSize();
    }
}
