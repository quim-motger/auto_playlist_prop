package prop.domain;

// Per PROP, només necessitem multigraf no dirigit ponderat
// http://www.docjar.com/docs/api/org/jboss/util/graph/Graph.html

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;

/**
 * Class Graph is a generic mixed weighted pseudograph. That is, a weighted graph that can have multiple edges between
 two vertices and loops, and these edges can be either directed(arcs) or undirected. In addition, Using a default weight
 the graph can be considered unweighted.
 * @param <T> the vertices type
 * @author Carles Garcia Cabot
 */
public class Graph<T> {
    // Old Implementation:
    //private Integer nextVertex; // Contains next Vertex identifier to be used
    //private HashMap<T, Integer> vToInt; // Each vertex has a unique identifier
    //private HashSet<Integer> vertices; // provisional
    //private HashSet<Integer> edges;

    // NOTA: els unweighted edges en comptes de tenir default_weight podria fer que els Edge tinguessin Double
    // i posar-los a null

    /* LinkedHashMap provides constant-time performance for the basic operations (add, contains and remove)
     * Performance is likely to be just slightly below that of HashMap, due to the added expense of maintaining the
     * linked list, with one exception: Iteration over the collection-views of a LinkedHashMap requires time
     * proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more
     * expensive, requiring time proportional to its capacity.
     */
    private LinkedHashMap<T, Vertex> vertices;
    private int vertexId;
    private double defaultWeight;
    private int edgeCount;

    /* CONSTRUCTORS */
    public Graph() {
        vertices = new LinkedHashMap<>();
        vertexId = 0;
        defaultWeight = 1;
        edgeCount = 0;
    }

    /* GETTERS */
    public double getDefaultWeight() { return defaultWeight; }

    /* SETTERS */
    public void setDefaultWeight(double w) { defaultWeight = w; }

    /* OTHER METHODS */
    private int nextId() {
        ++vertexId;
        return vertexId;
    }
    public int numberOfVertices() { return vertices.size(); }
    public int numberOfEdges() { return edgeCount; }
    public boolean isEmpty() { return vertices.isEmpty(); }

