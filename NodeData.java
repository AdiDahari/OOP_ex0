package ex0;



import java.util.Collection;
import java.util.HashMap;

/**
 * this class implements node_data interface including all methods in it.
 * each node contains an unique key, set of neighbors, info string and tag value
 * @author Adi Dahari
 */
public class NodeData implements node_data {
    private final int  _key;
    private static int count = 0;
    private HashMap<Integer, node_data> _neighbors;
    private String _info;
    private int _tag;

    /**
     * empty constructor - builds a new node without any given values.
     * as default, key is initialized by a counter, so every new node created is given a new number as a key,
     * neighbors' HashMap is initialized empty,
     * info string initialized as null,
     * tag value is initialized as 0.
     */
    public NodeData(){
        _key = count++;
        _neighbors = new HashMap<>();
        _info = null;
        _tag = 0;

    }

    /**
     * same as the empty constructor but with a given key - a.
     * sets node's key to a
     * @param a - key of the new node
     */
    public NodeData(int a){
        _key = a;
        _info = null;
        _tag = 0;
        _neighbors = new HashMap<>();
    }

    /**
     * copy constructor for deep-copying a given node contains a key, neighbors map, info string and a tag.
     * Complexity: depends on the amount of neighbors of the given node. for n neighbors - O(n)
     * @param n - node to be deep-copied into this
     */
    public NodeData(node_data n){
        _key = n.getKey();
        _info = n.getInfo();
        _tag = n.getTag();
        _neighbors = new HashMap<>();
        if(n.getNi().size() > 0) {
            for (node_data node : n.getNi()) {
                _neighbors.put(node.getKey(), node);
            }
        }
    }


    /**
     * override method
     * returns this key value
     * Complexity: O(1)
     * @return the key (int) of this node
     */
    @Override
    public int getKey(){
        return this._key;
    }

    /**
     * override method
     * returns a Collection of nodes, contains all of this node's neighbors
     * Complexity: O(1) - shallow pointer of map's values.
     * @return node's neighbors Collection
     */
    @Override
    public Collection<node_data> getNi(){
    return _neighbors.values();
    }

    /**
     * override method
     * checks if a given node's key is in this node's neighbors Collection.
     * Complexity: O(1) - reference by key - like a "get" method.
     * @param key - key to search in neighbors map
     * @return if nodes are neighbors - true. else - false
     */
    @Override
    public boolean hasNi(int key){
        return _neighbors.containsKey(key);
    }

    /**
     * override method
     * creating an edge between a given node and this node
     * by adding the given node to the neighbors map of this node,
     * only if not already connected.
     * Complexity: O(1) - 2 instant checks, put method.
     * @param t = node to connect with this node
     */
    @Override
    public void addNi(node_data t){
        if(!this.hasNi(t.getKey()) && t.getKey() != _key) {
            this._neighbors.put(t.getKey(),t);
        }
    }

    /**
     * override method
     * removing an edge between a given node and this node,
     * only if there is an edge between them.
     * using "remove" method of HashMap class.
     * node = the node needed to be removed.
     * Complexity: O(1) - one instant check,
     * @param node - node to remove from neighbors map.
     */
    @Override
    public void removeNode(node_data node){
        if(_neighbors.containsKey(node.getKey())) {
            this._neighbors.remove(node.getKey());
        }
    }

    /**
     * override method
     * returns a string containing this node's info
     * Complexity: O(1).
     * @return info's String
     */
    @Override
    public String getInfo(){
        return this._info;
    }

    /**
     * override method
     * setting the info (String) of this node.
     * Complexity: O(1).
     * @param s = String containing new info to set
     */
    @Override
    public void setInfo(String s){
        this._info = s;
    }

    /**
     * override method
     * returns an int which is this node's tag.
     * Complexity: O(1)
     * @return tag's int
     */
    @Override
    public int getTag(){
        return this._tag;
    }

    /**
     * override method
     * setting the tag (int) of this node.
     * Complexity: O(1)
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t){
        this._tag = t;
    }

    /**
     * toString method auto-generated.
     * Complexity: O(1).
     * @return String with all parameters of this node
     */
    @Override
    public String toString() {
        return "NodeData{" +
                "_key=" + _key +
                ", _info='" + _info + '\'' +
                ", _tag=" + _tag +
                '}';
    }
}
