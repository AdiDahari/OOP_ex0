package ex0;

import java.util.Collection;
import java.util.HashMap;
/**
 * this class implements graph interface including all methods in it.
 * each graph contains a HashMap of it's nodes,
 * an edge int - counting the number of edges in it,
 * and a modeCount int - counting the changes being made in this graph.
 * @author Adi Dahari
 */
public class Graph_DS implements graph{
    private HashMap<Integer, node_data> graph;
    private int edges;
    private int modeCount;

    /**
     * empty constructor
     * initialize the graph's map as a new, empty HashMap,
     * edges and modeCount as 0.
     */
    public Graph_DS(){
     graph = new HashMap<>();
     edges = 0;
     modeCount = 0;
    }

    /**
     * copy constructor
     * deep-copying of all g (given graph) parameters to this graph parameters,
     * using the copy-constructor built in NodeData.
     * Complexity: Worst Casr = O(n^2) - as n stands for number of nodes, if all nodes connected.
     * @param g - given graph to copy
     */
    public Graph_DS(graph g){
        graph = new HashMap<>();
        Collection<node_data> arr = g.getV();
        for(node_data node : arr){
            graph.put(node.getKey(), new NodeData(node));
        }
        edges = g.edgeSize();
        modeCount = g.getMC();
    }

    /**
     * override method
     * getting the node by it's key, only if there is a matching key in the graph
     * Complexity: O(1).
     * @param key - the node_id
     * @return node_data - the graph's node with the given key
     */
    @Override
    public node_data getNode(int key){
        if(!graph.containsKey(key)) return null;
       return graph.get(key);
    }

    /**
     * override method
     * checking if 2 nodes of the given keys are connected by edge,
     * only if the graph contains both of them.
     * as 2 nodes contains each other in neighbors if connected,
     * simply check if one of them is in the other's neighbors Collection.
     * Complexity: O(1) - use of hasNi method of NodeData class.
     * @param node1 - key of 1st node
     * @param node2 - key of 2nd node
     * @return if connected - true. else - false.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(!graph.containsKey(node1) || !graph.containsKey(node2)) return false;
        return graph.get(node1).hasNi(node2);
    }

    /**
     * override method
     * adding a new node to the graph,
     * only of not already in it.
     * as the graph changes, modeCount is increased by 1.
     * Complexity: O(1).
     * @param n - new node added to the graph
     */
    @Override
    public void addNode(node_data n) {
       if(!graph.containsKey(n.getKey())) {
           graph.put(n.getKey(), n);
           modeCount++;
       }
    }

    /**
     * override method
     * connecting 2 nodes given by their keys,
     * adding an edge only if both are in the graph and not already connected.
     * Complexity: O(1) - 3 instant checks, use of hasNi and addNi of NodeData class,
     * and simple increment of 2 parameters.
     * @param node1 - key of 1st node
     * @param node2 - key of 2nd node
     */
    @Override
    public void connect(int node1, int node2) {

        if(node1 == node2 || !graph.containsKey(node1) || !graph.containsKey(node2)) return;
        node_data n1 = graph.get(node1), n2 = graph.get(node2);
        if(!n1.hasNi(node2)) {
            n1.addNi(n2);
            n2.addNi(n1);
            this.edges++;
            modeCount++;
        }

    }

    /**
     * override method
     * getting all of graph's node as a Collection of node_data objects
     * as the graph's nodes are in a HashMap, simply returns Collection of values in graph.
     * Complexity: O(1).
     * @return Collection of all node_data objects in the graph
     */
    @Override
    public Collection<node_data> getV() {
        return graph.values();
    }

    /**
     * override method
     * getting all of nodes connected to the given node_id's node,
     * only of node_id key is in the graph.
     * Complexity: O(1) - instant check and getNi method of NodeData class.
     * @param node_id - an int representing a key
     * @return Collection of all nodes connected to node_id's node
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if(!graph.containsKey(node_id)) return null;
        return graph.get(node_id).getNi();
    }

    /**
     * override method
     * removing a node off the graph,
     * only if actually in the graph.
     * a bit complicated as the node itself needs to be removed from neighbors Collection of
     * every node it is connected to.
     * at last, removes the node itself off the graph's HashMap
     * also changing the edges value after deleting edges,
     * and increases the modeCount by 1.
     * Complexity: Worst Case - O(n) - if all nodes in graph connected to the given key's node.
     * @param key - key of the node to be deleted
     * @return null - if not in the graph, node_data - the node deleted off the graph
     */
    @Override
    public node_data removeNode(int key) {
        if(!graph.containsKey(key)) return null;
        Collection<node_data> ni = graph.get(key).getNi();
        node_data n = graph.get(key);
        for(node_data node : ni){
            node.removeNode(n);
        }
        graph.remove(key);
        edges = edges - n.getNi().size();
        modeCount++;
        return n;
    }

    /**
     * override method
     * removing the edge between 2 given node's keys,
     * only if exists.
     * checking whether 2 nodes are connected or not using the hasEdge method.
     * if connected, removing each of the nodes off other's neighbors Collection,
     * using removeNode method of NodeData class.
     * as one edge is removed, edges decreased by 1,
     * modeCount increased by 1.
     * Complexity: O(1) - using O(1) methods of this class and NodeData's class.
     * @param node1 - key of 1st node
     * @param node2 - key of 2nd node
     */
    @Override
    public void removeEdge(int node1, int node2) {
       if(this.hasEdge(node2, node1)) {
           node_data n1 = graph.get(node1);
           node_data n2 = graph.get(node2);
           n1.removeNode(n2);
           n2.removeNode(n1);
           modeCount++;
           edges--;
       }
    }

    /**
     * override method
     * getting the graph's size, referring to the amount of nodes it contains.
     * Complexity: O(1).
     * @return int - number of nodes in graph.
     */
    @Override
    public int nodeSize() {
        return graph.size();
    }

    /**
     * override method
     * getting edges value - referring to number of edges in the graph.
     * Complexity: O(1).
     * @return int - number of edges in graph.
     */
    @Override
    public int edgeSize() {
        return edges;
    }

    /**
     * getting modeCount value - referring to the number of changes made in graph.
     * Complexity: O(1).
     * @return int - number of changes made in graph
     */
    @Override
    public int getMC() {
        return modeCount;
    }

    /**
     * toString method auto-generated
     * Complexity: O(1).
     * @return String - all graph's parameters.
     */
    @Override
    public String toString() {
        return "Graph_DS{" +
                "graph=" + graph +
                ", edges=" + edges +
                ", modeCount=" + modeCount +
                '}';
    }

}
