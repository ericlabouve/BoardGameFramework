package pawns.actions;

import framework.GameBoardStatusLabel;
import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.table.AbstractTableModel;
import pawns.PawnsStatusLabel;
import pawns.PawnsTableFrame;
import pawns.PawnsTableModel;

/**
 * Pops a GameState off the cache and restores the game to one valid game
 * move back in time.
 * 
 * @author Eric LaBouve
 */
public class UndoAction extends AbstractAction
{
    /** A reference to the model */
    private PawnsTableModel model;
    /** A reference to the status label model */
    private PawnsStatusLabel label;
    /** A reference to the frame */
    private PawnsTableFrame frame;
    
    /**
     * Constructor for UndoAction
     * @param model Reference to the model
     * @param label Reference to the status label model
     * @param frame Reference to the frame
     */
    public UndoAction(GameBoardTableModel model, 
        GameBoardStatusLabel label, GameBoardTableFrame frame) 
    {
        this.model = (PawnsTableModel) model;
        this.label = (PawnsStatusLabel) label;
        this.frame = (PawnsTableFrame) frame;
    }

    /**
     * Reverts the game to one move earlier.
     * @param evt Th event
     */
    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        model.restoreGameBoard();
        frame.setSize(model.getExpectedDimension());
    }    
}