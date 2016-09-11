package framework;


/**
 * Represents the state of the game.
 * Game Over = The game is over and there is a winner.
 * Game Not Over = The game is still in play and there is no winner, yet.
 * Game Draw = There game is over and there is no winner.
 * 
 * @author Eric LaBouve
 */
public enum GameState 
{
    /**Game is Over*/
    GAMEOVER, 
    /**Game is not Over*/
    GAMENOTOVER, 
    /** Game has ended in a Draw */
    GAMEDRAW;
}
