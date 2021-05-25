import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/minimum-knight-moves/
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
Return the minimum number of steps needed to move the knight to the square [x, y].
It is guaranteed the answer exists.

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
|x| + |y| <= 300

*/

public class MinimumKnightMoves {

    public static void main(String[] args)
    {
        Solution obj = new Solution();
        System.out.println(obj.minKnightMoves(5,5));
    }

}
class Solution {
    int value ;
    Map<Integer,Integer> map;

    public int minKnightMoves(int x, int y) {

        x = Math.abs(x);
        y = Math.abs(y);
        map = new HashMap<Integer,Integer>();
        value = x+y+13;
        return minMoves(x,y);
    }

    public int minMoves(int x, int y)
    {
        if(x+y==0)
            return 0;
        if(x+y==2)
            return 2;

        int index = x*value+y;
        if(map.containsKey(index))
            return map.get(index);

        int temp1 = minMoves(Math.abs(x-2),Math.abs(y-1));
        int temp2 = minMoves(Math.abs(x-1),Math.abs(y-2));
        map.put(index,Math.min(temp1,temp2)+1);

        return map.get(index);
    }
}
