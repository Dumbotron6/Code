package misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationCity {
    /*
    https://leetcode.com/problems/destination-city/
     */

    public String destCity(List<List<String>> paths) {
        Map<String, String> tracker = new HashMap<String, String>();

        for(List<String> cities : paths) {
            tracker.put(cities.get(0), cities.get(1));
        }

        String city = paths.get(0).get(0);

        while(tracker.containsKey(city)) {
            city = tracker.get(city);
        }

        return city;
    }
}
