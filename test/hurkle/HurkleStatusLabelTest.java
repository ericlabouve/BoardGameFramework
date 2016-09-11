package hurkle;

import framework.GameBoardTableModel;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class HurkleStatusLabelTest extends TestCase {
    
    HurkleStatusLabel label;
    HurkleTableModel model = mock(HurkleTableModel.class);
    public HurkleStatusLabelTest(String testName) {
        super(testName);
        label = new HurkleStatusLabel();
    }

    /**
     * Test of getInitialStatus method, of class HurkleStatusLabel.
     */
    public void testGetInitialStatus() {
        System.out.println("getInitialStatus");
        assertEquals("Moves: 0", label.getInitialStatus());
    }

    /**
     * Test of getIllegalMoveStatus method, of class HurkleStatusLabel.
     */
    public void testGetIllegalMoveStatus() {
        System.out.println("getIllegalMoveStatus");
        assertEquals("Illegal Move", label.getIllegalMoveStatus(model));
    }

    /**
     * Test of getValidMoveStatus method, of class HurkleStatusLabel.
     */
    public void testGetValidMoveStatus() {
        System.out.println("getValidMoveStatus");
        when(model.getPlayerGuesses()).thenReturn(3);
        when(model.getHint()).thenReturn(Direction.NORTH);
        assertEquals("Moves: 3, Search North", label.getValidMoveStatus(model));
    }

    /**
     * Test of getGameOverStatus method, of class HurkleStatusLabel.
     */
    public void testGetGameOverStatus() {
        System.out.println("getGameOverStatus");
        when(model.getPlayerWon()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        assertEquals("You Win!", label.getGameOverStatus(model));
        assertEquals("You Lose!", label.getGameOverStatus(model));
    }

    /**
     * Test of getGameDrawStatus method, of class HurkleStatusLabel.
     */
    public void testGetGameDrawStatus() {
        System.out.println("getGameDrawStatus");
        assertEquals("Draw - Should never happen", label.getGameDrawStatus(model));
    }
}
