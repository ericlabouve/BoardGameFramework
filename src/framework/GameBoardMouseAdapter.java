package framework;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse Adapter for the Board Game.
 * Saves essential information.
 * Causes "moves" to occur in the game.
 * Updates the GameState.
 * And refreshes the gui display.
 * 
 * @author Eric LaBouve
 */
public class GameBoardMouseAdapter extends MouseAdapter
{
    /** A reference to the underlying data model */
    private GameBoardTableModel model;
    /** A reference to the graphical table view */
    private GameBoardTableView view;
    /** Key code for the Control key */
    private final int kCtrl = 128;
    /** Key code for the Shift key */
    private final int kShift = 64;

    /**
     * Constructor for the HurkleMouseAdapter.
     * 
     * @param modelp a reference to the model.
     * @param viewp a reference to the view.
     */
    public GameBoardMouseAdapter(GameBoardTableModel modelp,
            GameBoardTableView viewp)
    {
        this.model = modelp;
        this.view = viewp;
    }

   /** 
    * Listener to respond to mouse clicks on the Game.
    * 
    * Right Click: Control Key = 128;
    * Left Click:  Shift Key = 64;
    * 
    *@param evt is the event that has occured   
    */
    public void mouseReleased(MouseEvent evt)
    {
        // obtain the selected cell coordinates
        int col = view.getSelectedColumn();
        int row = view.getSelectedRow();
        boolean ctrl = false;
        boolean shift = false;
        boolean illegalMove = false; 

        // Get the Key that is pressed with the mouse
        int pressedKey = evt.getModifiersEx(); 

        //Check if the Control Key has been pressed
        if (pressedKey == kCtrl)
        {
            ctrl = true;
        }
        //Check if the Shift Key has been pressed
        else if (pressedKey == kShift)
        {
            shift = true;
        }
        // Some clicks are outside the JTable:
        if (model.isValidCoordinate(row, col))
        {
            // Save the current configuration in the cache for undo actions
            model.save();
            // Pushes the game forwoard: Make a move
            model.makeMove(row, col, shift, ctrl);
            // Check for winners
            model.gameState = model.updateGameState();
            // Update status panel
            model.fireTableDataChanged();
            // refresh view
            view.repaint();
        }
    }
} 