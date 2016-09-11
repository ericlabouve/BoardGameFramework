package hurkle;

import framework.GameBoardTableView;
import hurkle.actions.CheatAction;
import hurkle.actions.ResizeBoardAction;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Eric
 */
public class HurkleTableModelTest extends TestCase {
    
    HurkleTableModel model;
    HurkleTableFrame frame = mock(HurkleTableFrame.class);
    GameBoardTableView view = mock(GameBoardTableView.class);
    
    public HurkleTableModelTest(String testName) {
        super(testName);
        model = new HurkleTableModel();                
        model = new HurkleTableModel(5, 5);
        model.addTableView(view);
    }

    /**
     * Test of makeMove method, of class HurkleTableModel.
     */
    public void testMakeMove() {
        System.out.println("makeMove");
        new ResizeBoardAction(model, frame, 5, 5).actionPerformed(null);
        model.cheatLayout();
        model.makeMove(0, 0, false, false); // Wrong guess
        assertEquals(Direction.SOUTH_EAST, model.getHint());
        assertEquals(Hurkle.EMPTY_CLEAR, (Hurkle) model.getValueAt(0, 0));
        assertEquals(1, model.getPlayerGuesses());
        assertEquals(false, model.getIllegalMove());
        model.makeMove(0, 0, false, false); // Illegal guess
        assertEquals(true, model.getIllegalMove());
        model.makeMove(1, 1, false, false); // Correct guess
        assertEquals(true, model.getPlayerWon());
        assertEquals(Hurkle.HURKLE, (Hurkle) model.getValueAt(1, 1));
        for (int row = 0; row < model.getRowCount(); row++)
        {
            // Loop through every column
            for (int col = 0; col < model.getColumnCount(); col++)
            {
                Hurkle hurkl = (Hurkle) model.getValueAt(row, col);
                if (row == 1 && col == 1) {
                    assertEquals(Hurkle.HURKLE, model.getValueAt(row, col));
                }
                else {
                    assertEquals(Hurkle.EMPTY_CLEAR, model.getValueAt(row, col));
                }
            }
        }
    }

    /**
     * Test of isGameOver method, of class HurkleTableModel.
     */
    public void testIsGameOver() {
        System.out.println("isGameOver");
        model.newGameBoard();
        new CheatAction(model, frame).actionPerformed(null);
        assertEquals(false, model.isGameOver());
        model.makeMove(0, 0, false, false);
        model.makeMove(1, 0, false, false);
        model.makeMove(2, 0, false, false);
        model.makeMove(3, 0, false, false);
        model.makeMove(4, 0, false, false);
        assertEquals(true, model.isGameOver());
    }

    /**
     * Test of isDraw method, of class HurkleTableModel.
     */
    public void testIsDraw() {
        System.out.println("isDraw");
        assertEquals(false, model.isDraw());
    }

    /**
     * Test of newGameBoard method, of class HurkleTableModel.
     */
    public void testNewGameBoard() {
        System.out.println("newGameBoard");
        model.newGameBoard();
        
        for (int row = 0; row < model.getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < model.getColumnCount(); col++)
            {
                Hurkle hurkle = (Hurkle) model.getValueAt(row, col);
                assertEquals(Hurkle.EMPTY_SOLID, hurkle);
            }
        }
    }

    /**
     * Test of save method, of class HurkleTableModel.
     */
    public void testSave() {
        System.out.println("save");
        model.save();
        // Save has no implementation. 
        // This test therefor has no implementation.
    }

    /**
     * Test of getPlayerGuesses method, of class HurkleTableModel.
     */
    public void testGetPlayerGuesses() {
        System.out.println("getPlayerGuesses");
        model.newGameBoard();
        assertEquals(0, model.getPlayerGuesses());
        model.cheatLayout();
        model.makeMove(0, 0, false, false);
        assertEquals(1, model.getPlayerGuesses());
    }

    /**
     * Test of getPlayerWon method, of class HurkleTableModel.
     */
    public void testGetPlayerWon() {
        System.out.println("getPlayerWon");
        model.newGameBoard();
        assertEquals(false, model.getPlayerWon());
    }

    /**
     * Test of getHint method, of class HurkleTableModel.
     */
    public void testGetHint() {
        System.out.println("getHint");
        model.newGameBoard();
        model.cheatLayout();
        model.makeMove(1, 0, false, false);
        assertEquals(Direction.EAST, model.getHint());
    }

    /**
     * Test of cheatLayout method, of class HurkleTableModel.
     */
    public void testCheatLayout() {
        System.out.println("cheatLayout");
        model.cheatLayout();
        model.makeMove(1, 1, false, false);
        assertEquals(Direction.NONE, model.getHint());
    }
}
