package Graphs;

import java.util.*;

public class Node {
    public Node child;
    private String name;

    Comparable key;
    Node parent;
    Node right;
    Node left;
    int degree;
    private boolean mark;
    public Node(String name, Comparable key) {
        this.name = name;
        this.key = key;
        right = this;
        left = this;
    }
    public Comparable getKey() {return key;}
    public void cascadingCut(Node min) {
        Node z = parent;
        if (z != null) {
            if (mark) {
                z.cut(this, min);
                z.cascadingCut(min);
            } else {
                mark = true;
            }
        }
    }
    public void cut(Node x, Node min) {
        x.left.right = x.right;
        x.right.left = x.left;
        degree--;
        if (degree == 0) {
            child = null;
        } else if (child == x) {
            child = x.right;
        }
        x.right = min;
        x.left = min.left;
        min.left = x;
        x.left.right = x;
        x.parent = null;
        x.mark = false;
    }
    public void link(Node parent) {
        left.right = right;
        right.left = left;
        this.parent = parent;
        if (parent.child == null) {
            parent.child = this;
            right = this;
            left = this;
        } else {
            left = parent.child;
            right = parent.child.right;
            parent.child.right = this;
            right.left = this;
        }
        parent.degree++;
        mark = false;
    }

    public void addToList(ArrayList<Node> l) {
        Node cur = this;
        do {
            l.add(cur);
            if (cur.child != null) cur.child.addToList(l);
            cur = cur.right;
        } while (cur != this);
    }
    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
