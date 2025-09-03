package greedy;

public class PartitionIntoSubstringsAtMostK {
    /*
    https://leetcode.com/problems/partition-string-into-substrings-with-values-at-most-k/description/
     */

    public int minimumPartition(String s, int k) {
        String kStr = ""+k;
        int len = kStr.length();
        int subStrings = 0;
        int start = 0;

        /*
        We can check integers the length of k(say 4) - the first if condition.
        If it is satisfied, check the next substring which starts at start+len. So increase start.
        If not, an integer of length 3 will always be lesser than integer of length 4.
            That is what the last else condition does. We increase by len-1 instead of len.
        */
        while(start+len <= s.length()) {
            String str = s.substring(start, start+len);
            if(str.compareTo(kStr) <= 0) {
                start += len;
            }else if(len == 1){
                return -1;
            }else {
                start += len-1;
            }
            subStrings++;
        }

        if(start < s.length()) {
            subStrings++;
        }

        return subStrings;
    }

    public int minimumPartitionAlt(String s, int k) {
        int n = s.length();
        int count = 0;
        long num = 0;

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            if (digit > k) {
                // Single digit itself > k → impossible
                return -1;
            }

            num = num * 10 + digit;

            if (num > k) {
                count++;       // finalize previous partition
                num = digit;   // start new partition from current digit
            }
        }

        if (num > 0) count++; // count the last partition
        return count;
    }
}
