package dynamicProgramming;

public class MinDeleteBalancedString {
    /*
    https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/
     */

    /*
    For string 'aababbab', at index 3, there is 1 'b' to the left and 1 'a' to the right. What is present at index 3 itself doesn't matter.
    Because if we delete all 'b' on the left and all 'a' on the right, index 3 can be 'a' or 'b'.
    */
    public int minimumDeletions(String s) {
        int len = s.length();
        int[] bCount = new int[len];
        int minDel = Integer.MAX_VALUE;

        for(int i = 1; i < len; i++) { //To store all 'b' to the left of that index.
            bCount[i] = bCount[i-1];
            if(s.charAt(i-1) == 'b') {
                bCount[i]++;
            }
        }

        int rightA = 0; //Keeps count of 'a' encountered as we move backwards from the end;

        //At every posision, check the count if all left 'b' and right 'a' are deleted.
        for(int i = len-1; i >= 0; i--) {
            minDel = Math.min(minDel, bCount[i]+rightA); //bCount[i] is all 'b' to the left and rightA is all 'a' to the right.
            if(s.charAt(i) == 'a') {
                rightA++;
            }
        }

        return minDel;
    }

    //Revist his approach.
    public int minimumDeletionsOptimal(String s) {
        int bCount = 0;
        int deletes = 0;

        for(char c : s.toCharArray()) {
            if(c == 'b') {
                bCount++;
            }else {
                //At 'a', check what would be minimum, deleting all b encountered so far, or keep all deletetions, and delete this a.
                deletes = Math.min(deletes+1, bCount);
            }
            /*
            What this would lead to is, deleteing all b on the left, or deleting a combination of a and b(deletes), and add this a to the deletion.
            'aababbab' at index 6, bCount is 3, but deletes+1 would be 2, so that is taken.
            deletes would be 1(from deleting the previous single b during index 3) plus this current a.
            */
        }

        return deletes;
    }
}
