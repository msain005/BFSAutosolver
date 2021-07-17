/* Misbah Hussain
// mhussa43
// 15 puzzle solver
// This program's goal is to solve any 16 input puzzle
// The way it solves the program is through breadth first search
*/
import java.util.*;
import java.util.Scanner;

public class Puzzle
{
    public static void main(String[] args)
    {
        int[] inputArray = new int[16];

        System.out.println("Input puzzle configuration: ");
        Scanner input = new Scanner(System.in);

        // input into array
        int k = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                k++;
                inputArray[k-1] = input.nextInt();
            }
        }

//-------------------------------------------------------------------------------------------------
        long startTime = System.currentTimeMillis(); // start timer

        Node root = new Node(inputArray);
        BreadthFirstSearch BFS = new BreadthFirstSearch();

        List<Node> autoSolve = BFS.bfs(root);
        int counter = 1;
        if(autoSolve.size() == 0)                            // To check to see if auto solve has a solution path
        {                                                   // if no path then solution cannot be found
            System.out.println("Solution cannot be found");
            System.exit(0);
        }
        else {                                                 // Print the moves to reach the solution
            System.out.println("Moves to reach solution: ");
            for(int i = autoSolve.size() - 1; i >= 0; i--)     // prints Backtrack in reverse so it solves in order
            {
                System.out.println("-----------------------");
                System.out.print("Phase: " + counter);
                System.out.println();
                autoSolve.get(i).PrintPuzzle();
                counter++;
            }
        }

        long stopTime = System.currentTimeMillis(); // stop timer
        long elapsedTime = stopTime - startTime; // elapsed time

        System.out.println();
        // Move-----------------------------------------------------------------------------
        String moveString = "";               // create a string of directions
        StringBuilder rmoveString = new StringBuilder(); // for reversing the string

        for(char d : BreadthFirstSearch.dirVector)            // create a string from each element
        {                                                     // in the character vector
            moveString += d;
        }

        rmoveString.append(moveString);
        System.out.println("Moves: " + rmoveString.reverse());
        // nodes expanded----------------------------------------------------------------------------------------
        System.out.println("Number of Nodes expanded: " + BreadthFirstSearch.expandedNodes.size());
        // time taken---------------------------------------------------------------------------------------------
        System.out.println("Time Taken: " + elapsedTime + "ms");
        // Memory Usage--------------------------------------------------------------------------------------------

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory Used: " + memory/1000 + "kb");  // memory/1000 for kb

        System.out.println();
    }

}
