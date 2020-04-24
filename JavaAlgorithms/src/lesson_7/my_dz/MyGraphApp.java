package lesson_7.my_dz;

//1. Реализовать программу, в которой задается граф из 10 вершин.
// Задать ребра и найти кратчайший путь с помощью поиска в ширину.

import lesson_7.BreadthFirstPath;
import lesson_7.DepthFirstPaths;
import lesson_7.Graph;

import java.util.Random;

public class MyGraphApp {
    public static void main( String[] args ) {
        int maxVertexCount = 10;
        Graph graph = new Graph(maxVertexCount);
//        for (int i = 0; i < graph.getVertexCount(); i++) {
//            int edge = getRandomNumberInRange(0, maxVertexCount - 1);
//            if (i == edge) edge++;
//            graph.addEdge(i, edge);
//        }
        graph.addEdge(1, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        //graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(9, 6);

        graph.addEdge(9, 3);
        //graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.addEdge(7, 8);

//        DepthFirstPaths dfp = new DepthFirstPaths(graph, 0);
//        System.out.println(dfp.hashPathTo(8));
//        System.out.println(dfp.pathTo(3));

        BreadthFirstPath bfp = new BreadthFirstPath(graph, 0);
        System.out.println(bfp.hasPathTo(8));
        System.out.println(bfp.hasPathTo(3));
        System.out.println(bfp.pathTo(3));
    }

    private static int getRandomNumberInRange( int min, int max ) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
