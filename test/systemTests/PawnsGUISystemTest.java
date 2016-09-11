package systemTests;

import framework.GameBoardTableView;
import framework.NewGameAction;
import pawns.actions.ChangeSkinAction;
import pawns.actions.ResizeBoardAction;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import junit.framework.TestCase;
import org.uispec4j.Key;
import pawns.PawnsRenderer;
import pawns.PawnsStatusLabel;
import pawns.PawnsTableFrame;
import pawns.PawnsTableModel;
import org.uispec4j.Table;
import org.uispec4j.Window;
import pawns.actions.UndoAction;

/**
 *
 * PAWNS

    Each color piece moves in correct direction.

    Only one square is moved on the first move.

    Click with the mouse on a piece to move it one space forward. 

    If the pawn is in a position to capture, left-click will capture 
    to the left, and right-click will capture to the right.

    If the player tries to make an illegal move, "Illegal move" is 
    displayed in the status area.

    Status bar displays number of pieces captured by each player, 
    e.g.  White: 0   Black: 3

    Undo action undoes immediately previous move.

    Game detects win configuration for black and displays win message.

    Game detects win configuration for white and displays win message.

    New Game action resets board and score.

    Board size can be changed through Preferences.

    Different piece images can be chosen through Skins preference.
 * 
 * @author Eric LaBouve
 */
public class PawnsGUISystemTest extends TestCase
{
    Window gameWindow;
    
    PawnsRenderer renderer;
    PawnsTableModel model;
    GameBoardTableView view;
    
    PawnsStatusLabel statuslabel;
    PawnsTableFrame frame;    
    
    public PawnsGUISystemTest() throws InterruptedException
    {
        super();
    }
    
    public void test() throws InterruptedException
    {
        renderer = new PawnsRenderer();
        model = new PawnsTableModel();        

        view = new GameBoardTableView(renderer, model);
        model.addTableView(view);
        statuslabel = new PawnsStatusLabel();
        
        frame = new PawnsTableFrame(model, statuslabel, renderer, "Hurkle");

        model.addTableModelListener(statuslabel);
        frame.getContentPane().add(statuslabel.getStatus());
        // Create the main contentPane panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 0));
        contentPanel.setOpaque(true);
        // Create the scroll pane and add the TableView to it.
        JScrollPane scrollPane = new JScrollPane(view);
        contentPanel.add(scrollPane);
        frame.getContentPane().add(contentPanel);
        // Pack the frame and set visible
        frame.pack();  
        new NewGameAction(model, frame).actionPerformed(null);
        
        gameWindow = new Window(frame);
        frame.setVisible(true);        
        
        Table table = gameWindow.getTable("table");
        
        pause(2000);
        // Start a new game
        new ResizeBoardAction(model, frame, 5, 5).actionPerformed(null);   
        pause();
        new ResizeBoardAction(model, frame, 8, 8).actionPerformed(null); 
        pause();
        new ChangeSkinAction(model, frame, renderer, "Pawns", "GameOfThrones").actionPerformed(null);
        
        pause();
        table.click(1, 2);
        pause(500);
        table.click(1, 1);
        pause(500);
        table.click(2, 2);
        pause(500);
        table.click(2, 1);
        pause(500);
        table.click(3, 2);
        pause(500);
        table.click(3, 1);
        pause(500);
        table.click(4, 2);
        pause(500);
        table.click(4, 1);
        pause(500);
        table.click(5, 1); // Illegal Move
        pause(500);
        table.click(6, 0, Key.Modifier.CONTROL);
        pause(500);
        table.click(6, 3, Key.Modifier.SHIFT);
        pause(500);
        new UndoAction(model, statuslabel, frame).actionPerformed(null);
        pause(500);
        new UndoAction(model, statuslabel, frame).actionPerformed(null);
        pause(500);
        table.click(5, 1, Key.Modifier.CONTROL);
        pause(500);
        table.click(5, 2, Key.Modifier.SHIFT);
        pause(500);
        table.click(6, 1);
        pause(500);
        table.click(6, 2);
        pause();
        new UndoAction(model, statuslabel, frame).actionPerformed(null);
        pause(500);
        new UndoAction(model, statuslabel, frame).actionPerformed(null);
        pause(500);
        table.click(6, 4);
        pause(300);
        table.click(5, 4);
        pause(300);
        table.click(4, 4);
        pause(300);
        table.click(3, 4);
        pause(300);
        table.click(2, 4, Key.Modifier.SHIFT);
        pause(300);
        table.click(1, 3);
        pause();
    }
    
    public void pause() throws InterruptedException
    {
        Thread.sleep(1000);
    }
    
    public void pause(int milli) throws InterruptedException
    {
        Thread.sleep(milli);
    } 
}
