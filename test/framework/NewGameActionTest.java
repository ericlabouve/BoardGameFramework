package framework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class NewGameActionTest extends TestCase {
    
    /** Reference to the model */
    GameBoardTableModel model = mock(GameBoardTableModel.class);
    /** Reference to the frame */
    GameBoardTableFrame frame = mock(GameBoardTableFrame.class);
    
    NewGameAction action;
    
    public NewGameActionTest(String testName) {
        super(testName);
        action = new NewGameAction(model, frame);
    }

    /**
     * Test of actionPerformed method, of class NewGameAction.
     */
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent event = null;
        
        Dimension dim = mock(Dimension.class);
        when(model.getExpectedDimension()).thenReturn(dim);
        
        action.actionPerformed(event);
                
        verify(model).newGameBoard();
        verify(frame).setSize(dim);
    }
}
