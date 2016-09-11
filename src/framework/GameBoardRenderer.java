package framework;


import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * Represents how Renderable objects get displayed in the gui and console.
 * A GameBoardRenderer can give you an image, given a Renderable-enum
 * A GameBoardRenderer contains the background image
 * A GameBoardRenderer can change the theme of the gui
 * 
 * @author Eric LaBouve
 */
public abstract class GameBoardRenderer extends DefaultTableCellRenderer
{
    /** Map that relates the Renderable Object to its ImageIcon*/
    protected HashMap<Renderable, ImageIcon> images;
    
    /** Background for the game */
    protected ImageIcon backgroundIcon;
    
    /**
     * Constructor for PawnsRenderer
     * Determines the default theme
     */
    public GameBoardRenderer()
    {
        super();
        images = new HashMap<Renderable, ImageIcon>();  
    }
        
    /**
     * Getter for |backgroundIcon|
     * @return The background ImageIcon
     */
    public ImageIcon getBackgroundIcon()
    {
        return backgroundIcon;
    }   
    
    /**
     * Getter for |images|
     * @return |images|
     */
    public HashMap<Renderable, ImageIcon> getImageMap()
    {
        return images;
    }
    
    /**
     * Determines how a renderable object is to be rendered in the gui.
     * 
     * @param value Passed in value from the DefaultTableCellRenderer.
     */
    @Override
    public abstract void setValue(Object value);
    
    /**
     * Loads the Renderable --> ImageIcon map according to the passed in path.
     * Load the background ImageIcon.
     * 
     * @param plugginName name of the plugin.
     * @param skinName name of the skin folder.
     */
    public abstract void setImages(String plugginName, String skinName);
}
