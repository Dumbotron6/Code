package graph;

import java.util.*;

public class EvaluateDivision {
    /*
    https://leetcode.com/problems/evaluate-division/
     */

    /*
    We form a graph- an adjacency list. We store numerator/denominator value. We also store the reverse as we'll be given a/b and asked b/a.
    */
    Set<String> visited;
    Map<String, List<Pair>> adjList;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        adjList = new HashMap<String, List<Pair>>();
        double[] result = new double[queries.size()];

        //Form graph.
        for(int i = 0; i < equations.size(); i++) {
            String num = equations.get(i).get(0);
            String den = equations.get(i).get(1);

            if(!adjList.containsKey(num)) {
                adjList.put(num, new ArrayList<Pair>());
            }
            if(!adjList.containsKey(den)) {
                adjList.put(den, new ArrayList<Pair>());
            }
            adjList.get(num).add(new Pair(den, values[i]));
            adjList.get(den).add(new Pair(num, (double)1/values[i]));
        }

        visited = new HashSet<String>();
        for(int i = 0; i < queries.size(); i++) {
            if(adjList.containsKey(queries.get(i).get(0)) && adjList.containsKey(queries.get(i).get(1))) {
                result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1));
            }else {
                result[i] = -1;
            }
        }

        return result;
    }

    public double dfs(String num, String target) {
        if(visited.contains(num)) {
            return -1.0;
        }

        visited.add(num);
        double retVal = 0;

        for(Pair divVal : adjList.get(num)) {
            if(divVal.den.equals(target)) {
                visited.remove(num);
                return divVal.val;
            }
            retVal = dfs(divVal.den, target); //We make this denominator the new numberator, see if that has the target as denominator.
            if(retVal != -1.0) { //If target found after recursion through above DFS.
                visited.remove(num);
                return divVal.val*retVal; //Why? If we are asked a/c then a/b * b/c = a/c. divVal.val is a/b and retVal is b/c (or b/d * d/c = b/c via recursion).
            }
        }

        visited.remove(num);

        return -1.0; //Target not found in current recursion end.
    }

    //NOTE: We remove from visited in 3 places. Instead we can also pass a fresh Hashset every time in the queries loop for each query and not remove from visited.

    class Pair {
        String den;
        double val;

        public Pair(String _den, double _val) {
            this.den = _den;
            this.val = _val;
        }
    }
}
