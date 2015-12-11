import java.util.HashSet;
import java.util.Set;

/**
 * Created by ekozi on 12/10/2015.
 */
public class Rock extends Piece {
    Rock(String name,int x,int y){
        super(name,x,y);
    }
    @Override
    public Set<Coordinates> allMoves(Coordinates cord){
        Set<Coordinates> moves = new HashSet<>();
        for(Coordinates c : super.verticalHorizontalMoves(cord))
            moves.add(c);
        return moves;
    }
}
