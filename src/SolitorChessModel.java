import java.io.*;
import java.util.*;

/**
 * Created by Egor Kozitski on 12/10/2015.
 */
public class SolitorChessModel extends Observable implements Puzzle < HashMap < Coordinates, Piece >> {
    public HashMap < Coordinates, Piece > board;

    public HashMap < Character, Piece > piece;
    public Solver < HashMap < Coordinates, Piece >> solver = new Solver < HashMap < Coordinates, Piece >> ();
    public HashMap < Character, Piece > piecesMap;
    public File file;
    public int x;
    public int y;
    public int counter;
    public ArrayList < Coordinates > list;
    SolitorChessModel(File file) {
        board = new HashMap < > ();
        piecesMap = new HashMap < > ();
        this.file = file;
        list = new ArrayList < > ();
        this.reset();
    }@Override
    public boolean getGoal(HashMap < Coordinates, Piece > config) {
        return config.size() == 1 ? true : false;
    }
    public boolean addPieces(Coordinates cord) {
        boolean flag = false;
        if (this.board.containsKey(cord)) {
            flag = true;
            list.add(cord);
            if (list.size() == 2) {
                Coordinates first = list.get(0);
                Coordinates second = list.get(1);
                if (board.get(first).allMoves(first).contains(second)) {
                    board.remove(second);
                    board.put(second, board.get(first));
                    board.remove(first);
                    counter++;
                    list.clear();
                    flag = false;
                } else list.remove(0);
            }
        }
        setChanged();
        notifyObservers(board);
        return flag;
    }

    public HashMap < Coordinates, Piece > getBoard() {
        return board;
    }

    public void setBoard(HashMap < Coordinates, Piece > board) {
        this.board = board;
    }

    public HashMap < Character, Piece > getPiece() {
        return piece;
    }

    public void setPiece(HashMap < Character, Piece > piece) {
        this.piece = piece;
    }

    public Solver < HashMap < Coordinates, Piece >> getSolver() {
        return solver;
    }

    public void setSolver(Solver < HashMap < Coordinates, Piece >> solver) {
        this.solver = solver;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ArrayList < Coordinates > getList() {
        return list;
    }

    public void setList(ArrayList < Coordinates > list) {
        this.list = list;
    }
    public int resetSize() {
        return list.size();
    }
    public boolean nextBestMove(SolitorChessModel chessModel) {
        try {
            this.board = this.solver.solver(chessModel).get(1);
            this.counter++;
            this.setChanged();
            this.notifyObservers(board);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }@Override
    public ArrayList < HashMap < Coordinates, Piece >> getNeighbors(HashMap < Coordinates, Piece > config) {
        ArrayList < HashMap < Coordinates, Piece >> neighbors = new ArrayList < HashMap < Coordinates, Piece >> ();
        // check the first piece
        for (Coordinates cord: config.keySet()) {
            Set < Coordinates > moves = new HashSet < > ();
            for (Coordinates c: config.get(cord).allMoves(cord)) {
                moves.add(c);
            }
            // check for possible captures if so
            for (Coordinates c: config.keySet()) {
                if (moves.contains(c)) {
                    //check if captured
                    HashMap < Coordinates, Piece > neighbor = new HashMap < > ();
                    // make neighbor copy of chessConfig
                    neighbor.putAll(config);
                    // remove captured piece
                    neighbor.remove(c);
                    // move first to the location of second
                    neighbor.put(c, config.get(cord));
                    // remove first piece
                    neighbor.remove(cord);

                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    @Override
    public HashMap < Coordinates, Piece > getStart() {
        return this.board;
    }
    private void readInputFile(File inputFile) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
            String[] arr = buffer.readLine().split(" ");
            char current;
            setY(Integer.parseInt(arr[0]));
            setX(Integer.parseInt(arr[1]));
            this.piece = this.fillinNames();

            for (int i = 0; i < this.y; i++) {

                arr = buffer.readLine().split(" ");
                for (int j = 0; j < this.x; j++) {
                    current = arr[j].charAt(0);
                    if (this.piece.containsKey(current)) {
                        this.board.put(new Coordinates(j, i), piece.get(current));
                    }
                }
            }
            buffer.close();

        } catch (FileNotFoundException fl) {
            fl.printStackTrace();
            return;
        } catch (IOException ie) {
            ie.printStackTrace();
            return;
        }
    }




    public HashMap < Character, Piece > fillinNames() {
        HashMap < Character, Piece > piese = new HashMap < > ();
        piese.put(new Character('K'), new King("King", getX(), getY()));
        piese.put(new Character('Q'), new Queen("Queen", getX(), getY()));
        piese.put(new Character('R'), new Rock("Rook", getX(), getY()));
        piese.put(new Character('B'), new Bishop("Bishop", getX(), getY()));
        piese.put(new Character('N'), new Knight("Knight", getX(), getY()));
        piese.put(new Character('P'), new Pawn("Pawn", getX(), getY()));
        return piese;
    }
    public void reset() {
        counter = 0;
        board.clear();;
        readInputFile(file);
        list.clear();;
        setChanged();
        notifyObservers(board);
    }

}