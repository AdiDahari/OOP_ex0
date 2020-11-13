package ex0;


import java.util.*;

/**
 * this class is an additional class for Breadth First Search methods used in Graph_Algo
 * 2 algorithms:
 *  bfsPath
 *  bfsConnection
 * @author Adi Dahari
 */

public class BFS {
    //************Methods************

    /**
     * a method for getting the path between 2 nodes, if exists.
     * using the Breadth First Search algorithm's idea shown in class.
     * using a boolean map for marking the visited nodes - for not going over them again
     * each node checked is given a tag refers to it's distance from start node,
     * for example - start node's tag is 0, all of start's neighbors' tags are 1, and so on...
     * Complexity: Worst Case - O(V+E) - V = number of nodes in graph, E = number of edges,
     * if we search for the longest path in the graph
     * @param g - graph initialized in graph_algorithms, implemented by Graph_Algo
     * @param start - key of the node to start with.
     * @param end - key of destination node.
     * @return Collection contains the path(nodes) from start to end.
     */
    public static Collection<node_data> bfsPath(graph g, int start, int end) {
        HashMap<Integer, Boolean> visited = new HashMap<>();    //boolean HashMap for marking visited nodes
        LinkedList<node_data> q = new LinkedList<>();           //LinkedList used as Queue
        visited.put(start, true);                               //initializing the start node as visited
        node_data dest = null;                                  //initializing the destination node as null -
                                                                // will be changed if start is connected to end.

        q.add(g.getNode(start));
        while(!q.isEmpty()){                        //as the Queue will be added by each of the connected nodes,
                                        //when finished the Queue will be empty, if dest node is not connected
            node_data n = q.poll();
            Collection<node_data> adj = n.getNi();  //getting every one of the node's neighbors,
                                                    // for every node added to the Queue
            for(node_data node : adj){              //for-each loop going through the neighbors
                if(!visited.containsKey(node.getKey()) || !visited.get(node.getKey())) {
                    visited.put(node.getKey(), true);   //every node of neighbors Collection added to visited map
                    node.setTag(n.getTag() + 1);        //tag is set as predecessor's tag+1, will help initializing path
                    q.add(node);
                    if(node.getKey() == end){           //if reached end's key,  setting dest node as current node and break
                        dest = node;
                        break;
                    }
                }
            }

        }
        /*
        next lines of code are for initializing the path between start and end,
        the trick here is to fill it from the end to the start - like a Stack structure.
        here i use the tags for defining the right path by descending tag values,
        means adding every node that it's tag is 1 less than the node that just been initialized
        in that way i make sure it is the shortest path, though there might be more than one.
         */
        if(dest == null) return null;   //if dest hasn't been changed to a specific node, this method will return null
                                        // means there is no path between start and end
        LinkedList<node_data> path = new LinkedList<>();
        path.addFirst(dest);
        node_data  curr = dest;
        while(curr.getKey() != start){
            Collection<node_data> ni = curr.getNi();
            for(node_data it : ni){
                if(it.getTag() == curr.getTag()-1){
                    path.addFirst(it);
                    curr = it;
                    break;
                }
            }
        }
        return path;
    }

    /**
     * a BFS'd based method for checking whether a given graph is connected or not,
     * using a LinkedList - as Queue and a boolean map for marking visited nodes.
     * using a very similar idea to the bfsPath execution, without an end node.
     * for further info about the idea look at bfsPath doc.
     * Complexity: O(V+E) - V = number of nodes in graph, E = number of edges in graph.
     * @param g - graph_algorithms implemented by Graph_Algo
     * @param start - a start node for the BFS method
     * @return if graph is connected - true. else - false
     */
    public static boolean bfsConnection(graph g, node_data start) {
        LinkedList<node_data> q = new LinkedList<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        q.add(start);
        visited.put(start.getKey(), true);
        while (!q.isEmpty()) {
            node_data n = q.poll();
            for (node_data edge : g.getV(n.getKey())) {
                if(g.getNode(edge.getKey()) != null) {
                    if (visited.containsKey(edge.getKey()) && !visited.get(edge.getKey())) {
                        q.add(edge);
                        visited.put(edge.getKey(), true);
                    } else if (visited.get(edge.getKey()) == null || !visited.get(edge.getKey())) {
                        visited.put(edge.getKey(), true);
                        q.add(edge);
                    }
                }
            }
        }
        return visited.size() == g.nodeSize();  //checking if the visited map size equals to number of nodes in graph
                                                //if is - the whole graph is connected
    }

}
