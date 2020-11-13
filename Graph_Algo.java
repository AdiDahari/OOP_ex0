package ex0;

import java.util.*;

/**
 * this class implements graph_algorithms interface including all methods in it.
 * using graph implementation by Graph_DS.
 * this class basically initializing a graph to run the algorithms on it.
 * gr - the graph all algorithm will run on.
 * @author Adi Dahari
 */
public class Graph_Algo implements graph_algorithms {

    private graph gr;

    /**
     * empty constructor - initializing a new graph using
     * Graph_DS constructor
     */
    public Graph_Algo(){
        gr = new Graph_DS();
    }

    /**
     * deep-copying graph to this graph.
     * using deep-copying constructors of Graph_DS and NodeData
     * @param g - graph to copy
     */
    public Graph_Algo(graph g){
        gr = new Graph_DS(g);
    }

    /**
     * override method
     * initializing a given graph as this graph.
     * @param g - graph to use in following algorithms
     */
    @Override
    public void init(graph g) {
        gr = g;
    }

    /**
     * a deep copy method of a graph, using deep-copying constructors
     * built in Graph_DS and NodeData
     * @return graph - a deep copy of this graph.
     */
    @Override
    public graph copy() {
        return new Graph_DS(gr);
    }

    /**
     * method for checking whether this graph is connected or not
     * using bfsConnection method made in additional class - BFS
     * for further info about the method look in BFS class.
     *
     * for start, checks if graph is single-noded or empty - if it is returns true.
     * than checking if the minimum amount of edges for a connected graph exists
     * (for a graph with n nodes - at least n-1 edges)
     * afterwards searching for a node to start with, by searching in loop
     * for a non-null node. when found one - initializng it as a start node.
     * @return if graph is connected - true. else - false.
     */
    @Override
    public boolean isConnected() {
        if(gr.nodeSize() == 0 || gr.nodeSize() == 1) return true;
        if(gr.edgeSize() < gr.nodeSize()-1 || (gr.edgeSize() == 0)) return false;
        node_data n = gr.getNode(0);
        int i = 1;
        while(n == null){
            n = gr.getNode(i);
            i++;
        }
        return BFS.bfsConnection(gr, n);
    }

    /**
     * override method
     * calculating the shortest path between 2 nodes: start, end.
     * using bfsPath method made in BFS class
     * see BFS class for further information about bfsPath method.
     * if source equals to dest - same node -> distance = 0.
     * if the returned collection of bfsPath method is null,
     * there is no connection between source and dest -> distance = -1.
     * else - src and dest are connected, the method returns the path's collection size - 1,
     * because the calculation of the path itself is counting the number of edges in path.
     * @param src - start node
     * @param dest - end (target) node
     * @return int - path length between src and dest.
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        if(src == dest) return 0;
        Collection<node_data> ans =  BFS.bfsPath(gr, src, dest);
        if(ans == null) return -1;
        return ans.size()-1;
    }

    /**
     * override method
     * as described above (in shortestPathDist),
     * this method uses same algorithm of BFS class,
     * only this time it returns the path Collection itself
     * for further info about bfsPath algorithm - see BFS class/
     * @param src - start node
     * @param dest - end (target) node
     * @return list of nodes on the path, by order of visit.
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        Collection<node_data> path = BFS.bfsPath(gr, src, dest);
        if(path == null) return new LinkedList<>();
        return new LinkedList<>(BFS.bfsPath(gr, src, dest));
    }

}
