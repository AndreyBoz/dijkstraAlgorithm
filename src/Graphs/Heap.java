package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Heap {
    private List<Integer> items = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();
    private Integer count;
    public Integer getCount() {
        return items.size();
    }
    public List<Node> getNodes() {
        return nodes;
    }
    public void add(Integer item){
        items.add(item);
        var currentIndex = getCount() - 1;
        var parentIndex = (currentIndex - 1)/2;
        while(currentIndex > 0 && items.get(parentIndex) < items.get(currentIndex)){
            Collections.swap(items, currentIndex,parentIndex);

            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1)/2;
        }
    }
    public Graph getGraph() { // For example, I set the values of the heap itself as distances
        Graph graph = new Graph();
        for(Integer i = 0;i<getCount();i++){
            nodes.add(new Node(i.toString()));
        }
        for(int i = 0;i<getCount();i++){
            if(2*i+1 >= getCount())
                break;
            nodes.get(i).addDestination(nodes.get(2*i+1),items.get(2*i+1));
            if(2*i+2 >= getCount())
                break;
            nodes.get(i).addDestination(nodes.get(2*i+2),items.get(2*i+2));
        }
        for(int i = 0; i<getCount();i++)
            graph.addNode(nodes.get(i));
        return graph;
    }
    public List<Node> getNodesHeap() {
        nodes.clear();
        for(Integer i = 0;i<getCount();i++){
            nodes.add(new Node(items.get(i).toString()));
        }
        return nodes;
    }
    public int getMax()
    {
        var result = items.get(0);
        items.set(0,items.get(getCount()-1));
        items.remove(getCount()-1);
        sort(0);
        return result;
    }

    private void sort(int curentIndex)
    {
        int maxIndex = curentIndex;
        int leftIndex;
        int rightIndex;

        while(curentIndex < getCount())
        {
            leftIndex = 2 * curentIndex + 1;
            rightIndex = 2 * curentIndex + 2;

            if(leftIndex < getCount() && items.get(leftIndex) > items.get(maxIndex))
            {
                maxIndex = leftIndex;
            }

            if(rightIndex < getCount() && items.get(rightIndex) > items.get(maxIndex))
            {
                maxIndex = rightIndex;
            }

            if(maxIndex == curentIndex)
            {
                break;
            }

            Collections.swap(items,curentIndex, maxIndex);
            curentIndex = maxIndex;
        }
    }


}
