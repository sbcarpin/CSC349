/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 * This is a DiGraph project that uses natural indexing to do a breath first tree, 
 *  and topological sort for project 5 in CSC 349.
 */

import java.util.LinkedList;
import java.util.Queue;

public class DiGraph {
    
    private class VertexInfo {
        
        private int dist;
        private int pred;
        
        public VertexInfo(int dist, int pred) {
            this.dist = dist;
            this.pred = pred;
        }
    }
    
    private LinkedList<Integer>[] arr;
    
    private VertexInfo[] BFS(int s) {
        int N = arr.length;
        VertexInfo[] VA = new VertexInfo[N+1];
        for (int u=1; u<=N; u++) {
            VA[u] = new VertexInfo(-1, -1);
        }
        VA[s].dist = 0;
        Queue q = new LinkedList<Integer>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = (int) (q.remove());
            for (int v=0; v<arr[u-1].size(); v++) {
                int vert = arr[u-1].get(v);
                if (VA[vert].dist == -1) {
                    VA[vert].dist = VA[u].dist + 1;
                    VA[vert].pred = u;
                    q.add(vert);
                }
            }
        }
        return VA;
    }
    
    
    public boolean isTherePath(int from, int to) {
        VertexInfo[] VA = BFS(from);
        if (VA[to].pred == -1) {
            return false;
        }
        return true;
    }
    
    public int lengthOfPath(int from, int to) {
        VertexInfo[] VA = BFS(from);
        return VA[to].dist;
    }
    
    public void printPath(int from, int to) {
        VertexInfo[] VA = BFS(from);
        if (VA[to].dist == -1) {
            System.out.println("There is no path");
        }
        else {
            String output = "";
            while (from != to) {
                output = " -> " + to + output;
                to = VA[to].pred;
            }
            output = from + output;
            System.out.println(output);
        }
    }
    
    public DiGraph(int n) {
        arr = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new LinkedList<Integer>();
        }
    }
    
    public boolean addEdge(int from, int to) {
        if (arr[from-1].contains(to)) {
            return false;
        }
        arr[from-1].add(to);
        return true;
    }
    
    public void deleteEdge(int from, int to) {
        int index = arr[from-1].indexOf(to);
        arr[from-1].remove(index);
    }
    
    public int edgeCount() {
        int edgeSum = 0;
        for (int i = 0; i < arr.length; i++) {
            edgeSum += arr[i].size();
        }
        return edgeSum;
    }
    
    public int vertexCount() {
        return arr.length;
    }
    
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i+1) + " is connected to: ");
            for (int j = 0; j < arr[i].size(); j++) {
                System.out.print(arr[i].get(j));
                if (j != arr[i].size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
    private int[] indegrees() {
        int n = arr.length;
        int[] indegrees = new int[n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < arr[u].size(); v++) {
                indegrees[arr[u].get(v) - 1] += 1;
            }
        }
        return indegrees;
    }
    
    public int[] topSort() throws IllegalArgumentException {
        int n = arr.length;
        int []indegrees = indegrees();
        int[] a = new int[n];
        Queue<Integer> q = new LinkedList<Integer>();
        for (int u = 0; u < n; u++) {
            if (indegrees[u] == 0) {
                q.add(u+1);
            }
        }
        int i = 0;
        while (!q.isEmpty()) {
            int u = q.remove();
            a[i] = u;
            i++;
            for (int v = 0; v < arr[u-1].size(); v++) {
                indegrees[arr[u-1].get(v) - 1]--;
                if (indegrees[arr[u-1].get(v) - 1] == 0) {
                    q.add(arr[u-1].get(v));
                }
            }
        }
        if (i != n) {
            throw new IllegalArgumentException();
        }
        return a;
    }
    
    
    private class TreeNode {
        
        private int vert;
        private LinkedList<TreeNode> child;
        
        public TreeNode(int vert, LinkedList<TreeNode> child) {
            this.vert = vert;
            this.child = child;
        }
    }
    
    private TreeNode buildTree(int s) {
        VertexInfo[] VA = BFS(s);
        int N = VA.length - 1;
        TreeNode[] tree = new TreeNode[N+1];
        for (int i=1; i<=N; i++) {
            tree[i] = new TreeNode(i, new LinkedList<TreeNode>());
        }
        for (int i=1; i<=N; i++) {
            int pred = VA[i].pred;
            if (pred != -1) {
                tree[pred].child.add(tree[i]);
            }
        }
        return tree[s];
    }
    
    
    public void printTree(int s) {
        System.out.println();
        TreeNode root = buildTree(s);
        String str = "";
        printTreeRecursive(root, str);
    }
    
    private void printTreeRecursive(TreeNode root, String str) {
        System.out.println(str + root.vert + " ");
        if (root.child.isEmpty()) {
            return;
        }
        for (int i=0; i<root.child.size(); i++) {
            printTreeRecursive(root.child.get(i), str + "    ");
        }
    }
}

