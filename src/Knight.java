import java.util.HashSet;
import java.util.Set;

/**
 * Created by Egor Kozitski on 12/10/2015.
 */
public class Knight extends Piece {
    public Knight(String name, int x, int y) {
        super(name, x, y);
    }@Override
    public Set < Coordinates > allMoves(Coordinates cord) {
        Set < Coordinates > moves = new HashSet < > ();
        int x = cord.getxCoord();
        int y = cord.getyCoord();
        moves.add(new Coordinates(x + 1, y - 2));
        moves.add(new Coordinates(x - 1, y - 2));
        moves.add(new Coordinates(x + 1, y + 2));
        moves.add((new Coordinates(x - 1, y + 2)));
        moves.add((new Coordinates(x + 2, y - 1)));
        moves.add(new Coordinates(x + 1, y + 1));
        moves.add(new Coordinates(x - 2, y - 1));
        moves.add(new Coordinates(x - 2, y + 1));
        super.board(moves);
        return moves;
    }
}