import java.io.*;
import java.util.*;

/**
 * Created by ekozi on 12/10/2015.
 */
public class SolitorChessModel extends Observable implements Puzzle<HashMap<Coordinates,Piece>> {
    public HashMap<Coordinates,Piece> board;

    public HashMap<Character,Piece> piece;
    public Solver<HashMap<Coordinates,Piece>> solver = new Solver<HashMap<Coordinates,Piece>>();
    public File file;
    public int x;
    public int y;
    public int counter;
    public ArrayList<Coordinates> list;
        SolitorChessModel(File file){
            board= new HashMap<>();
            this.file=file;
            list = new ArrayList<>();
            this.reset();
        }
    @Override
    public boolean getGoal(HashMap<Coordinates,Piece> config){return config.size()==1 ? true : false;}
    public boolean addPieces(Coordinates cord){
        boolean flag = false;
        if(this.board.containsKey(cord)){
            flag=true;
            list.add(cord);
            if(list.size()==2){
                Coordinates first = list.get(0);
                Coordinates second = list.get(1);
                if(board.get(first).allMoves(first).contains(second)){
                    board.remove(second);
                    board.put(second,board.get(first));
                    board.remove(first);
                    counter++;
                    list.clear();
                    flag=false;
                }
                else
                    list.remove(0);
            }
        }
        setChanged();
        notifyObservers(board);
        return flag;
    }

    public HashMap<Coordinates, Piece> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Coordinates, Piece> board) {
        this.board = board;
    }

    public HashMap<Character, Piece> getPiece() {
        return piece;
    }

    public void setPiece(HashMap<Character, Piece> piece) {
        this.piece = piece;
    }

    public Solver<HashMap<Coordinates, Piece>> getSolver() {
        return solver;
    }

    public void setSolver(Solver<HashMap<Coordinates, Piece>> solver) {
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

    public ArrayList<Coordinates> getList() {
        return list;
    }

    public void setList(ArrayList<Coordinates> list) {
        this.list = list;
    }
    public int resetSize(){
        return list.size();
    }
    public boolean nextBestMove(SolitorChessModel chessModel) {
           try{
               this.board = this.solver.solver(chessModel).get(1);
               this.counter++;
               this.setChanged();
               this.notifyObservers(board);
               return true;
           }catch(Exception ex){
               ex.printStackTrace();
               return false;
           }

    }
    @Override
    public ArrayList<HashMap<Coordinates, Piece>> getNeighbors(HashMap<Coordinates, Piece> chessConfig) {
        //this.debugger("Get the neighbors for " + chessConfig);
        ArrayList<HashMap<Coordinates, Piece>> neighbors = new ArrayList<HashMap<Coordinates, Piece>>();

        // check the first position
        for (Coordinates first : chessConfig.keySet()) {
            Set<Coordinates> possibleMoves = chessConfig.get(first).allMoves(first);
            //this.debugger("Move piece at " + first + " are " + possibleMoves);
            // check for captures
            for (Coordinates second : chessConfig.keySet()) {
                //this.debugger("Check for captures at " + second);
                if (possibleMoves.contains(second)) {
                    //this.debugger("Captured");
                    HashMap<Coordinates, Piece> neighbor = new HashMap<Coordinates, Piece>();
                    // make neighbor copy of chessConfig
                    neighbor.putAll(chessConfig);
                    // remove captured piece
                    neighbor.remove(second);
                    // move first to the location of second
                    neighbor.put(second, chessConfig.get(first));
                    // remove first piece
                    neighbor.remove(first);

                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    @Override
    public HashMap<Coordinates,Piece> getStart(){
        return this.board;
    }
    private void readInputFile(File startingFile) {
        try {
            FileReader readFile = new FileReader(startingFile);
            BufferedReader inputFile = new BufferedReader(readFile);
            String[] nextLine = inputFile.readLine().split(" ");
            char current;

            this.y = Integer.parseInt(nextLine[0]);
            this.x = Integer.parseInt(nextLine[1]);
            this.piece = this.names();

            // checking for the chess pieces
            for (int i = 0; i < this.y; i++) {
                //this.debugger("Parse line " + i);
                nextLine = inputFile.readLine().split(" ");
                for (int j = 0; j < this.x; j++) {
                    current = nextLine[j].charAt(0);
                    if (this.piece.containsKey(current)) {
                        //this.debugger("Found piece");
                        this.board.put(new Coordinates(j,i),piece.get(current));
                    }
                }
            }
            inputFile.close();

        } catch (FileNotFoundException e) {
            //System.err.println("FileNotFoundException: Input File not found in directory.");
            System.err.println(startingFile + " not found.i");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.exit(0);
        }
    }




    public HashMap<Character,Piece> names(){
        HashMap<Character, Piece> piese = new HashMap<>();
        piese.put(new Character('K'), new King("King", getX(), getY()));
        piese.put(new Character('Q'), new Queen("Queen", getX(), getY()));
        piese.put(new Character('R'), new Rock("Rook", getX(), getY()));
        piese.put(new Character('B'), new Bishop("Bishop", getX(), getY()));
        piese.put(new Character('N'), new Knight("Knight", getX(), getY()));
        piese.put(new Character('P'), new Pawn("Pawn", getX(), getY()));
        return piese;
    }
    public void reset(){
        counter=0;
        board.clear();;
        readInputFile(file);
        list.clear();;
        setChanged();
        notifyObservers(board);
    }

}
