import java.util.*;

/*
 *
 * @author Egor Kozitski
 * */
public class Solver < E > {


    public ArrayList < E > solver(Puzzle < E > puzzle) {


        ArrayList < ArrayList < E >> queue = new ArrayList < ArrayList < E >> ();

        ArrayList < E > current = new ArrayList < E > ();

        HashSet < E > visited = new HashSet < E > ();
        ArrayList < E > solution = null;


        current.add(puzzle.getStart());
        queue.add(current);


        visited.add(puzzle.getStart());


        boolean found = false;
        if (puzzle.getGoal(puzzle.getStart())) {
            found = true;
        }

        while (!queue.isEmpty() && found != true) {


            solution = queue.remove(0);

            ArrayList < E > neighbors = puzzle.getNeighbors(solution.get(solution.size() - 1));
            for (E neighbor: neighbors) {


                if (!visited.contains(neighbors)) {
                    ArrayList < E > next = new ArrayList < E > ();
                    for (E i: solution) {
                        next.add(i);
                    }
                    next.add(neighbor);

                    if (puzzle.getGoal(next.get(next.size() - 1))) {
                        solution = next;
                        found = true;
                        break;
                    } else {

                        queue.add(next);
                    }

                    visited.add(neighbor);

                }
            }
        }
        if (found) {
            //this.solution = current;
            return solution;
        } else {
            //System.out.println("There is not solution");
            return null;
        }
    }
}