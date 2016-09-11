package hurkle;

import framework.GameBoardRenderer;
import framework.Renderable;
import javax.swing.ImageIcon;

/**
 * Represents how Pawns objects get displayed in the gui.
 * A PawnsRenderer can give you an image, given a Hurkle-enum
 * A PawnsRenderer contains the background image
 * A PawnsRenderer can change the theme of the gui
 * 
 * @author Eric LaBouve
 */
public class HurkleRenderer extends GameBoardRenderer
{
    /**
     * Constructor for PawnsRenderer
     * Determines the default theme
     */
    public HurkleRenderer()
    {
        super();
        setImages("Hurkle", "Default"); 
    }
    
    /**
     * Determines how a renderable object is to be rendered in the gui.
     * 
     * @param value Passed in value from the DefaultTableCellRenderer.
     */    
    @Override
    public void setValue(Object value) 
    {
        // Protect against a null pointer exception
        if (value != null)
        {
            Renderable piece = (Hurkle) value;
            String name = piece.getText();
            setIcon(null);
            setText(null);

            // Assign an image if the spot is not empty
            if (!name.equals("Empty Clear"))
            {
                setIcon(images.get(piece));
            }
        }
    }

    /**
     * Loads the Renderable --> ImageIcon map according to the passed in path.
     * Load the background ImageIcon.
     * 
     * @param plugginName name of the plugin.
     * @param skinName name of the skin folder.
     */    
    @Override
    public void setImages(String plugginName, String skinName) 
    {
        plugginName = plugginName.toLowerCase();
        
        String sep = System.getProperty("file.separator");
        
        ImageIcon hurkle = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "hurkle.jpg");
        ImageIcon blank = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "emptySolid.png");
       
        backgroundIcon = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "Background" + sep + "bkgd.png");
        
        images.clear();
        images.put(Hurkle.HURKLE, hurkle);
        images.put(Hurkle.EMPTY_SOLID, blank);        
    }
    
}
