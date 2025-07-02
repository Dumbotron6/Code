package greedy;

public class GasStation {
    /*
    https://leetcode.com/problems/gas-station/
     */

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int begin = 0;
        int balance = 0;
        int ptr = begin;

        /*
        Why loopLen = len*2 and actPtr = ptr%len?
        This problem asks if the car can cover one circle. So it's a circular array problem.
        So we can hav a gasCircle[len*2] and costCircle[len*2] but not doing so makes us have O(1) space instead of O(N) space.
        Which is why we use actPtr.
        If at any point balance(the balance fuel) is less than 0, then no begin point before that will be correct.
        So we go to that point+1 and check.
        */
        while(begin < len && (ptr-begin) < len) {
            int actPtr = ptr%len;
            balance += gas[actPtr]-cost[actPtr];
            if(balance < 0) {
                begin = ptr+1;
                balance = 0;
            }
            ptr++;
        }

        return begin < len ? begin : -1;
    }

    /*
    Alternatively, we can calculate the total gas and cost beforehand. This will let us eliminate actPtr and
        (ptr-begin) < len check altogether.  We can only query till len and not go circular.
    But this is moot as both take O(2N).
     */
}
