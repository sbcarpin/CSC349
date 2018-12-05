/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

import java.util.Scanner;
import java.util.*;
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
        System.out.println("- BFS, is there path (enter i)"); // PART 3
        System.out.println("- BFS, length of path (enter l)");
        System.out.println("- BFS, shortest path (enter s)");
        System.out.println("- Quit (enter q)");
        int num1, num2;
        
        
        //as long as the user does not enter "q" for quit keeo doing the below
        while (true) {
            char input = keyboard.next().charAt(0);
            String i = keyboard.nextLine();
            
            //System.out.println("String length: " + i.length());
            if (i.length() != 0) {
                input = 'x'; // to go to default
            }
            
            switch (input) {
                case 'q':
                    System.out.println("Goodbye.");
                    System.exit(0);
                case 'a':
                    System.out.println("Enter two numbers please: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    diobject.addEdge(num1, num2);
                    System.out.println("(" + (num1) + "," + (num2) + ")" + " edge is now added to the graph");
                    break;
                case 'd':
                    System.out.println("Enter two numbers please: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    diobject.deleteEdge(num1, num2);
                    break;
                case 'e':
                    System.out.print("Number of edges is ");
                    System.out.print(diobject.edgeCount() + "\n");
                    break;
                case 'v':
                    System.out.print("Number of vertices is ");
                    System.out.print(diobject.vertexCount() + "\n");
                    break;
                case 'p':
                    System.out.println("The graph is the following: ");
                    diobject.print();
                    break;
                    // PART 2
                case 't':
                    try {
                        System.out.println("The graph is the following: ");
                        int arr2[] = diobject.topSort();
                        int x;
                        for (x = 0; x < arr2.length-1; x++) {
                            System.out.print(arr2[x] + ",");
                        }
                        System.out.print(arr2[x]);
                        System.out.print("\n");
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    //PART 3
                case 'i':
                    System.out.println("Enter two vertex numbers: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    if(diobject.isTherePath(num1, num2)){
                        System.out.println("There is a path");
                    }
                    else{
                        System.out.println("There is no path");
                    }
                    break;
                case 'l':
                    System.out.println("Enter two vertex numbers: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    System.out.print("The length of the shortest path is: ");
                    System.out.print(diobject.lengthOfPath(num1, num2)+ "\n");
                    break;
                case 's':
                    System.out.println("Enter two vertex numbers: ");
                    num1 = keyboard.nextInt();
                    num2 = keyboard.nextInt();
                    System.out.print("The shortest path is: ");
                    diobject.printPath(num1, num2);
                    break;
                    //PART 4
                case 'b':
                    System.out.print("The breadth-first-tree is: ");
                    diobject.printTree();
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again.");
                    break;
            }
            System.out.println("Choose one of the operations: ");
        }
    }
}

