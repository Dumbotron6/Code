package graph;

import java.util.*;

public class WordLadder {
    /*
    https://leetcode.com/problems/word-ladder/
     */

    /*
    We form an adjacency list with possible patterns. For example, h*t will map to both 'hot' and 'hit'. So for *ot, h*t, ho*,
        we will get all possible neighbours of 'hot'.
    We iterate through the neighbours, add them to queue, and perform a BFS to find the shortest path from beginWord to endWord.
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord); //beginWord is not present in worList.

        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        int length = beginWord.length();

        for(String word : wordList) { //Add to adjList using patterns.
            for(int i = 0; i < length; i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                if(!adjList.containsKey(pattern)) {
                    adjList.put(pattern, new ArrayList<String>());
                }
                adjList.get(pattern).add(word);
            }
        }

        Set<String> visited = new HashSet<String>();
        visited.add(beginWord);

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);

        int minPathLen = 0;

        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i = 0; i < len; i++) {
                String word = queue.poll();
                if(word.equals(endWord)) { //Found endWord.
                    return minPathLen+1;
                }

                for(int j = 0; j < length; j++) { //Iterate through all patters *ot, h*t and ho* to then iterate through neighbours.
                    String pattern = word.substring(0, j) + "*" + word.substring(j+1);
                    for(String nei : adjList.get(pattern)) {
                        if(!visited.contains(nei)) {
                            queue.offer(nei);
                            visited.add(nei);
                        }
                    }
                }
            }
            minPathLen++;
        }

        return 0;
    }

    //In this approach, we don't form adjList but instead check on the fly using all 26 characters if a neighbour exists.
    public int ladderLengthAlt(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0; // end word itself not in set
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) { // going level by level
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();

                for (int j = 0; j < charArray.length; j++) {
                    char temp = charArray[j]; // storing the value to reset back
                    for (char ch = 'a'; ch <= 'z'; ch++) { // try all letters in alphabets
                        if (temp == ch) {
                            continue;
                        }
                        charArray[j] = ch;
                        String newWord = String.valueOf(charArray);
                        if (set.contains(newWord)) {
                            if (newWord.equals(endWord)) { // if found return level
                                return level + 1;
                            }
                            queue.add(newWord);// else add to queue and continue
                            set.remove(newWord);// because you already reached this word, no need to see again
                        }
                    }
                    charArray[j] = temp;
                }
            }
            level++;
        }

        return 0;
    }

}
