package framework;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import org.ini4j.*;

/**
 * Represents the JFrame that contains the game. 
 * Constructs the Menu Bar and all menu items.
 * 
 * @author Eric LaBouve
 */
public abstract class GameBoardTableFrame extends JFrame
{
    /** MenuBar with Game options. */
    private JMenuBar menuBar = new JMenuBar();
    /** Drop down Game gameMenu. */
    private JMenu gameMenu = new JMenu("Game");
    /** Drop down Preferences gameMenu. */
    private JMenu preferencesMenu = new JMenu("Preferences");
    /** Items in the gameMenu that restart the game. */
    private JMenuItem restartItem;
    /** Items in the gameMenu that quit the game. */
    private JMenuItem quitItem;
    /** List of all JMenuItem Actions for convenient access */
    private List<AbstractAction> menuActionItems;
    /** List of all JMenuItem for convenient access */
    private List<JMenuItem> menuItems;
    /**Width of a board square*/
    private int kSquareWidth = 80;
    /**Height of a board square*/
    private int kSquareHeight = 80;
    /**Reference to the Renderer*/
    protected GameBoardRenderer renderer;
    /** The name of the pluggin */
    protected String plugginName;
    
    /**
     * Constructor for the PawnsTableFrame.
     * 
     * @param model A reference to the PawnsTableModel
     * @param label A reference to the StatusLabel
     * @param renderer A reference to the renderer
     * @param path The name of the plugin.
     */
    public GameBoardTableFrame(GameBoardTableModel model, 
        GameBoardStatusLabel label, GameBoardRenderer renderer, String path)
    {
        super();                   
        
        setTitle("Eric LaBouve - " + path);        
        setName("frame");

        // Define the layout manager that will control order of components
        getContentPane().setLayout(new BoxLayout(getContentPane(),
            BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menuActionItems = new ArrayList<AbstractAction>();
        menuItems = new ArrayList<JMenuItem>();
        setJMenuBar(menuBar);
        this.renderer = renderer;
        this.plugginName = path;
        
        /***************** Create the 'Game' Menu ********************/
        // Add the Game Menu
        menuBar.add(gameMenu);
        
        // Create the restartItem Menu Item
        restartItem = new JMenuItem("Restart");
        AbstractAction newGameAction = new NewGameAction(model, this);
        restartItem.addActionListener(newGameAction);
        restartItem.setAccelerator(KeyStroke.getKeyStroke(
            'R', ActionEvent.ALT_MASK));        
        menuActionItems.add(newGameAction);
        menuItems.add(restartItem);
        gameMenu.add(restartItem);        
        
        // Create the quit Menu Item
        quitItem = new JMenuItem("Quit");
        AbstractAction quitAction = new QuitGameAction(this);
        quitItem.addActionListener(quitAction);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(
            'Q', ActionEvent.ALT_MASK)); 
        menuActionItems.add(quitAction);
        menuItems.add(quitItem);
        gameMenu.add(quitItem);
        
        /*************** Create the 'Preferences' Menu ****************/
        // Add the Preferences Menu
        menuBar.add(preferencesMenu);
        createPreferenceMenuItems(path, model, label);   
    }
    
    /**
     * Creates the preferences menu itesm
     * @param path String path to plugin
     * @param model reference to the model
     * @param label reference to the label
     */
    private void createPreferenceMenuItems(String path, 
            GameBoardTableModel model, GameBoardStatusLabel label)
    {
        List<JMenu> prefMenus = loadPreferences(path);
        AbstractAction[] prefActions = getPreferencesActions(model, label);
        int prefActionsIdx = 0;
        char accelKey = '1';
        // Add the dynamically created menus to our preferences menu
        for (JMenu jmenu : prefMenus)
        {            
            // Loop through all the JMenuItems   
            for (Component comp : jmenu.getMenuComponents())
            {
                // Only add JMenuItem
                if (comp instanceof JMenuItem)
                {
                    JMenuItem item = (JMenuItem) comp;
                    item.addActionListener(prefActions[prefActionsIdx]);
                    item.setAccelerator(KeyStroke.getKeyStroke(
                        accelKey++, ActionEvent.ALT_MASK)); 
                    menuActionItems.add(prefActions[prefActionsIdx]);
                    menuItems.add(item);
                }
                prefActionsIdx++;
            }
            preferencesMenu.add(jmenu);
        }
    }
    
    /**
     * Loads Preferences MenuItems from preferences.ini
     * using the external library Ini4J
     * @param path Is the name of the package which preferences.ini is located.
     * @return a list of JMenu Items for the Preferences Menu
     */
    private final List<JMenu> loadPreferences(String path)
    {
        String sep = System.getProperty("file.separator");
        path = path.toLowerCase();
        // Will hold preferences JMenus found in preferences.ini
        ArrayList<JMenu> prefMenus = new ArrayList<JMenu>();
        // May cause an IO error
        try
        {
            Ini ini = new Ini(new File("src" + sep + path 
                + sep + "preferences.ini"));
            int itemCount = 0;
            boolean loaded = false;

            // Loop through all the sections
            for (Object sectionp : ini.keySet())
            {
                String section = (String) sectionp;
                JMenu menu = new JMenu(section);
                Profile.Section sectionProfile = 
                    (Profile.Section) ini.get(section);
                
                // Loop through all the inner sections
                for (Object namep : sectionProfile.keySet())
                {
                    String name = (String) namep;
                    JMenuItem item = new JMenuItem(name);
                    item.setName(name);
                    menu.add(item);
                }
                // Add to our Preference Menu list
                prefMenus.add(menu);
            }            
        }
        // Do nothing
        catch (IOException ex)
        {
            ex.printStackTrace();
            System.err.println("Coundn't find " + path + 
                sep + "preferences.ini");
        }
        //System.out.println(prefMenus.toArray());
        return prefMenus;
    }
    
    /**
     * Getter for |menuItems|
     * @return The list of JMenuItems
     */
    public List<JMenuItem> getMenuItems()
    {
        return menuItems;
    }
    
    /**
     * Getter for |menuActionItems|
     * @return A list of Actions to hand off to the Preferences menu
     */
    public List<AbstractAction> getMenuActionItems()
    {
        return menuActionItems;
    }
    
    /**
     * Developer is to supply AbstractAction objects to be passed into 
     * the Preferences menu items that will be executed when the menu 
     * items are clicked.
     * 
     * The order in which the Actions are returned is very important, the first
     * Action will be assigned to the first menuitem provided in the 
     * preferences.ini file, the second action to the second menuitem, and
     * so on in that order.
     * 
     * All actions will automatically force the game to restart.
     * 
     * @param model Reference to the underlying data structure for the 
     * preference actions to manipulate.
     * @param label Reference to the Status Label if needed.
     * @return A list of AbstractAction objects to be added to the
     * Preference menu items.
     */
    public abstract AbstractAction[] getPreferencesActions(
        GameBoardTableModel model, GameBoardStatusLabel label);    
}