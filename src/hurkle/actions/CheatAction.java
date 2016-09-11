package hurkle.actions;

import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import hurkle.HurkleTableFrame;
import hurkle.HurkleTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Places the hurkle in 1 from the upper left an corner of the board.
 * Used as a predictable layout for testing purposes.
 * 
 * @author Eric LaBouve
 */
public class CheatAction extends AbstractAction
{
    /** Reference to the model */
    private HurkleTableModel model;
    /** Reference to the JFrame*/
    private HurkleTableFrame frame;
    
    /**
     * Constructor to NewGameAction
     * @param model  Reference to the model 
     * @param frame Reference to the frame
     */
    public CheatAction(GameBoardTableModel model, GameBoardTableFrame frame)
    {
        this.model = (HurkleTableModel) model;
        this.frame = (HurkleTableFrame) frame;
    }
    
    /**
     * Places the hurkle in 1 from the upper left an corner of the board.
     * Resets the game board
     * Resizes the gui view
     * Clears any saved data
     * @param event An ACtionEvent (not used)
     */    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        model.cheatLayout();
        frame.setSize(model.getExpectedDimension());
    }
}
