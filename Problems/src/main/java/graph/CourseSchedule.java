package graph;

import java.util.*;

public class CourseSchedule {
    /*
    https://leetcode.com/problems/course-schedule/
    Can be done using toplogical sort also to check for cycle.
    The first solution uses regular DFS. The second uses topoloical sort.
     */

    Set<Integer> visited;
    Map<Integer, List<Integer>> courses;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        courses = new HashMap<Integer, List<Integer>>();
        visited = new HashSet<Integer>();

        for(int[] course : prerequisites) { //Since there are multiple connections, form a graph.
            if(!courses.containsKey(course[0])) {
                courses.put(course[0], new ArrayList<Integer>());
            }
            courses.get(course[0]).add(course[1]); //Track all prerequisite that pre[0] has.
        }

        for(Map.Entry<Integer, List<Integer>> entry : courses.entrySet()) { //For every entry in the graph, find if there is a cycle.
            visited.add(entry.getKey());
            for(int prereq : entry.getValue()) {
                if(!dfs(prereq)) {
                    return false;
                }
            }
            visited.remove(entry.getKey()); //See reason for backtracking below.
        }

        return true;
    }

    public boolean dfs(int courseId) {
        if(!courses.containsKey(courseId)) { //Means the course we are checking does not have a prerequisite, so it's safe.
            return true;
        }
        if(visited.contains(courseId)) { //Since course was already visited, it means there is a cycle.
            return false;
        }

        visited.add(courseId); //Keep track of the visited courses.
        for(int prereq : courses.get(courseId)) { //Recursively check the prerequisite courses.
            if(!dfs(prereq)) {
                return false;
            }
        }
        visited.remove(courseId);
        /*
        We backtrack because as we move to next course in the overall graph, it may have a previous course as a prerequisite.
        If we don't backtrack, that will return false as it is present in the visited set, which may not be correct.
        */
        courses.put(courseId, new ArrayList<Integer>());
        /*
        Why do the above? Say we check courseid 2 and determined it is safe, now we move to courseid 3, which will have 2 as prerequisite.
        Now inside recursion, it will check 2's prerequisites, which we don't need to do as we already checked it.
        Doing the above eliminates checking something already checked.
        We can also very well do this by maintaining something like a Set<Integer> checked;
        */

        return true;
    }

    //This uses Topological BFS sort.
    public boolean canFinishTopoSort(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<Integer, List<Integer>>();
        int[] inDegree = new int[numCourses];
        LinkedList<Integer> sortQueue = new LinkedList<Integer>();

        int takenCourses = 0;

        for(int[] course : prerequisites) { //Since there are multiple connections, form a graph.
            if(!courses.containsKey(course[0])) {
                courses.put(course[0], new ArrayList<Integer>());
            }
            courses.get(course[0]).add(course[1]); //Track all prerequisite that course[0] has.
            inDegree[course[1]]++; //Count the prerequisites for the target course.
        }

        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) { //Add all courses without prerequisites to queue.
                sortQueue.add(i);
            }
        }

        while(!sortQueue.isEmpty()) {
            int curr = sortQueue.pop();
            takenCourses++; //If it's in queue, then it means the course's prerequisites were taken. So we can increment the popped course.

            for(int nei : courses.getOrDefault(curr, new ArrayList<Integer>())) {
                inDegree[nei]--; //We decrement for each prerequisites course taken by 'nei' course.
                if(inDegree[nei] == 0) { //Only add if all prerequisites were covered and there are no more prerequisites.
                    sortQueue.add(nei);
                }
            }
        }

        //If there was no cycle detected, then every course would've been added to and popped from the queue.
        return takenCourses == numCourses;
    }
}
