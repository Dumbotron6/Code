package arrays;

public class StudentsLunch {
    /*
    https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/
     */

    public int countStudents(int[] students, int[] sandwiches) {
        int squareSt = 0;
        int circleSt = 0;

        for(int student : students) { //Count square and circle students.
            if(student == 0) {
                circleSt++;
            }else {
                squareSt++;
            }
        }

        for(int sandwich : sandwiches) {
            if(sandwich == 0) {
                if(circleSt > 0) { //If circle sandwich but not circleSt left, then all squareSt will be left waiting forever.
                    circleSt--;
                }else {
                    return squareSt;
                }
            }else {
                if(squareSt > 0) {  //If square sandwich but not squareSt left, then all circleSt will be left waiting forever.
                    squareSt--;
                }else {
                    return circleSt;
                }
            }
        }

        return 0;
    }
}
