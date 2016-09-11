package framework;

import java.awt.event.ActionEvent;
import java.io.Writer;
import java.util.Scanner;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import java.io.StringWriter;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author Eric
 */
public class GameBoardConsoleTest extends TestCase {
    
    GameBoardTableModel model = mock(GameBoardTableModel.class);
    GameBoardTableFrame frame = mock(GameBoardTableFrame.class); 
    GameBoardStatusLabel statusLabel = mock(GameBoardStatusLabel.class);
    GameBoardConsole console;
    
    String board = "testSetVisible\n" +
                   "    0 1 2 3 4 \n" +
                   "A:  + + + + + \n" +
                   "B:  + + + + + \n" +
                   "C:  + + + + + \n" +
                   "D:  + + + + + \n" +
                   "E:  + + + + + \n" +
                   "--------------\n" +
                   "0)Quit \n"; 
    String expected = "";
    
    public GameBoardConsoleTest(String testName) {
        super(testName);
        console = new GameBoardConsole(model, frame, statusLabel);  
        for(int i = 0; i < 5; i++){
            expected += board;
        }
        expected = expected.trim();
    }

    /**
     * Test of setVisible method, of class GameBoardConsole.
     */
    public void testSetVisible() {
        System.out.println("setVisible");
        StringWriter swriter = new StringWriter();
        console.setWriter(swriter);
        //Left Click, Right Click, Error Click, Quit
        console.setReader(new Scanner("B2L\nB2R\nB2m\n2\n"));
        
        JMenuItem item = mock(JMenuItem.class);
        when(item.getText()).thenReturn("Quit");
        List<JMenuItem> list = mock(List.class);
        when(list.get(anyInt())).thenReturn(item);
        when(list.size()).thenReturn(1);
        when(frame.getMenuItems()).thenReturn(list);  
        
        when(model.updateGameState()).thenReturn(GameState.GAMEOVER);
        
        Renderable obj = mock(Renderable.class);
        when(obj.getConsoleSymbol()).thenReturn("+");
        when(model.getValueAt(anyInt(), anyInt())).thenReturn(obj);
        when(model.getRowCount()).thenReturn(5);
        when(model.getColumnCount()).thenReturn(5);
        
        JLabel label = mock(JLabel.class);
        when(label.getText()).thenReturn("testSetVisible");
        when(statusLabel.getStatus()).thenReturn(label);        
        
        List<AbstractAction> actionList = mock(List.class);
        AbstractAction action = mock(AbstractAction.class);
        when(actionList.get(anyInt())).thenReturn(action);
        when(frame.getMenuActionItems()).thenReturn(actionList);
        
        console.setVisible();
        String result = swriter.toString().trim();
        assertEquals("Output String comparison", expected, result);
    }
}
