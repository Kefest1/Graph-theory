package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

abstract class Data_t extends Object {
    public abstract boolean isEqual(Object node);

}

class Vertex_t extends Data_t {
    char x = 0;


    Vertex_t(char X) {
        x = X;
    }

    @Override
    public boolean isEqual(Object node) {
        return this.x == ((Vertex_t) node).x;
    }

}

public class UndirectedGraph {
    ArrayList<LinkedList<Data_t>> vertexes;

    UndirectedGraph() {
        vertexes = new ArrayList<>();
    }

    UndirectedGraph(Data_t vertex) {
        vertexes = new ArrayList<>();
        vertexes.add(new LinkedList<Data_t>());
        vertexes.get(0).add(vertex);
    }

    boolean addNode(Data_t vertex) {
        for (int i = 0; i < vertexes.size(); i++)
            if (vertex.isEqual(vertexes.get(i).getFirst()))
                return false;


        vertexes.add(new LinkedList<Data_t>());
        vertexes.get(vertexes.size() - 1).addFirst(vertex);

        return true;
    }
    /**                    x1 ------> x2 */
    boolean addEdge(Data_t x1, Data_t x2) {
        boolean check = false;
        int x1_index = 0;
        int x2_index = 0;

        int dupa;

        for (int i = 0; i < vertexes.size(); i++) {
            if (x1.isEqual(vertexes.get(i).getFirst())) {
                check = true;
                x1_index = i;
                break;
            }
        }

        if (!check) return false;

        for (int i = 0; i < vertexes.size(); i++) {
            if (x2.isEqual(vertexes.get(i).getFirst())) {
                x2_index = i;
                check = false;
                break;
            }
        }
        if (check) return false;

        vertexes.get(x1_index).add(vertexes.get(x2_index).getFirst());

        return true;
    }
}
