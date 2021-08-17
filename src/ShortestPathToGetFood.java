
/*
https://leetcode.com/problems/shortest-path-to-get-food/
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
You are given an m x n character matrix, grid, of these different types of cells:
'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
Example 1:
Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
 */


import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathToGetFood {
    int rowMax;
    int colMax;
    int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};

    public int getFood(char[][] grid)
    {
        rowMax = grid.length;
        colMax = grid[0].length;

        Deque<int[]> queue = new ArrayDeque();
        boolean[][] visited = new boolean[rowMax][colMax];
        int[] start = findStart(grid);

        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int minPath = 0;
        System.out.print(start[0]+ "," + start[1]);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i=0; i <size; i++)
            {
                int[] current = queue.poll();
                if(grid[current[0]][current[1]] == '#')
                    return minPath;
                for(int[] direction : directions)
                {
                    int newX = direction[0] + current[0];
                    int newY = direction[1] + current[1];

                    if(isValid(grid,newX,newY) && !visited[newX][newY])
                    {
                        queue.offer(new int[]{newX,newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            minPath++;
        }
        return -1;
    }

    public int[] findStart(char[][] grid)
    {
        for(int i=0; i<rowMax; i++)
            for(int j=0; j<colMax; j++)
            {
                if(grid[i][j]=='*')
                    return new int[]{i,j};
            }
        throw new RuntimeException("Starting point not found.");
    }

    public Boolean isValid(char[][] grid, int i, int j)
    {
        if(i>=0 && j>=0 && i<rowMax && j<colMax && grid[i][j] != 'X')
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        ShortestPathToGetFood obj = new ShortestPathToGetFood();
        char[][] grid = new char[][]{{'X','X','X','X','X','X'},{'X','*','O','O','O','X'},{'X','O','O','#','O','X'},{'X','X','X','X','X','X',}};
        Assert.assertEquals(3, obj.getFood(grid));
    }
}
