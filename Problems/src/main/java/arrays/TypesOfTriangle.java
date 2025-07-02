package arrays;

public class TypesOfTriangle {
    /*
    https://leetcode.com/problems/type-of-triangle/description/
     */

    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];

        if(!(a+b > c && b+c > a && a+c > b)) {
            return "none";
        }

        int same = 0;
        if(a == b) {
            same++;
        }

        if(b == c) {
            same++;
        }else if(a == c) {
            same++;
        }

        if(same == 2) {
            return "equilateral";
        }else if(same == 1) {
            return "isosceles";
        }

        return "scalene";
    }
}
