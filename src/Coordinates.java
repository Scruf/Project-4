/**
 * Created by ekozi on 12/10/2015.
 */
public class Coordinates {
    private int xCoord;
    private int yCoord;
        Coordinates(int x,int y){
            this.xCoord=x;
            this.yCoord=y;
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
    @Override
    public boolean equals(Object o){
        Coordinates cord = (Coordinates)(o);
        return cord.getxCoord()==this.xCoord &&
                cord.getyCoord()==this.yCoord;
    }
    @Override
    public int hashCode(){
        if(this.xCoord < 0 || this.yCoord <0){
            return -1;
        }
        return Integer.parseInt(String.valueOf(this.yCoord)+String.valueOf(this.xCoord));
    }
}
