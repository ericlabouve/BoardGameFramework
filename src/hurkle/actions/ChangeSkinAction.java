package hurkle.actions;

import framework.GameBoardRenderer;
import framework.GameBoardTableFrame;
import framework.GameBoardTableModel;
import framework.NewGameAction;
import hurkle.HurkleRenderer;
import hurkle.HurkleTableFrame;
import hurkle.HurkleTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

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
    private HurkleTableModel model;
    /** Reference to the frame */
    private HurkleTableFrame frame;
    /** Reference to the renderer */
    private HurkleRenderer renderer;
    /** The name of the pluggin */
    private String plugginName;
    /** The name of the skin type */
    private String skinName;

    /**
     * Constructor for the ChangeSkinAction
     * @param model Reference to the model
     * @param frame Reference to the frame
     * @param renderer Reference to the renderer
     * @param plugginName The name for the plugin
     * @param skinName The name of the skin type
     */
    public ChangeSkinAction(GameBoardTableModel model, 
        GameBoardTableFrame frame, GameBoardRenderer renderer, 
        String plugginName, String skinName)
    {
        this.model = (HurkleTableModel) model;
        this.frame = (HurkleTableFrame) frame;
        this.renderer = (HurkleRenderer) renderer;
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