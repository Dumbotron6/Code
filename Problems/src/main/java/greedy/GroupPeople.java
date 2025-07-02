package greedy;

import java.util.*;

public class GroupPeople {
    /*
    https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
     */

    /*
    We group people of same group sizes and then split them into individual groups.
    Better solution below.
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> uniqueGroups = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> groupings = new ArrayList<List<Integer>>();

        for(int i = 0; i < groupSizes.length; i++) { //Group all members of same group size together.
            if(!uniqueGroups.containsKey(groupSizes[i])) {
                uniqueGroups.put(groupSizes[i], new ArrayList<Integer>());
            }
            uniqueGroups.get(groupSizes[i]).add(i);
        }

        for(Map.Entry<Integer, List<Integer>> entry : uniqueGroups.entrySet()) { //Put each group member into group of their size.
            int size = entry.getKey();
            List<Integer> members = entry.getValue();
            for(int i = 0; i < members.size(); i++) { //When i exceeds group size, create new group.
                if(i%size == 0) {
                    groupings.add(new ArrayList<Integer>());
                }
                groupings.getLast().add(members.get(i));
            }
        }

        return groupings;
    }

    /*
    Building on previous solution, instead of grouping and then splitting,
        we split while building.
     */
    public List<List<Integer>> groupThePeopleAlt(int[] groupSizes) {
        Map<Integer, List<Integer>> uniqueGroups = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> groupings = new ArrayList<List<Integer>>();

        for(int i = 0; i < groupSizes.length; i++) {
            addToGroup(groupSizes[i], uniqueGroups, groupings, i);
        }

        return groupings;
    }

    public void addToGroup(int size, Map<Integer, List<Integer>> uniqueGroups, List<List<Integer>> groupings, int pos) {
        if(!uniqueGroups.containsKey(size)) {
            uniqueGroups.put(size, new ArrayList<Integer>());
        }

        uniqueGroups.get(size).add(pos); //Add to group that is being built.

        if(uniqueGroups.get(size).size() == size) { //When a group size is reached, add it to final result.
            groupings.add(uniqueGroups.get(size));
            uniqueGroups.remove(size);
        }
    }
}
