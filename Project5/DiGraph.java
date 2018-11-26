/*Project 5
 *November 25, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

import java.util.Scanner;
import java.io.*;

//a directed graph as an array of Adjacency Linked Lists. 
public class DiGraph {

//One private instance variable: this is an array of linked lists (use Java’s LinkedListclass).
private LinkedList<Integer>[] vertex;

// https://stackoverflow.com/questions/20202889/how-can-i-create-an-array-of-linked-lists-in-java
// A constructor with one int type parameter for N. This constructor creates and 
// initializes the instance variable-array
new DiGraph(int N){
	vertex = new LinkedList[N-1];

	int i = 0, m = N;
	while(i!=m){
  		int temp = sc.nextInt();
  		int temp2 = sc.nextInt();

  		// Make sure the list is initialized before adding to it
  		if (vertex[temp] == null) {
     		vertex[temp] = new LinkedList<Integer>();
  		}

  		vertex[temp].add(temp2);
  		i++;
	}	
}

//two parameters identify vertices representing the edge that needs to be added to the graph
// (to vertex is added as from vertex’s neighbor).
public addEdge(int from, int to){

	//the edge should not be added if it already exists: needs to be checked before adding


	//vertex-numbers are given in natural numbering(starting with 1) so you should “turn
	// ”them to Java-indexing to reflect correct connection. No need for validity check


}

//two parameters identify vertices representing the edge that needs to be deleted from the graph
// (to vertex is removed from vertex’s neighbor).
public deleteEdge(int from, int to){
	//nothing done if edge does not exist (no error message)

	//vertex-numbers are given in natural numbering(starting with 1) so you should “turn
	// ”them to Java-indexing to reflect correct connection. No need for validity check

}

// computes and returns edges of graph
public edgeCount(){

}


// returns 
public vertexCount(){

}




}