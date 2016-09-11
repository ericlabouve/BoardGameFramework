package pawns;

import framework.GameBoardRenderer;
import framework.GameBoardStatusLabel;
import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import javax.swing.AbstractAction;
import javax.swing.table.AbstractTableModel;
import pawns.actions.ChangeSkinAction;
import pawns.actions.ResizeBoardAction;
import pawns.actions.UndoAction;

/**
 * Represents the JFrame that contains the game. 
 * Constructs the Menu Bar and all menu items.
 * 
 * @author Eric LaBouve
 */
public class PawnsTableFrame extends GameBoardTableFrame
{
    /**
     * Constructor for the PawnsTableFrame.
     * 
     * @param model A reference to the PawnsTableModel
     * @param label A reference to the StatusLabel
     * @param renderer A reference to the renderer
     * @param path The name of the plugin.
     */
    public PawnsTableFrame(GameBoardTableModel model, 
        GameBoardStatusLabel label, GameBoardRenderer renderer, String path)
    {
        super(model, label, renderer, path);
    }
    /**
     * Developer is to supply AbstractAction objects to be passed into 
     * the Preferences menu items that will be executed when the menu 
     * items are clicked.
     * 
     * The order in which the Actions are returned is very important, the first
     * Action will be assigned to the first menuitem provided in the 
     * preferences.ini file, the second action to the second menuitem, and
     * so on in that order.
     * 
     * All actions will automatically force the game to restart.
     * 
     * @param model Reference the the underlying data structure for the 
     * preference actions to manipulate.
     * @param label Reference to the label if needed.
     * @return A list of AbstractAction objects to be added to the
     * Preference menu items.
     */
    @Override
    public AbstractAction[] getPreferencesActions(GameBoardTableModel model, 
        GameBoardStatusLabel label) 
    {
        AbstractAction[] actions =
        {
            new ResizeBoardAction(model, this, 5, 5),
            new ResizeBoardAction(model, this, 8, 8),
            new ResizeBoardAction(model, this, 10, 10), 
            new UndoAction(model, label, this),
            new ChangeSkinAction(model, this, renderer,
                plugginName, "Default"),
            new ChangeSkinAction(model, this, renderer, 
                plugginName, "RedVSBlue"),
            new ChangeSkinAction(model, this, renderer, 
                plugginName, "GameOfThrones")
        };
        return actions;
    }
    
}
