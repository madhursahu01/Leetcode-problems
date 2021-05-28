
/*
https://leetcode.com/discuss/interview-question/1235202/Amazon-or-Onsite-or-Determine-Path
Given a 2D array where each element contains 1 or 0, where 1 indicates land and 0 indicates water.
Determine if there is a path by land from a given start point to end point.
Example:
0 0 1 0 1
0 1 1 1 1
1 1 1 0 0
1 1 1 0 1
sample start and end:
ex 1:  0,0   to  3,3  --> return false
ex 2:  1,0   to  3,3  --> return true
You can consider embarking/disembarking (a SINGLE spot adjacent to water) to be reachable
Moving diagonally is not considered a valid movement
public boolean pathExists(int startX, int startY, int endX, int endY, int[][] map) {}
 */

public class DeterminePath {

    public boolean pathExists(int startX, int startY, int endX, int endY, int[][] map)
    {

        map[startX][startY]=1;
        return dfs(startX,startY,endX,endY,map);
    }

    public boolean dfs(int currX, int currY, int endX, int endY, int[][] map)
    {
        if(currX==endX && currY==endY)
            return true;
        if(currX<0 || currX>=map.length || currY<0 || currY>=map[0].length || map[currX][currY]==0)
            return false;
        int[][] directions = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        map[currX][currY] = 0;
        for(int[] d : directions)
        {
            int newX = currX+d[0];
            int newY = currY+d[1];
            if(dfs(newX,newY,endX,endY,map))
                return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        DeterminePath obj = new DeterminePath();
        int[][] map = new int[][]{{0,0,1,0,1},{0,1,1,1,1},{1,1,1,0,0},{1,1,1,0,1}};
        int startX = 0, startY = 0;
        int endX = 3, endY = 3;
        System.out.println(obj.pathExists(startX,startY,endX,endY,map));
    }

}
