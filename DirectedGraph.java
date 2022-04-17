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

    private LinkedList<Data_t> findDataByData(Data_t data) {
        for (int i = 0; i < vertexes.size(); i++)
            if (data.equals(vertexes.get(i).getFirst()))
                return vertexes.get(i);
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
        } catch (Exception NullPointerException) {
            System.out.print("Error!");
        }
    }

    public void breadthFirstSearch() {
        MyQueue<Data_t> queue = new MyQueue<>(vertexes.get(0).getFirst());
        boolean[] visited = new boolean[vertexes.size()];
        visited[0] = true;

        do {
            for (int j = 1; j < findDataByData(queue.queueGetFirst()).size(); j++) {
                if (!visited[findIndexByData(findDataByData(queue.queueGetFirst()).get(j))]) {
                    queue.queuePush(findDataByData(queue.queueGetFirst()).get(j));
                    visited[findIndexByData(findDataByData(queue.queueGetFirst()).get(j))] = true;
                }
            }
            Data_t data = queue.queuePop();
            System.out.print(data.toString() + " ");
        } while (!queue.isEmpty());
    }

    private void breadthFirstSearchHelp(boolean[] visited, MyQueue<Data_t> queue) {

    }


}
