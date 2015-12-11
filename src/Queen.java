import java.util.Set;
import java.util.HashSet;
/**
 * Created by Egor Kozitski on 12/10/2015.
 */
public class Queen extends Piece {
    Queen(String name, int x, int y) {
        super(name, x, y);
    }@Override
    public Set < Coordinates > allMoves(Coordinates cord) {
        Set < Coordinates > moves = new HashSet < Coordinates > ();
        for (Coordinates c: super.diagonalMoves(cord))
            moves.add(c);
        for (Coordinates c: super.verticalHorizontalMoves(cord))
            moves.add(c);

        return moves;
    }
}