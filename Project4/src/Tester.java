/*Project 4
 *November 16, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

public class Tester {
    public static void main(String[] args) {
        int[] dp, greedy;
        int matches;
        int set = 0;

        System.out.println("Testing change_DP and change_greedy algorithms");
        for (int i = 0; i < 5; i++) {
            //increment set and reinitialize matches
            set++;
            matches = 0;
            for (int j = 0; j <= 200; j++) { //amount of tests
                //US denominations
                if(set == 1)
                {
                    int[] d = { 100, 50, 25, 10, 5, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j +1, d);
                    matches = compare(set, matches, dp, greedy, j);
                }
                //soviet denominations
                if(set == 2)
                {
                    int[] d = { 100, 50, 20, 15, 10, 5, 3, 2, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(set, matches, dp, greedy, j);
                }
                //Powers of 2
                if(set == 3)
                {
                    int[] d = { 64, 32, 16, 8, 4, 2, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(set, matches, dp, greedy, j);
                }
                //Us without the nickel
                if(set == 4)
                {
                    int[] d = { 100, 50, 25, 10, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(set, matches, dp, greedy, j);
                }
                //Some set
                if(set == 5)
                {
                    int[] d = { 66, 35, 27, 18, 10, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(set, matches, dp, greedy, j);
                }
            }
        }
    }
    private static int compare( int set, int matches, int[] dp, int[] greedy, int tests){
        boolean check = true;
        for(int i = 0; i < dp.length; i++)      //check each index of the arrays
        {
            if(dp[i] != greedy[i])
                check = false;
        }
        //increment if arrays are identical
        if(check) {
            matches++;
        }
        //output result when we reach 200 tests
        if(tests == 200) {
            System.out.println("Testing set " + set + ": " + (matches - 1) + " matches in 200 tests");
        }
        return matches;
    }
}
