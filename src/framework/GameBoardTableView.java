package framework;


import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Represents the JTable view for the game board framework.
 * 
 * Contains the class GameBoardMouseAdapter
 * The GameBoardMouseAdapter tracks mouse clicks and makes changes to the 
 * model depending on developer implemented methods.
 * 
 * @author Eric LaBouve
 */
public class GameBoardTableView extends JTable
{
    /** Window width. */
    public final static int kImageWidth = 71;
    /** Window Height. */
    public final static int kImageHeight = 63;     
    /** JMenuBar + Status Panel Height. */
    public final static int kTopHeight = 80;    
    /**Model for the view*/
    private GameBoardTableModel model;    
    /** Reference to the renderer (to get the background) */
    private GameBoardRenderer renderer;
    
    /**
     * Constructor for HurkleTableView
     * 
     * @param renderer Reference to the Renderer
     * @param modelp Reference to the underlying table model
     */
    public GameBoardTableView(GameBoardRenderer renderer, 
        GameBoardTableModel modelp)
    {
        super(modelp);

        model = modelp;
        setName("table");
        this.renderer = renderer;
        
        setDefaultRenderer(Renderable.class, renderer);                
        setDefaultEditor(Object.class, null); 
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        setCellSelectionEnabled(false);        
        setOpaque(false);
        setShowGrid(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setRowHeight(kImageHeight);
        // Set the dimensions for each column in the board to match the image 
        adjustSize();
        // Add a custom mouse listener that will handle player's clicks.
        addMouseListener(new GameBoardMouseAdapter(model, this));
    }
    
    /**
     * Set the dimensions for each column in the board to match the image 
     */
    public void adjustSize()
    {
        // Set the dimensions for each column in the board to match the image 
        for (int col = 0; col < model.getColumnCount(); col++)
        {
            TableColumn column = getColumnModel().getColumn(col);
            column.setMaxWidth(kImageWidth);
            column.setMinWidth(kImageWidth);
        }  
    }
    
    /**
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     * 
     * @param columnIndex The index of the column
     * @return The class of the column
     */
    public Class getColumnClass(int columnIndex) 
    {
        return Renderable.class;
    }
    
    /**
     * Render components to be transparent so the background image is visible.
     * 
     * @param rend A reference to the renderer
     * @param row The row location
     * @param column The column location
     * @return A Component object
     */
    @Override
    public Component prepareRenderer(TableCellRenderer rend, int row, int column)
    {
        Component component = super.prepareRenderer(rend, row, column);
        // Renderer component to be transparent so background image is visible
        if (component instanceof JComponent)
        {
            ((JComponent) component).setOpaque(false);
        }
        return component;
    }

    /** 
     * Override paint so as to show the table background.
     * @param gfx is a graphics object to set the background
     */
    @Override
    public void paint(Graphics gfx)
    {
        // paint an image in the table background
        if (renderer.getBackgroundIcon() != null)
        {
            gfx.drawImage(renderer.getBackgroundIcon().getImage(),
                0, 0, null, null);
        }
        // Now let the paint do its usual work
        super.paint(gfx);
    }       
}