package framework;

import java.awt.event.ActionEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class QuitGameActionTest extends TestCase {
    
    GameBoardTableFrame view = mock(GameBoardTableFrame.class);
    
    QuitGameAction action;
    
    public QuitGameActionTest(String testName) {
        super(testName);
        action = new QuitGameAction(view);
    }

    /**
     * Test of actionPerformed method, of class QuitGameAction.
     */
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent event = null;
        action.actionPerformed(event);
        
        verify(view).dispose();
    }
}
