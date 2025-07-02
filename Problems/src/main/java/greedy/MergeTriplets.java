package greedy;

public class MergeTriplets {

    /*
    https://leetcode.com/problems/merge-triplets-to-form-target-triplet/description/
     */

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] found = new boolean[3];

        /*
        Any triplet who has the 0 we need but with 1 or 2 greater than the target would not suit our needs.
        Because if it were greater, that would exceed our target any that would always be the max, even if we found 1 or 2
            in another triplet.
        So if we find 0 or 1 or 2, the other two need to be less than or equal to our target.
        Of course, all 3 targets need to be found.
        */
        for(int[] triple : triplets) {
            if(triple[0] == target[0] && triple[1] <= target[1] && triple[2] <= target[2]) {
                found[0] = true;
            }
            if(triple[1] == target[1] && triple[0] <= target[0] && triple[2] <= target[2]) {
                found[1] = true;
            }
            if(triple[2] == target[2] && triple[1] <= target[1] && triple[0] <= target[0]) {
                found[2] = true;
            }
        }

        return found[0] && found[1] && found[2];
    }

    public boolean mergeTripletsAlt(int[][] triplets, int[] target) {
        boolean[] found = new boolean[3];

        /*
        Any triplet who has the 0 we need but with 1 or 2 greater than the target would not suit our needs.
        Because if it were greater, that would exceed our target any that would always be the max, even if we found 1 or 2
            in another triplet.
        So if we find 0 or 1 or 2, the other two need to be less than or equal to our target.
        So we skip all triplets with any value greater than our target.
        Of course, all 3 targets need to be found.
        */
        for(int[] triple : triplets) {
            if(triple[0] > target[0] || triple[1] > target[1] || triple[2] > target[2]) {
                continue;
            }
            if(triple[0] == target[0]) {
                found[0] = true;
            }
            if(triple[1] == target[1]) {
                found[1] = true;
            }
            if(triple[2] == target[2]) {
                found[2] = true;
            }
        }

        return found[0] && found[1] && found[2];
    }
}
