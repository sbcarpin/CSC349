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
            for (int j = 0; j <= 200; j++) {
                if(set == 1)
                {
                    int[] d = { 100, 50, 25, 10, 5, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(dp, greedy, j, matches, set);
                }
                if(set == 2)
                {
                    int[] d = { 100, 50, 20, 15, 10, 5, 3, 2, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(dp, greedy, j, matches, set);
                }
                if(set == 3)
                {
                    int[] d = { 64, 32, 16, 8, 4, 2, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(dp, greedy, j, matches, set);
                }
                if(set == 4)
                {
                    int[] d = { 100, 50, 25, 10, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(dp, greedy, j, matches, set);
                }
                if(set == 5)
                {
                    int[] d = { 66, 35, 27, 18, 10, 1 };
                    dp = ChangeMaker.change_DP(j + 1, d);
                    greedy = ChangeMaker.change_greedy(j + 1, d);
                    matches = compare(dp, greedy, j, matches, set);
                }
            }
        }
    }
    private static int compare(int[] dp, int[] greedy, int Tests, int Matches, int set){
        

    }



}
