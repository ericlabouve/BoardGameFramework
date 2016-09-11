package pawns;

import framework.Renderable;
import junit.framework.TestCase;

/**
 *
 * @author Eric
 */
public class PawnsModelStateTest extends TestCase {
    
    PawnsModelState state;
    Renderable[][] tab;
    
    public PawnsModelStateTest(String testName) {
        super(testName);
        tab = new Renderable[1][1];
        tab[0][0] = Pawn.BLACK;
        state = new PawnsModelState(tab);
        state = new PawnsModelState(tab, false, 1, 1, false, null);
    }

    /**
     * Test of getTable method, of class PawnsModelState.
     */
    public void testGetTable() {
        System.out.println("getTable");
        assertEquals(true, state.getTable() instanceof Renderable[][]);
    }
}
