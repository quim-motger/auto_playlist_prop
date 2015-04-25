package prop.domain;

// Per PROP, només necessitem multigraf no dirigit ponderat
// http://www.docjar.com/docs/api/org/jboss/util/graph/Graph.html

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class Graph is a generic mixed weighted pseudograph. That is, a weighted graph that can have multiple edges between
 two vertices and loops, and these edges can be either directed(arcs) or undirected. In addition, Using a default weight
 the graph can be considered unweighted.
 * @param <T> the vertices type
 * @author Carles Garcia Cabot
 */
public class Graph<T> {

    /* LinkedHashMap provides constant-time performance for the basic operations (add, contains and remove)
     * Performance is likely to be just slightly below that of HashMap, due to the added expense of maintaining the
     * linked list, with one exception: Iteration over the collection-views of a LinkedHashMap requires time
     * proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more
     * expensive, requiring time proportional to its capacity.
     */
    private LinkedHashMap<T, Vertex> vertices;
    // NOTA: els unweighted edges en comptes de tenir default_weight podria fer que els Edge tinguessin Double
    // i posar-los a null
    private double defaultWeight;
    private int edgeCount;

    /* CONSTRUCTORS */
    public Graph() {
        vertices = new LinkedHashMap<>();
        defaultWeight = 1;
        edgeCount = 0;
    }

    /* GETTERS */
    public double getDefaultWeight() { return defaultWeight; }

    /* SETTERS */
    public void setDefaultWeight(double w) { defaultWeight = w; }

    /* OTHER METHODS */
    public int numberOfVertices() { return vertices.size(); }
    public int numberOfEdges() { return edgeCount; }
    public boolean isEmpty() { return vertices.isEmpty(); }

    /**
     * Adds a vertex to the graph. Cost O(1)
     * @param v T vertex to add
     * @return False if the vertex was already in the graph, true if it was successfully added
     */
    public boolean addVertex(T v) {
        if (vertices.containsKey(v)) return false;
        vertices.put(v, new Vertex());
        return true;
    }

    /**
     * Removes a vertex from the graph.
     * @param v T vertex to remove
     * @return False if the vertex wasn't in the graph, true if it was successfully removed
     */
    public boolean removeVertex(T v) {
        // Todo: s'han d'eliminar totes les aparicions en EdgeList
        if (vertices.containsKey(v)) {
            for (T vertex : vertices.get(v).list.undirected.keySet()) {
                //vertices.get(vertex).list.undirected.
            }
            vertices.remove(v);
            return true;
        }
        return false;
    }

    /**
     * Indicates if the graph contains a vertex. Cost O(1)
     * @param v Vertex to search
     * @return True if found, false otherwise
     */
    public boolean contains(T v) {
        return vertices.containsKey(v);
    }

    /**
     * Returns all vertices in the graph
     * @return ArrayList of vertices in the graph
     */
    public ArrayList<T> getVertices() {
        ArrayList l = new ArrayList();
        for (T t: vertices.keySet()) l.add(t);
        return l;
    }

    /**
     * Adds an undirected edge between two vertices with defaultWeight. Useful to add "unweighted" edges. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if the edge was successfully added. False if a vertex doesn't exist.
     */
    public boolean addEdge(T v1, T v2)
    {
        return addEdge(v1, v2, defaultWeight);
    }

