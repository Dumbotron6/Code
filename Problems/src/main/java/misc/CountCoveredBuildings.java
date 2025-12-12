package misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountCoveredBuildings {
    /*
    https://leetcode.com/problems/count-covered-buildings/
     */

    /*
    For all rows, we store the minimum and maximum y in that row.
    For all columns, we store the minimum and maximum x in the column.
    Now when taking a point, if the x lies between min x and max x for that y,
        and y lies between min y and max y for that x, it means it is covered from all 4 sides.
    */
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, MinMax> xMinMax = new HashMap<Integer, MinMax>(); //Min and max y for each x.
        Map<Integer, MinMax> yMinMax = new HashMap<Integer, MinMax>(); //Min and max x for each y.

        for(int i = 0; i < buildings.length; i++) {
            int x = buildings[i][0];
            int y = buildings[i][1];
            if(xMinMax.containsKey(x)) { //Store min and max y for that row.
                MinMax pos = xMinMax.get(x);
                pos.min = Math.min(y, pos.min);
                pos.max = Math.max(y, pos.max);
            }else {
                xMinMax.put(x, new MinMax(y));
            }

            if(yMinMax.containsKey(y)) { //Store min and max x for that column.
                MinMax pos = yMinMax.get(y);
                pos.min = Math.min(x, pos.min);
                pos.max = Math.max(x, pos.max);
            }else {
                yMinMax.put(y, new MinMax(x));
            }
        }

        int total = 0;

        for(int i = 0; i < buildings.length; i++) {
            int x = buildings[i][0];
            int y = buildings[i][1];

            MinMax posx = xMinMax.get(x);
            MinMax posy = yMinMax.get(y);

            //Check if point lies between min and max rows and min and max columns (for that y and that x).
            if(x > posy.min && x < posy.max && y > posx.min && y < posx.max) {
                total++;
            }
        }

        return total;
    }

    class MinMax {
        int min;
        int max;

        public MinMax(int val) {
            this.min = val;
            this.max = val;
        }
    }

    /*
    For example, [0,0,0]
                 [1,1,1]
                 [0,0,0]
        Taking points like this, and taking the building on [1,1], we have posx.min(min y) as 0 and posx.max(max y) as 2.
        So it's covered on left and right. But we have posy.min(min x) as 1 and posy.max(min x) as 1,
            so it's not covered on up and down.
    */

    //Same approach but avoids using hashmap. Revise this. Code taken from leetcode solutions.
    public int countCoveredBuildingsOptimal(int n, int[][] buildings) {
        int[] maxRow= new int[n+1];
        int[] minRow= new int[n+1];
        int[] maxCol= new int[n+1];
        int[] minCol= new int[n+1];

        Arrays.fill(minRow, n+1);
        Arrays.fill(minCol, n+1);

        for(int[] p: buildings){
            int x= p[0];
            int y= p[1];

            maxRow[y]= Math.max(maxRow[y], x);
            minRow[y]= Math.min(minRow[y], x);
            maxCol[x]= Math.max(maxCol[x], y);
            minCol[x]= Math.min(minCol[x], y);
        }

        int res=0;
        for(int[] p: buildings){
            int x= p[0];
            int y= p[1];

            if(x> minRow[y] && x< maxRow[y] && y> minCol[x] && y< maxCol[x]) res++;
        }
        return res;
    }
}
