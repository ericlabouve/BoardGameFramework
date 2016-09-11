package pawns;

import framework.GameBoardStatusLabel;
import framework.GameBoardTableModel;
import javax.swing.table.AbstractTableModel;

/**
 * Represents the information that is displayed above the game board.
 * 
 * @author Eric LaBouve
 */
public class PawnsStatusLabel extends GameBoardStatusLabel
{
    /**
     * Constructor for PawnsStatusLabel.
     */
    public PawnsStatusLabel()
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
        return "White: 0\tBlack: 0";
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
        return "Illegal move";
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
        PawnsTableModel pawnModel = (PawnsTableModel) model;
        return "White: " + pawnModel.getWhiteScore() + 
            "\tBlack: " + pawnModel.getBlackScore();
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
        PawnsTableModel pawnModel = (PawnsTableModel) model;
        String returnString = "";
        // White Wins
        if (pawnModel.hasWhiteWon())
        {
            returnString += "White Wins!";
        }
        // Black Wins
        else
        {
            returnString += "Black Wins!";
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
        return "Draw";
    }
}
