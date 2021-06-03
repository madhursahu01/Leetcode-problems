import java.util.*;
/*
https://leetcode.com/problems/shortest-path-in-binary-matrix/
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.
Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2
 */

public class ShortestPathInBinaryMatrix {
    int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,-1},{-1,1}};
    int rowLength = 0;
    int colLength = 0;

    public int shortestPathBinaryMatrix(int[][] grid) {

        rowLength = grid.length;
        colLength = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<int[]>();
        int[][] visited = new int[rowLength][colLength];
        int distance = 1;

        if(grid[0][0] != 0 || grid[rowLength-1][colLength-1] != 0)
        {
            return -1;
        }

        visited[0][0] = 1;
        queue.offer(new int[]{0,0});

        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i = size ; i>0 ; i--)
            {
                int[] currentIndex = queue.poll();
                int row = currentIndex[0];
                int col = currentIndex[1];
                if(row == rowLength-1 && col == colLength-1)
                {
                    return distance;
                }
                for(int[] neighbour : getNeighbours(row,col, grid, visited))
                {
                    visited[neighbour[0]][neighbour[1]] = 1;
                    queue.offer(neighbour);
                }

            }
            distance++;
        }
        return -1;
    }

    public List<int[]> getNeighbours(int row, int col, int[][] grid, int[][] visited)
    {
        List<int[]> list = new ArrayList<int[]>();
        for(int[] direction : directions)
        {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if(newRow<0 || newRow>=rowLength || newCol<0 || newCol>=colLength || grid[newRow][newCol] ==1 || visited[newRow][newCol] ==1)
                continue;

            list.add(new int[]{newRow,newCol});
        }
        return list;
    }

    public static void main(String[] args)
    {
        ShortestPathInBinaryMatrix obj = new ShortestPathInBinaryMatrix();
        int[][] grid = {{0,1},{1,0}};
        System.out.println(obj.shortestPathBinaryMatrix(grid));
    }
}
