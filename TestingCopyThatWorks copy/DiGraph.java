import java.util.LinkedList;

import java.util.Queue;

public class DiGraph {

    private class VertexInfo {

        private int dist;
        private int pred;

        public VertexInfo(int distance, int predecessor) {
            this.dist = dist;
            this.pred = pred;
        }
    }

    private LinkedList<Integer>[] arr;

    private VertexInfo[] BFS(int s) {
        int N = arr.length;
        VertexInfo[] VA = new VertexInfo[N];
        for (int u = 0; u < N; u++) {
            VA[u] = new VertexInfo(-1, -1); //may need to add 1
        }
        VA[s].dist = 0;
        Queue queue = new LinkedList<Integer>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = (int) (queue.remove());
            for (int v=0; v<arr[u].size(); v++) {
                int vertex = arr[u].get(v);
                if (VA[vertex].dist == -1) {
                    VA[vertex].dist = VA[u].dist; //add 1?
                    VA[vertex].pred = u;
                    queue.add(vertex);
                }
            }

        }
        return VA;

    }


    public boolean isTherePath(int from, int to) {
        from--;
        to--;

        VertexInfo[] VA = BFS(from);
        if (VA[to].pred == -1) {
            return false;
        }
        return true;

    }

    public int lengthOfPath(int from, int to) {
        from--;
        to--;

        VertexInfo[] VA = BFS(from);
        return VA[to].dist;
    }

    public void printPath(int from, int to) {
        from--;
        to--;

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
        from--;
        to--;
        if (arr[from].contains(to)) {
            return false;
        }
        arr[from].add(to);
        return true;
    }

    public void deleteEdge(int from, int to) {
        from--;
        to--;
        int index = arr[from].indexOf(to);
        arr[from].remove(index);
    }

    public int edgeCount() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].size();
        }
        return sum;
    }

    public int vertexCount() {
        return arr.length;
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i+1) + " is connected to: ");
            for (int j = 0; j < arr[i].size(); j++) {
                System.out.print(arr[i].get(j));
                if (j != arr[i].size()) {
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
                indegrees[arr[u].get(v)] += 1;
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
                q.add(u);
            }
        }
        int i = 0;
        while (!q.isEmpty()) {
            int u = q.remove();
            a[i] = u;
            i++;
            for (int v = 0; v < arr[u].size(); v++) {
                indegrees[arr[u].get(v)]--;
                if (indegrees[arr[u].get(v)] == 0) {
                    q.add(arr[u].get(v));
                }
            }
        }
        if (i != n) {
            throw new IllegalArgumentException();
        }
        return a;
    }


    private class TreeNode {

        private int vertex;
        private LinkedList<TreeNode> children;

        public TreeNode(int vertex, LinkedList<TreeNode> children) {
            this.vertex = vertex;
            this.children = children;
        }

    }

    private TreeNode buildTree(int s) {
        VertexInfo[] VA = BFS(s);
        int N = VA.length;
        TreeNode[] treeNodes = new TreeNode[N];
        for (int i=0; i<N; i++) {
            treeNodes[i] = new TreeNode(i, new LinkedList<TreeNode>());
        }
        for (int i=0; i<N; i++) {
            int predecessor = VA[i].pred;
            if (predecessor != -1) {
                treeNodes[predecessor].children.add(treeNodes[i]);
            }
        }
        return treeNodes[s];
    }


    public void printTree(int s) {
        TreeNode root = buildTree(s);
        String indent = "";
        printTreeRecursive(root, indent);
    }

    private void printTreeRecursive(TreeNode root, String indent) {
        System.out.println(indent + root.vertex + " ");
        if (root.children.isEmpty()) {
            return;
        }
        for (int i=0; i<root.children.size(); i++) {
            printTreeRecursive(root.children.get(i), indent + "    ");
        }
    }
}