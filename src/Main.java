
import Graphs.FibonacciHeap;
import Graphs.Graph;
import Graphs.Heap;
import Graphs.Node;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);

        Heap heap = new Heap();
        heap.add(7);
        heap.add(20);
        heap.add(15);
        heap.add(17);
        heap.add(18);
        heap.add(10);
        graph = heap.getGraph(); // For example, I set the values of the heap itself as distances
        graph = Dijkstra.calculateShortestPathFromSource(graph, heap.getNodes().get(0));

        // Here, for example, the distances can be set independently
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert("A",2);
        fibonacciHeap.insert("B",4);
        fibonacciHeap.insert("C",3);
        fibonacciHeap.insert("D",123);
        fibonacciHeap.insert("E",1);
        fibonacciHeap.insert("D",4);
        List<Node> nodes = fibonacciHeap.nodeList();
        nodes.get(0).addDestination(nodes.get(2),20);
        nodes.get(1).addDestination(nodes.get(3),10);
        graph = Dijkstra.calculateShortestPathFromSource(graph, nodes.get(0));

    }

}