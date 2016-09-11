package framework;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Represents the algorithm that executes when a new game is initiated
 * @author Eric LaBouve
 */
public class NewGameAction extends AbstractAction
{
    /** Reference to the model */
    private GameBoardTableModel model;
    /** Reference to the frame */
    private GameBoardTableFrame frame;
    
    /**
     * Constructor to NewGameAction
     * @param model  Reference to the model 
     * @param frame  Reference to the frame
     */
    public NewGameAction(GameBoardTableModel model, GameBoardTableFrame frame)
    {
        this.model = model;
        this.frame = frame;
    }
       
    /**
     * What is to happen to start a new game
     * Resets the game board
     * Resizes the gui view
     * Clears any saved data
     * @param event An ACtionEvent (not used)
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        model.newGameBoard();
        frame.setSize(model.getExpectedDimension());
    }     
}
