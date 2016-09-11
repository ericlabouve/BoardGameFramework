package pawns.actions;

import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import framework.NewGameAction;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import pawns.PawnsTableFrame;
import pawns.PawnsTableModel;

/**
 * Represents an action object. This action object, when invoked through 
 * actionPerfomed, resizes the underlying game board model and 
 * resizes the JFrame encompassing the game.
 * 
 * @author Eric LaBouve
 */
public class ResizeBoardAction extends AbstractAction
{
    /** Reference to the model */
    private PawnsTableModel model;
    /** Reference to the JFrame*/
    private PawnsTableFrame frame;
    /** The new board row count*/
    private int rows;
    /** The new board column count*/
    private int columns;
    
    /**
     * Constructor for ResizeBoardAction.
     * 
     * @param model Reference to the model
     * @param frame Reference to the JFrame
     * @param numrow The new board row count
     * @param numcol  The new board column count
     */
    public ResizeBoardAction(GameBoardTableModel model, 
        GameBoardTableFrame frame, int numrow, int numcol)
    {
        this.model = (PawnsTableModel) model;
        this.frame = (PawnsTableFrame) frame;
        rows = numrow;
        columns = numcol;
    }
    
    /**
     * Resizes the underlying game board model and resizes the JFrame
     * @param evt Action event to perform
     */
    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        model.resizeBoard(rows, columns);
        new NewGameAction(model, frame).actionPerformed(null);
    }
}