package hurkle;

import hurkle.actions.ChangeSkinAction;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class HurkleRendererTest extends TestCase {
    
    HurkleRenderer renderer;    
    /** Reference to the model */
    HurkleTableModel model = mock(HurkleTableModel.class);
    /** Reference to the frame */
    HurkleTableFrame frame = mock(HurkleTableFrame.class);
    /** The name of the pluggin */
    String plugginName = "Hurkle";
    /** The name of the skin type */
    String skinName = "Alderaan";
    
    public HurkleRendererTest(String testName) {
        super(testName);
        renderer = new HurkleRenderer(); 
    }

    /**
     * Test of setValue method, of class HurkleRenderer.
     */
    public void testSetValue() {
        System.out.println("setValue");
        // No Tests for GUI
    }

    /**
     * Test of setImages method, of class HurkleRenderer.
     */
    public void testSetImages() {
        System.out.println("setImages");
        new ChangeSkinAction(model, frame, renderer, plugginName, skinName).actionPerformed(null);
        assertEquals("src/hurkle/Images/Alderaan/Background/bkgd.png", renderer.getBackgroundIcon().getDescription());
        assertEquals(2, renderer.getImageMap().size());
    }
}