    /**
     * Adds a vertex to the graph
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
        if (vertices.containsKey(v)) {
            vertices.remove(v);
            return true;
        }
        return false;
    }

    /**
     * Indicates if the graph contains a vertex
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

    public boolean addEdge(T v1, T v2)
    {
        if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) return false;
        if (v1 == v2) { // check if it's a loop
            vertices.get(v1).list.edgeLoops.add(new Edge(v1, defaultWeight));
        }
        else {
            vertices.get(v1).list.undirected.add(new Edge(v2, defaultWeight));
            vertices.get(v2).list.undirected.add(new Edge(v1, defaultWeight));
        }
        return true;
    }

    public boolean addEdge(T v1, T v2, double weight) {
        if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) return false;
        if (v1 == v2) { // check if it's a loop
            vertices.get(v1).list.edgeLoops.add(new Edge(v1, weight));
        }
        else {
            vertices.get(v1).list.undirected.add(new Edge(v2, weight));
            vertices.get(v2).list.undirected.add(new Edge(v1, weight));
        }
        return true;
    }

    public boolean addArc(T from, T to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) return false;
        if (from == to) { // check if it's a loop
            vertices.get(from).list.arcLoops.add(new Edge(from, defaultWeight));
        }
        else {
            vertices.get(from).list.outgoing.add(new Edge(to, defaultWeight));
            vertices.get(to).list.incoming.add(new Edge(from, defaultWeight));
        }
        return true;
    }

    public boolean addArc(T from, T to, double weight) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) return false;
        if (from == to) { // check if it's a loop
            vertices.get(from).list.arcLoops.add(new Edge(from, weight));
        }
        else {
            vertices.get(from).list.outgoing.add(new Edge(to, weight));
            vertices.get(to).list.incoming.add(new Edge(from, weight));
        }
        return true;
    }

    private boolean searchVertex(TreeSet<Edge> e, int id) {
        return false;
    }

    public boolean hasEdge(T v1, T v2) {
        if (v1 == v2) {// check only loops
            if (searchVertex(vertices.get(v1).list.edgeLoops, vertices.get(v1).id)) return true;
        }
        else {
            // check what vertex has less undirected edges to optimize search
            T va, vb;
            if (vertices.get(v1).list.undirected.size() < vertices.get(v2).list.undirected.size()) {
                va = v1;
                vb = v2;
            }
            else {
                va = v2;
                vb = v1;
            }
            for (Edge e : vertices.get(va).list.undirected) {
                if (e.vertex == vb) return true;
            }
        }
        return false;
    }

    public boolean hasArc(T from, T to) {
        if (from == to) {// check only loops
            for (Edge e : vertices.get(from).list.arcLoops) {
                if (e.vertex == from) return true;
            }
        }
        else {
            // check what vertex has less incoming or outgoing arcs to optimize search
            if (vertices.get(from).list.outgoing.size() < vertices.get(to).list.incoming.size()) {
                for (Edge e : vertices.get(from).list.outgoing) {
                    if (e.vertex == to) return true;
                }
            }
            else {
                for (Edge e : vertices.get(to).list.incoming) {
                    if (e.vertex == from) return true;
                }
            }
        }
        return false;
    }

    public boolean areAdjacent(T v1, T v2) {
        if (v1 == v2) {// check only loops
            for (Edge e : vertices.get(v1).list.edgeLoops) {
                if (e.vertex == v1) return true;
            }
            for (Edge e : vertices.get(v1).list.arcLoops) {
                if (e.vertex == v1) return true;
            }
        }
        else {
            // check what vertex has less undirected edges to optimize search
            T va, vb;
            if (vertices.get(v1).list.undirected.size() < vertices.get(v2).list.undirected.size()) {
                va = v1;
                vb = v2;
            }
            else {
                va = v2;
                vb = v1;
            }
            for (Edge e : vertices.get(va).list.undirected) {
                if (e.vertex == vb) return true;
            }
            // check what vertex has less arcs to optimize search
            int v1in_v2in = vertices.get(v1).list.incoming.size() + vertices.get(v2).list.incoming.size();
            int v1out_v2out = vertices.get(v1).list.outgoing.size() + vertices.get(v2).list.outgoing.size();
            int v1in_v1out = vertices.get(v1).list.incoming.size() + vertices.get(v1).list.outgoing.size();
            int v2in_v2out = vertices.get(v2).list.incoming.size() + vertices.get(v2).list.outgoing.size();
            int min = Math.min(Math.min(v1in_v2in, v1out_v2out), Math.min(v1in_v1out, v2in_v2out));
            if (min == v1in_v2in) {
                for (Edge e : vertices.get(v1).list.incoming) {
                    if (e.vertex == v2) return true;
                }
                for (Edge e : vertices.get(v2).list.incoming) {
                    if (e.vertex == v1) return true;
                }
            }
            else if (min == v1out_v2out) {
                for (Edge e : vertices.get(v1).list.outgoing) {
                    if (e.vertex == v2) return true;
                }
                for (Edge e : vertices.get(v2).list.outgoing) {
                    if (e.vertex == v1) return true;
                }
            }
            else if (min == v1in_v1out) {
                for (Edge e : vertices.get(v1).list.incoming) {
                    if (e.vertex == v2) return true;
                }
                for (Edge e : vertices.get(v1).list.outgoing) {
                    if (e.vertex == v2) return true;
                }
            }
            else if (min == v2in_v2out) {
                for (Edge e : vertices.get(v2).list.incoming) {
                    if (e.vertex == v1) return true;
                }
                for (Edge e : vertices.get(v2).list.outgoing) {
                    if (e.vertex == v1) return true;
                }
            }
        }
        return false;
    }



/*


    public boolean removeEdge(Vertex<T> from, Vertex<T> to) // remove an arbitrary edge between the 2 vertices
    public void unvisitEdges();
    public void unvisitVertices()
*/

    private class Vertex {
        int id;
        boolean visited;
        EdgeList list;

        Vertex() {
            id = nextId();
            visited = false;
            list = new EdgeList();
        }

        void visit() { visited = true; }
        void unvisit() { visited = false; }
    }
    private class Edge implements Comparable<Edge> {
        T vertex; // to or from
        double weight;
        boolean visited;

        Edge() {
            vertex = null;
            weight = defaultWeight;
            visited = false;
        }

        Edge(T v, double w) {
            vertex = v;
            weight = w;
        }

        public int compareTo(Edge e) {
            return Integer.compare(vertices.get(this.vertex).id, vertices.get(e.vertex).id);
        }
    }

    private class EdgeList {
        TreeSet<Edge> undirected;
        TreeSet<Edge> edgeLoops; //count as 2 incoming & 2 outgoing
        TreeSet<Edge> incoming;
        TreeSet<Edge> outgoing;
        TreeSet<Edge> arcLoops; // count as 1 incoming & 1 outgoing

        int size() {
            return undirected.size() + edgeLoops.size() + incoming.size() + outgoing.size() + arcLoops.size();
        }
    }
}



/*


    public boolean hasEdge(Edge<T> e);

    public boolean remove(Edge<T> e);

    public int getDegree();

    public int getIndegree();

    public int getIndegree();

    public void visit();

    public void unvisit();
}
 */
