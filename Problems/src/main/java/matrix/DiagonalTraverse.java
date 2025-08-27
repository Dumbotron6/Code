package matrix;

public class DiagonalTraverse {
    /*
    https://leetcode.com/problems/diagonal-traverse/description/
     */

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[m-1].length;
        int[] diagArray = new int[m*n];

        int pointerM = 0;
        int pointerN = 0;
        int up = 1;
        int i = 0;

        if(m > 1 && n > 1)
            for(i = 0; i < diagArray.length; i++) {
                diagArray[i] = mat[pointerM][pointerN];
                if(pointerM == m-1) { //At the last row
                    diagArray[++i] = mat[pointerM--][++pointerN];
                    ++pointerN; //Go to next column
                    up*=-1; //Change direction
                }else if(pointerN == n-1) { //At the last column
                    diagArray[++i] = mat[++pointerM][pointerN--];
                    ++pointerM;
                    up*=-1;
                }else
                if(pointerM == 0){ //At first row
                    if(up > 0)
                        diagArray[++i] = mat[pointerM++][pointerN+1];
                    else
                        diagArray[++i] = mat[pointerM++][pointerN-1];
                    up*=-1;
                } else if(pointerN == 0){ //At first column
                    if(up < 0)
                        diagArray[++i] = mat[pointerM+1][pointerN++];
                    else
                        diagArray[++i] = mat[pointerM-1][pointerN++];
                    up*=-1;
                } else
                if(up > 0) {
                    pointerM--;
                    pointerN++;
                }
                else {
                    pointerM++;
                    pointerN--;
                }
            }
        else if(m == 1) {
            for(pointerN = 0, i=0; pointerN < n; pointerN++)
                diagArray[i++] = mat[pointerM][pointerN];
        }else if(n == 1) {
            for(pointerM = 0, i=0; pointerM < m; pointerM++)
                diagArray[i++] = mat[pointerM][pointerN];
        }

        return diagArray;
    }
}
