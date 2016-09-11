package framework;


import java.awt.Dimension;
import javax.swing.table.AbstractTableModel;

/**
 * Represents the underlying data model for the Game
 * 
 * @author Eric LaBouve
 */
public abstract class GameBoardTableModel extends AbstractTableModel 
{
    /** The current game board model */
    protected Renderable[][] myTable;
    /** The current number of rows in the table */
    protected int rowSize;
    /** The default number of rows to start the game */
    protected static final int kDefaultRowSize = 5;
    /** The current number of columns in the table */
    protected int colSize;
    /** The default number of columns to start the game */
    protected static final int kDefaultColSize = 5;        
    /** State determining if the game is to continue or stop. */
    protected GameState gameState = GameState.GAMENOTOVER;
    /** Reference to the corresponding JTable */
    protected GameBoardTableView view;
    /** Flag for if the last move that was made was illegal */
    protected boolean illegalMove = false;
    
    /**
     * Default constructor for GameBoardTableModel
     */
    public GameBoardTableModel()
    {
        myTable = new Renderable[kDefaultRowSize][kDefaultColSize];
        rowSize = kDefaultRowSize;
        colSize = kDefaultColSize; 
    }
    
    /**
     * Constructor for GameBoardTableModel
     * @param rowSizep Number of rows in the board
     * @param colSizep Number of columns in the board
     */
    public GameBoardTableModel(int rowSizep, int colSizep)
    {
        myTable = new Renderable[rowSizep][colSizep];
        rowSize = rowSizep;
        colSize = colSizep;
    }
    
    /**
     * Saves a reference of the JTable 
     * 
     * @param viewp The JTable
     */
    public void addTableView(GameBoardTableView viewp)
    {
        this.view = viewp;
    }
    
        
    /**
     * Get the number of rows in the table
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() 
    {
        return rowSize;
    }

        
    /**
     * Get the number of columns in the table
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount() 
    {
        return colSize;
    }

    /**
     * Gets a value at a specified coordinate in the table
     * @param rowIndex The row coordinate
     * @param columnIndex The column coordinate
     * @return the Object at the specified location
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return myTable[rowIndex][columnIndex];
    }
    
    /**
     * Getter for |gameState|
     * @return |gameState|
     */
    public GameState getGameState()
    {
        return gameState;
    }
    
    /**
     * Getter for |illegalMove|
     * @return |illegalMove|
     */
    public boolean getIllegalMove()
    {
        return illegalMove;
    }
    
    /**
     * Setter for |illegalMove|.
     * @param status the new status for illegalMove.
     */
    public void setIllegalMove(boolean status)
    {
        illegalMove = status;
    }
    
    /**
     * Set a cell to a particular coordinate.
     * 
     * @param value is the piece at row and col.
     * @param row Is the board row number.
     * @param col Is the board column number.
     */
    public void setValueAt(Renderable value, int row, int col)
    {
        myTable[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    /**
     * Checks if the coordinate is in the bounds of the board.
     * 
     * @param row Is the board row number.
     * @param col Is the board column number.
     * @return boolean True if the spot is in bounds.
     */
    public boolean isValidCoordinate(int row, int col)
    {
        boolean answer = false;
        //Check if within range of rows
        if (row >= 0 && row < rowSize)
        {
            //Check if in range of columns
            if (col >= 0 && col < colSize)
            {
                answer = true;
            }
        }
        return answer;
    }    
    
    /**
     * Determines if the game is at a draw, over, or still in play.
     * 
     * @return The current state of the game as a GameState enum object.
     */
    public GameState updateGameState() 
    {
        GameState newGameState = gameState;
        // Drawing is a stronger condition than winning because it requires
        // the union of winning conditions d White and d Black
        if (isDraw())
        {
            newGameState = GameState.GAMEDRAW;
        }
        // Check if there is a winner
        else if (isGameOver())
        {
            newGameState = GameState.GAMEOVER;
        }
        // Game is still being played        
        else
        {
            newGameState = GameState.GAMENOTOVER;
        }

        return newGameState;
    }
    
    /**
     * Resizes the underlying game board and adjusts the JTable column widths.
     * 
     * @param row The new number of rows.
     * @param col The new number of columns.
     */
    public void resizeBoard(int row, int col)
    {
        myTable = new Renderable[row][col];
        rowSize = row;
        colSize = col;
        newGameBoard();
        fireTableStructureChanged();
        view.adjustSize();
    }
    
    /**
     * Calculates and returns the expected dimension for the game's frame.
     * 
     * @return The expected dimension for the game's frame.
     */
    public Dimension getExpectedDimension()
    {
        // Frame width is the Sum of block widths
        int newWidth = getColumnCount() * GameBoardTableView.kImageWidth;
        // Frame height is Sum of block heights and the top of the screen
        int newHeight = (getRowCount() * 
            GameBoardTableView.kImageHeight) + GameBoardTableView.kTopHeight;

        return new Dimension(newWidth, newHeight);
    }  
    
    /**
     * What should be done on a click by click basis.
     * 
     * @param row Row at which click occurred
     * @param col Column at which click occurred
     * @param lClick Left Click (Not used)
     * @param rClick Right Click (Not used)
     */
    public abstract void makeMove(int row, int col, boolean lClick, boolean rClick);     
    
    
    /**
     * Check if the game is over (for example, if there is a winner)
     * Winner is declared in makeMove().
     * 
     * @return true if the game is over
     */
    public abstract boolean isGameOver();
    
    /**
     * Determines if the game has ended in a draw. 
     *
     * @return true if the game has ended in a draw.
     */ 
    public abstract boolean isDraw();    
    
    /**
     * Start a new game by putting new Renderable objects in the board 
     * 
     * Call fireTableDataChanged() at the end of the method.
     */
    public abstract void newGameBoard();
           
    /**
     * Save any essential data, if needed, before moving to the next move
     */
    public abstract void save();
}