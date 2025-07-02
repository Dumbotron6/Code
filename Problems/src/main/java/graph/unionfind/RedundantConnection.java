package graph.unionfind;

public class RedundantConnection {
    /*
    https://leetcode.com/problems/redundant-connection/description/
     */

    //We use union find to group all connected vertices together. When we encounter a cycle, it means that's a redundant connection.
    public int[] findRedundantConnection(int[][] edges) {
        int[] redundant = new int[]{0,0};

        UnionFind uf = new UnionFind(edges.length+1); //Why +1? Because constraint starts at 1, not 0.

        for(int[] edge : edges) {
            if(!uf.union(edge[0], edge[1])) { //Union all edges. If we can't union, it means it's already unioned and cycle is found.
                redundant[0] = edge[0];
                redundant[1] = edge[1];
            }
        }

        return redundant;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            for(int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int findRoot(int verti) {
            int curr = verti;
            int par = this.parent[curr];
            while(par != curr) { //Keep going up till we find the root.
                curr = par;
                par = this.parent[curr];
                this.parent[verti] = par; //Move verti's parent also up as we move up. Makes finding root for same verti easier next time.
            }
            return par;
        }

        public boolean union(int v1, int v2) {
            int root1 = findRoot(v1);
            int root2 = findRoot(v2);

            if(root1 == root2) { //They have samm root, meaning they already belong to a set, ie, cycle detected.
                return false;
            }

            //Assign root with lower rank as the child to the root with higher rank.
            if(this.rank[root1] > this.rank[root2]) {
                this.parent[root2] = root1;
            }else if(this.rank[root1] < this.rank[root2]) {
                this.parent[root1] = root2;
            }else { //If same rank, assign one to the other and increase the rank.
                this.parent[root1] = root2;
                this.rank[root2]++;
            }
            return true;
        }
    }
}
