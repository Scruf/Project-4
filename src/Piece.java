/**
 * Created by Egor Kozitski on 12/10/2015.
 */
import java.util.*;
public abstract  class Piece {
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
    public abstract Set<Coordinates> allMoves(Coordinates cord);
    public Set<Coordinates> diagonalMoves(Coordinates cord){
        Set<Coordinates> moves = new HashSet<Coordinates>();
        int x = cord.getxCoord();
        int y = cord.getyCoord();
        //Get right corner
        while(x<xCoord && y<yCoord){
            moves.add(new Coordinates(x,y));
            x++;
            y++;
        }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get upper  left
        while(x>=0 && y>=0){
            moves.add(new Coordinates(x,y));
            x--;
            y--;
        }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get lower left
        while(x>=0 && getyCoord()<y){
            moves.add(new Coordinates(x,y));
            x--;
            y++;
        }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get lower right
        while(x<getxCoord() && y>=0){
            moves.add(new Coordinates(x,y));
            x++;
            y--;
        }
        moves.remove(cord);
        return moves;
    }
    public Set<Coordinates> verticalHorizontalMoves(Coordinates cord){
        Set<Coordinates> moves = new HashSet<Coordinates>();
            int x = cord.getxCoord();
            int y=cord.getyCoord();
            //get up
            while(y>=0){
                moves.add(new Coordinates(cord.getxCoord(),y));
                y--;
            }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get down
        while(y<getyCoord()){
            moves.add(new Coordinates(cord.getxCoord(),y));
            y++;
        }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get right
        while(x<getxCoord()){
            moves.add(new Coordinates(x,cord.getyCoord()));
            x++;
        }
        x=cord.getxCoord();
        y=cord.getyCoord();
        //get right
        while(x>=0){
            moves.add(new Coordinates(x,cord.getyCoord()));
            x--;
        }
        moves.remove(cord);
        return moves;

    }
    public Set<Coordinates>board(Set<Coordinates> movesSet){
        Set<Coordinates> moves = new HashSet<>();
        for(Coordinates c : movesSet){
            if(c.getxCoord()<0 || c.getxCoord()>=this.xCoord || c.getyCoord() <0 || c.getyCoord()>=this.yCoord)
                moves.add(c);
        }
        for(Coordinates c : moves)
            movesSet.remove(c);
        return movesSet;

    }
    public int hashCode(){
        int code = 0;
        for(char c : getName().toCharArray())
            code+=(int)c;
        return code * this.number;
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
