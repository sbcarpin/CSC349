/*Project 5
 *November 30, 2018
 *Stephanie Carpintero-Flores - sbcarpin@calpoly.edu
 *Aurora Paz - aepaz@calpoly.edu
 *Natalie Miller - nmille35@calpoly.edu
 */

import java.util.LinkedList;

import java.util.Queue;

public class DiGraph {
    
    private class VertexInfo {
        
        private int distance;
        private int pred;
        
        public VertexInfo(int distance, int pred) {
            this.distance = distance;
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
        VA[s].distance = 0;
        Queue queue = new LinkedList<Integer>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = (int) (queue.remove());
            for (int v=0; v<arr[u-1].size(); v++) {
                int vert = arr[u-1].get(v);
                if (VA[vert].distance == -1) {
                    VA[vert].distance = VA[u].distance + 1;
                    VA[vert].pred = u;
                    queue.add(vert);
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
        return VA[to].distance;
    }
    
    public void printPath(int from, int to) {
        VertexInfo[] VA = BFS(from);
        if (VA[to].distance == -1) {
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
        TreeNode[] treeNodes = new TreeNode[N+1];
        for (int i=1; i<=N; i++) {
            treeNodes[i] = new TreeNode(i, new LinkedList<TreeNode>());
        }
        for (int i=1; i<=N; i++) {
            int pred = VA[i].pred;
            if (pred != -1) {
                treeNodes[pred].child.add(treeNodes[i]);
            }
        }
        return treeNodes[s];
    }
    
    
    public void printTree(int s) {
        System.out.println(s);
        TreeNode root = buildTree(s);
        String indent = "";
        printTreeRecursive(root, indent);
    }
    
    private void printTreeRecursive(TreeNode root, String indent) {
        System.out.println(indent + root.vert + " ");
        if (root.child.isEmpty()) {
            return;
        }
        for (int i=0; i<root.child.size(); i++) {
            printTreeRecursive(root.child.get(i), indent + "    ");
        }
    }
}

