package framework;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;


/**
 * No tests are written since GameBoardTableView is part of the User Interface.
 * @author Eric
 */
public class GameBoardTableViewTest extends TestCase {
    
    GameBoardRenderer renderer = mock(GameBoardRenderer.class); 
    GameBoardTableModel model = mock(GameBoardTableModel.class);
    
    public GameBoardTableViewTest(String testName) {
        super(testName);
        GameBoardTableView view = new GameBoardTableView(renderer, model);
    }
    
    public void testNothing()
    {
        // Do Nothing.
    }
}
