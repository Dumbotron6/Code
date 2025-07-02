package arrays;

public class AlternatingGroups2 {
    /*
    https://leetcode.com/problems/alternating-groups-ii/
     */

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int len = colors.length;
        int len2 = len+k-1;
        int[] circleColors = new int[len2];
        /*
        To make a circular array, we can double its length and copy its elements twice. So we'll always hit every element in circular array.
        Improving on previously submitted solution, we don't need the full circular array. We just need len + additional k length to be copied.
        Why len+k-1 instead of len+k? Doing so helps us eliminate the need to keep track of left and begin like in the previously
            submitted solution. As the loop will end before it starts counting duplicates(ie, before the beginning of alter reaches len).
        */

        for(int i = 0; i < len2; i++) {
            circleColors[i] = colors[i%len];
        }

        int right = 1;
        int alter = 0;
        int count = 0;
        k = k-1; //Why k = k-1? If there are 3 elements, then alter will be 2. So make k = k-1 instead of checking alter+1 == k every time.

        //No need to reset alter to 0 when alter == k. Why? Because alter is reset to 0 anyway when we encounter a non alter.
        while(right < len2) {
            if(circleColors[right] != circleColors[right-1]) {
                alter++;
            }else {
                alter = 0;
            }
            right++;
            if(alter >= k) {
                count++;
            }
        }

        return count;
    }

    public int numberOfAlternatingGroupsOld(int[] colors, int k) {
        int len = colors.length;
        int len2 = len*2;
        int[] circleColors = new int[len2];
        //To make a circular array, we can double its length and copy its elements twice. So we'll always hit every element in circular array.

        for(int i = 0; i < len2; i++) {
            circleColors[i] = colors[i%len];
        }

        int left = 0;
        int right = 1;
        int alter = 0;
        int count = 0;
        k = k-1; //Why k = k-1? If there are 3 elements, then alter will be 2. So make k = k-1 instead of checking alter+1 == k every time.

        //Find the first occurrence of alternating group.
        while(alter < k && right < len2) {
            if(circleColors[right] != circleColors[right-1]) {
                alter++;
            }else {
                alter = 0;
                left = right;
            }
            right++;
        }

        //If found, increase count and decrease alter count(because we need to move on to next element).
        if(alter == k) {
            count++;
            alter--;
        }

        int begin = len+left; //This keeps track of the first occurrence of alternating group in the circular array so we don't duplicate count.
        left++; //Why do left++? Because we already did a right++ at the end of previous loop so we have +1 than necessary length.

        //Loop ends when we come back to first occurrence(importance of keeping track of left and begin).
        while(right < len2 && left < begin) {
            if(circleColors[right] != circleColors[right-1]) {
                alter++;
            }else {
                alter = 0;
                left = right;
            }
            right++;
            if(alter == k) {
                count++;
                left++;
                alter--;
            }
        }

        return count;
    }
}
