package graphtool;

import java.util.*;

public class DijkstraPathFinder {

    private final Graph graph;

    public DijkstraPathFinder(Graph graph) {
        this.graph = graph;
    }

    public Queue<String> getShortestPath(String source, String destination) {
        PathTable pathTable = getPathTable(source);
        Queue<String> path = new LinkedList<>();

        String currentNode = destination;
        while (!currentNode.equals(source)) {
            path.add(currentNode);
            currentNode = pathTable.getPrevious(currentNode);
        }

        return path;
    }

    private PathTable getPathTable(String source) {
        Set<String> unvisitedNodes = graph.getNodes();
        PathTable paths = new PathTable();

        paths.setDistance(source, 0);

        while (!unvisitedNodes.isEmpty()) {
            String closestNode = getClosestNode(paths.getDistanceList(), unvisitedNodes);

            graph.getOutgoingVertices(closestNode).forEach( adjacency -> {
                Integer newDistance = paths.getDistance(closestNode) + adjacency.getWeight();
                Integer adjacencyDistance = paths.getDistance(adjacency.getDestiny());

                if (adjacencyDistance == null || newDistance < adjacencyDistance) {
                    paths.setDistance(adjacency.getDestiny(), newDistance);
                    paths.setPrevious(adjacency.getDestiny(), closestNode);
                }
            });
            unvisitedNodes.remove(closestNode);
        }

        return paths;
    }

    private String getClosestNode(HashMap<String, Integer> distances, Set<String> nodes) {
        Integer smallestDistance = Integer.MAX_VALUE;
        String closestNode = null;

        for (String node : nodes) {
            Integer distance = distances.get(node);

            if (distance != null && distance < smallestDistance) {
                smallestDistance = distance;
                closestNode = node;
            }
        }

        return closestNode;
    }

    class PathTable {
        final HashMap<String, String> previousList = new HashMap<>();
        final HashMap<String, Integer> distanceList = new HashMap<>();

        public void setPrevious(String node, String previous) {
            previousList.put(node, previous);
        }

        public void setDistance(String node, Integer distance) {
            distanceList.put(node, distance);
        }

        public Integer getDistance(String node) {
            return distanceList.get(node);
        }

        public String getPrevious(String node) {
            return previousList.get(node);
        }

        public HashMap<String, Integer> getDistanceList() {
            return distanceList;
        }
    }
}
