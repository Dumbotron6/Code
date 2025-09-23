package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FoodRatingSystem {
    /*
    https://leetcode.com/problems/design-a-food-rating-system/
     */

    class FoodRatings {
        //A TreeSet is a BST. So by order of our sorting, the highest rated element will be at the top.
        Map<String, String> foodCuisine; //Food, [Cuisine, Rating];
        Map<String, Integer> foodToRating; //This is used to identify the rating of the food so that it can be used to search in the TreeSet.
        Map<String, TreeSet<FoodRate>> cuisineFood; //Cuisine, [Food, Rating];

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            foodCuisine = new HashMap<String, String>();
            foodToRating = new HashMap<String, Integer>();
            cuisineFood = new HashMap<String, TreeSet<FoodRate>>();

            for(int i = 0; i < foods.length; i++) {
                foodCuisine.put(foods[i], cuisines[i]);
                if(!cuisineFood.containsKey(cuisines[i])) {
                    TreeSet<FoodRate> bstSet = new TreeSet<FoodRate>((a,b) -> { //Sort by rating, if match, then food name.
                        if(a.rating == b.rating) {
                            return a.food.compareTo(b.food);
                        }else {
                            return b.rating-a.rating;
                        }
                    });
                    cuisineFood.put(cuisines[i], bstSet);
                }
                cuisineFood.get(cuisines[i]).add(new FoodRate(foods[i], ratings[i]));
                foodToRating.put(foods[i], ratings[i]);
            }
        }

        public void changeRating(String food, int newRating) {
            String cuisine = foodCuisine.get(food);
            TreeSet<FoodRate> bstSet = cuisineFood.get(cuisine);
            int rating = foodToRating.get(food);
            bstSet.remove(new FoodRate(food, rating)); //Find and remove the existing food and its rating.
            bstSet.add(new FoodRate(food, newRating)); //Add the food and it's new rating.
            foodToRating.put(food, newRating); //Change rating in the foodToRating tracker.
            //Both remove and add will take O(N) time.
        }

        //Get the top food from the TreeSet(which is a BST).
        public String highestRated(String cuisine) {
            return cuisineFood.get(cuisine).first().food;
        }

        class FoodRate {
            String food;
            int rating;

            public FoodRate(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }
        }
    }
}
