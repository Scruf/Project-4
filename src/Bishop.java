import java.util.Set;

/**
 * Created by ekozi on 12/10/2015.
 */
public class Bishop extends Piece {
    Bishop(String name, int x, int y) {
        super(name, x, y);
    }@Override
    public Set < Coordinates > allMoves(Coordinates cord) {
        return super.diagonalMoves(cord);
    }
}