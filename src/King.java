import java.util.HashSet;
import java.util.Set;

/**
 * Created by ekozi on 12/10/2015.
 */
public class King extends  Piece {
    public King(String name, int x,int y){
        super(name,x,y);
    }
    @Override
    public Set<Coordinates> allMoves(Coordinates cord){
        Set<Coordinates> moves = new HashSet<Coordinates>();
        int x = cord.getxCoord();
        int y = cord.getyCoord();
        moves.add(new Coordinates(x-1,y-1));
        moves.add(new Coordinates(x+1,y+1));
        moves.add(new Coordinates(x+1,y-1));
        moves.add(new Coordinates(x+1,y));
        moves.add(new Coordinates(x-1,y+1));
        moves.add(new Coordinates(x-1,y));
        moves.add(new Coordinates(x,y-1));
        moves.add(new Coordinates(x,y+1));


        super.board(moves);
        return moves;
    }
}
