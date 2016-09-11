package pawns;

import framework.GameBoardRenderer;
import framework.Renderable;
import javax.swing.ImageIcon;

/**
 * Represents how Pawns objects get displayed in the gui.
 * A PawnsRenderer can give you an image, given a Pawn-enum
 * A PawnsRenderer contains the background image
 * A PawnsRenderer can change the theme of the gui
 * 
 * @author Eric LaBouve
 */
public class PawnsRenderer extends GameBoardRenderer
{
    /**
     * Constructor for PawnsRenderer
     */
    public PawnsRenderer()
    {
        super();
        setImages("Pawns", "Default"); 
    }

    /**
     * Determines how a renderable object is to be rendered in the gui
     * 
     * @param value Passed in value from the DefaultTableCellRenderer
     */
    @Override
    public void setValue(Object value) 
    {
        Renderable piece = (Pawn) value;
        String name = piece.getText();
        setIcon(null);
        setText(null);
        
        // Assign an image if the spot is not empty
        if (!name.equals("Empty"))
        {
            setIcon(images.get(piece));
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
        ImageIcon white = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhitePawn.png");
        ImageIcon black = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackPawn.png");
       
        backgroundIcon = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "Background" + sep + "bkgd.jpg");
        
        images.clear();
        images.put(Pawn.WHITE, white);
        images.put(Pawn.BLACK, black);
    }
}
