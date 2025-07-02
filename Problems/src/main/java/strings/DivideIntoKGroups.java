package strings;

public class DivideIntoKGroups {
    /*
    https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
     */

    public String[] divideString(String s, int k, char fill) {
        int len = s.length();
        int arraySize = len/k;
        if(len%k > 0) {
            arraySize++;
        }

        String[] result = new String[arraySize];
        int pos = 0;
        for(int i = 0; i < len; i+=k) {
            result[pos++] = s.substring(i, Math.min(len, i+k));
        }

        //If last element is less than k, fill it.
        if(result[arraySize-1].length() < k) {
            StringBuilder adj = new StringBuilder(result[arraySize-1]);
            int adjLen = result[arraySize-1].length();
            while(adjLen < k) {
                adj.append(fill);
                adjLen++;
            }
            result[arraySize-1] = adj.toString();
        }
        return result;
    }
}
