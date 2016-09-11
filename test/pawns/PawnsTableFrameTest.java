package pawns;

import framework.GameBoardRenderer;
import framework.GameBoardStatusLabel;
import framework.GameBoardTableModel;
import javax.swing.AbstractAction;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class PawnsTableFrameTest extends TestCase {
    
    PawnsTableFrame frame;
    PawnsTableModel model = mock(PawnsTableModel.class);
    PawnsStatusLabel label = mock(PawnsStatusLabel.class);
    PawnsRenderer renderer = mock(PawnsRenderer.class);
    String path = "Pawns";
    
    public PawnsTableFrameTest(String testName) {
        super(testName);
        frame = new PawnsTableFrame(model, label, renderer, path);        
    }

    /**
     * Test of getPreferencesActions method, of class PawnsTableFrame.
     */
    public void testGetPreferencesActions() {
        System.out.println("getPreferencesActions");
        assertEquals(7, frame.getPreferencesActions(model, label).length);
    }
    
    public void testGetters() {
        System.out.println("testGetters");
        assertEquals(9, frame.getMenuItems().size());
        assertEquals(9, frame.getMenuActionItems().size());
    }
            
}
