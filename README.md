# ex0 - OOP

## node_data:
this interface is implemented by class NodeData,
each node contains:
1. key: unique key for every node, no 2 nodes with the same key.
2. neighbors: HashMap<Integer, node_data> representing the node's neighbors, which have an edge connection with this node, Integer - the neighbor's key, node_data - the node.
3. info: contains the node's info.
4. tag: used in the BFS alogrithm for finding the shortest path, used as marking the distance from the start node.

## graph:
this interface is implemented by class Graph_DS.
each graph is represented ny a HashMap contains all nodes in graph by their keys.
each graph contains:
1. HashMap<Integer, node_data>: contains all nodes in grapg by Integer - the node's key and node_data - the node itself.
2. edges: int that represents the number of edges in graph.
3. modeCount: int for counting the changes being made in the graph.

## graph_algorithms:
this inteface is implemented by class Graph_Algo.
each method uses the pre-made method in classes Graph_DS, NodeData.
Methods: isConnected, shortestPathDist, shortestPath uses 2 algorithm made in an additional class called BFS.

## BFS(Additional Class):
this cass contains 2 algorithms based on Breath's First Search algorithm shown in class.
using a queue made by LinkedList and a boolean HashMap,
created for preventing checking over the same node twice.
Methods:
1. bfsPath: as the queue keeps being added all nodes which have a path to the start node, each node checked is tagged with it's distance from the start node.
by that idea, i assure that each node gets the tag that represent the minium number of edges it takes to get to it from the start node.
also, the shortest path can be found by going backwards from the end node to the start node, in every node in the path it looks for the node with the tag value of itself minus 1.
2. bfsConnection: this BFS-based algorithm uses boolean HashMap for marking visited nodes. very simillary to the bfsPath method it uses an Queue,
insereted the visited nodes and used to get all of node's neighbors.
this method checks every possible path from a start node, by that assuring no node which have a connection to the start node is being checked.
in the end of this method it checks the size of the boolean map against the praph's nodeSize, if the size is equal - all nodes are connected.
else - not all nodes are connected and the boolean value "false" is returned.
