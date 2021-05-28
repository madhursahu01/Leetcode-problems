
/*
https://leetcode.com/problems/rotting-oranges/
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.
If this is impossible, return -1.
Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {
    int rowLength;
    int colLength;

    public int orangesRotting(int[][] grid)
    {
        rowLength = grid.length;
        colLength = grid[0].length;
        int numOfFreshOranges = 0;
        Deque<int[]> queue = new ArrayDeque();

        for(int i=0 ; i<rowLength; i++)
        {
            for (int j=0; j<colLength; j++)
            {
                if(grid[i][j]==2)
                    queue.offer(new int[]{i,j});
                else if(grid[i][j]==1)
                    numOfFreshOranges++;
            }
        }
        return performBFS(queue,numOfFreshOranges, grid);
    }

    private int performBFS(Deque<int[]> queue, int numOfFreshOranges, int[][] grid) {

        int time = -1;
        int[][] direction = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for (int s=size; s>0; s--)
            {
                int[] currentIndex = queue.poll();
                for (int[] ints : direction) {
                    int newRowIndex = currentIndex[0] + ints[0];
                    int newColIndex = currentIndex[1] + ints[1];
                    if(newRowIndex<0 || newRowIndex>= rowLength || newColIndex<0 || newColIndex>=colLength || grid[newRowIndex][newColIndex]==0 || grid[newRowIndex][newColIndex]==2)
                        continue;
                    grid[newRowIndex][newColIndex] = 2;
                    queue.offer(new int[]{newRowIndex,newColIndex});
                    numOfFreshOranges--;
                }
            }
            time++;
        }
        return numOfFreshOranges==0 ? time : -1;
    }

    public static void main(String[] args)
    {
        RottingOranges obj = new RottingOranges();
        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(obj.orangesRotting(grid));
    }
}
