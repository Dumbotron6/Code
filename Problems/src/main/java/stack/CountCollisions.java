package stack;

import java.util.LinkedList;

public class CountCollisions {
    public int countCollisions(String directions) {
        LinkedList<Character> cars = new LinkedList<Character>();
        int collisions = 0;

        for(char car : directions.toCharArray()) {
            /*
            For all left cars, check if there was a right car or stationary car before. No need to add 'L' to stack as it will be at far left
                or face a collision and become stationary.
            */
            char curr = car;
            if(curr == 'L') {
                if(!cars.isEmpty()) {
                    if(cars.peek() == 'R') { //'L' will collide with 'R'.
                        collisions += 2;
                        cars.pop();
                        curr = 'S'; //Current car becomes stationary.
                    }else if(cars.peek() == 'S') { //'L' will collide with 'S'/
                        collisions++;
                    }
                }
            }
            if(curr == 'S'){ //For current stationary car, check if there are right moving cars before.
                while(!cars.isEmpty() && cars.peek() == 'R') { //All right cars will collide with current 'S'.
                    collisions++;
                    cars.pop();
                }
                cars.push(curr);
            }else if(curr == 'R'){ //Right cars will be added to stack.
                cars.push(curr);
            }
        }
        //At the end, all cars in the stack would either be stationary, or they would be tailing right cars.

        return collisions;
    }

    public int countCollisionsBetter(String directions) {
        int rightCars = 0;
        int statCars = 0;
        int collisions = 0;

        for(char car : directions.toCharArray()) {
            if(car == 'L') {
                if(rightCars != 0) {
                    collisions += 2;;
                    rightCars--;
                    statCars++;
                    if(rightCars != 0) {
                        collisions += rightCars;
                        rightCars = 0;
                    }
                }else if(statCars != 0) {
                    collisions++;
                }
            }else if(car == 'S') {
                collisions += rightCars;
                statCars++;
                rightCars = 0;
            }else {
                rightCars++;
            }

        }

        return collisions;
    }

    //No need to even use stack.
    public int countCollisionsOptimal(String directions) {
        int len = directions.length()-1;
        int leftCarPos = 0;
        int rightCarPos = len;
        int collisions = 0;

        while(leftCarPos <= len && directions.charAt(leftCarPos) == 'L') { //Discard all trailing left cars on the left.
            leftCarPos++;
        }

        while(rightCarPos >= leftCarPos && directions.charAt(rightCarPos) == 'R') { //Discard all trailing right cars on the right.
            rightCarPos--;
        }

        /*
        All remaining calls will collide. The +2 for LR situation is irrelevant because L and R are counted resulting in net +2.
        To better understand, note that RL is 2 and RSL is also 2.
         */
        for(int i = leftCarPos; i <= rightCarPos; i++) {
            if(directions.charAt(i) != 'S') {
                collisions++;
            }
        }

        return collisions;
    }

    //Mistake is, after a right car collides and becomes stationary, we don't count all the right cars before it.
    public int countCollisionsMistake(String directions) {
        LinkedList<Character> cars = new LinkedList<Character>();
        int collisions = 0;

        for(char car : directions.toCharArray()) {
            /*
            For all left cars, check if there was a right car or stationary car before. No need to add 'L' to stack as it will be at far left
                or face a collision and become stationary.
            */
            if(car == 'L') {
                if(!cars.isEmpty()) {
                    if(cars.peek() == 'R') { //'L' will with 'R'.
                        collisions += 2;
                        cars.pop();
                        cars.push('S'); //Collide and becomes stationary.
                    }else if(cars.peek() == 'S') { //'L' will collide with 'S'/
                        collisions++;
                    }
                }
            }else if(car == 'S'){ //For all stationary cars, check if there are right moving cars before.
                while(!cars.isEmpty() && cars.peek() == 'R') { //All right cars will collide with current 'S'.
                    collisions++;
                    cars.pop();
                }
                cars.push(car);
            }else { //Right cars will be added to stack.
                cars.push(car);
            }
        }
        //At the end, all cars in the stack would either be stationary, or they would be tailing right cars.

        return collisions;
    }
}
