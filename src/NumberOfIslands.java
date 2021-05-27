/*
https://leetcode.com/problems/number-of-islands/
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
return the number of islands. An island is surrounded by water and
is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
 */

public class NumberOfIslands {
    int row;
    int col;

    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int count =0;
        for(int i=0 ; i<row ; i++)
        {
            for(int j=0; j<col ; j++)
            {
                if(grid[i][j]=='1')
                {
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs( char[][] grid, int r, int c)
    {
        if(r>=row || r<0 || c>=col || c<0 || grid[r][c]=='0')
            return;

        grid[r][c]='0';
        dfs(grid,r+1,c);
        dfs(grid,r-1,c);
        dfs(grid,r,c+1);
        dfs(grid,r,c-1);
    }
    public static void main(String[] args)
    {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        NumberOfIslands obj = new NumberOfIslands();
        System.out.println(obj.numIslands(grid));
    }
}
