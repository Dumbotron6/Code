package graph;

import java.util.LinkedList;

public class ShortestPathInBinaryMatrix {
    /*
    https://leetcode.com/problems/shortest-path-in-binary-matrix/
     */
    
    /*
    This takes a BFS approach.
    At any point, we check all directions we can move and add them to queue. So from length 1, we add all length 2.
    From length 2, we add all length 3. That is the purpose of while(s < qSize).
    We mark all places visited. So if we visit a place first and mark it, another path arrives to that point later,
        we can discard that path, because a previous iteration already took this path, covering it in less length.
    This way, whichever path arrives at the end first will always have the shortest length.
    */
    int maxRows, maxCols;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) { //Can't traverse at all if beginning is 1.
            return -1;
        }
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;

        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0,0});
        grid[0][0] = 1; //Mark as visited.

        int length = 1;

        /*
        traversalPoints gives all 8 directions that we can move. left-top, top, right-bottom etc.
        We add these to the current coordinates we are checking to check all 8 directions.
        */
        int[][] traversalPoints = new int[][]{{-1,-1},{0,-1},{-1,0},{0,1},{1,0},{1,1},{-1,1},{1,-1}};
        while(!queue.isEmpty()) { //Meaning, traverse as long as there is a valid path to move.
            int qSize = queue.size();
            int s = 0;
            while(s < qSize){ //Checking all points of a particular length, for example, 2.
                int[] current = queue.pop();
                if(current[0] == maxRows && current[1] == maxCols) { //When we reach the end, return immediately.
                    return length;
                }
                for(int[] trav : traversalPoints) { //Check all 8 directions.
                    int checkRow = current[0]+trav[0];
                    int checkCol = current[1]+trav[1];
                    if(canTraverse(grid, checkRow, checkCol)) { //Add if we can move in that direction.
                        queue.add(new int[]{checkRow, checkCol});
                    }
                }
                s++;
            }
            length++;
        }

        return -1;
    }

    public boolean canTraverse(int[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row > maxRows || col > maxCols) { //Exceeds bounds.
            return false;
        }
        if(grid[row][col] == 1) { //Already 1 or already visited.
            return false;
        }
        grid[row][col] = 1; //Change visited to 1.
        return true;
    }
}
