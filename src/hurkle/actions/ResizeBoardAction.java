package hurkle.actions;

import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import framework.NewGameAction;
import hurkle.HurkleTableFrame;
import hurkle.HurkleTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

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
    private HurkleTableModel model;
    /** Reference to the JFrame*/
    private HurkleTableFrame frame;
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
        this.model = (HurkleTableModel) model;
        this.frame = (HurkleTableFrame) frame;
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