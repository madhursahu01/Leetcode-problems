import java.util.*;

/*
Given a directed graph, find the topological ordering of its vertices.
Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
Output: Following are the two valid topological sorts for the given graph:
1) 3, 2, 0, 1
2) 3, 2, 1, 0
 */
class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {

        List<Integer> sortedOrder = new ArrayList<Integer>();
        Map<Integer,Integer> indegree = new HashMap<Integer,Integer>();
        Map<Integer,List<Integer>> graph = new HashMap<Integer,List<Integer>>();

        if(vertices == 0)
            return sortedOrder;

        for(int i = 0 ; i < vertices ; i++)
        {
            indegree.put(i,0);
            graph.put(i,new ArrayList<Integer>());
        }

        for(int[] edge : edges)
        {
            int parent = edge[0];
            int child = edge[1];
            indegree.put(child, indegree.get(child)+1);
            graph.get(parent).add(child);
        }

        Deque<Integer> queue = new ArrayDeque<Integer>();

        for(Map.Entry<Integer,Integer> entry : indegree.entrySet())
        {
            if(entry.getValue()==0)
            {
                queue.offer(entry.getKey());
            }
        }

        while(!queue.isEmpty())
        {
            Integer vertex = queue.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(Integer child : children)
            {
                indegree.put(child, indegree.get(child)-1);
                if(indegree.get(child) == 0)
                {
                    queue.offer(child);
                }
            }
        }

        if(sortedOrder.size() != vertices)
        {
            return new ArrayList<Integer>();
        }
        return sortedOrder;
    }


    public static void main(String[] args)
    {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

    }
}