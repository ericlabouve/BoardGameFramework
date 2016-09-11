package framework;


/**
 * All images or text that appear in the game must be implement Renderable.
 * 
 * @author Eric LaBouve
 */
public interface Renderable 
{    
    /**
     * Used to identify the Renderable Object in the gui
     * @return String representation of the renderable object.
     */
    public String getText();
    
    /**
     * Used to identify the Renderable Object on the console.
     * Usage: Return a 1 character String for best results.
     * @return String display for the console.
     */
    public String getConsoleSymbol();
}
