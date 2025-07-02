package graph;

import java.util.*;

public class CourseScheduler4 {
    /*
    https://leetcode.com/problems/course-schedule-iv/
     */

    //We can use topological BFS sort as we know the graph won't have a cycle. Given in the constraints.
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] adjList = new List[numCourses];
        Set<Integer>[] preReq = new Set[numCourses];
        int[] inDegree = new int[numCourses]; //The number of prerequisites that each course has.
        LinkedList<Integer> sortQueue = new LinkedList<Integer>();

        for(int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<Integer>();
            preReq[i] = new HashSet<Integer>();
        }

        for(int[] course : prerequisites) {
            adjList[course[0]].add(course[1]); //Track all courses that depend on course[0] as prerequisite.
            inDegree[course[1]]++; //Count the prerequisites for the target course.
        }

        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                sortQueue.add(i); //Add courses that don't have prerequisites.
            }
        }

        while(!sortQueue.isEmpty()) {
            int curr = sortQueue.pop();

            for(int parent : adjList[curr]) {
                preReq[parent].add(curr); //For parent, current is a prerequisite.
                preReq[parent].addAll(preReq[curr]); //For parent, every prerequisite of current is also a prerequisite.
                inDegree[parent]--; //For every prerequisite covered, decrement.
                if(inDegree[parent] == 0) { //Add to queue if all prerequisites covered.
                    sortQueue.add(parent);
                }
            }
        }
        /*
        Say we have a node 0 which is a prereqisite for 1 and 2.
        Now preReq[0] will have an empty set. preReq[1] and preReq[2] will have 0 in the set.
        If node 3 has a prerequisite of 1 and 2, preReq[3] will have 0, 1 and 2 because of the above addition inside the loop.
        */

        List<Boolean> result = new ArrayList<Boolean>();

        for(int[] query : queries) {
            result.add(preReq[query[1]].contains(query[0]));
        }

        return result;
    }

    //DFS solution.
    Set<Integer>[] adjList;
    Set<Integer>[] preReq;
    public List<Boolean> checkIfPrerequisiteDFS(int numCourses, int[][] prerequisites, int[][] queries) {
        adjList = new Set[numCourses];
        preReq = new Set[numCourses];

        for(int i = 0; i < numCourses; i++) {
            adjList[i] = new HashSet<Integer>();
        }

        for(int[] course : prerequisites) {
            adjList[course[1]].add(course[0]); //Track all prerequisite that course[1] has.
        }

        for(int course = 0; course < numCourses; course++) {
            dfs(course);
        }

        /*
        Say we have a node 0 which is a prereqisite for 1 and 2.
        Now preReq[0] will have an empty set. preReq[1] and preReq[2] will have 0 in the set.
        If node 3 has a prerequisite of 1 and 2, preReq[3] will have 0, 1 and 2 because of the above addition inside the loop.
        */

        List<Boolean> result = new ArrayList<Boolean>();

        for(int[] query : queries) {
            result.add(preReq[query[1]].contains(query[0]));
        }

        return result;
    }

    public Set<Integer> dfs(int course) {
        if(preReq[course] != null) {
            return preReq[course];
        }
        preReq[course] = new HashSet<Integer>();

        for(int pre : adjList[course]) { //Go through all prerequisites of course.
            preReq[course].addAll(dfs(pre)); //Recursively add prerequisites.
        }
        preReq[course].add(course); //We add course itself as a prerequisite for course. Why? So parent caller will get course in the set.
        //We can safely do is as u != v in the queries in the constraints.

        return preReq[course];
    }
}
