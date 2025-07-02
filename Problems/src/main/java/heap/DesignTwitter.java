package heap;

import java.util.*;

public class DesignTwitter {
    /*
    https://leetcode.com/problems/design-twitter/description/
     */

    class Twitter {
        /*
        Complex problem.
        follows contains set of all users this user follows. userTweets contains list of tweets by a particular user.
        We can make tweet an arraylist or linkedlist. Reason for using linkedlist is given in getNewsFeed.
        */
        int overallTime;
        Map<Integer, Tweet> userTweets;
        Map<Integer, Set<Integer>> follows;
        public Twitter() {
            overallTime = 0;
            userTweets = new HashMap<Integer, Tweet>();
            follows = new HashMap<Integer, Set<Integer>>();
        }

        /*
        For every user, we keep track of the latest tweet.
        When we get a new tweet, we get the previous tweet and make it the newTweet's previous tweet.
        This is where using linkedlist becomes useful.
        */
        public void postTweet(int userId, int tweetId) {
            Tweet newTweet = new Tweet(overallTime++, tweetId);
            if(userTweets.containsKey(userId)) {
                newTweet.prev = userTweets.get(userId);
            }
            userTweets.put(userId, newTweet);
        }

        /*
        During getNewsFeed, we make the user follow himself.
        */
        public List<Integer> getNewsFeed(int userId) {
            if(!follows.containsKey(userId)) {
                follows.put(userId, new HashSet<Integer>());
            }
            follows.get(userId).add(userId);


            //Get all the latest tweets by the all followees and store in the queue. All tweets are stored in desc order of time.
            PriorityQueue<Tweet> allTweets = new PriorityQueue<Tweet>((a, b) -> b.time - a.time);
            for(int followee : follows.get(userId)) {
                if(userTweets.containsKey(followee)) {
                    allTweets.offer(userTweets.get(followee));
                }
            }

            List<Integer> latestTweets = new ArrayList<Integer>();
            int count = 0;
        /*
        We loop through the tweets, pop(poll) the latest tweet, add it to the final list we have to send back.
        We get the previous tweet from the popped tweet, add it to the queue, so that tweet is ordered in the queue.
        */
            while(!allTweets.isEmpty() && count < 10) {
                Tweet tweet = allTweets.poll();
                latestTweets.add(tweet.tweetId);
                if(tweet.prev != null) { //Always check. Current tweet might not have a previous tweet.
                    allTweets.offer(tweet.prev);
                }
                count++;
            }

            return latestTweets;
        }

        public void follow(int followerId, int followeeId) {
            if(!follows.containsKey(followerId)) {
                follows.put(followerId, new HashSet<Integer>());
            }
            follows.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if(follows.containsKey(followerId)) {
                follows.get(followerId).remove(followeeId);
            }
        }

        class Tweet {
            int time;
            int tweetId;
            Tweet prev;

            public Tweet(int time, int tweetId) {
                this.time = time;
                this.tweetId = tweetId;
            }
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
}
