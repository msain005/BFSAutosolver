/*
This class is responsible for finding the correct path to the solution.
The path uses BFS and then records the solution backwards through the
parent node by the function BackTrack.
*/
import java.util.*;
import java.util.Vector;
public class BreadthFirstSearch
{
    public static Vector<Character> dirVector = new Vector<Character>();  // vector to store directions
    public static Vector<Integer> expandedNodes = new Vector<Integer>(); // vector to store number of expanded nodes

    public BreadthFirstSearch()
    {

    }

    public List<Node> bfs(Node root)  // this will return our path
    {
        List<Node> SolutionList = new ArrayList<Node>();  // will have the nodes that lead to solution
        List<Node> FrontierList = new ArrayList<Node>();    // keeps node you can expand
        List<Node> ExploredList = new ArrayList<Node>();    // store node we have expanded

        FrontierList.add(root); //a FIFO queue with node as the only element
        boolean goalFound = false;

        while(!goalFound)
        {
            if(FrontierList.size() == 0)
            {
                goalFound = false;
                break;
            }

            Node currentNode = FrontierList.get(0);

            FrontierList.remove(0);
            ExploredList.add(currentNode);


            currentNode.ExpandNode(); // find 0 in puzzle and apply moves

            for(Node action : currentNode.childList)
            {
                Node currentChild = action;
                expandedNodes.add(1);                                 // keeps track of expanded nodes

                if(!Contains(FrontierList, currentChild) && !Contains(ExploredList, currentChild)) // check if openlist contains currentChild? && ExploredList Contains currentChild? if not add it to open list
                {
                    if(currentChild.GoalArray())
                    {
                        goalFound = true;
                        // traces path to root node
                        BackTrack(SolutionList, currentChild);
                    }
                    FrontierList.add(currentChild);
                }
            }
        }
        return SolutionList;
    }

    public void BackTrack(List<Node> path, Node n)  // this
    {
        Node current = n;
        path.add(current);  // add current child

        while(current.parentNode != null) // while on expanded node
        {
            current = current.parentNode; // go one node up
            path.add(current);  // add that node to path
        }

        for(Node x: path)                // for each node in the path tack a direction with it
        {
            dirVector.add(x.direction);  // add direction to vector
        }
    }

    public static boolean Contains(List<Node> list, Node c)
    {
        boolean contains = false;

        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).CheckPuzzle(c.inputArray))
            {
                contains = true;
            }
        }
        return contains;
    }

}
