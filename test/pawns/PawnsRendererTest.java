package pawns;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import pawns.actions.ChangeSkinAction;

/**
 *
 * @author Eric
 */
public class PawnsRendererTest extends TestCase {
    
    PawnsRenderer renderer;
    
    /** Reference to the model */
    PawnsTableModel model = mock(PawnsTableModel.class);
    /** Reference to the frame */
    PawnsTableFrame frame = mock(PawnsTableFrame.class);
    /** The name of the pluggin */
    String plugginName = "Pawns";
    /** The name of the skin type */
    String skinName = "Game Of Thrones";
    
    public PawnsRendererTest(String testName) {
        super(testName);  
        renderer = new PawnsRenderer();        
    }

    /**
     * Test of setValue method, of class PawnsRenderer.
     */
    public void testSetValue() {
        System.out.println("setValue");
        // NO TEST FOR GUI
    }

    /**
     * Test of setImages method, of class PawnsRenderer.
     */
    public void testSetImages() {
        System.out.println("setImages"); 
        
        new ChangeSkinAction(model, frame, renderer, plugginName, skinName).actionPerformed(null);
        assertEquals("src/pawns/Images/Game Of Thrones/Background/bkgd.jpg", renderer.getBackgroundIcon().getDescription());
        assertEquals(2, renderer.getImageMap().size());
        
    }
}
