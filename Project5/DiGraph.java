/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

import java.util.LinkedList;
import java.io.*;
import java.util.*;
import java.lang.*;

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
    
    public void addEdge(int from, int to) {
        from -= 1;
        to -= 1;
        //the edge should not be added if it already exists
        if (!arr[from].contains(to)) {
            arr[from].add(to);
        }
    }
    
    public void deleteEdge(int from, int to) {
        from -= 1;
        to -= 1;
        System.out.println("To: " + to + " from: " + from);
        
        //nothing done if edge does not exist (no error message)
        if (arr[from].contains(to)) {
            arr[from].remove(new Integer(to));
        }
        System.out.println("Edge Removed");
    }
    
    public int edgeCount() {
        int edge_num = 0;
        
        for (int i = 0; i < arr.length; i++) {
            edge_num += arr[i].size();
        }
        return edge_num;
    }
    
    
    // returns number of vetices (its the arrays length)
    public int vertexCount() {
        return arr.length;
    }
    
    //outputs the graph in the format provide din handout
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + " is connected to: ");
            for (int j = 0; j < arr[i].size(); j++) {
                System.out.print((arr[i].get(j) + 1));
                if (arr[i].size() > 1 && j != (arr[i].size() - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.print("\n");
        }
    }
    
    // ******---- PART 2 ------******
    //include the implementation of the Topological Sort
    
    private int[] indegrees() {
        int N = arr.length;
        int[] indegrees = new int[N];
        
        for (int j = 0; j < N; j++) {
            for (int z = 0; z < arr[j].size(); z++) {
                int v = arr[j].get(z);
                indegrees[v] += 1;
            }
        }
        return indegrees;
    }
    
    public int[] topSort() {
        int N = arr.length;
        int[] indegrees = indegrees();
        int[] A = new int[N];
        
        LinkedList<Integer> q = new LinkedList<>();
        
        for (int u = 0; u < N; u++) {
            if (indegrees[u] == 0) {
                q.addLast(u);
            }
        }
        
        int u;
        int i = 0;
        while (!q.isEmpty()) {
            u = q.removeFirst();
            A[i] = u + 1;
            i += 1;
            for (int j = 0; j < arr[u].size(); j++) {
                int v = arr[u].get(j);
                indegrees[v]--;
                
                if (indegrees[v] == 0) {
                    q.addLast(v);
                }
            }
        }
        
        if (i != N) {
            throw new IllegalArgumentException("Cyclic cycle");
        }
        
        return A;
    }
    
    
    // ******---- PART 3 ------******
    //implementation of breadth-first-search and related routines
    private class VertexInfo {
        public int distance;
        public int predecessor;
    }
    
    //could decide if natural or not
    //used to construct shortest paths from s vertex to all vertices in the graph that are reachable from s.
    //This is the BFS algorithm we discussed in class (see lecture handout).
    private VertexInfo[] BFS(int s) {
        //returns an array of VertexInfo type objects containing data
        int u;
        
        int N = arr.length;
        VertexInfo[] va = new VertexInfo[N];
        
        for(int i = 0; i < N; i ++){
            va[i] = new VertexInfo();
        }
        
        for (u = 0; u < N; u++) {
            va[u].distance = -1;
            va[u].predecessor = -1;
        }
        va[s].distance = 0;
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(s);
        
        while (!q.isEmpty()) {
            //System.out.println("into the while");
            
            u = q.remove();
            for (int i = 0; i < arr[u].size(); i++) {
                int v = arr[u].get(i);
                if (va[v].distance == -1) {
                    va[v].distance = va[u].distance + 1;
                    va[v].predecessor = u;
                    q.add(v);
                }
            }
        }
        
        return va;
        
    }
    
    //parameters are given in NATURAL (for these 3 methods)
    //invokes BFS method and uses data in the returned array.
    public boolean isTherePath(int from, int to) {
        boolean path = false;
        
        VertexInfo[] va = BFS(from - 1 ); //crashes here
        
        //returns true if there is a path from from vertex to to vertex, and false otherwise.
        
        if (va[to - 1].distance != -1) {
            path = true;
        }
        
        return path;
    }
    
    public int lengthOfPath(int from, int to) {
        int length = 0;
        //returns an integer – the shortest distance of the to vertex from the from vertex.
        
        VertexInfo[] va = BFS(from - 1); //crashes here
        
        
        return va[to - 1].distance;
    }
    
    public void printPath(int from, int to) {
        //arranges the output of the shortest path from from vertex to to vertex if to is reachable from from
        // (vertices of the path should be printed in natural numbering);
        from -= 1;
        to -= 1;
        
        String output = "";
        VertexInfo[] va = BFS(from);
        
        if (va[to].distance == -1) {
            System.out.println("There is no path");
        } else {
            output = "";
            while (from != to) {
                output = "->" + (to + 1) + output;
                to = va[to].predecessor;
            }
            output = (from + 1) + output;
            System.out.println(output);
        }
    }
    
    // ******---- PART 4 ------******
    // building and printing of the breadth-first-tree
    
    private class TreeNode{
        public int vert_num;
        public LinkedList<TreeNode>[] child;
        //LinkedList type list to hold TreeNode type objects representing this vertex’s children.
    }
    
    private int buildTree(int s){
        
        System.out.println("S: " + s);
        int root;
        int N = arr.length;
        // gives distance and predecessor
        VertexInfo[] va = BFS(s);
        
        // HOW BIG SHOULD THIS BE
        TreeNode[] tree = new TreeNode[arr.length];
        for(int i = 0; i < N; i ++){
            tree[i] = new TreeNode();
            tree[i].child = new LinkedList[2];
            System.out.println("tree");
        }
        
        
        for(int i = 0; i < N; i++){
            int z = 0;
            tree[i].vert_num = va[i].predecessor;
            //trying to go through array of children so we don't overwrite them
            //going to next "empty" child to fill it
            System.out.println(tree[i].child[z]);
            while(tree[i].child[z] != null)
                z++;
            System.out.println("z: " + z);
            
            tree[i].child[z].add(tree[i]);
            s = va[s].predecessor;
        }
        // while not a vertex
        while(va[s].predecessor != -1){
            
            // goes up the tree
            s = va[s].predecessor;
        }
        root = s;
        //returns the root of the breadth-first-tree for the given s source- vertex.
        //The tree can be built based on the data in the array returned by the BFS method.
        
        return root;
    }
    
    public void printTree(int s){
        int N = arr.length;
        s--;
        
        if (buildTree(s) != 0)
            
            //prints the breadth-first-tree for a given source vertex s.
            //Vertex s is given via natural numbering: manage adjustments with Java indexing.
            for(int i = 0; i < N; i++){
                breath(buildTree(s));
            }
        
        //invoke buildTree method and obtain the breadth-first-tree (more precisely, its root-node). Then
        //arrange the printing of this tree in the required format (vertices must be naturally numbered)
        
    }
    
    private static void breath(int root){
        if (root == 0)
            return;
        
        System.out.print(root + " ");
        breath(root);
        breath(root);
    }
}

