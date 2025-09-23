package strings;

public class CompareVersionNumbers {
    /*
    https://leetcode.com/problems/compare-version-numbers/
     */

    /*
    All leading zeroes do not matter. 1.01 is the same as 1.001 while 1.20 is higher than 1.2.
    */
    public int compareVersion(String version1, String version2) {
        String[] ver1Array = version1.split("\\.");
        String[] ver2Array = version2.split("\\.");

        int i = 0, j = 0;
        while(i < ver1Array.length && j < ver2Array.length) {
            int val1 = Integer.parseInt(ver1Array[i]); //Eliminates all leading zeroes.
            int val2 = Integer.parseInt(ver2Array[j]);
            if(val1 < val2) { //version 1 is lower.
                return -1;
            }else if(val1 > val2) { //version 2 is lower.
                return 1;
            }
            i++;
            j++;
        }

        //For comparing 1.0 and 1.0.0.0(both are same) or 1.0 and 1.0.0.06(second is higher.)
        while(i < ver1Array.length) {
            int val = Integer.parseInt(ver1Array[i]);
            if(val > 0) {
                return 1;
            }
            i++;
        }
        while(j < ver2Array.length) {
            int val = Integer.parseInt(ver2Array[j]);
            if(val > 0) {
                return -1;
            }
            j++;
        }

        return 0;
    }
    /*
    NOTE: If we don't want to use Integer.parseInt(), below solution can be used which still eliminates leading zeroes.
    val = val * 10 + (ver1Array[i].charAt(k) - '0'); where  is used to iterate char within ver1Array[i].
     */
}
