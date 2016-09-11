package framework;

import java.awt.event.MouseEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class GameBoardMouseAdapterTest extends TestCase {
    
    GameBoardTableModel model = mock(GameBoardTableModel.class);
    GameBoardTableView view = mock(GameBoardTableView.class);
    GameBoardMouseAdapter mouse;
    
    /** Key code for the Control key */
    private final int kCtrl = 128;
    /** Key code for the Shift key */
    private final int kShift = 64;
                    
    public GameBoardMouseAdapterTest(String testName) {
        super(testName);
        mouse = new GameBoardMouseAdapter(model, view);
    }

    /**
     * Test of mouseReleased method, of class GameBoardMouseAdapter.
     */
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        
        MouseEvent evt = mock(MouseEvent.class);
        when(evt.getModifiersEx()).thenReturn(kCtrl);
        when(view.getSelectedColumn()).thenReturn(5);
        when(view.getSelectedRow()).thenReturn(5);
        when(model.isValidCoordinate(anyInt(), anyInt())).thenReturn(Boolean.TRUE);
        
        mouse.mouseReleased(evt);
        
        verify(model).save();
        verify(model).makeMove(anyInt(), anyInt(), anyBoolean(), anyBoolean());
        verify(model).updateGameState();
        verify(model).fireTableDataChanged();
        verify(view).repaint();
    }
}
