package Graphs;

import java.util.ArrayList;

public class FibonacciHeap {
    private Node min;
    private int n;
    public void clear() {
        min = null;
        n = 0;
    }
    private void consolidate() {
        Node[] A = new Node[45];
        Node start = min;
        Node w = min;
        do {
            Node x = w;
            Node nextW = w.right;
            int d = x.degree;
            while (A[d] != null) {
                Node y = A[d];
                if (x.key.compareTo(y.key) > 0) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    start = start.right;
                }
                if (y == nextW) {
                    nextW = nextW.right;
                }
                y.link(x);
                A[d] = null;
                d++;
            }
            A[d] = x;
            w = nextW;
        } while (w != start);
        min = start;
        for (Node a : A) {
            if (a != null && a.key.compareTo(min.getKey()) < 0) {
                min = a;
            }
        }
    }
    public void decreaseKey(Node x, String newName, Comparable k) {
        decreaseKey(x, newName, k, false);
    }
    private void decreaseKey(Node x, String newName, Comparable k, boolean delete) {
        if (!delete && k.compareTo(x.getKey()) > 0) {
            throw new IllegalArgumentException("cannot increase key value");
        }
        x.key = k;
        x.setName(newName);
        Node y = x.parent;
        if (y != null && (delete || k.compareTo(y.getKey()) < 0)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }
        if (delete || k.compareTo(min.getKey()) < 0) {
            min = x;
        }
    }
    public void delete(Node x) {
        decreaseKey(x, x.getName(), 0, true);
        removeMin();
    }
    public boolean isEmpty() {
        return min == null;
    }
    public Node insert(String x, Comparable key) {
        Node node = new Node(x, key);
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (key.compareTo(min.getKey()) < 0) {
                min = node;
            }
        } else {
            min = node;
        }
        n++;
        return node;
    }
    public Node min() {
        return min;
    }
    public String removeMin() {
        Node z = min;
        if (z == null) {
            return null;
        }
        if (z.child != null) {
            z.child.parent = null;
            for (Node x = z.child.right; x != z.child; x = x.right) {
                x.parent = null;
            }
            Node minleft = min.left;
            Node zchildleft = z.child.left;
            min.left = zchildleft;
            zchildleft.right = min;
            z.child.left = minleft;
            minleft.right = z.child;
        }
        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }
        n--;
        return z.getName();
    }
    public int size() {
        return n;
    }

    public int count() {
        return n;
    }
    public static FibonacciHeap union(FibonacciHeap H1, FibonacciHeap H2) {
        FibonacciHeap H = new FibonacciHeap();
        if (H1 != null && H2 != null) {
            H.min = H1.min;
            if (H.min != null) {
                if (H2.min != null) {
                    H.min.right.left = H2.min.left;
                    H2.min.left.right = H.min.right;
                    H.min.right = H2.min;
                    H2.min.left = H.min;
                    if (H2.min.key.compareTo(H1.min.getKey()) < 0) {
                        H.min = H2.min;
                    }
                }
            } else {
                H.min = H2.min;
            }
            H.n = H1.n + H2.n;
        }
        return H;
    }

    public ArrayList<Node> nodeList() {
        ArrayList<Node> l = new ArrayList<Node>();
        if (min != null) min.addToList(l);
        return l;
    }
}