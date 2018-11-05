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

import java.util.Scanner;
import java.io.*;

public class ChangeMaker {

    public static void main(String[] args) {

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
        int [] A = new int[];


        return C;
        /*returns an array containing the count of coins for 
        each di-denomination to be used when making change for the given 
        n amount*/
    }

    private static void findOptiamalSol(){

    }

}
