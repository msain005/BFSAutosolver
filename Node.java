/*
This class is responsible for working in the node level.
This class accounts for the swaps and records them with
a direction character, then is used BFS class to be stored
in a vector. Which is then converted into a string in main.
*/
import java.util.*;

public class Node
{
    public int[] inputArray = new int[16];
    public List<Node> childList = new ArrayList<Node>();
    public Node parentNode;                                  // used for backtracking
    public char direction;   // keep track of the moves

    public Node(int p[])
    {
        for(int i = 0; i < inputArray.length; i++)
        {
            this.inputArray[i] = p[i];
        }
    }

    public void ExpandNode()
    {
        int x = 0;
        for(int i = 0; i < inputArray.length; i++)
        {
            if(inputArray[i] == 0)
            {
                x = i;

                if(x % 4 < 4 - 1)
                {
                    SwapRight(inputArray, x);
                }
                if(x % 4 > 0)
                {
                    SwapLeft(inputArray, x);
                }
                if(x - 4 >= 0)
                {
                    SwapUp(inputArray, x);
                }
                if(i + 4 < inputArray.length)
                {
                    SwapDown(inputArray, x);
                }
            }
        }
    }


    public boolean CheckPuzzle(int[] p) // Check to see if same puzzle if it is, then don't store
    {
        boolean samePuzzle = false;
        if(inputArray[0] == p[0] && inputArray [1] == p[1] && inputArray [2] == p[2]
                && inputArray [3] == p[3] && inputArray [4] == p[4] && inputArray [5] == p[5]
                && inputArray [6] == p[6] && inputArray [7] == p[7] && inputArray [8] == p[8]
                && inputArray [9] == p[9] && inputArray [10] == p[10] && inputArray [11] == p[11]
                && inputArray [12] == p[12] && inputArray [13] == p[13] && inputArray [14] == p[14]
                && inputArray [15] == p[15])
        {
            samePuzzle = true;
        }
        return samePuzzle;
    }


    public boolean GoalArray()  // detects if in Goal State
    {
        boolean isGoal = false;

        if(inputArray[0] == 1 && inputArray[1] == 2 && inputArray[2] == 3 && inputArray[3] == 4
                && inputArray[4] == 5 && inputArray[5] == 6 && inputArray[6] == 7 && inputArray[7] == 8
                && inputArray[8] == 9 && inputArray[9] == 10 && inputArray[10] == 11 && inputArray[11] == 12
                && inputArray[12] == 13 && inputArray[13] == 14 && inputArray[14] == 15 && inputArray[15] == 0) // is position 0 > 1? is position 1 > 2 ?
        {
            isGoal = true;
        }
        return isGoal;
    }

    public void SwapRight(int[] p, int i)
    {
        int[] tempPuzzle = new int[16];

        for(int j = 0; j < p.length; j++)
        {
            tempPuzzle[j] = p[j];
        }

        int temp = tempPuzzle[i + 1]; // temp is gonna store right integer
        tempPuzzle[i + 1] = tempPuzzle[i];
        tempPuzzle[i] = temp; // put 5 where 0 used to be

        Node child = new Node(tempPuzzle); // pass in possible move
        child.direction = 'R';
        childList.add(child);
        child.parentNode = this;

    }

    public void SwapLeft(int[] p, int i)
    {

        int[] tempPuzzle = new int[16];

        for(int j = 0; j < p.length; j++)
        {
            tempPuzzle[j] = p[j];
        }

        int temp = tempPuzzle[i - 1];
        tempPuzzle[i - 1] = tempPuzzle[i];
        tempPuzzle[i] = temp;

        Node child = new Node(tempPuzzle);
        child.direction = 'L';
        childList.add(child);
        child.parentNode = this;

    }

    public void SwapUp(int[] p, int i)
    {

        int[] tempPuzzle = new int[16];

        for(int j = 0; j < p.length; j++)
        {
            tempPuzzle[j] = p[j];
        }

        int temp = tempPuzzle[i - 4];
        tempPuzzle[i - 4] = tempPuzzle[i];
        tempPuzzle[i] = temp;

        Node child = new Node(tempPuzzle);
        child.direction = 'U';
        childList.add(child);
        child.parentNode = this;

    }

    public void SwapDown(int[] p, int i)
    {

        int[] tempPuzzle = new int[16];

        for(int j = 0; j < p.length; j++)
        {
            tempPuzzle[j] = p[j];
        }

        int temp = tempPuzzle[i + 4];
        tempPuzzle[i + 4] = tempPuzzle[i]; // tempPuzzle[i] = tempPuzzle[i+3] ??
        tempPuzzle[i] = temp;

        Node child = new Node(tempPuzzle);
        child.direction = 'D';
        childList.add(child);
        child.parentNode = this;

    }

    public void PrintPuzzle()  // same as input array in main
    {
        System.out.println();
        int k = 0;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                k++;
                System.out.print(inputArray[k-1] + " ");
            }
            System.out.println();
        }
    }
}
