package com.company;


import java.util.*;

class Vertex {
    private Data_t from, to;
    private int weight;



    public int getWeight() {
        return weight;
    }

    public Data_t getFrom() {
        return from;
    }

    public Data_t getTo() {
        return to;
    }

    public boolean equals(Vertex o) {
        // Purposefully ignores weight //
        return this.from.isEqual(o.from) && this.to.isEqual(o.to);
    }

    public Vertex(Data_t from, Data_t to, int weight) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public Vertex(Vertex vertex) {
        this.weight = vertex.getWeight();
        this.from = vertex.getTo();
        this.to = vertex.getFrom();
    }
}

public class DirectedWeightedGraph {
    int getSize() {
        return edges.size();
    }

    boolean isEmpty() {
        return edges.size() == 0;
    }

    private LinkedList<Vertex> vertices; // krawędzie
    private ArrayList<Data_t> edges; // wierzchołki

    public DirectedWeightedGraph() {
        this.vertices = new LinkedList<>();
        this.edges = new ArrayList<>();
    }

    void addEdge(Data_t node) {
        for (Data_t edge : edges)
            if (node.isEqual(edge))
                return;

        edges.add(node);
    }

    void addVertex(Vertex vertex1) {
        Vertex vertex2 = new Vertex(vertex1.getTo(), vertex1.getFrom(), vertex1.getWeight());

        for (Vertex vertex : vertices) {
            try {
                if (vertex.equals(vertex1) || vertex.equals(vertex2))
                    throw new RedundantPath();
            } catch (Exception RedundantPath) {
                System.out.println("Vertex already exists");
                return;
            }

        }
        vertices.add(vertex1);
    }

    private Data_t findByIndex(int index) {
        return edges.get(index);
    }

    private int findIndexByData(Data_t data) {
        for (int i = 0; i < edges.size(); i++)
            if (edges.get(i).isEqual(data))
                return i;

        return -1;
    }

    public LinkedList<Data_t> getNeighbours(Data_t data) {
        LinkedList<Data_t> list = new LinkedList<Data_t>();
        boolean check = true;
        for (Data_t edge : edges) {
            if (data.isEqual(edge)) {
                check = false;
                break;
            }
        }
        if (check) return null;

        for (int i = 0; i < vertices.size(); i++)
            if (data.isEqual(vertices.get(i).getFrom()))
                list.addLast(vertices.get(i).getTo());

        return list;
    }

    public LinkedList<Data_t> topSort() {
        int len = edges.size();
        boolean[] visited = new boolean[len];
        int[] order = new int[len];
        int it = len - 1;

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                LinkedList<Data_t> visitedNodes = new LinkedList<>();
                depthFirstSearch(edges.get(i), visited, visitedNodes);
                for (int j = visitedNodes.size() - 1; j >= 0; j--) {
                    order[it] = findIndexByData(visitedNodes.get(j));
                    it--;
                }
            }
        }

        LinkedList<Data_t> topSortOrder = new LinkedList<>();
        for (int i : order) topSortOrder.addLast(findByIndex(i));

        return topSortOrder;
    }


    public int[] topSortIndexes() {
        int len = edges.size();
        boolean[] visited = new boolean[len];
        int[] order = new int[len];
        int it = len - 1;

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                LinkedList<Data_t> visitedNodes = new LinkedList<>();
                depthFirstSearch(edges.get(i), visited, visitedNodes);
                for (int j = visitedNodes.size() - 1; j >= 0; j--) {
                    order[it] = findIndexByData(visitedNodes.get(j));
                    it--;
                }
            }
        }

        return order;
    }

    public void depthFirstSearch(Data_t at, boolean visited[], LinkedList<Data_t> order) {
        visited[findIndexByData(at)] = true;
        LinkedList<Data_t> neighbours = getNeighbours(at);

        for (Data_t buff : neighbours)
            if (!visited[findIndexByData(buff)])
                depthFirstSearch(buff, visited, order);

        order.addFirst(at);
    }

    public Integer[] shortestPathList(Data_t start) {
        int[] topSort = this.topSortIndexes();
        int size = this.getSize();
        Integer[] dist = new Integer[size];
        dist[findIndexByData(start)] = 0;

        for (int i = 0; i < size; i++) {
            int nodeIndex = topSort[i];
            if (dist[nodeIndex] != null) {
                List<Data_t> adjacentEdges = this.getNeighbours(findByIndex(nodeIndex));
                if (adjacentEdges != null) {
                    for (Data_t data : adjacentEdges) {
                        int newDist = dist[nodeIndex] + getWeight(findByIndex(topSort[i]), data);
                        if (dist[findIndexByData(data)] == null) dist[findIndexByData(data)] = newDist;
                        else dist[findIndexByData(data)] = Math.min(dist[findIndexByData(data)],newDist);
                    }
                }

            }
        }

        return dist;
    }

    // TODO exception //
    int getWeight(Data_t from, Data_t to) {
        for (Vertex vertex : vertices)
            if (vertex.getFrom().isEqual(from) && vertex.getTo().isEqual(to))
                return vertex.getWeight();
        return -1;
    }

}
