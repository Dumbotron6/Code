package graph;

import java.util.*;

public class CourseSchedule2 {
    /*
    https://leetcode.com/problems/course-schedule-ii/
    Two solutions. One is direct DFS, another is topological sort DFS.
     */

    int pos;
    int[] order;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseMap = new HashMap<Integer, List<Integer>>(); //Graph.
        boolean[] takenCourses = new boolean[numCourses]; //To keep track of taken courses, so dfs doesn't have to be repeated.
        boolean[] visited = new boolean[numCourses]; //Visited courses during each dfs run, to detect cycle.
        //Both boolean arrays are of size numCourses because constraint size is small.

        order = new int[numCourses];
        pos = 0;

        for(int[] pre : prerequisites) { //Form a graph of all courses and prerequisites.
            if(!courseMap.containsKey(pre[0])) {
                courseMap.put(pre[0], new ArrayList<Integer>());
            }
            courseMap.get(pre[0]).add(pre[1]); //Track all prerequisite that pre[0] has.
        }

        for(Map.Entry<Integer, List<Integer>> entry : courseMap.entrySet()) {
            if(dfsHasCycle(entry.getKey(), courseMap, takenCourses, visited)) { //Return empty array if graph has cycle.
                return new int[]{};
            }
        }

        /*
        Not all courses will have prerequisites or be prerequisites, so they may not be in the graph.
        So add all courses that are not taken.
        */
        for(int i = 0; i < numCourses; i++) {
            if(!takenCourses[i]) {
                order[pos++] = i;
            }
        }

        return order;
    }

    public boolean dfsHasCycle(int course, Map<Integer, List<Integer>> courseMap, boolean[] takenCourses, boolean[] visited) {
        if(takenCourses[course]) {
            return false;
        }

        if(visited[course]) { //If already visited during current dfs recursion, then cycle exists.
            return true;
        }

        visited[course] = true;
        if(courseMap.containsKey(course)) {
            for(int preCourse : courseMap.get(course)) {
                if(dfsHasCycle(preCourse, courseMap, takenCourses, visited)) { //Return if cycle detected.
                    return true;
                }
            }
        }
        visited[course] = false; //Backtrack as next dfs in graph will use same array.

        takenCourses[course] = true;
        order[pos++] = course;
        /*
        Because of backtracking, the final prerequisite(which has no prerequisite) inside dfs will be added first.
        Due to recursion, final will be assigned to 0, parent will be assigned to 1 and its parent will be assigned to 2 etc.
        */

        return false;
    }

    //This uses topological sort to get the order of courses.
    LinkedList<Integer> sortStack;
    public int[] findOrderTop(int numCourses, int[][] prerequisites) {
        List<Integer>[] courseMap = new List[numCourses]; //Graph.
        boolean[] takenCourses = new boolean[numCourses]; //To keep track of taken courses, so dfs doesn't have to be repeated.
        boolean[] visited = new boolean[numCourses]; //Visited courses during each dfs run, to detect cycle.
        //Both boolean arrays are of size numCourses because constraint size is small.

        sortStack = new LinkedList<Integer>(); //To store topologically sorted courses.

        int[] order = new int[numCourses];
        int pos = 0;

        for(int i = 0; i < numCourses; i++) {
            courseMap[i] = new ArrayList<Integer>();
        }

        for(int[] pre : prerequisites) { //Form a graph of all courses and prerequisites.
            courseMap[pre[0]].add(pre[1]); //Track all prerequisite that pre[0] has.
        }

        for(int i = 0; i < numCourses; i++) {
            if(dfsHasCycleTop(i, courseMap, takenCourses, visited)) { //Return empty array if graph has cycle.
                return new int[]{};
            }
        }

        while(!sortStack.isEmpty()) {
            order[pos++] = sortStack.pop();
        }

        return order;
    }

    public boolean dfsHasCycleTop(int course,  List<Integer>[] courseMap, boolean[] takenCourses, boolean[] visited) {
        if(takenCourses[course]) {
            return false;
        }

        if(visited[course]) { //If already visited during current dfs recursion, then cycle exists.
            return true;
        }

        visited[course] = true;
        for(int preCourse : courseMap[course]) {
            if(dfsHasCycleTop(preCourse, courseMap, takenCourses, visited)) { //Return if cycle detected.
                return true;
            }
        }
        visited[course] = false; //Backtrack as next dfs in graph will use same array.

        takenCourses[course] = true;
        sortStack.add(course);
        /*
        Because of backtracking, the final prerequisite(which has no prerequisite) inside dfs will be added first.
        Due to recursion, final will be assigned to 0, parent will be assigned to 1 and its parent will be assigned to 2 etc.
        */

        return false;
    }
}
