package com.company;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        // Purposefully ignores weight //
        return Objects.equals(from, vertex.from) && Objects.equals(to, vertex.to);
    }

    public Vertex(Data_t from, Data_t to, int weight) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

public class DirectedWeightedGraph {
    int getSize() {
        return edges.size();
    }
    private LinkedList<Vertex> vertices; // krawędzie
    private ArrayList<Data_t> edges; // wierzchołki

    public DirectedWeightedGraph() {
        this.vertices = new LinkedList<>();
        this.edges = new ArrayList<>();
    }

    void addEdge(Data_t node) {
        for (int i = 0; i < vertices.size(); i++)
            if (node == edges.get(i))
                return;

        edges.add(node);
    }

    void addVertex(Vertex vertex1) {
        Vertex vertex2 = new Vertex(vertex1.getTo(), vertex1.getFrom(), vertex1.getWeight());

        for (Vertex vertex : vertices) {
            try {
                if (vertex.equals(vertex1) && vertex.equals(vertex2)) {
                    throw new RedundantPath();
                }
            } catch (Exception RedundantPath) {
                System.out.println("Vertex already exists");
                return;
            }
        }



        vertices.add(vertex1);
    }

}
