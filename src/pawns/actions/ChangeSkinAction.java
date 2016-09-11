package pawns.actions;

import framework.GameBoardRenderer;
import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import framework.NewGameAction;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import pawns.PawnsRenderer;
import pawns.PawnsTableFrame;
import pawns.PawnsTableModel;

/**
 * Action to change the skin, or theme of the game. Changing the skin
 * simply mean using different images for all the pieces and background.
 * A new game will be started if the skin is changed mid game.
 * 
 * @author Eric LaBouve
 */
public class ChangeSkinAction extends AbstractAction
{
    /** Reference to the model */
    private PawnsTableModel model;
    /** Reference to the frame */
    private PawnsTableFrame frame;
    /** Reference to the renderer */
    private PawnsRenderer renderer;
    /** The name of the pluggin */
    private String plugginName;
    /** The name of the skin type */
    private String skinName;

    /**
     * Constructor for the ChangeSkinAction
     * @param model Reference to the model
     * @param frame Reference to the frame
     * @param renderer Reference to the renderer
     * @param plugginName Name of the plugin in use.
     * @param skinName The name of the skin type
     */
    public ChangeSkinAction(GameBoardTableModel model, 
        GameBoardTableFrame frame, GameBoardRenderer renderer, 
        String plugginName, String skinName)
    {
        this.model = (PawnsTableModel) model;
        this.frame = (PawnsTableFrame) frame;
        this.renderer = (PawnsRenderer) renderer;
        this.plugginName = plugginName;
        this.skinName = skinName;
    }
    
    /**
     * Changes the skin of the game by switching out the player pieces
     * and the background image.
     * @param evt The event
     */
    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        renderer.setImages(plugginName, skinName);
        new NewGameAction(model, frame).actionPerformed(null);
    }
}