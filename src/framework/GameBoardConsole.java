package framework;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


/**
 * Represents the console view for the Board Game
 * Represents the controller class that coordinates JMenuItem Actions
 * for the console view.
 * 
 * @author Eric LaBouve
 */
public class GameBoardConsole implements ActionListener
{
    /** reference to the board game model */
    private GameBoardTableModel model;
    /** reference to the JFrame */
    private GameBoardTableFrame frame;
    /** reference to the status label*/
    private GameBoardStatusLabel statusLabel;
    /** ASCII value of 'A' */
    private static final int kAlpha = 65;
    /** Console for reading input */
    private Scanner reader;
    /** Writer for writing to files */
    private Writer writer;
    /** true if the console is still running */
    private boolean running = true;    

    /**
     * Constructor for the GameBoardConsole.
     * 
     * @param model reference to the board game model 
     * @param frame reference to the JFrame 
     * @param statusLabel reference to the status label
     */
    public GameBoardConsole(GameBoardTableModel model, 
        GameBoardTableFrame frame, GameBoardStatusLabel statusLabel)
    {
        this.model = model;
        this.frame = frame;
        this.statusLabel = statusLabel;
        Writer wtr = new PrintWriter(System.out);
        setWriter(wtr);
        reader = new Scanner(System.in);        
    }
    
    /**
     * Sets the reader.
     * 
     * @param newReader The reader to pass into the CADConsoleViewer
     */
    public void setReader(Scanner newReader)
    {
        reader = newReader;
    }
    
    /**
     * Sets the writer.
     * 
     * @param out The writer to pass into the CADConsoleViewer
     */
    public void setWriter(Writer out)
    {
        writer = new PrintWriter(out, true);
    }
    
    /**
     * Reads in input from the user, executes user input
     * and prints to the console.
     */
    public void setVisible()
    {
        try
        {
            printBoard();
            // Run this thread FOR-EV-EERRRRRRR, or until we end the game.
            while (running)
            {
                String line = reader.nextLine();
                actionPerformed(new ActionEvent(0, 0, line));
                printBoard();
            }
        }
        catch(IOException exc)
        {
            System.err.println("IOException: printBoard()");
        }
    }

    /**
     * Execute the command given by the user at the command line.
     * @param event To get the key input
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String userInput = event.getActionCommand();
        // User has selected a Menu Item
        if (userInput.matches("[0-9]+"))
        {
            int location = Integer.parseInt(userInput);
            // Get the corresponding AbstractAction at the passed in location
            // and execute the action
            frame.getMenuActionItems().get(location).actionPerformed(null);
            // Special case for the Quit command
            if (frame.getMenuItems().get(location).getText().equals("Quit"))
            {
                running = false;
            }
        }
        // User has given coordinates to a location on the board
        else
        {
            try
            {
                // Get row location
                int row = (int) userInput.substring(
                    0, 1).toUpperCase().charAt(0) - kAlpha;
                // Get column location
                int col = Integer.parseInt(userInput.substring(1, 2));
                String leftover = userInput.substring(2);
                boolean leftClick = false;
                boolean rightClick = false;
                // If there is another character (Left / Right Click)
                if (leftover.length() > 0)
                {
                    String keyLorR = leftover.substring(0, 1).toUpperCase();
                    // Left Click
                    if (keyLorR.equals("L"))
                    {
                        leftClick = true;
                    }
                    // Right Click
                    else if (keyLorR.equals("R"))
                    {
                        rightClick = true;
                    }
                    // Error input
                    else
                    {
                        throw new IOException();
                    }
                }  
                // Save the current configuration in the cache for undo actions
                model.save();
                // Pushes the game forwoard: Make a move
                model.makeMove(row, col, leftClick, rightClick);
                // Check for winners
                model.gameState = model.updateGameState();
                // Update status panel
                model.fireTableDataChanged();
            }
            // Catch error input
            catch(IOException ext)
            {
                System.err.println("ERROR: Invalid Command Entered\n"
                    + "Usage: [Row Letter][Column Number][Optional: L or R]");
            }
        }
    }    
    
    /**
     * Prints the board
     */
    public void printBoard() throws IOException
    {
        // Print the status
        writer.write(statusLabel.getStatus().getText() + "\n");
        writer.write("    ");
        // Print column numbers        
        for (int col = 0; col < model.getColumnCount(); col++)
        {
            writer.write((col) + " ");
        }
        writer.write("\n");
        // Loop through all the rows
        for (int row = 0; row < model.getRowCount(); row++) 
        {
            // Print row letter
            writer.write((char) (kAlpha + row) + ":  ");
            // Loop through all the columns
            for (int col = 0; col < model.getColumnCount(); col++) 
            {
                String symbol = ((Renderable) model.getValueAt(
                        row, col)).getConsoleSymbol();
                writer.write(symbol + " ");
            }
            writer.write("\n");
        }
        writer.write("----");
        // Print a line to separate the board from the JMenuItems
        for (int num = 0; num < model.getRowCount(); num++)
        {
            writer.write("--");
        }
        writer.write("\n");
        // Print each JMenuItem
        for (int itemNum = 0; itemNum < frame.getMenuItems().size(); itemNum++)
        {
            writer.write(itemNum + ")" + 
                frame.getMenuItems().get(itemNum).getText() + " ");
        }
        writer.write("\n");
        writer.flush();
    }   
}