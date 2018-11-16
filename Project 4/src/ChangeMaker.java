/*Project 4
 *November 16, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 *
 * In this project we are implemening a a change maker where the given set
 * of coin demominations {d1, d2, ..., dk} and amount n, make change for n 
 * using the min # of coins. No limit to # of coins of any di. 
 */

//hello there
//k is number of coin denominations options

import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class ChangeMaker {

    public static void main(String[] args) {

        //CANT REDFINE IT - NO "NEW" OBJECT
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the number of coin-denominations and the set of coin values: ");

        int num_dem = keyboard.nextInt();

        int set_coin;
        int d[] = new int [];
        int n = 1;

        for(int i = num_dem-1; i == 0; i--){
            set_coin = keyboard.nextInt();
            d[i] = set_coin;
        }


        while(n != 0) {

            do {
                System.out.println("Enter a positive amount to be changed (enter 0 to quit): ");
                n = keyboard.nextInt();
            } while(n > 0);
            change_DP(n, d);
        }
        //check how to exit or what to do when negative

        System.out.println(n);


        System.out.println("Thanks for playing. Good Bye.");
        System.exit(0);


    }

    /* change_DPmmethod should implement a dynamic programming 
    algorithm(Bottom-up method)*/
    //receives the n amount and the d-array
    public static int[] change_DP(int n, int []d){
    	// needs the n amount and the d-array of coin values
    	// will be bottom up method

    	//array C[0...n] to hold cj vals
    	//goal get C[n]
        int [] C = new int[n-1];

        // A[j] info saved when computing C[j] (choice that was made)
        // save the i value representing the d[i] coin that was computing the min of all C[j -d[i]]
        // save the index
        int [] A = new int[n-1];

        //for going through d array
        for(int i = 0; i < n; i++){

        	// going to go thrugh and fill C for size n
        	// j <= n
            C[0] = 0;

        	for(int j = 0; j < n; j++){
        		C[j] = findOptiamalSol(i, j, d, C, A);
        	}
        }

        return C;
        /*returns an array containing the count of coins for 
        each di-denomination to be used when making change for the given 
        n amount*/
    }

    // finding optimal sol recursivly and solving for C[j]
    private static int findOptiamalSol(int i, int j, int []d, int [] C, int [] A){
    	// declares a minimum val for the loop below so it always saves min
        int minimum = 1;

        //
        // declares k by the size of d the size is = to the num of coin deminations
        int k = d.length;

        //if(j != 0 && j > 0){
    		if(j >= d[i]) {

                // CHECK THIS AREA
                if (i >= 1) {
                    for (int z = i; z <= k; z++) {
                        //compares the last minimum and then stores
                        //  whatever is smallest
                        minimum = Math.min(C[j - d[i]], minimum);
                    }
                }

                A[j] = i;

                //return 1 + Math.min(C[j-d[i]]);
                return 1 + minimum;
            }

    		else{
    			// if j<di, then the C[j-di] can’t be the min value and 
    			//thus shouldn’t be compared/checked
                // CHECK THIS
                // should it return 1 if it isn't going to be compared?
    			return 1;
    		}
    	//}

    	// we return 0 because if the if statement does not get executed,
    	// it usually means it is zero
    	return 0;
    }

}
