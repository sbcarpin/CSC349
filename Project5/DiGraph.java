/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

// ----https://www.geeksforgeeks.org/graph-and-its-representations/
// Good Example: http://www.cs.cornell.edu/courses/cs211/2006fa/Lectures/L22-More%20Graphs/Digraph.java

import java.util.LinkedList;
import java.io.*;
import java.util.*;

//a directed graph as an array of Adjacency Linked Lists.
public class DiGraph {
    
    //One private instance variable: this is an array of linked lists (use Java’s LinkedListclass).
    private LinkedList<Integer>[] arr;
    
    // A constructor with one int type parameter for N. creates and initializes the instance variable-array
    DiGraph(int N) {
        arr = (LinkedList<Integer>[]) new LinkedList[N];
        
        // Create a new list for each vertex such that adjacent nodes can be stored
        for (int i = 0; i < N; i++) {
            arr[i] = new LinkedList<>();
        }
    }
    
    //two parameters identify vertices representing the edge that needs to be added to the graph
    // (to vertex is added as from vertex’s neighbor).
    public void addEdge(int from, int to) {
        from -= 1;
        to -= 1;
        //the edge should not be added if it already exists
        if (!arr[from].contains(to)) {
            arr[from].add(to);
        }
        System.out.println("(" + (from + 1) + "," + (to + 1) + ")" + " edge is now added to the graph");
        //*** if it does exist do we output error message ****
    }
    
    //two parameters identify vertices representing the edge that needs to be deleted from the graph
    // (to vertex is removed from vertex’s neighbor).
    public void deleteEdge(int from, int to) {
        int N = arr.length;
        from -= 1;
        to -= 1;
        
        //nothing done if edge does not exist (no error message)
        if(N > to || N > from){
            if(arr[from].contains(to)){
                arr[from].remove(to);
            }
        }
    }
    
    //vertex-numbers are given in natural numbering(starting with 1) so you should “turn
    // ”them to Java-indexing to reflect correct connection. No need for validity check
    
    
    //https://www.geeksforgeeks.org/count-number-edges-undirected-graph/
    // computes and returns edges of graph
    public int edgeCount() {
        int edge_num = 0;
        
        for (int i = 0; i < arr.length; i++) {
            edge_num += arr[i].size();
        }
        // since it will transverse each edge twice
        return edge_num;
    }
    
    
    // returns number of vetices (its the arrays length)
    public int vertexCount() {
        return arr.length;
    }
    
    //outputs the graph in the format provide din handout
    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + " is connected to: ");
            for(int j = 0; j < arr[i].size(); j++) {
                System.out.print((arr[i].get(j) + 1));
                if(arr[i].size() > 1 && j != (arr[i].size() - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.print("\n");
        }
    }
    
    
    // ******---- PART 2 ------******
    // CHECK THIS OUT
    //https://www.geeksforgeeks.org/topological-sorting/
    
    //include the implementation of the Topological Sort
    //algorithm including a supporting routine for computing vertex indegrees.
    
    //returns an array of integers representing the indegrees of all vertices in the graph
    //the i-th integer in the resulting array is the indegree of the i-th vertex.
    private int[] indegrees() {
        int N = arr.length;
        int[] indegrees = new int [N];
        for(int i = 0; i < N; i++){
            indegrees[i] = 0;
        }
        
        for(int u = 0; u < N; u++){
            for(int z = 0; z < arr[u].size(); z++){
                indegrees[z] = indegrees[z] + 1;
            }
        }
        
        return indegrees;
    }
    
    //returns an array containing the list of topologically sorted vertices
    // (values in the array should represent natural vertex-numbers, i.e. starting with 1).
    public int[] topSort() {
        int u, i;
        int N = arr.length;
        int[] indegrees = indegrees();
        int[] A = new int [N];
        Queue<Integer> q = new LinkedList<>();
        for(u = 0; u < N; u++){
            if(indegrees[u] == 0){
                q.add(u);
            }
        }
        i = 1;
        while(!q.isEmpty()){
            u = q.remove();
            A[i] = u;
            i = i+1;
            for (int v = 0; v < arr.length; v++) {
                indegrees[v] = indegrees[v]-1;
                if (indegrees[v] == 0){
                    q.remove();
                }
            }
        }
        
        return A;
        //If the graph is cyclic, this method must throw IllegalArgumentException type exception
        //(read the note on top of the last page of your Topological Sort lecture handout).
        
    }
}
