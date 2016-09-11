package hurkle;

import framework.GameBoardStatusLabel;
import framework.GameBoardTableModel;
import javax.swing.table.AbstractTableModel;

/**
 * Represents the message label at the top of the game board view that the
 * plugin can manipulate.
 * 
 * @author Eric LaBouve
 */
public class HurkleStatusLabel extends GameBoardStatusLabel
{
    /**
     * Constructor for HurkleStatusLabel
     */
    public HurkleStatusLabel()
    {
        super();
    }
    
    /**
     * Sets the initial status for the game when the game is constructed.
     * 
     * @return the initial status to show at the start of a new game.
     */
    @Override
    public String getInitialStatus() 
    {
        return "Moves: 0";
    }
    
    /**
     * Developer is to return the string to display when there is an illegal 
     * move.
     * 
     * @param model Reference to the table model
     * @return a String to display when an illegal move is made.
     */
    @Override
    public String getIllegalMoveStatus(GameBoardTableModel model) 
    {
        return "Illegal Move";
    }
    
    /**
     * Developer is to return the string to display when there is an legal move.
     * 
     * @param model Reference to the table model
     * @return string to display when there is an legal move
     */
    @Override
    public String getValidMoveStatus(GameBoardTableModel model) 
    {
        HurkleTableModel hurkleModel = (HurkleTableModel) model;
        String hint = "Moves: " + hurkleModel.getPlayerGuesses();
        // Special case for when the game is just starting
        if (hurkleModel.getHint() != null)
        {
            hint += ", Search " + hurkleModel.getHint().getText();
        }
        return hint;
    }
    
    /**
     * Developer is to return the string to display when the game is over.
     * 
     * @param model Reference to the table model
     * @return string to display when the game is over
     */
    @Override
    public String getGameOverStatus(GameBoardTableModel model) 
    {
        HurkleTableModel hurkleModel = (HurkleTableModel) model;
        String returnString = "";
        // Player wins
        if (hurkleModel.getPlayerWon())
        {
            returnString += "You Win!";
        }
        // Player loses
        else
        {
            returnString += "You Lose!";
        }
        return returnString;
    }

    /**
     * User is to return the string to display when the game has ended 
     * in a draw
     * 
     * @param model Reference to the table model
     * @return string to display when the game has ended in a draw
     */
    @Override
    public String getGameDrawStatus(GameBoardTableModel model) 
    {
        return "Draw - Should never happen";
    }
    
}
