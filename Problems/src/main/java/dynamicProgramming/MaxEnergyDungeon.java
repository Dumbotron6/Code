package dynamicProgramming;

public class MaxEnergyDungeon {
    /*
    https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/
     */

    /*
    We use DP and prefix sum. We consider at each point, if we pick that as the starting point, what is the
        max energy we can gain. So we do the prefix sum backwards. By going backwards, we can get i+k energy at each point.
    Because of DP, we have the max energy at each point.
    For example, for [1,2,3,4,5] and k as 2, at 5, we have maxEnergy. At 3, maxEnergy is [3+5].
        At 1, the maxEnergy would be [1+3+5], but we already calculated [3+5] and stored it at 3.
    */
    public int maximumEnergy(int[] energy, int k) {
        int len = energy.length;
        int[] energyK = new int[len];

        int i = len-1;
        int maxEnergy = energy[len-1];

        while(i >= len-k) {
            energyK[i] = energy[i];
            maxEnergy = Math.max(maxEnergy, energyK[i]);
            i--;
        }
        while(i >= 0) {
            energyK[i] = energy[i] + energyK[i+k];
            maxEnergy = Math.max(maxEnergy, energyK[i]);
            i--;
        }

        return maxEnergy;
    }
}
