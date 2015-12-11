import javax.swing.*;

/**
 * Created by Egor Koziski on 12/10/2015.
 */

public class ButtonPiece extends JButton {
    Coordinates cord;
    ButtonPiece(int x, int y) {
        this.cord = new Coordinates(x, y);
    }
    public Coordinates getCord() {
        return this.cord;
    }
}