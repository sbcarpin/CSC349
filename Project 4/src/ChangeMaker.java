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

//k is number of coin denominations options

import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class ChangeMaker {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the number of coin-denominations and the set of coin values (in decreasing order): ");
        int k = keyboard.nextInt();
        int d[] = new int [k];
        
        for(int i = 0; i < k; i++){
            d[i] = keyboard.nextInt();
        }
        
        System.out.println("Enter a positive amount to be changed (enter 0 to quit): ");
        int n = keyboard.nextInt();
        if(n==0) {
            System.out.println("Thanks for playing. Good Bye.");
            System.exit(0);
        }
        else if(n > 0) {
            System.out.println("DP algorithm results");
            
            int[] x = change_DP(n, d);
            printOut(n, x, d, k);
            
            System.out.println("Greedy algorithm results");
            int [] y = change_greedy(n,d);
            printOut(n, y, d, k);
        }
        //check how to exit or what to do when negative
    }
    
    private static void printOut(int n, int a [], int d [], int k){
        System.out.println("Amount: " + n);
        int count = 0;
        int print = 0;
        
        System.out.print("Optimal distribution: ");
        for(int i = 0; i < a.length; i++){
            if(a[i] > 0) {
                System.out.print(a[i] + "*" + d[i] + "c");
                print += a[i];
                if(i != k-1) {
                    //if(print != count && a[i] > 0 && (i != a.length - 1)){
                    System.out.print(" + ");
                }
            }
        }
        for(int i = 0; i < a.length; i++) {
            count += a[i];
        }
        System.out.println("\nOptimal coin count: " + count);
    }
    
    public static int[] change_DP(int n, int []d){
        int [] C = new int[n+1];
        int [] A = new int[n+1];
        int k = d.length;
        int [] B = new int[k];
        C[0] = 0;
        
        for(int j = 1; j < n + 1; j++){
            int min = Integer.MAX_VALUE; //min will always be greater first
            int i_val = -1;
            
            for(int i = 0; i < k; i++){
                if(j >= d[i]) {
                    if(min > C[j - d[i]]){
                        min = (C[j - d[i]]);
                        i_val = i;
                    }
                }
            }
            A[j] = i_val;
            C[j] = min + 1;
        }
        
        while(n > 0){
            B[A[n]] += 1;
            n = n - d[A[n]];
        }
        
        return B;
    }
    
    public static int[] change_greedy(int n, int[] d) {//needs n-amount and the d-array of coin-values
        int[] C = new int[n + 1];
        //for going through d array
        /*Find Highest Demno*/
        int j = 0;
        while (n > 0) {
            // if n can hold that value
            if (d[j] <= n) {
                // subtracts amount from d[j]
                n -= d[j];
                //A[j] += j;
                C[j] += 1;
            } else {
                j++;
            }
        }
        return C;
    }
}


