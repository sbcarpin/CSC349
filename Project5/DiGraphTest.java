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
        
        System.out.println("Enter number of vertices: ");
        int num_vert = keyboard.nextInt();
        //input the number of vertices and define an object of DiGraph class.
        
        DiGraph diobject = new DiGraph(num_vert);
        
        System.out.println("Choose one of the following operations: ");
        System.out.println("- add edge (enter a)");
        System.out.println("- delete edge (enter d)");
        System.out.println("- edge count (enter e)");
        System.out.println("- vertex count (enter v)");
        System.out.println("- print graph (enter p)");
        System.out.println("- Topological sort (enter t)"); // PART 2
        System.out.println("- Quit (enter q)");
        int num1, num2;
        
        //as long as the user does not enter "q" for quit keeo doing the below
        while(true) {
            char input = keyboard.next().charAt(0);
            String i = keyboard.nextLine();
            //char input = 'x';
            
            System.out.println("String length: " + i.length());
            if(i.length() != 0){
                input = 'x'; // to go to default
            }
            
            switch(input){
                case 'q':
                    System.out.println("Quiting... Goodbye.");
                    System.exit(0);
                case 'a':
                    System.out.println("Enter two numbers please: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    System.out.println("first num: " + num1 + " " + "second num: " + num2);
                    //DiGraph.addEdge(num1, num2); // why it no work with static.. whyy
                    break;
                case 'd':
                    System.out.println("Enter two numbers please: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    System.out.println("first num: " + num1 + " " + "second num: " + num2);
                    diobject.deleteEdge(num1, num2); // why it no work with static.. whyy
                    break;
                case 'e':
                    System.out.println("Number of edges is: ");
                    diobject.edgeCount();
                    break;
                case 'v':
                    System.out.println("Number of vertices is: ");
                    diobject.vertexCount();
                    break;
                case 'p':
                    System.out.println("The graph is the following: ");
                    diobject.print();
                    break;
                case 't':
                    System.out.println("The graph is the following: ");
                    //diobject.topSort();
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again.");
                    break;
            }
            System.out.println("Choose one of the operations: ");
        }
    }
}

