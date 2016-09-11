package framework;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Represents what should happen when the user wants to quit the program.
 * Disposes the main frame.
 * 
 * @author Eric LaBouve
 */
public class QuitGameAction extends AbstractAction
{
    /** Reference to the frame */
    private GameBoardTableFrame view;
    
    /**
     * Constructor for QuitGameAction.
     * 
     * @param viewp Reference to the frame
     */
    public QuitGameAction(GameBoardTableFrame viewp)
    {
        view = viewp;
    }
    
    /**
     * What is to happen to end the program.
     * 
     * @param event Not used
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        view.dispose();
    }
}