package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectedGraph {
    ArrayList<LinkedList<Data_t>> vertexes;

    public int getSize() {
        return vertexes.size();
    }

    DirectedGraph() {
        vertexes = new ArrayList<>();
    }

    DirectedGraph(Data_t vertex) {
        vertexes = new ArrayList<>();
        vertexes.add(new LinkedList<>());
        vertexes.get(0).add(vertex);
    }

    boolean addNode(Data_t vertex) {
        for (LinkedList<Data_t> data_ts : vertexes)
            if (vertex.isEqual(data_ts.getFirst()))
                return false;


        vertexes.add(new LinkedList<Data_t>());
        vertexes.get(vertexes.size() - 1).addFirst(vertex);

        return true;
    }

                       /**
     * x1 ------> x2
     */
    boolean addEdge(Data_t x1, Data_t x2) {
        boolean check = false;
        int x1_index = 0;
        int x2_index = 0;

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

    private Data_t findByIndex(int index) {
        return vertexes.get(index).getFirst();
    }

    private int findIndexByData(Data_t data) {
        for (int i = 0; i < vertexes.size(); i++)
            if (data.equals(vertexes.get(i).getFirst()))
                return i;
        return -1;
    }

    LinkedList<Data_t> findDataByData(Data_t data) {
        for (LinkedList<Data_t> vertex : vertexes)
            if (data.isEqual(vertex.getFirst()))
                return vertex;
        return null;
    }

    public void depthFirstSearch() {
        boolean[] arr = new boolean[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++)
            arr[i] = false;
        depthFirstSearchHelp(findByIndex(0), arr);
        System.out.println();
    }


    private void depthFirstSearchHelp(Data_t data, boolean[] arr) {
        arr[findIndexByData(data)] = true;
        System.out.print(data.toString() + " ");
        LinkedList<Data_t> neighbours = findDataByData(data);
        try {
            for (int i = 1; i < neighbours.size(); i++)
                if (!arr[findIndexByData(neighbours.get(i))])
                    depthFirstSearchHelp(neighbours.get(i), arr);
        }
        catch (Exception NullPointerException) {
            System.out.print("Error!");
        }
    }

    // TODO Checking whether a graph is acyclic //

    public void printTopSort() {
        System.out.println(topSort().toString());
    }

    public LinkedList<Data_t> topSort() {
        int len = vertexes.size();
        boolean[] V = new boolean[len];
        int[] ordering = new int[len];
        int it = len - 1;

        for (int i = 0; i < len; i++) {
            if (!V[i]) {
                LinkedList<Data_t> visitedNodes = new LinkedList<>();
                dfsHelp(vertexes.get(i).getFirst(), V, visitedNodes);
                for (int j = visitedNodes.size() - 1; j >= 0; j--) {
                    ordering[it] = findIndexByData(visitedNodes.get(j));
                    it--;
                }
            }
        }

        LinkedList<Data_t> topSortOrder = new LinkedList<>();
        for (int j : ordering) topSortOrder.addLast(findByIndex(j));

        return topSortOrder;
    }

    private void dfsHelp(Data_t at, boolean[] arr, LinkedList<Data_t> visitedNodes) {
        arr[findIndexByData(at)] = true;

        LinkedList<Data_t> edges = new LinkedList<>();
        LinkedList<Data_t> buff = findDataByData(at);
        for (int i = 1; i < buff.size();i++)
            edges.add(buff.get(i));


        for (Data_t edge : edges)
            if (!arr[findIndexByData(edge)])
                dfsHelp(edge, arr, visitedNodes);

        visitedNodes.addFirst(at);
    }
}
