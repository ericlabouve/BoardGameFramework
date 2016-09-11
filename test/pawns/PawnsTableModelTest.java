package pawns;

import framework.GameBoardTableView;
import framework.GameState;
import framework.Renderable;
import java.awt.Dimension;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import pawns.actions.ResizeBoardAction;
import pawns.actions.UndoAction;

/**
 *
 * @author Eric
 */
public class PawnsTableModelTest extends TestCase {
    
    PawnsTableModel model;
    GameBoardTableView view;
    PawnsTableFrame frame = mock(PawnsTableFrame.class);
    PawnsStatusLabel label = mock(PawnsStatusLabel.class);
    
    public PawnsTableModelTest(String testName) {
        super(testName);
        view = mock(GameBoardTableView.class);
        
        model = new PawnsTableModel(5, 5);
        model = new PawnsTableModel();
        model.addTableView(view);        
        model.resizeBoard(5, 5);                
    }
    
/**************Framework Tests*************************************/    
    
    public void testIsValidCoordinate()
    {
        System.out.println("testIsValidCoordinate");
        // This test needs to use a 5 x 5 game board
        model.resizeBoard(5, 5);
        assertEquals(true, model.isValidCoordinate(0,0));
        assertEquals(false, model.isValidCoordinate(9,9));
    }
    
    public void testUpdateGameState()
    {
        System.out.println("testUpdateGameState");
        // This test needs to use a 5 x 5 game board
        new ResizeBoardAction(model, frame, 5, 5).actionPerformed(null);
        
        model.newGameBoard();
        assertEquals(GameState.GAMENOTOVER, model.updateGameState());
        
        model.setValueAt(Pawn.WHITE, 0, 0);
        assertEquals(GameState.GAMEOVER, model.updateGameState());
        
        model.setValueAt(Pawn.EMPTY, 0, 0);
        model.setValueAt(Pawn.BLACK, 4, 4);
        assertEquals(GameState.GAMEOVER, model.updateGameState());
    }
    
    /**
     * [width=355,height=395]
     */
    public void testGetExpectedDimension()
    {
        System.out.println("testGetExpectedDimension");
        // This test needs to use a 5 x 5 game board
        model.resizeBoard(5, 5);
        
        Dimension dim = model.getExpectedDimension();
        assertEquals((double) 355, dim.getWidth());
        assertEquals((double) 395, dim.getHeight());
    }
    
    public void testGettersAndSetters()
    {
        System.out.println("testGettersAndSetters");
        assertEquals(5, model.getRowCount());
        assertEquals(5, model.getColumnCount());
        model.setValueAt(Pawn.WHITE, 0, 0);
        assertEquals(Pawn.WHITE, model.getValueAt(0, 0));
        model.updateGameState();
        assertEquals(GameState.GAMENOTOVER, model.getGameState());
        model.setIllegalMove(false);
        assertEquals(false, model.getIllegalMove());
        
    }
    
    
/**************Plugin Tests*************************************/
    /**
     * Test of makeMove method, of class PawnsTableModel.
     */
    public void testMakeMove() {
        System.out.println("makeMove");
        model.newGameBoard();
        // This test needs to use a 5 x 5 game board
        model.resizeBoard(5, 5);
        
        model.makeMove(0, 0, false, false);
        assertEquals(true, model.getIllegalMove());
        
        model.makeMove(1, 0, false, false);
        assertEquals(Pawn.EMPTY, (Pawn)model.getValueAt(1, 0)); // Move Black pawn down
        assertEquals(Pawn.BLACK, (Pawn)model.getValueAt(2, 0));
        
        model.makeMove(3, 2, false, false);
        assertEquals(Pawn.EMPTY, (Pawn)model.getValueAt(3, 2)); // Move White Pawn up
        assertEquals(Pawn.WHITE, (Pawn)model.getValueAt(2, 2));
        
        model.makeMove(2, 2, true, false);
        assertEquals(Pawn.EMPTY, (Pawn)model.getValueAt(2, 2)); // Attack Left with White Pawn
        assertEquals(Pawn.WHITE, (Pawn)model.getValueAt(1, 1));
        
        model.makeMove(2, 0, false, true);
        assertEquals(Pawn.EMPTY, (Pawn)model.getValueAt(2, 0)); // Attack Right with Black Pawn
        assertEquals(Pawn.BLACK, (Pawn)model.getValueAt(3, 1));
    }

    /**
     * Test of newGameBoard method, of class PawnsTableModel.
     */
    public void testNewGameBoard() {
        System.out.println("newGameBoard");

        model.newGameBoard();
        
        for (int row = 0; row < model.getRowCount(); row++)
        {
            // Initialize every column
            for (int col = 0; col < model.getColumnCount(); col++)
            {
                //The second row from the bottom
                if (row == 1)
                {
                    Pawn pawn = (Pawn) model.getValueAt(row, col);
                    assertEquals(Pawn.BLACK, pawn);
                }
                //The second row from the top
                else if (row == model.getRowCount() - 2)
                {
                    Pawn pawn = (Pawn) model.getValueAt(row, col);
                    assertEquals(Pawn.WHITE, pawn);
                }
                //All other spots
                else
                {
                    Pawn pawn = (Pawn) model.getValueAt(row, col);
                    assertEquals(Pawn.EMPTY, pawn);
                }
            }
        }
    }

    /**
     * Test of save method, of class PawnsTableModel.
     */
    public void testSave() {
        System.out.println("save");
        model.newGameBoard();
        // This test needs to use a 5 x 5 game board
        model.resizeBoard(5, 5);       
        
        model.save();
        
        model.makeMove(1, 0, false, false);
        assertEquals(Pawn.EMPTY, (Pawn)model.getValueAt(1, 0)); // Move Black pawn down
        assertEquals(Pawn.BLACK, (Pawn)model.getValueAt(2, 0));
        
        new UndoAction(model, label, frame).actionPerformed(null);
        
        assertEquals(Pawn.BLACK, (Pawn)model.getValueAt(1, 0));
    }


    /**
     * Test of isEnemyPawn method, of class PawnsTableModel.
     */
    public void testIsEnemyPawn() {
        System.out.println("isEnemyPawn");
        model.newGameBoard();        
        assertEquals(true, model.isEnemyPawn(Pawn.WHITE, 1, 1));
    }

    /**
     * Test of getWhiteScore method, of class PawnsTableModel.
     */
    public void testGetWhiteScore() {
        System.out.println("getWhiteScore");

    }

    /**
     * Test of getBlackScore method, of class PawnsTableModel.
     */
    public void testGetBlackScore() {
        System.out.println("getBlackScore");

    }

    /**
     * Test of hasWhiteWon method, of class PawnsTableModel.
     */
    public void testHasWhiteWon() {
        System.out.println("hasWhiteWon");

    }

    /**
     * Test of restoreGameBoard method, of class PawnsTableModel.
     */
    public void testRestoreGameBoard() {
        System.out.println("restoreGameBoard");

    }

    /**
     * Test of resizeBoard method, of class PawnsTableModel.
     */
    public void testResizeBoard() {
        System.out.println("resizeBoard");

    }
}
