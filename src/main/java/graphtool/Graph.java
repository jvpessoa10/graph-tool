package graphtool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {

    private final HashMap<String, List<Adjacency>> adjMatrix = new HashMap<>();

    public void addNode(String node) {
        adjMatrix.put(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        List<Adjacency> fromNode = adjMatrix.get(from);
        if (fromNode != null) {
            fromNode.add(new Adjacency(to, weight));
            adjMatrix.put(from, fromNode);
        }
    }

    public Set<String> getNodes() {
        return adjMatrix.keySet();
    }

    /**
     * @return The order of the Graph
     */
    public int getOrder() {
        return adjMatrix.size();
    }

    /**
     * @return The size of the Graph
     */
    public int getSize() {
        int total = 0;

        for (Object value : adjMatrix.values()) {
            total += ((List<Adjacency>) value).size();
        }

        return total;
    }

    public int getIngoingDegree(String node) {
        return getIngoingVertices(node).size();
    }

    public int getOutgoingDegree(String node) {
        return getOutgoingVertices(node).size();
    }

    public List<Adjacency> getIngoingVertices(String node) {
        List<Adjacency> ingoingEdges = new ArrayList<>();
        for (String key : adjMatrix.keySet()) {
            adjMatrix.get(key).forEach(adjacency -> {
                if (adjacency.destiny.equals(node)) ingoingEdges.add(adjacency);
            });
        }
        return ingoingEdges;
    }

    public List<Adjacency> getOutgoingVertices(String node) {
        return adjMatrix.get(node);
    }

    public boolean areAdjancents(String node1, String node2) {
        List<Adjacency> node1Adjacency= new ArrayList<>();

        node1Adjacency.addAll(getIngoingVertices(node1));
        node1Adjacency.addAll(getOutgoingVertices(node2));

        for (Adjacency adjacency: node1Adjacency) {
            if (adjacency.destiny.equals(node2)) {
                return true;
            }
        }

        return false;
    }

    public class Adjacency {

        private final String destiny;

        private final int weight;

        public Adjacency(String node, int weight) {
            this.destiny = node;
            this.weight = weight;
        }

        public String getDestiny() {
            return destiny;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Node: " + destiny + " and Weight: " + weight;
        }
    }
}


//Done
//O usuário poderá criar grafos direcionados e não-direcionados (0,5 pts).
//O usuário poderá criar grafos valorados e não-valorados (0,5 pts).
//O usuário deverá conseguir obter a informação da Ordem do Grafo criado (0,5 pts).
//O usuário deverá conseguir obter a informação do Tamanho do Grafo criado (0,5 pts).
//Dado um par de vértices o sistema deverá retornar a informação se os dois vértices são adjacentes ou não (0,5 pt).
//O usuário poderá ser capaz de imprimir o grafo (formato livre a ser definido pelo grupo, poder ser textual como uma lista de adjacência, matrix de adjacência, etc.) ou gráfico como imagem na tela ou download de arquivo de imagem (1 pt).
//Para um dado vértice o usuário deverá ser capaz de obter a informação da lista de vértices adjacentes. Se o grafo for direcionado o sistema deverá ser capaz de informar a lista de vértices adjacentes de entrada e a lista de vértices adjacentes de saída do dado vértice (1 pt).

//Medium
//Para um dado vértice o sistema deverá ser capaz de informar o grau daquele vértice. Se o grafo for direcionado o sistema deverá informar o grau de adjacência de entrada e de saída do vértice (0,5 pts).
//To define

//O usuário deverá ser capaz de interagir com o sistema para criar um grafo (2 pts). *
