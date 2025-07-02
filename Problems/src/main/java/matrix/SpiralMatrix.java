package matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    /*
    https://leetcode.com/problems/spiral-matrix/description/
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length-1;
        List<Integer> retArray = new ArrayList<Integer>();

        while(rowBegin <= rowEnd && colBegin <= colEnd) {
            for(int i = colBegin; i <= colEnd; i++) {
                retArray.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            for(int i = rowBegin; i <= rowEnd; i++) {
                retArray.add(matrix[i][colEnd]);
            }
            colEnd--;

            if(rowBegin <= rowEnd) {
                for(int i = colEnd; i >= colBegin; i--) {
                    retArray.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if(colBegin <= colEnd) {
                for(int i = rowEnd; i >= rowBegin; i--) {
                    retArray.add(matrix[i][colBegin]);
                }
            }
            colBegin++;

        }

        return retArray;
    }
}
