package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

abstract class Data_t {
    public abstract boolean isEqual(Object node);
}

class Node_t extends Data_t {
    char x;

    public String toString() {
        return String.valueOf(x);
    }

    Node_t(){
        x = 0;
    }

    Node_t(char X) {
        x = X;
    }

    @Override
    public boolean isEqual(Object node) {
        return this.x == ((Node_t) node).x;
    }
}

public class UndirectedGraph {
    ArrayList<LinkedList<Data_t>> vertices;

    public int getSize() {
        return vertices.size();
    }

    UndirectedGraph() {
        vertices = new ArrayList<>();
    }

    UndirectedGraph(Data_t vertex) {
        vertices = new ArrayList<>();
        vertices.add(new LinkedList<>());
        vertices.get(0).add(vertex);
    }

    boolean addNode(Data_t vertex) {
        for (LinkedList<Data_t> data_ts : vertices)
            if (vertex.isEqual(data_ts.getFirst()))
                return false;


        vertices.add(new LinkedList<Data_t>());
        vertices.get(vertices.size() - 1).addFirst(vertex);

        return true;
    }
    /**                    x1 ------- x2 */
    boolean addEdge(Data_t x1, Data_t x2) {
        boolean check = false;
        int x1_index = 0;
        int x2_index = 0;

        for (int i = 0; i < vertices.size(); i++) {
            if (x1.isEqual(vertices.get(i).getFirst())) {
                check = true;
                x1_index = i;
                break;
            }
        }

        if (!check) return false;

        for (int i = 0; i < vertices.size(); i++) {
            if (x2.isEqual(vertices.get(i).getFirst())) {
                x2_index = i;
                check = false;
                break;
            }
        }
        if (check) return false;

        vertices.get(x1_index).add(vertices.get(x2_index).getFirst());
        vertices.get(x2_index).add(vertices.get(x1_index).getFirst());

        return true;
    }

    private Data_t findByIndex(int index) {
        return vertices.get(index).getFirst();
    }

    private int findIndexByData(Data_t data) {
        for (int i = 0; i < vertices.size(); i++)
            if (data.equals(vertices.get(i).getFirst()))
                return i;
        return -1;
    }

    private LinkedList<Data_t> findDataByData(Data_t data) {
        for (int i = 0; i < vertices.size(); i++)
            if (data.equals(vertices.get(i).getFirst()))
                return vertices.get(i);
        return null;
    }


    public void depthFirstSearch() {
        boolean[] arr = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++)
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

    public void breadthFirstSearch() {
        MyQueue<Data_t> queue = new MyQueue<>(vertices.get(0).getFirst());
        boolean[] visited = new boolean[vertices.size()];
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
}

class MyQueue<T> {
    private int size;
    private MyNode<T> head;

    public int getSize() {
        return size;
    }

    MyQueue() {
        size = 0;
    }

    MyQueue(T xdata) {
        head = new MyNode<T>(xdata);
        size = 1;
    }

    public boolean isEmpty() {
        return size == 0;
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

    T queueGetFirst() {
        return head.data;
    }
}

class MyStack<T> {
    private int size;
    private MyNode<T> head;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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
