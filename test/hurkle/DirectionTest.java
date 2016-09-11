package hurkle;

import junit.framework.TestCase;

/**
 *
 * @author Eric
 */
public class DirectionTest extends TestCase {
    
    
    public DirectionTest(String testName) {
        super(testName);
    }

    /**
     * Test of getText method, of class Direction.
     */
    public void testGetText() {
        System.out.println("getText");
        Direction dir = Direction.EAST;
        assertEquals("East", dir.getText());        
    }

    /**
     * Test of calculateDirection method, of class Direction.
     */
    public void testCalculateDirection() {
        System.out.println("calculateDirection");
        int rowHurkle = 1;
        int colHurkle = 1;
        
        // West
        assertEquals(Direction.WEST, Direction.calculateDirection(1, 2, rowHurkle, colHurkle));
        // East
        assertEquals(Direction.EAST, Direction.calculateDirection(1, 0, rowHurkle, colHurkle));
        // North
        assertEquals(Direction.NORTH, Direction.calculateDirection(2, 1, rowHurkle, colHurkle));
        // South 
        assertEquals(Direction.SOUTH, Direction.calculateDirection(0, 1, rowHurkle, colHurkle));
        // North East
        assertEquals(Direction.NORTH_EAST, Direction.calculateDirection(2, 0, rowHurkle, colHurkle));
        // Nort West
        assertEquals(Direction.NORTH_WEST, Direction.calculateDirection(2, 2, rowHurkle, colHurkle));
        // South East
        assertEquals(Direction.SOUTH_EAST, Direction.calculateDirection(0, 0, rowHurkle, colHurkle));
        // South Wwst
        assertEquals(Direction.SOUTH_WEST, Direction.calculateDirection(0, 2, rowHurkle, colHurkle));
        // None
        assertEquals(Direction.NONE, Direction.calculateDirection(1, 1, rowHurkle, colHurkle));
    }
}
