package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeKeyValue {
    /*
    https://leetcode.com/problems/time-based-key-value-store/description/
     */

    class TimeMap {

        Map<String, List<Pair>> timeMap;

        public TimeMap() {
            timeMap = new HashMap<String, List<Pair>>();
        }

        public void set(String key, String value, int timestamp) {
            if(!timeMap.containsKey(key)) {
                timeMap.put(key, new ArrayList<Pair>());
            }
            timeMap.get(key).add(new Pair(timestamp, value));
        }

        /*
        Since the timestamp will be strictly ascending, it means the timestamps for a key will be sorted.
        Then it is simply a case of finding the value of the timestamp closest to the target
            which is what timestamp_prev <= timestamp (in the problem statement) means.
        So we can use binary search.
        */
        public String get(String key, int timestamp) {
            if(!timeMap.containsKey(key)) {
                return "";
            }
            List<Pair> valueList = timeMap.get(key);

            int left = 0, right = valueList.size()-1;

            while(left <= right) {
                int mid = left+(right-left)/2;

                if(valueList.get(mid).timestamp <= timestamp) {
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
            if(left == 0) { //This means there is no timestamp_prev <= timestamp, as left-1 in the return statement will give -1.
                return "";
            }
            return valueList.get(left-1).value;
        }

        class Pair {
            int timestamp;
            String value;

            public Pair(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }
    }
}
