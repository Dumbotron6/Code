package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SingleThreadCPU {
    /*
    https://leetcode.com/problems/single-threaded-cpu/
     */

    /*
    We sort tasks by time, take all tasks available within a given time, add it to scheduler.
    Scheduler will sort the tasks with the least processingTime.
    Once we execute the top task, time would have incremented by it's respective processingTime.
    Now we add all tasks available within this new time to the scheduler.
    So at all times, the least processingTime task will be executed by the scheduler within the given time.
    */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] result = new int[n];

        Integer[] indices = new Integer[n];
        for(int i = 0; i < n; i++) {
            indices[i] = i;
        }

        //Sorts by startTime, then index.
        Arrays.sort(indices, (a, b) -> tasks[a][0]!=tasks[b][0] ? tasks[a][0]-tasks[b][0] : a-b);

        int i = 0;
        int currTime = tasks[indices[i]][0];
        int currIndex = 0;

        //Sorts by processingTime, then index, prioritizing lesser processing time.
        Queue<Integer> scheduler = new PriorityQueue<Integer>((a, b) -> tasks[a][1]!=tasks[b][1] ? tasks[a][1]-tasks[b][1] : a-b);

        while(i < n || !scheduler.isEmpty()) {
            //As long as there are tasks available that are less than current time, add it to scheduler.
            while(i < n && tasks[indices[i]][0] <= currTime) {
                scheduler.offer(indices[i]);
                i++;
            }

            //Run the least processingTime task that can be executed.
            if(!scheduler.isEmpty()) {
                result[currIndex] = scheduler.peek();
                currTime += tasks[scheduler.poll()][1];
                currIndex++;
            }else { //Increment time till minimum time is met.
                currTime = tasks[indices[i]][0];
            }
        }

        return result;
    }

    //Uses extra space but does the same thing as above.
    public int[] getOrderAlt(int[][] tasks) {

        // Store task enqueue time, processing time, index.
        int sortedTasks[][] = new int[tasks.length][3];
        for (int i = 0; i < tasks.length; ++i) {
            sortedTasks[i][0] = tasks[i][0];
            sortedTasks[i][1] = tasks[i][1];
            sortedTasks[i][2] = i;
        }

        // Sort tasks ascending based on enqueue time
        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        int tasksProcessingOrder[] = new int[tasks.length];

        // Queue sorts based on min task processing time then min task index.
        PriorityQueue<int[]> nextTask = new PriorityQueue<int[]>((a, b) -> (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));

        long currTime = 0;
        int taskIndex = 0;
        int ansIndex = 0;

        // Stop when there are no tasks left in array or in heap.
        while (taskIndex < tasks.length || !nextTask.isEmpty()) {
            if (nextTask.isEmpty() && currTime < sortedTasks[taskIndex][0]) {
                // When the heap is empty, try updating currTime to next task's enqueue time.
                currTime = sortedTasks[taskIndex][0];
            }

            // Push all the tasks whose enqueueTime <= currtTime into the heap.
            while (taskIndex < tasks.length && currTime >= sortedTasks[taskIndex][0]) {
                nextTask.add(sortedTasks[taskIndex]);
                ++taskIndex;
            }

            int processTime = nextTask.peek()[1];
            int index = nextTask.peek()[2];
            nextTask.remove();

            // Complete this task and increment currTime.
            currTime += processTime;
            tasksProcessingOrder[ansIndex++] = index;
        }

        return tasksProcessingOrder;
    }
}
