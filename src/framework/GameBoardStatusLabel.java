package framework;


import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * Represents the information that is displayed above the game board.
 * 
 * @author Eric LaBouve
 */
public abstract class GameBoardStatusLabel implements TableModelListener
{
    /** Label that shows game specific messages */
    private JLabel status;
    
    /**
     * Constructor for HurkleStatusLabel
     */
    public GameBoardStatusLabel()
    {
        super();
        status = new JLabel(getInitialStatus());        
        status.setName("status");    
    }

    /**
     * What needs to be executed when the table is changed.
     * 
     * @param evt Reference to the model
     */
    @Override
    public void tableChanged(TableModelEvent evt) 
    {
        GameBoardTableModel model = (GameBoardTableModel) evt.getSource();
        updateStatus(model);
    }
    
    /**
     * Getter for the current status of the board
     * 
     * @return |status|
     */
    public JLabel getStatus()
    {
        return status;
    }
   
    /**
     * Changes the status label according to the state of the game.
     * The state of the game can be obtained through the GameState variable
     * located inside the AbstractTableModel
     * 
     * @param model The internal game model
     */
    public void updateStatus(GameBoardTableModel model)
    {
        GameBoardTableModel gbModel = (GameBoardTableModel) model;
        
        // Game is still in play
        if (gbModel.getGameState().equals(GameState.GAMENOTOVER))
        {
            // Illegal move detected
            if (gbModel.getIllegalMove())
            {
                status.setText(getIllegalMoveStatus(model));
                gbModel.setIllegalMove(false);
            }
            // Display Score (or whatever really)
            else
            {
                status.setText(getValidMoveStatus(model));
            }
        }
        // Game has a winner!
        else if (gbModel.getGameState().equals(GameState.GAMEOVER))
        {
            status.setText(getGameOverStatus(model));
            
        }
        // Game has ended in a draw
        else
        {
            status.setText(getGameDrawStatus(model));
        }
    }
    
    /**
     * Sets the initial status for the game when the game is constructed.
     * 
     * @return the initial status to show at the start of a new game.
     */
    public abstract String getInitialStatus();
    
    /**
     * Developer is to return the string to display when there is an illegal 
     * move.
     * 
     * @param model Reference to the table model
     * @return a String to display when an illegal move is made.
     */
    public abstract String getIllegalMoveStatus(GameBoardTableModel model);
    
    /**
     * Developer is to return the string to display when there is an legal move.
     * 
     * @param model Reference to the table model
     * @return string to display when there is an legal move
     */
    public abstract String getValidMoveStatus(GameBoardTableModel model);
    
    /**
     * Developer is to return the string to display when the game is over.
     * 
     * @param model Reference to the table model
     * @return string to display when the game is over
     */
    public abstract String getGameOverStatus(GameBoardTableModel model);
    
    /**
     * User is to return the string to display when the game has ended 
     * in a draw
     * 
     * @param model Reference to the table model
     * @return string to display when the game has ended in a draw
     */
    public abstract String getGameDrawStatus(GameBoardTableModel model);
}
