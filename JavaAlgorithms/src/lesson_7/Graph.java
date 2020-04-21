package lesson_7;

import java.util.LinkedList;

public class Graph {

    private int vertexCount;
    private int edgeCount = 0;
    private LinkedList<Integer>[] adjList;


    public Graph( int vertexCount ) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Кол-во вершин " + vertexCount);
        }

        this.vertexCount = vertexCount;
        adjList = new LinkedList[vertexCount];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public LinkedList<Integer> getAdjList( int vertex ) {
        return (LinkedList<Integer>) adjList[vertex].clone();
    }

    public void addEdge( int v1, int v2 ) {
        if (v1 < 0 || v2 < 0 || v1 >= vertexCount || v2 >= vertexCount) {
            throw new IllegalArgumentException();
        }

        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}
