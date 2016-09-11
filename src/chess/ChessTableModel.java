package chess;

import framework.GameBoardTableModel;

/**
 * Represents the underlying data table for the Chess Board Game.
 * @author Eric LaBouve
 */
public class ChessTableModel extends GameBoardTableModel
{
    public static final int kRowCount = 8;
    public static final int kColumnCount = 8;
    
    /**
     * Constructor for ChessTableModel
     * Uses an 8 x 8 board
     */
    public ChessTableModel()
    {
        super(kRowCount, kColumnCount);
    }

    @Override
    public void makeMove(int row, int col, boolean lClick, boolean rClick) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGameOver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDraw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newGameBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
