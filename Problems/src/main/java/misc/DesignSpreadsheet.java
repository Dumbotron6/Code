package misc;

public class DesignSpreadsheet {
    /*
    https://leetcode.com/problems/design-spreadsheet/
     */

    class Spreadsheet {

        int[][] table;

        public Spreadsheet(int rows) {
            table = new int[rows+1][26];
        }

        public void setCell(String cell, int value) {
            int col = cell.charAt(0)-'A';
            int row = Integer.parseInt(cell.substring(1));
            if(row < table.length && col < table[0].length) {
                table[row][col] = value;
            }
        }

        public void resetCell(String cell) {
            setCell(cell, 0);
        }

        public int getValue(String formula) {
            String form = formula.substring(1);
            String[] values = form.split("\\+");
            return getIntVal(values[0])+getIntVal(values[1]);
        }

        int getIntVal(String str) {
            char c = str.charAt(0);
            if(c >= 'A' && c <= 'Z') {
                int col = str.charAt(0)-'A';
                int row = Integer.parseInt(str.substring(1));
                if(row < table.length && col < table[0].length) {
                    return table[row][col];
                }
                return 0;
            }
            return Integer.parseInt(str);
        }
    }
}
