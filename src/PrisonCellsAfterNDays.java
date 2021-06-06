/*
https://leetcode.com/problems/prison-cells-after-n-days/
There are 8 prison cells in a row and each cell is either occupied or vacant.
Each day, whether the cell is occupied or vacant changes according to the following rules:
If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith cell is vacant, and you are given an integer n.
Return the state of the prison after n days (i.e., n such changes described above).
Example 1:
Input: cells = [0,1,0,1,1,0,0,1], n = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 */

import java.util.Arrays;

public class PrisonCellsAfterNDays {
        public int[] prisonAfterNDays(int[] cells, int N)
        {
            N = N-1; //because we want to find repeat days and it repeats the same state only after 1 change because of 0 at start and end after first change.
            int days = N;
            cells = nextDayCells(cells);
            if(N > (Math.pow(2,6)))
            {
                int repeatDays = findRepeatDays(cells);
                days = N % repeatDays;
            }
            while(days>0)
            {
                cells = nextDayCells(cells);
                days--;
            }
            return cells;
        }

        protected int cellstoBitMap(int[] cells)
        {
            int stateBitMap = 0x0;
            for(int cell : cells)
            {
                stateBitMap <<= 1;
                stateBitMap = (stateBitMap | cell);
            }
            return stateBitMap;
        }

        public int findRepeatDays(int[] newCells)
        {
            int repeatDay = 1;
            int originalState = cellstoBitMap(newCells);
            newCells = nextDayCells(newCells);
            while(originalState!=cellstoBitMap(newCells))
            {
                repeatDay++;
                newCells = nextDayCells(newCells);
            }
            return repeatDay;
        }

        public int[] nextDayCells(int[] cells)
        {
            int prev = cells[0];
            int curr;
            for(int i=1; i<7; i++)
            {
                curr = cells[i];
                if(prev == cells[i+1])
                    cells[i]=1;
                else
                    cells[i]=0;
                prev = curr;
            }
            cells[0]=0;
            cells[7]=0;
            return cells;
        }

    public static void main(String[] args)
    {
        PrisonCellsAfterNDays obj = new PrisonCellsAfterNDays();
        int n =7;
        int[] cells = {0,1,0,1,1,0,0,1};
        System.out.println(Arrays.toString(obj.prisonAfterNDays(cells,n)));
    }
}
