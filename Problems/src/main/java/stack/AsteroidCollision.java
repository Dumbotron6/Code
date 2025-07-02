package stack;

import java.util.LinkedList;

public class AsteroidCollision {
    /*
    https://leetcode.com/problems/asteroid-collision/description/
     */

    /*
    For every asteroid going right, add it to the stack.
    For every asteroid going left, check it against last element in stack and if it goes right, decide the collision
        and insert.
     */
    public int[] asteroidCollision(int[] asteroids) {

        LinkedList<Integer> asteroidStack = new LinkedList<Integer>();

        for(int asteroid : asteroids) {
            if(asteroid > 0) {
                asteroidStack.push(asteroid);
            } else {
                if(asteroidStack.isEmpty())
                    asteroidStack.push(asteroid);
                else {
                    while(!asteroidStack.isEmpty() && asteroidStack.peek() > 0 && asteroid != 0) {
                        if(asteroidStack.peek() == -1*asteroid) {
                            asteroidStack.pop();
                            asteroid = 0;
                        } else if(asteroidStack.peek()  < Math.abs(asteroid)) {
                            asteroidStack.pop();
                        } else
                            asteroid = 0;
                    }
                    if(asteroid != 0) {
                        asteroidStack.push(asteroid);
                    }
                }
            }
        }

        int[] finalAsteroids = new int[asteroidStack.size()];

        for(int i = finalAsteroids.length - 1; i >= 0; i--) {
            finalAsteroids[i] = asteroidStack.pop();
        }

        return finalAsteroids;
    }
}
