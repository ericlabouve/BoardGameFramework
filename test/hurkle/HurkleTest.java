package hurkle;

import junit.framework.TestCase;

/**
 *
 * @author Eric
 */
public class HurkleTest extends TestCase {
    
    public HurkleTest(String testName) {
        super(testName);
    }

    /**
     * Test of getText method, of class Hurkle.
     */
    public void testGetText() {
        System.out.println("getText");
        assertEquals("Hurkle", Hurkle.HURKLE.getText());
    }

    /**
     * Test of getConsoleSymbol method, of class Hurkle.
     */
    public void testGetConsoleSymbol() {
        System.out.println("getConsoleSymbol");
        assertEquals("H", Hurkle.HURKLE.getConsoleSymbol());
        assertEquals("O", Hurkle.EMPTY_CLEAR.getConsoleSymbol());
        assertEquals("+", Hurkle.EMPTY_SOLID.getConsoleSymbol());
    }
}
