package graph;

import java.util.*;

public class SwimInRisingWater {
    //Uses Dijkstra's algorithm. Slightly better solution below.
    int n;
    public int swimInWater(int[][] grid) {
        n = grid.length-1;
        int currTime = grid[0][0];
        /*
        Time elapsed. We do this as we can't move from grid[0][0] till that time is reached.
        So we make it so that we are already at that least time before we start moving.
        We can make currTime = 0 but then we'd be doing a few extra loops doing the if check.
        */

        //Store graph in ascending order of time.
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a,b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        Set<Integer> visitedTime = new HashSet<Integer>();

        minHeap.offer(new int[]{0,0});
        visitedTime.add(grid[0][0]);

        int[][] movement = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}}; //Four directions.

        while(!minHeap.isEmpty()) {
            if(grid[minHeap.peek()[0]][minHeap.peek()[1]] > currTime) {
                currTime++;
                continue;
            } //If time elapsed is less than peek of heap, then we can't reach this position yet.
            /*
            Why do we increment currTime if its less than least time?
            Because once least time is reached, we can cross infinite grids till the next least grid or the end is reached.
            */
            int[] curr = minHeap.poll();

            if(curr[0] == n && curr[1] == n) { //Reached final cell.
                return currTime;
            }

            for(int[] move : movement) { //Check all 4 directions and add to heap.
                addToHeap(grid, curr[0]+move[0], curr[1]+move[1], minHeap, visitedTime);
            }
        }

        return currTime;
    }

    public void addToHeap(int[][] grid, int row, int col, PriorityQueue<int[]> minHeap, Set<Integer> visitedTime) {
        if(row < 0 || col < 0) {
            return;
        }
        if(row > n || col > n) {
            return;
        }

        int cellTime = grid[row][col];
        if(visitedTime.contains(cellTime)) { //Already checked current cell.
            return;
        }
        visitedTime.add(cellTime);
        minHeap.offer(new int[]{row, col});
    }

    /*
    NOTE: If we can only move 1 grid(instead of infinite) once the least is reached, then we can calculate totalTime as below.
    This would give us wait time on each grid + time spent moving from grid to grid.

    totalTime++;
    if(grid[minHeap.peek()[0]][minHeap.peek()[1]] > totalTime) {
        continue;
    }
    */

    /*
    Building on previous solution, we don't have to check if least time is reached, if not doing currTime++.
    We know we will reach currTime = leastTime eventually. So we can directly assign it.
    currTime = Math.max(currTime, grid[curr[0]][curr[1]]);
    */
    public int swimInWaterTweaked(int[][] grid) {
        n = grid.length-1;
        int currTime = grid[0][0];
        /*
        Time elapsed. We do this as we can't move from grid[0][0] till that time is reached.
        So we make it so that we are already at that least time before we start moving.
        We can also make currTime = 0.
        */

        //Store graph in ascending order of time.
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a,b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        Set<Integer> visitedTime = new HashSet<Integer>(); //Can be replaced with boolean[][] as constraint is small.

        minHeap.offer(new int[]{0,0});
        visitedTime.add(grid[0][0]);

        int[][] movement = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}}; //Four directions.

        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();

            currTime = Math.max(currTime, grid[curr[0]][curr[1]]);

            if(curr[0] == n && curr[1] == n) { //Reached final cell.
                return currTime;
            }

            for(int[] move : movement) { //Check all 4 directions and add to heap.
                addToHeap(grid, curr[0]+move[0], curr[1]+move[1], minHeap, visitedTime);
            }
        }

        return currTime;
    }

}
