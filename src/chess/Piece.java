package chess;

import framework.Renderable;

/**
 * Represents a single piece on the game board.
 * 
 * @author Eric LaBouve
 */
public enum Piece implements Renderable
{
    /** Pawn - Move forward*/
    PAWN_W ("White Pawn", "p"),
    PAWN_B ("Black Pawn", "P"),
    /** Knight - Move in L shapes*/
    KNIGHT_W ("White Knight", "k"),
    KNIGHT_B ("Black Knight", "K"),
    /** Bishop - Move diagonally*/
    BISHOP_W ("White Bishop", "b"),
    BISHOP_B ("Black Bishop", "B"),
    /** Rook - Move horizontally and vertically*/
    ROOK_W ("White Rook", "r"),
    ROOK_B ("Black Rook", "R"),
    /** Queen - Move horizontally, vertically, and diagonally*/
    QUEEN_W ("White Queen", "q"),
    QUEEN_B ("Black Queen", "Q"),
    /** King - Moves 1 space horizontally, vertically, and diagonally*/
    KING_W ("White King", "k"),
    KING_B ("Black King", "K"),
    /** Null - Represents and empty space on the board */
    NULL ("Null", "+"),
    /** Highlight - Represents a yellow tinted space on the board */
    HIGHLIGHT("Highlight", "*");
    
    private String text;
    private String consoleText;
    
    Piece(String textp, String consoleTextp)
    {
        text = textp;
        consoleText = consoleTextp;
    }

    @Override
    public String getText() 
    {
        return text;
    }

    @Override
    public String getConsoleSymbol() 
    {
        return consoleText;
    }
}
