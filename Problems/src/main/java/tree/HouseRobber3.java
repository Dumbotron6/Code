package tree;

public class HouseRobber3 {
    /*
    https://leetcode.com/problems/house-robber-iii/description/
     */

    /*
    Since directly linked nodes can't be robbed, it means every alternating level of houses can be robbed.
    So level 0,2,4 or 1,3,5 or 0,3,5 etc.
    So at each level, we need to assess if we have to keep current value or skip it and check downstream.
    That's why we use max.
    */
    public int rob(TreeNode root) {
        int[] result = robHelper(root); //Max of current+grandchildren and children.
        return Math.max(result[0], result[1]);
    }

    /*
    Below solution works but runs into TLE. Because we end up checking places we already checked for each recursion.
    Time complexity is O(2^N).
    */
    public int robHelperNaive(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int ans = root.val; //Take current value.

        if(root.left != null) { //Add left grandchild value.
            ans += robHelperNaive(root.left.left) + robHelperNaive(root.left.right);
        }
        if(root.right != null) { //Add right grandchild value.
            ans += robHelperNaive(root.right.left) + robHelperNaive(root.right.right);
        }

        int ansChild = robHelperNaive(root.left) + robHelperNaive(root.right); //Direct children value.

        return Math.max(ans, ansChild); //Max of current+grandchildren and children.
    }

    /*
    Optimal solution,
    Building on previous solution, we check children or grandchildren as soon as we reach it and
        store it, so we don't recursively check the same nodes again. We use memoization.
    */
    public int[] robHelper(TreeNode root) {
        if(root == null) {
            return new int[2];
        }

        int[] ans = new int[2]; //0 index is if we rob current house, 1 index is we skip.
        ans[0] = root.val; //Add current.

        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);

        //Add grandchildren, ie, only root.left.left, root.left.right etc.
        ans[0] += left[1] + right[1];
        //Skip current, take max of children and grandchildren.
        ans[1] += Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return ans;
    }
}
