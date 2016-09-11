package chess;

import framework.GameBoardRenderer;
import framework.Renderable;
import javax.swing.ImageIcon;

/**
 * Represents how each Piece will be rendered in the board game.
 * 
 * @author Eric LaBouve
 */
public class ChessRenderer extends GameBoardRenderer
{

    @Override
    public void setValue(Object value) 
    {
        Renderable piece = (Piece) value;
        String name = piece.getText();
        setIcon(null);
        setText(null);
        
        // Assign an image if the spot is not empty
        if (!name.equals("Null"))
        {
            setIcon(images.get(piece));
        }        
    }

    @Override
    public void setImages(String plugginName, String skinName) 
    {
        plugginName = plugginName.toLowerCase();
        
        String sep = System.getProperty("file.separator");
        
        ImageIcon whitePawn = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhitePawn.png");
        ImageIcon blackPawn = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackPawn.png");
        
        ImageIcon whiteKnight = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhiteKnight.png");
        ImageIcon blackKnight = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackKnight.png");
        
        ImageIcon whiteBishop = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhiteBishop.png");
        ImageIcon blackBishop = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackBishop.png");
        
        ImageIcon whiteRook = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhiteRook.png");
        ImageIcon blackRook = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackRook.png");
        
        ImageIcon whiteQueen = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhiteQueen.png");
        ImageIcon blackQueen = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackQueen.png");
        
        ImageIcon whiteKing = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "WhiteKing.png");
        ImageIcon blackKing = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "BlackKing.png");
        
        ImageIcon highlight = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "Highlight.png");
       
        backgroundIcon = new ImageIcon("src" + sep + plugginName + sep + 
            "Images" + sep + skinName + sep + "Background" + sep + "bkgd.png");
        
        images.clear();
        
        images.put(Piece.PAWN_W, whitePawn);
        images.put(Piece.PAWN_B, blackPawn);
        
        images.put(Piece.KNIGHT_W, whiteKnight);
        images.put(Piece.KNIGHT_B, blackKnight);
        
        images.put(Piece.BISHOP_W, whiteBishop);
        images.put(Piece.BISHOP_B, blackBishop);
        
        images.put(Piece.ROOK_W, whiteRook);
        images.put(Piece.ROOK_B, blackRook);
        
        images.put(Piece.QUEEN_W, whiteQueen);
        images.put(Piece.QUEEN_B, blackQueen);
        
        images.put(Piece.KING_W, whiteKing);
        images.put(Piece.KING_B, blackKing);
        
        images.put(Piece.HIGHLIGHT, highlight);
        
    }
    
}
