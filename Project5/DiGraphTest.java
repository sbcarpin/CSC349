/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

import java.util.Scanner;
import java.io.*;

public class DiGraphTest {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean no_exit = true;

        System.out.println("Enter the number of vertices: ");
        int num_vert = keyboard.nextInt();
        //iInput the number of vertices and define an object of DiGraph class.

        System.out.println("Choose one of the following operations: ");
        System.out.println("- add edge (enter a)");
        System.out.println("- delete edge (enter d)");
        System.out.println("- edge count (enter e)");
        System.out.println("- vertex count (enter v)");
        System.out.println("- print graph (enter p)");
        System.out.println("- Quit (enter q)");


        //as long as the user does not enter "q" for quit keeo doing the below
        while(no_exit) {
            char input = keyboard.next();
            if(input = 'q'){
                System.out.println("Quiting... Goodbye.");
                System.exit(0);
            }

        }
    }



}
