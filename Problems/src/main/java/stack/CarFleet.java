package stack;

import java.util.Arrays;
import java.util.LinkedList;

public class CarFleet {
    /*
    https://leetcode.com/problems/car-fleet/
     */

    public int carFleet(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];
        for(int i = 0; i < position.length; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> b[0]-a[0]); //Sort the cars by descending position(closest to target first).

        LinkedList<Double> stack = new LinkedList<Double>();

        for(int[] car : cars) {
            double currCar = (double)(target-car[0])/car[1]; //Will give us time taken to reach target -- (target-position)/speed.
            /*
            stack.peek() is the previous car(ahead) and currCar is the current car(behind).
            If the previous car's(the one ahead) time is greater than current car's(the one behind) time, then that means
                current car will catch up to previous car, thus slowing down and matching the
                speed(and thereby time taken to reach target).
            So we don't add the faster(current) car to the stack.
            Hence currCar = stack.pop(); -- implies currCar will catch up and match previous car's time(or speed).
            */
            if(!stack.isEmpty() && currCar <= stack.peek()) {
                currCar = stack.pop();
            }
            stack.push(currCar);
        }

        return stack.size();
    }

    //This can also be done without stack. As observed from above, we merely need to keep track of the previous car.
    public int carFleetNonStack(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];
        for(int i = 0; i < position.length; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a,b) -> b[0]-a[0]); //Sort the cars by descending position(closest to target first).

        double prevCar = (double)(target-cars[0][0])/cars[0][1];
        int fleet = 1;

        for(int i = 1; i < cars.length; i++) {
            double currCar = (double)(target-cars[i][0])/cars[i][1]; //Will give us time taken to reach target -- (target-position)/speed.
            /*
            If the previous car's(the one ahead) time is greater than current car's(the one behind) time, then that means
                current car will catch up to previous car, thus slowing down and matching the
                speed(and thereby time taken to reach target).
            So only is the currCar time is greater, then the fleet size increases.
            Doing so, the previous time will become the slowest time, which is currCar.
            */
            if(currCar > prevCar) {
                fleet++;
                prevCar = currCar;
            }
        }

        return fleet;
    }
}
