/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

// ----https://www.geeksforgeeks.org/graph-and-its-representations/
// Good Example: http://www.cs.cornell.edu/courses/cs211/2006fa/Lectures/L22-More%20Graphs/Digraph.java

import java.util.Scanner;
import java.io.*;

//a directed graph as an array of Adjacency Linked Lists. 
public class DiGraph {
	// not sure if we can have this
	int N; 

	//One private instance variable: this is an array of linked lists (use Java’s LinkedListclass).
	private LinkedList<Integer>[] vertex;

	
	// A constructor with one int type parameter for N. This constructor creates and
	// initializes the instance variable-array
	new DiGraph(int N){
	vertex = new LinkedList[N-1];

		// Create a new list for each vertex 
        // such that adjacent nodes can be stored 
  		for(int i = 0; i < N ; i++){ 
                vertex[i] = new LinkedList<>(); 
    	} 
	}

	//two parameters identify vertices representing the edge that needs to be added to the graph
	// (to vertex is added as from vertex’s neighbor).
	public addEdge(int from, int to){

		//the edge should not be added if it already exists: needs to be checked before adding
		//if already exislts - do not add
		if(){
			vertex[src].add(dest); 
			vertex[from].add(to);
		}


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

    //https://www.geeksforgeeks.org/count-number-edges-undirected-graph/
    // computes and returns edges of graph
    public int edgeCount(){
	    int edge_num = 0;

	    for(int i = 0; i < V; i++){
	    	sum += vertex[i].size;
	    }
	   	// since it will transverse each edge twice
	    return edge_num/2;
    }


    // returns number of vetices (its the arrays length)
    public int vertexCount(){
        int num_vert;

        return num_vert;
    }

//outputs the graph in the format provide din handout
    public print(){
	    int num_vertix;

	    for(int i =0; i <= num_vertix; i++){
        System.out.println((i+1) + " is connected to: " );
        //outputs a line: i is connected to: x1, ..., xk
        //where x1,..., xk are vertices that are adjacent to i.
        }
    }

}