    /**
     * Adds an undirected weighted edge between two vertices. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param weight edge weight
     * @return true if the edge was successfully added. False if a vertex doesn't exist.
     */
    public boolean addEdge(T v1, T v2, double weight) {
        if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) return false;
        if (v1 == v2) { // check if it's a loop
            vertices.get(v1).list.edgeLoops.put(v1, new Edge(weight));
        }
        else {
            vertices.get(v1).list.undirected.put(v2, new Edge(weight));
            vertices.get(v2).list.undirected.put(v1, new Edge(weight));
        }
        return true;
    }

    public boolean removeEdge(T v1, T v2) {
        // todo
        return false;
    }

    /**
     * Adds an arc(directed edge) between two vertices with defaultWeight. Useful to add "unweighted" arcs. Cost O(1)
     * @param from vertex origin
     * @param to vertex destination
     * @return true if the arc was successfully added. False if a vertex doesn't exist.
     */
    public boolean addArc(T from, T to) {
        return addArc(from, to, defaultWeight);
    }

    /**
     * Adds a weighted arc(directed edge) between two vertices. Cost O(1)
     * @param from vertex origin
     * @param to vertex destination
     * @param weight arc weight
     * @return true if the arc was successfully added. False if a vertex doesn't exist.
     */
    public boolean addArc(T from, T to, double weight) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) return false;
        if (from == to) { // check if it's a loop
            vertices.get(from).list.arcLoops.put(from, new Edge(weight));
        }
        else {
            vertices.get(from).list.outgoing.put(to, new Edge(weight));
            vertices.get(to).list.incoming.put(from, new Edge(weight));
        }
        return true;
    }

    /**
     * Returns if there is an edge between two specified vertices. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean hasEdge(T v1, T v2) {
        if (v1 == v2) {// check only loops
            if (vertices.get(v1).list.edgeLoops.containsKey(v1)) return true;
        }
        else {
            if (vertices.get(v1).list.undirected.containsKey(v2)) return true;
        }
        return false;
    }

    /**
     * Returns if there is an arc between two specified vertices. Cost O(1)
     * @param from vertex origin
     * @param to vertex destination
     * @return true if found, false otherwise
     */
    public boolean hasArc(T from, T to) {
        if (from == to) {// check only loops
            if (vertices.get(from).list.arcLoops.containsKey(from)) return true;
        }
        else {
            if (vertices.get(from).list.outgoing.containsKey(to)) return true;
        }
        return false;
    }

    /**
     * Returns if two specified vertices are adjacent, that is, if there's an edge or an arc(ignoring the direction)
     * between the two vertices.
     * Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean areAdjacent(T v1, T v2) {
        return hasEdge(v1,v2) || hasArc(v1,v2) || hasArc(v2,v1);
    }



/*


    public boolean removeEdge(Vertex<T> from, Vertex<T> to) // remove an arbitrary edge between the 2 vertices
    public void unvisitEdges();
    public void unvisitVertices()
*/

    private class Vertex {
        //int id;
        boolean visited;
        EdgeList list;

        Vertex() {
            //id = nextId();
            visited = false;
            list = new EdgeList();
        }

        void visit() { visited = true; }
        void unvisit() { visited = false; }
    }
    private class Edge {
        //T vertex; // to or from
        double weight;
        boolean visited;

        Edge() {
            //vertex = null;
            weight = defaultWeight;
            visited = false;
        }

        Edge(double w) {
            //vertex = v;
            weight = w;
        }

        /*public int compareTo(Edge e) {
            return Integer.compare(vertices.get(this.vertex).id, vertices.get(e.vertex).id);
        }*/
    }

    private class EdgeList {
        // todo: AIXI NOMES HI POT HAVER UNA ARESTA ENTRE DOS VERTEXS. HAURIA DE SER <T, ArrayList<Edge>>
        HashMap<T, Edge> undirected;
        HashMap<T, Edge> edgeLoops; //count as 2 incoming & 2 outgoing
        HashMap<T, Edge> incoming;
        HashMap<T, Edge> outgoing;
        HashMap<T, Edge> arcLoops; // count as 1 incoming & 1 outgoing

        int size() {
            return undirected.size() + edgeLoops.size() + incoming.size() + outgoing.size() + arcLoops.size();
        }
    }
}



/*


    public boolean remove(Edge<T> e);

    public int getDegree();

    public int getIndegree();

    public int getIndegree();

    public void visit();

    public void unvisit();
}
 */
