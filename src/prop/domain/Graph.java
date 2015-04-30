package prop.domain;

import java.util.*;

/**
 * Class Graph is a weighted graph that can have multiple edges between two vertices and loops.
 * These edges can be directed(arcs) or undirected.
 * @param <T> the vertices type
 * @author Carles Garcia Cabot
 */
public class Graph<T> {
    private HashMap<T, Integer> T_to_Int;
    private ArrayList<T> Int_to_T;
    private ArrayList<EdgeList> vertices; // Adjacency list
    private double defaultWeight;
    private int edgeCount;

    /* CONSTRUCTORS */
    public Graph() {
        T_to_Int = new HashMap<>();
        Int_to_T = new ArrayList<>();
        vertices = new ArrayList<>();
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
     * Adds a vertex to the graph.
     * @param v T vertex to add
     */
    public void addVertex(T v) {
        if (!T_to_Int.containsKey(v)) {
            T_to_Int.put(v, vertices.size());
            Int_to_T.add(v);
            vertices.add(new EdgeList());
        }
    }

    /**
     * Returns the original value associated to the integer
     * @param v integer vertex
     * @return T original value
     */
    public T getVertex(int v) {
        checkVertex(v);
        return Int_to_T.get(v);
    }

    /**
     * Indicates if the graph contains a vertex. Cost O(1)
     * @param v Vertex to search
     * @return True if found, false otherwise
     */
    public boolean contains(T v) {
        return T_to_Int.containsKey(v);
    }

    /**
     * Returns all original vertices in the graph
     * @return ArrayList of vertices in the graph
     */
    public ArrayList<T> getOriginalVertices() {
        ArrayList<T> l = new ArrayList<>();
        for (T t: T_to_Int.keySet()) l.add(t);
        return l;
    }

    /**
     * Returns all adjacent vertices to v ignoring the edge type.
     * @param v
     * @return Set containing all adjacent vertices to v
     */
    public LinkedHashSet<Integer> adjacentVertices(int v) {
        LinkedHashSet<Integer> ret = new LinkedHashSet<>();
        for (Integer va : vertices.get(v).undirected.keySet()) ret.add(va);
        if (!vertices.get(v).edgeLoops.isEmpty()) ret.add(v);
        for (Integer va : vertices.get(v).incoming.keySet()) ret.add(va);
        for (Integer va : vertices.get(v).outgoing.keySet()) ret.add(va);
        if (!vertices.get(v).arcLoops.isEmpty()) ret.add(v);
        return ret;
    }

    /**
     * Checks if a vertex is illegal. If so, throws an exception.
     * @param v int vertex to check
     */
    private void checkVertex(int v) {
        if ((v < 0) || v >= vertices.size()) throw new IllegalArgumentException("Illegal vertex");
    }

    /**
     * Adds an undirected edge between two vertices with defaultWeight. Useful to add "unweighted" edges.
     * @param v1 vertex 1
     * @param v2 vertex 2
     */
    public void addEdge(int v1, int v2) { addEdge(v1, v2, defaultWeight); }


    /**
     * Adds an undirected edge between two vertices
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param weight double weight
     */
    public void addEdge(int v1, int v2, double weight) {
        checkVertex(v1);
        checkVertex(v2);
        Double newEdge = new Double(weight);
        if (v1 == v2) vertices.get(v1).edgeLoops.add(newEdge);
        else {
            if (!hasEdge(v1,v2)) {
                vertices.get(v1).undirected.put(v2, new ArrayList<Double>());
                vertices.get(v2).undirected.put(v1, new ArrayList<Double>());
            }
            vertices.get(v1).undirected.get(v2).add(newEdge);
            vertices.get(v1).undirectedCount++;
            vertices.get(v2).undirected.get(v1).add(newEdge);
            vertices.get(v2).undirectedCount++;
        }
        ++edgeCount;
    }

    /**
     * Removes an arbitrary edge between v1 and v2
     * @param v1
     * @param v2
     * @return true if an edge existed and was removed
     */
    public boolean removeEdge(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        if (!hasEdge(v1,v2)) return false;
        if (v1 == v2) vertices.get(v1).edgeLoops.remove(0);
        else {
            Double removedEdge = vertices.get(v1).undirected.get(v2).get(0);
            vertices.get(v1).undirected.get(v2).remove(removedEdge);
            vertices.get(v1).undirectedCount--;
            vertices.get(v2).undirected.get(v1).remove(removedEdge);
            vertices.get(v2).undirectedCount--;
        }
        --edgeCount;
        return true;
    }

    /**
     * Adds an arc between two vertices with defaultWeight. Useful to add "unweighted" edges.
     * @param v1 origin
     * @param v2 destination
     */
    public void addArc(int v1, int v2) { addArc(v1, v2, defaultWeight); }

    /**
     * Adds an arc(directed edge) between two vertices.
     * @param v1 vertex origin
     * @param v2 vertex destination
     */
    public void addArc(int v1, int v2, double weight) {
        checkVertex(v1);
        checkVertex(v2);
        Double newEdge = new Double(weight);
        if (v1 == v2) vertices.get(v1).arcLoops.add(newEdge);
        else {
            if (!hasArc(v1, v2)) {
                vertices.get(v1).outgoing.put(v2, new ArrayList<Double>());
                vertices.get(v2).incoming.put(v1, new ArrayList<Double>());
            }
            vertices.get(v1).outgoing.get(v2).add(newEdge);
            vertices.get(v1).outgoingCount++;
            vertices.get(v2).incoming.get(v1).add(newEdge);
            vertices.get(v2).incomingCount++;
        }
        ++edgeCount;
    }

    /**
     * Removes an arbitrary arc between v1 and v2
     * @param v1 vertex origin
     * @param v2 vertex destination
     * @return true if an arc existed and was removed
     */
    public boolean removeArc(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        if (!hasArc(v1,v2)) return false;
        if (v1 == v2) vertices.get(v1).arcLoops.remove(0);
        else {
            Double removedEdge = vertices.get(v1).outgoing.get(v2).get(0);
            vertices.get(v1).outgoing.get(v2).remove(removedEdge);
            vertices.get(v1).outgoingCount--;
            vertices.get(v2).incoming.get(v1).remove(removedEdge);
            vertices.get(v2).incomingCount--;
        }
        --edgeCount;
        return true;
    }

    /**
     * Returns if there is an edge between two specified vertices.
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean hasEdge(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        if (v1 == v2) return !vertices.get(v1).edgeLoops.isEmpty();
        else return vertices.get(v1).undirected.containsKey(v2);
    }

    /**
     * Returns if there is an arc between two specified vertices. Cost O(1)
     * @param v1 vertex origin
     * @param v2 vertex destination
     * @return true if found, false otherwise
     */
    public boolean hasArc(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        if (v1 == v2) return !vertices.get(v1).arcLoops.isEmpty();
        else return vertices.get(v1).outgoing.containsKey(v2);
    }

    /**
     * Returns if two specified vertices are adjacent, that is, if there's an edge or an arc(ignoring the direction)
     * between the two vertices.
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean areAdjacent(int v1, int v2) {
        return hasEdge(v1,v2) || hasArc(v1,v2) || hasArc(v2,v1);
    }

    /**
     * Returns every undirected edge's weight between two vertices
     * @param v1
     * @param v2
     * @return ArrayList of Doubles containing the weights
     */
    public ArrayList<Double> weights(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        return vertices.get(v1).undirected.get(v2);
    }

    /**
     * Returns the weight of an arbitrary undirected edge between two vertices
     * @param v1
     * @param v2
     * @return double weight
     */
    public double weight(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        if (!vertices.get(v1).undirected.containsKey(v2)) throw new IllegalArgumentException("The vertices aren't adjacent");
        return vertices.get(v1).undirected.get(v2).get(0);
    }

    /**
     * Returns the number of undirected edges between two vertices
     * @param v1
     * @param v2
     * @return int number of edges
     */
    public int numberOfEdges(int v1, int v2) {
        checkVertex(v1);
        checkVertex(v2);
        return vertices.get(v1).undirected.get(v2).size();
    }
    /**
     * Returns the total number of edges of a vertex.
     * @param v
     * @return int total edges
     */
    public int totalEdges(int v) {
        checkVertex(v);
        return vertices.get(v).numberOfEdges();
    }

    /**
     * Returns the number of incident undirected edges in a vertex
     * @param v
     * @return int degree
     */
    public int getDegree(int v) {
        checkVertex(v);
        return vertices.get(v).undirectedCount;
    }

    /**
     * Returns the number of edge loops in a vertex
     * @param v
     * @return int loops
     */
    public int getLoopDegree(int v) {
        checkVertex(v);
        return vertices.get(v).edgeLoops.size();
    }

    /**
     * Returns the number of incoming arcs of a vertex
     * @param v
     * @return int indegree
     */
    public int getIndegree(int v) {
        checkVertex(v);
        return vertices.get(v).incomingCount;
    }

    /**
     * Returns the number of outgoing arcs of a vertex
     * @param v
     * @return int outdegree
     */
    public int getOutdegree(int v) {
        checkVertex(v);
        return vertices.get(v).outgoingCount;
    }

    /**
     * Returns the number of arc loops in a vertex
     * @param v
     * @return int arc loops
     */
    public int getArcLoopDegree(int v) {
        checkVertex(v);
        return vertices.get(v).arcLoops.size();
    }

    /**
     * Class EdgeList contains the adjacent vertices of a certain vertex and the edges that connect them.
     */
    private class EdgeList {
        /*The adjacent vertices are divided in 5 categories depending on the type of edge that connects them:
          * undirected, undirected loop (edgeLoops), incoming arcs, outgoing arcs and directed loops (arcLoops).
          * Each edge is represented as a Double that contains its weight.
          * */
        HashMap<Integer, ArrayList<Double>> undirected;
        ArrayList<Double> edgeLoops; //count as 2 incoming & 2 outgoing
        HashMap<Integer, ArrayList<Double>> incoming;
        HashMap<Integer, ArrayList<Double>> outgoing;
        ArrayList<Double> arcLoops; // count as 1 incoming & 1 outgoing
        int undirectedCount;
        int incomingCount;
        int outgoingCount;

        EdgeList() {
            undirected = new HashMap<>();
            edgeLoops = new ArrayList<>();
            incoming = new HashMap<>();
            outgoing = new HashMap<>();
            arcLoops = new ArrayList<>();
            undirectedCount = 0;
            incomingCount = 0;
            outgoingCount = 0;
        }

        int numberOfEdges() {
            return undirectedCount + edgeLoops.size() + incomingCount + outgoingCount + arcLoops.size();
        }
    }
}