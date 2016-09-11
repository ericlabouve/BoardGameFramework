package pawns;

import framework.GameBoardTableModel;
import framework.GameState;
import javax.swing.event.TableModelEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class PawnsStatusLabelTest extends TestCase {
    
    PawnsStatusLabel label;
    PawnsTableModel model;
    
    public PawnsStatusLabelTest(String testName) {
        super(testName);
        label = new PawnsStatusLabel();
        model = mock(PawnsTableModel.class);
    }

    /**
     * Test of getInitialStatus method, of class PawnsStatusLabel.
     */
    public void testGetInitialStatus() {
        System.out.println("getInitialStatus");
        assertEquals("White: 0\tBlack: 0", label.getInitialStatus());
    }

    /**
     * Test of getIllegalMoveStatus method, of class PawnsStatusLabel.
     */
    public void testGetIllegalMoveStatus() {
        System.out.println("getIllegalMoveStatus");
        assertEquals("Illegal move", label.getIllegalMoveStatus(null));
    }

    /**
     * Test of getValidMoveStatus method, of class PawnsStatusLabel.
     */
    public void testGetValidMoveStatus() {
        System.out.println("getValidMoveStatus");
        when(model.getWhiteScore()).thenReturn(1);
        when(model.getBlackScore()).thenReturn(1);
        assertEquals("White: 1\tBlack: 1", label.getValidMoveStatus(model));
    }

    /**
     * Test of getGameOverStatus method, of class PawnsStatusLabel.
     */
    public void testGetGameOverStatus() {
        System.out.println("getGameOverStatus");
        when(model.hasWhiteWon()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        assertEquals("White Wins!", label.getGameOverStatus(model));
        assertEquals("Black Wins!", label.getGameOverStatus(model));
    }

    /**
     * Test of getGameDrawStatus method, of class PawnsStatusLabel.
     */
    public void testGetGameDrawStatus() {
        System.out.println("getGameDrawStatus");
        assertEquals("Draw", label.getGameDrawStatus(model));
    }
    
    public void testUpdateStatus()
    {
        TableModelEvent evt = mock(TableModelEvent.class);
        when(evt.getSource()).thenReturn(model);
        when(model.getGameState()).thenReturn(GameState.GAMENOTOVER).thenReturn(
                GameState.GAMENOTOVER).thenReturn(GameState.GAMEOVER).thenReturn(
                GameState.GAMEDRAW).thenReturn(GameState.GAMEOVER);
        when(model.getIllegalMove()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(model.getWhiteScore()).thenReturn(1);
        when(model.getBlackScore()).thenReturn(1);
        when(model.hasWhiteWon()).thenReturn(Boolean.TRUE);
        
        label.tableChanged(evt);
        assertEquals("Illegal move", label.getStatus().getText()); // Get Illegal Move Status
        label.updateStatus(model);
        assertEquals("White: 1\tBlack: 1", label.getStatus().getText()); // Get Valid Move Status
        
        label.updateStatus(model);
        assertEquals("Draw", label.getStatus().getText()); //Get Game Draw Status
        
        label.updateStatus(model);
        assertEquals("White Wins!", label.getStatus().getText()); // Get Game Over Status
    }
}
