/**
 * Created by ekozi on 12/10/2015.
 */
import java.util.*;
public class Piece {
    private String name;
    private int xCoord;
    private int yCoord;
    private int number;
    private int counter = 0;
        public Piece(String name,int x,int y){
            this.name=name;
            this.xCoord=x;
            this.yCoord=y;
            counter++;
        }
    public HashSet<Coordinates> diagonalMoves(Coordinates cord){
        HashSet<Coordinates> moves = new HashSet<Coordinates>();
        return moves;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }


}
