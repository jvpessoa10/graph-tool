package graphtool;

import java.util.Queue;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "B", 3);
        graph.addEdge("C", "E", 2);
        graph.addEdge("B", "D", 10);
        graph.addEdge("E", "D", 5);
        graph.addEdge("E", "F", 10);
        graph.addEdge("D", "F", 1);

        DijkstraPathFinder pathFinder = new DijkstraPathFinder(graph);

        Queue<String> path = pathFinder.getShortestPath("A", "F");

        System.out.println(path);
    }
}
