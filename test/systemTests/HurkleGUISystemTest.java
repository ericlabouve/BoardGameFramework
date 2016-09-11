package systemTests;

import framework.GameBoardTableView;
import framework.NewGameAction;
import hurkle.HurkleRenderer;
import hurkle.HurkleStatusLabel;
import hurkle.HurkleTableFrame;
import hurkle.HurkleTableModel;
import hurkle.actions.ChangeSkinAction;
import hurkle.actions.CheatAction;
import hurkle.actions.ResizeBoardAction;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import junit.framework.TestCase;
import org.uispec4j.Table;
import org.uispec4j.Window;

/**
 *
 * 
    HURKLE

    "Cheat" action starts a new game with the Hurkle hidden in the 
    upper left corner of the board. 

    If the player finds the Hurkle, the program displays a win message 
    in the status area and the Hurkle icon is displayed in the cell.

    Each time the game is run the program places the Hurkle at a random 
    position on the board.

    For incorrect guess, all eight possible hints are given correctly 
    in the status panel.
    
    Each guess reveals the background behind that cell.

    Player loses after five guesses.

    Board size can be changed through Preferences.

    Different hurkle icon and background can be chosen through 
    Skins preference.
    
 * 
 * @author Eric LaBouve
 */
public class HurkleGUISystemTest extends TestCase
{
    Window gameWindow;
    
    HurkleRenderer renderer;
    HurkleTableModel model;
    GameBoardTableView view;
    
    HurkleStatusLabel statuslabel;
    HurkleTableFrame frame;
    
    
    public HurkleGUISystemTest() throws InterruptedException
    {
        super();
    }    
    
    public void test() throws InterruptedException
    {
        renderer = new HurkleRenderer();
        model = new HurkleTableModel();        

        view = new GameBoardTableView(renderer, model);
        model.addTableView(view);
        statuslabel = new HurkleStatusLabel();
        
        frame = new HurkleTableFrame(model, statuslabel, renderer, "Hurkle");

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
        new ChangeSkinAction(model, frame, renderer, "Hurkle", "Alderaan").actionPerformed(null);
        new CheatAction(model, frame).actionPerformed(null);        
        pause();
        
        // Click around the Hurkle to induce all hints:
        table.click(0, 0);
        pause();
        table.click(1, 0);
        pause();
        table.click(2, 0);
        pause();
        table.click(2, 1);
        pause();
        table.click(2, 2);
        pause();
        // Acter 5 clicks the player loses
        new CheatAction(model, frame).actionPerformed(null);        
        pause();
        table.click(2, 2);
        pause();
        table.click(1, 2);
        pause();
        table.click(0, 2);
        pause();
        table.click(0, 1);
        pause();
        new CheatAction(model, frame).actionPerformed(null); 
        pause();
        table.click(1, 1);
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