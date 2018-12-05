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
        arr = new LinkedList[N];
        // Create a new list for each vertex such that adjacent nodes can be stored
        for (int i = 0; i < N; i++) {
            arr[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        int nfrom = from - 1;
        //the edge should not be added if it already exists
        if (!arr[nfrom].contains(to)) {
            arr[nfrom].add(to);
        }
    }

    public void deleteEdge(int from, int to) {
        int nfrom = from - 1;

        //nothing done if edge does not exist (no error message)
        if (arr[nfrom].contains(to)) {
            arr[nfrom].removeFirstOccurrence(to);
        }
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
                if (j == arr[i].size()-1) {
                    System.out.print(arr[i].get(j));
                }
                else{
                    System.out.print(arr[i].get(j) + ",");
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
                indegrees[v-1] += 1;
            }
        }
        return indegrees;
    }

    public int[] topSort() {
        int N = arr.length;
        int[] indegrees = indegrees();
        int[] A = new int[N];
        Integer U;

        Queue<Integer> q = new LinkedList<>();

        for (int u = 0; u < N; u++) {
            if (indegrees[u] == 0) {
                U = new Integer(u);
                q.add(U);
            }
        }

        int i = 0;
        while(q.size() != 0) {

            //remove returns Integer, casting back to primitive int
            Integer W = q.remove();
            int w = W.intValue();

            A[i] = w + 1;
            i+=1;

            for(int z = 0; z < N; z++) {
                for(int j = 0; j < arr[z].size(); j++) {
                    int n = arr[z].get(j);
                    indegrees[n-1] -=1;
                    if(indegrees[n-1] == 0) {
                        Integer NODE = new Integer(n-1);
                        q.add(NODE);
                    }
                }
            }
        }

        if((N) != i) {
            throw new IllegalArgumentException("Graph is Cylic");
        }
        return A;
    }


    // ******---- PART 3 ------******
    //implementation of breadth-first-search and related routines
    private class VertexInfo {
        int distance;
        int predecessor;

        public VertexInfo (int distance, int predecessor) {
            this.distance = distance;
            this.predecessor = predecessor;
        }
    }

    //could decide if natural or not
    //used to construct shortest paths from s vertex to all vertices in the graph that are reachable from s.
    //This is the BFS algorithm we discussed in class (see lecture handout).
    private VertexInfo[] BFS(int s) {
        s++;
        //returns an array of VertexInfo type objects containing data
        int N = arr.length;
        VertexInfo[] va = new VertexInfo[N];

        for(int i = 0; i < N; i++){
            va[i] = new VertexInfo(-1,-1);
        }
        va[s].distance = 0;

        Queue<Integer> q = new LinkedList<Integer>();

        q.add(new Integer(s));

        while(q.size() > 0) {
            Integer u = q.remove();
            for(int j = 0; j < arr[u].size(); j++) {
                int node = arr[u].get(j) - 1;

                if (va[node].distance == -1) {
                    va[node].distance = va[u].distance + 1;
                    va[node].predecessor = u;
                    q.add(new Integer(node));
                }

            }

        }
        return va;
    }

    //parameters are given in NATURAL (for these 3 methods)
    //invokes BFS method and uses data in the returned array.
    public boolean isTherePath(int from, int to) {
        from -= 1;
        to -= 1;
        VertexInfo[] va = BFS(from ); //crashes here

        //returns true if there is a path from from vertex to to vertex, and false otherwise.

        if (va[to].distance != -1) {
            return true;
        }
        //else {
        return false;
        //}
    }

    public int lengthOfPath(int from, int to) {
        from -= 1;
        to -= 1;
        //returns an integer – the shortest distance of the to vertex from the from vertex.
        VertexInfo[] va = BFS(from - 1); //crashes here

        return va[to].distance;
    }

    public void printPath(int from, int to) {
        //arranges the output of the shortest path from from vertex to to vertex if to is reachable from from
        // (vertices of the path should be printed in natural numbering);

        from -= 1;
        to -= 1;

        VertexInfo[] va = BFS(from);

            if (!isTherePath(from+1, to+1)) {
                System.out.println("There is no path");
            }
            else {
                String path = "";
                while (va[to].predecessor != -1) {
                    path = "->"+ (to) + path;
                    to = va[to].predecessor;
                }
                System.out.println("" + (from + 1) + path);
            }
    }

    // ******---- PART 4 ------******
    // building and printing of the breadth-first-tree

    private class TreeNode{
        int vert_num;
        LinkedList<TreeNode> child;
        //LinkedList type list to hold TreeNode type objects representing this vertex’s children.

        public TreeNode(int vertexNum, LinkedList<TreeNode> list) {
            this.vert_num = vert_num;
            this.child = child;
        }
    }

    private TreeNode buildTree(int s) {
        VertexInfo[] va = BFS(s-1);
        TreeNode[] tree = new TreeNode[arr.length];

        for(int i = 0; i < arr.length; i++) {
            tree[i] = new TreeNode(i+1, new LinkedList<TreeNode>());
        }

        for (int i = 0; i < va.length; i++) {
            if (va[i].predecessor != -1) {
                tree[va[i].predecessor].child.add(tree[i]);
            }
        }
        return tree[s-1];
    }


    public void printTree(int s) {
        TreeNode root = buildTree(s);
        treeprint(root, "");
    }

    private void treeprint(TreeNode s, String tabs){
        System.out.println(tabs + s.vert_num);
        if (s.child.size() > 0){
            for(int i = 0; i < s.child.size(); i++){
                treeprint(s.child.get(i), tabs + "    ");
            }
        }

    }
}
