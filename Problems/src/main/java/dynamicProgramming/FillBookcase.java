package dynamicProgramming;

public class FillBookcase {
    /*
    https://leetcode.com/problems/filling-bookcase-shelves/
     */

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int len = books.length;
        int[] minHeight = new int[len];

        return calcHeight(books, shelfWidth, minHeight, 0, len);
    }

    public int calcHeight(int[][] books, int shelfWidth, int[] minHeight, int ptr, int len) {
        if(ptr >= len) {
            return 0;
        }

        if(minHeight[ptr] != 0) { //Min height if new shelf starts from her - already calculated.
            return minHeight[ptr];
        }

        int width = 0;
        int maxHeight = 0;
        int totalHeight = Integer.MAX_VALUE;
        int i = ptr;

        /*
        How we check is, [1] in one shelf, [2,3,4] in another shelf.
        In the next loop [1,2] in one shelf, [3,4] in another shelf.
        Among the two, we check which gives less total height.
        totalHeight at any time will have minimum height of all shelf combinations checked so far.
        Each calcHeight call actually returns the totalHeight if a new shelf is started from the i+1.
        */
        while(i < len) {
            width +=  books[i][0];
            if(width <= shelfWidth) { //Only check till width can be accommodated.
                maxHeight = Math.max(maxHeight, books[i][1]); //Max height of all books in current shelf.
                totalHeight = Math.min(totalHeight, maxHeight+calcHeight(books, shelfWidth, minHeight, i+1, len));
            }else {
                /*
                Height so far + Height after this(subsequent books are in next shelf), also applicable to above line.
                Only difference is we took current book in previous line in same shelf, in below line,
                    we push current book to next shelf. Hence, i+1 and i.
                */
                totalHeight = Math.min(totalHeight, maxHeight+calcHeight(books, shelfWidth, minHeight, i, len));
                break;
            }
            i++;
        }
        minHeight[ptr] = totalHeight;

        return minHeight[ptr];
    }
}
