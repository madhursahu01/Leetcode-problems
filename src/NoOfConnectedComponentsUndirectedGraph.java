import java.util.*;

/*
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

 */
public class NoOfConnectedComponentsUndirectedGraph {
    Map<Integer, List<Integer>> graph;
    int[] visited;
    public int countComponents(int n, int[][] edges) {
        visited = new int[n];
        graph = new HashMap<Integer, List<Integer>>();
        int count = 0;
        for(int i=0; i<n; i++)
            graph.put(i,new ArrayList<Integer>());
        for(int[] edge : edges)
        {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }
        for(int i=0; i<n; i++)
        {
            if(visited[i]==0)
            {
                dfs(i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int i)
    {
        if(visited[i]!=0)
            return;
        visited[i] =1;
        for(Integer child : graph.get(i))
            dfs(child);
    }
    public static void main(String[] args)
    {
        NoOfConnectedComponentsUndirectedGraph obj = new NoOfConnectedComponentsUndirectedGraph();
        int n=5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println(obj.countComponents(n,edges));
    }
}
