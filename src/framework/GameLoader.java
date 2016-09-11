package framework;


import java.awt.GridLayout;
import java.lang.reflect.Constructor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 * Main class that loads the appropriate pluggin and displays either
 * a gui display or a console display.
 * 
 * @author Eric LaBouve
 */
public class GameLoader 
{
    private static final String kUsage = "ERROR IN COMMANDLINE ARGUMENTS\n:"
                   + "Usage:\n"
                   + "args[0] Either \"-g\" for GUI view "
                   + "or \"-c\" for Console view.\n"
                   + "args[1] The name of the plugin.\n"
                   + "     - First letter must be upper case.\n"
                   + "     - All subsequent letters must be lower case. ";
    /**
     * Usage: args[0] Either "-g" for GUI view or "-c" for Console view.
     *    args[1] The name of the plugin.
     *          - First letter must be upper case.
     *          - All subsequent letters must be lower case.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try 
        {       
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");  
            
            String path = args[1].toLowerCase() + "." + args[1];           
            // Create the Renderer
            Class rendererClass = Class.forName(path + "Renderer");
            GameBoardRenderer renderer = (GameBoardRenderer) rendererClass.newInstance();

            // Create the TableModel
            Class tableModelClass = Class.forName(path + "TableModel");
            GameBoardTableModel model = (GameBoardTableModel) 
                tableModelClass.newInstance();

            // Create the JTable.  // Encapsulated by framework
            GameBoardTableView view = new GameBoardTableView(renderer, model);

            model.addTableView(view);

            // Create a panel for the status information
            Class statusClass = Class.forName(path + "StatusLabel");
            GameBoardStatusLabel statuslabel = (GameBoardStatusLabel) 
                statusClass.newInstance();
           
            // Create the frame to hold our game.  
            // Pass in name of pluggin for preferences path
            Class tableFrameClass = Class.forName(path + "TableFrame");
            Constructor tableFrameConst = tableFrameClass.getConstructor(
                GameBoardTableModel.class, GameBoardStatusLabel.class, 
                    GameBoardRenderer.class, String.class);
            GameBoardTableFrame frame = (GameBoardTableFrame) 
                tableFrameConst.newInstance(
                    model, statuslabel, renderer, args[1]);
           
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
           
            // Start a new game
            new NewGameAction(model, frame).actionPerformed(null);
            


            // Determine if the user wants the GUI view or Console view
            // Gui View
            if (args[0].equals("-g"))
            {
                // Set the frame visible
                frame.setVisible(true);
            }
            // Console View
            else if (args[0].equals("-c"))
            {               
                GameBoardConsole console = new GameBoardConsole(
                    model, frame, statuslabel);
                console.setVisible();
            }
            // Commandline args are wrong
            else
            {
                System.err.println(kUsage);
            }
        }
        catch(Exception exc) 
        {
            exc.printStackTrace();
        }
    }
}