package com.company;

import java.util.Stack;

class Main {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.queuePush(5);
        queue.queuePush(51);
        queue.queuePush(52);

        Integer x = queue.queueGetFirst();
        x = 2137;

        UndirectedGraph graph = new UndirectedGraph(new Vertex_t('A'));
        graph.addNode(new Vertex_t('A')); // 0
        graph.addNode(new Vertex_t('B')); // 1
        graph.addNode(new Vertex_t('C')); // 2
        graph.addNode(new Vertex_t('D')); // 3
        graph.addNode(new Vertex_t('E')); // 4
        graph.addNode(new Vertex_t('F')); // 5
        graph.addNode(new Vertex_t('G')); // 6
        graph.addNode(new Vertex_t('H')); // 7
        graph.addNode(new Vertex_t('I')); // 8
        graph.addNode(new Vertex_t('J')); // 9
        graph.addNode(new Vertex_t('K')); // 10
        graph.addNode(new Vertex_t('L')); // 11
        graph.addNode(new Vertex_t('M')); // 12

        graph.addEdge(new Vertex_t('A'), new Vertex_t('B'));
        graph.addEdge(new Vertex_t('B'), new Vertex_t('I'));
        graph.addEdge(new Vertex_t('I'), new Vertex_t('J'));
        graph.addEdge(new Vertex_t('J'), new Vertex_t('A'));
        graph.addEdge(new Vertex_t('I'), new Vertex_t('H'));


        graph.addEdge(new Vertex_t('H'), new Vertex_t('K'));
        graph.addEdge(new Vertex_t('K'), new Vertex_t('L'));
        graph.addEdge(new Vertex_t('L'), new Vertex_t('H'));

        graph.addEdge(new Vertex_t('H'), new Vertex_t('G'));
        graph.addEdge(new Vertex_t('G'), new Vertex_t('F'));
        graph.addEdge(new Vertex_t('F'), new Vertex_t('D'));

        graph.addEdge(new Vertex_t('H'), new Vertex_t('D'));
        graph.addEdge(new Vertex_t('D'), new Vertex_t('C'));
        graph.addEdge(new Vertex_t('D'), new Vertex_t('E'));
        graph.breadthFirstSearch();
    }
}
