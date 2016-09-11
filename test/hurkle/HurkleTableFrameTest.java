package hurkle;

import framework.GameBoardStatusLabel;
import framework.GameBoardTableModel;
import javax.swing.AbstractAction;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class HurkleTableFrameTest extends TestCase {
    
    HurkleTableFrame frame;
    HurkleTableModel model = mock(HurkleTableModel.class);
    HurkleStatusLabel label = mock(HurkleStatusLabel.class);
    HurkleRenderer renderer = mock(HurkleRenderer.class);
    String path = "Hurkle";
    
    public HurkleTableFrameTest(String testName) {
        super(testName);
        frame = new HurkleTableFrame(model, label, renderer, path);
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
