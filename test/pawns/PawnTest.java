package pawns;

import junit.framework.TestCase;

/**
 *
 * @author Eric
 */
public class PawnTest extends TestCase {
    
    Pawn pawnW = Pawn.WHITE;
    Pawn pawnB = Pawn.BLACK;
    Pawn pawnE = Pawn.EMPTY;
    
    public PawnTest(String testName) 
    {
        super(testName);
    }

    /**
     * Test of getText method, of class Pawn.
     */
    public void testGetText() {
        System.out.println("testGetText");
        assertEquals("White", pawnW.getText());
        assertEquals("Black", pawnB.getText());
        assertEquals("Empty", pawnE.getText());
    }

    /**
     * Test of getConsoleSymbol method, of class Pawn.
     */
    public void testGetConsoleSymbol() {
        System.out.println("getConsoleSymbol");
        assertEquals("W", pawnW.getConsoleSymbol());
        assertEquals("B", pawnB.getConsoleSymbol());
        assertEquals("+", pawnE.getConsoleSymbol());
    }
}
