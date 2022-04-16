package com.company;

class Main {
    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph(new Vertex_t('A'));
        graph.addNode(new Vertex_t('B'));
        graph.addNode(new Vertex_t('C'));

        graph.addEdge(new Vertex_t('B'), new Vertex_t('C'));
        graph.addEdge(new Vertex_t('A'), new Vertex_t('C'));
    }
}
