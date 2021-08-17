
/*
https://leetcode.com/problems/design-parking-system/

Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.
Implement the ParkingSystem class:
ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class. The number of slots for each parking space are given as part of the constructor.
bool addCar(int carType) Checks whether there is a parking space of carType for the car that wants to get into the parking lot. carType can be of three kinds:
big, medium, or small, which are represented by 1, 2, and 3 respectively. A car can only park in a parking space of its carType. If there is no space available,
return false, else park the car in that size space and return true.
Example 1:
Input
["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
[[1, 1, 0], [1], [2], [3], [1]]
Output
[null, true, true, false, false]
 */

import org.junit.Assert;

public class DesignParkingSystem {

    private int big_limit;
    private int medium_limit;
    private int small_limit;

    private int big_current=0;
    private int medium_current=0;
    private int small_current=0;

    public DesignParkingSystem(int big, int medium, int small) {

        this.big_limit = big;
        this.medium_limit = medium;
        this.small_limit = small;

    }

    public boolean addCar(int carType) {
        boolean canAdd = false;
        switch(carType)
        {
            case 1 :
                if(big_current<big_limit) {
                    canAdd = true;
                    big_current = carAdded(big_current);
                }
                break;
            case 2 :
                if(medium_current<medium_limit) {
                    canAdd = true;
                    medium_current = carAdded(medium_current);
                }
                break;
            case 3 :
                if(small_current<small_limit) {
                    canAdd = true;
                    small_current = carAdded(small_current);
                }
                break;
            default :
                canAdd = false;
        }
        return canAdd;
    }

    public int carAdded(int current)
    {
        return ++current;
    }

    public static void main(String[] args)
    {
        DesignParkingSystem obj = new DesignParkingSystem(1,1,0);
        Assert.assertTrue(obj.addCar(1));
        Assert.assertTrue(obj.addCar(2));
        Assert.assertFalse(obj.addCar(3));
        Assert.assertFalse(obj.addCar(1));

    }
}
