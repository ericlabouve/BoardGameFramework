package chess.actions;

import chess.ChessTableModel;
import framework.GameBoardTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Represents how a pawn can move
 * @author Eric LaBouve
 */
public class PawnMoveAction extends AbstractAction
{
    private GameBoardTableModel model;
    
    public PawnMoveAction(GameBoardTableModel model)
    {
        this.model = (ChessTableModel) model;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        // Black pawns move down
        
        // White pawns move up
        
    }
    
}
