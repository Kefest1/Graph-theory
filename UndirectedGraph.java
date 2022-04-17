package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

abstract class Data_t {
    public abstract boolean isEqual(Object node);

}

class Vertex_t extends Data_t {
    char x;

    public String toString() {
        return String.valueOf(x);
    }

    Vertex_t(){
        x = 0;
    }

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
    /**                    x1 ------- x2 */
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
        vertexes.get(x2_index).add(vertexes.get(x1_index).getFirst());

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

    public void breadthFirstSerch() {
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
}

class MyQueue<T> {
    private int size;
    private MyNode<T> head;

    MyQueue() {
        size = 0;
    }

    MyQueue(T xdata) {
        head = new MyNode<T>(xdata);
        size = 1;
    }

    void queuePush(T element) {
        if (head == null) head = new MyNode<T>(element);
        else {
            MyNode<T> temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new MyNode<T>(element);
        }
        size++;
    }

    T queuePop() {
        if (size != 0) {
            size--;
            T buf = head.data;
            head = head.next;
            return buf;
        }
        return null;
    }

}

class MyStack<T> {
    private int size;
    private MyNode<T> head;

    MyStack() {
        size = 0;
    }

    MyStack(T xdata) {
        head = new MyNode<T>(xdata);
        size = 1;
    }

    void stackPush(T element) {
        if (head == null) head = new MyNode<T>(element);
        else {
            MyNode<T> temp = new MyNode<>(element);
            temp.next = head;
            head = temp;
        }
        size++;
    }

    T stackPop() {
        if (size == 0) return null;
        T ret = head.data;
        head = head.next;
        return ret;
    }

}

class MyNode<T> {
    T data;
    MyNode<T> next;

    MyNode(T Data) {
        data = Data;
    }
}
