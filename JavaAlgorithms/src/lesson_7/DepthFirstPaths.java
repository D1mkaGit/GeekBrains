package lesson_7;

import java.util.LinkedList;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    public DepthFirstPaths( Graph g, int source ) {
        this.source = source;
        edgeTo = new int[g.getVertexCount()];
        marked = new boolean[g.getVertexCount()];
        dfs(g, source);
    }

    private void dfs( Graph g, int v ) {
        marked[v] = true;
        for (int w : g.getAdjList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hashPathTo( int v ) {
        return marked[v];
    }

    public LinkedList<Integer> pathTo( int v ) {
        if (!hashPathTo(v)) {
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = v;

        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }

        return stack;
    }
}
