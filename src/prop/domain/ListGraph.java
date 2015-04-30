package prop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class Graph is a weighted graph that can have multiple edges between two vertices and loops.
 * These edges can be either directed(arcs) or undirected.
 * @param <T> the vertices type
 * @author Carles Garcia Cabot
 */
public class ListGraph<T> {

    private HashMap<T, Integer> T_to_Int;
    private HashMap<Integer, T> Int_to_T;
    private ArrayList<EdgeList> adjacencyList;
    private double defaultWeight;
    private int edgeCount;

    private boolean illegalVertex(int v) { return (v < 0) || v >= adjacencyList.size(); }
    /* CONSTRUCTORS */
    public ListGraph() {
        T_to_Int = new HashMap<>();
        Int_to_T = new HashMap<>();
        adjacencyList = new ArrayList<>();
        defaultWeight = 1;
        edgeCount = 0;
    }

    /* GETTERS */
    public double getDefaultWeight() { return defaultWeight; }

    /* SETTERS */
    public void setDefaultWeight(double w) { defaultWeight = w; }

    /* OTHER METHODS */
    public int numberOfVertices() { return adjacencyList.size(); }
    public int numberOfEdges() { return edgeCount; }
    public boolean isEmpty() { return adjacencyList.isEmpty(); }

    /**
     * Adds a vertex to the graph.
     * @param v T vertex to add
     */
    public void addVertex(T v) {
        if (!T_to_Int.containsKey(v)) {
            T_to_Int.put(v, adjacencyList.size());
            Int_to_T.put(adjacencyList.size(), v);
            adjacencyList.add(new EdgeList());
        }
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


    public ArrayList<Integer> adjacentVertices(int v) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (Edge e : adjacencyList.get(v).undirected) {
            ret.add((e.v1 == v) ? e.v1 : e.v2);
        }
        return ret;
    }

    /**
     * Adds an undirected edge between two vertices with defaultWeight. Useful to add "unweighted" edges. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if the edge was successfully added. False if a vertex doesn't exist.
     */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, defaultWeight);
    }

    /**
     * Adds an undirected weighted edge between two vertices. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param weight edge weight
     * @return true if the edge was successfully added. False if a vertex doesn't exist.
     */
    public void addEdge(int v1, int v2, double weight) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        else {
            Edge newEdge = new Edge(v1,v2,weight);
            if (v1 == v2) { adjacencyList.get(v1).edgeLoops.add(newEdge); }
            else {
                adjacencyList.get(v1).edgeLoops.add(newEdge);
                adjacencyList.get(v2).edgeLoops.add(newEdge);
            }
            ++edgeCount;
        }
    }

    // this removes an arbitrary edge. If edges where ordered, it could remove the last edge added
    public void removeEdge(int v1, int v2) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        else {
            if (v1 == v2) {
                if (adjacencyList.get(v1).edgeLoops.isEmpty())
                    throw new IllegalArgumentException("Tried to remove edge between two non-adjacent vertices");
                adjacencyList.get(v1).edgeLoops.remove(0);
            }
            else {
                Edge removedEdge = null;
                for (Edge e : adjacencyList.get(v1).undirected) {
                    if ((e.v1 == v1 && e.v2 == v2) || e.v1 == v2 && e.v2 == v1) {
                        removedEdge = e;
                        break;
                    }
                }
                if (removedEdge == null)
                    throw new IllegalArgumentException("Tried to remove edge between two non-adjacent vertices");
                adjacencyList.get(v1).undirected.remove(removedEdge);
                adjacencyList.get(v2).undirected.remove(removedEdge);
            }
            --edgeCount;
        }
    }

    /**
     * Adds an arc(directed edge) between two vertices with defaultWeight. Useful to add "unweighted" arcs. Cost O(1)
     * @param v1 vertex origin
     * @param v2 vertex destination
     */
    public void addArc(int v1, int v2) {
        addArc(v1, v2, defaultWeight);
    }

    /**
     * Adds a weighted arc(directed edge) between two vertices. Cost O(1)
     * @param v1 vertex origin
     * @param v2 vertex destination
     * @param weight arc weight
     */
    public void addArc(int v1, int v2, double weight) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        else {
            Edge newEdge = new Edge(v1,v2,weight);
            if (v1 == v2) { adjacencyList.get(v1).arcLoops.add(newEdge); }
            else {
                adjacencyList.get(v1).outgoing.add(newEdge);
                adjacencyList.get(v2).incoming.add(newEdge);
            }
            ++edgeCount;
        }
    }

    // this removes an arbitrary edge. If edges where ordered, it could remove the last edge added
    public void removeArc(int v1, int v2) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        else {
            if (v1 == v2) {
                if (adjacencyList.get(v1).arcLoops.isEmpty())
                    throw new IllegalArgumentException("Tried to remove edge between two non-adjacent vertices");
                adjacencyList.get(v1).arcLoops.remove(0);
            }
            else {
                Edge removedEdge = null;
                for (Edge e : adjacencyList.get(v1).outgoing) {
                    if ((e.v1 == v1 && e.v2 == v2) || e.v1 == v2 && e.v2 == v1) {
                        removedEdge = e;
                        break;
                    }
                }
                if (removedEdge == null)
                    throw new IllegalArgumentException("Tried to remove edge between two non-adjacent vertices");
                adjacencyList.get(v1).outgoing.remove(removedEdge);
                adjacencyList.get(v2).incoming.remove(removedEdge);
            }
            --edgeCount;
        }
    }

    /**
     * Returns if there is an edge between two specified vertices. Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean hasEdge(int v1, int v2) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        if (v1 == v2) return !adjacencyList.get(v1).edgeLoops.isEmpty();
        else {
            for (Edge e : adjacencyList.get(v1).undirected) {
                if ((e.v1 == v1 && e.v2 == v2) || e.v1 == v2 && e.v2 == v1) return true;
            }
            return false;
        }
    }

    /**
     * Returns if there is an arc between two specified vertices. Cost O(1)
     * @param v1 vertex origin
     * @param v2 vertex destination
     * @return true if found, false otherwise
     */
    public boolean hasArc(int v1, int v2) {
        if (illegalVertex(v1) || illegalVertex(v2)) throw new IllegalArgumentException();
        if (v1 == v2) return !adjacencyList.get(v1).arcLoops.isEmpty();
        else {
            for (Edge e : adjacencyList.get(v1).outgoing) {
                if ((e.v1 == v1 && e.v2 == v2) || e.v1 == v2 && e.v2 == v1) return true;
            }
            return false;
        }
    }

    /**
     * Returns if two specified vertices are adjacent, that is, if there's an edge or an arc(ignoring the direction)
     * between the two vertices.
     * Cost O(1)
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if found, false otherwise
     */
    public boolean areAdjacent(int v1, int v2) {
        return hasEdge(v1,v2) || hasArc(v1,v2) || hasArc(v2,v1);
    }

    /**
     * Returns every edge's weight between two vertices
     * @param v1
     * @param v2
     * @return
     */
    /*public ArrayList<Double> weights(T v1, T v2) {
        ArrayList<Double> ws = new ArrayList<>();
        for (Edge e : vertices.get(v1).list.undirected.get(v2)) {
            ws.add(e.weight);
        }
        return ws;
    }
    */


    public double weight(int v1, int v2) {
        if (adjacencyList.get(v1).undirected.isEmpty()) throw new IllegalArgumentException();
        return adjacencyList.get(v1).undirected.get(0).weight;
    }

    // s'ha de fer tenint en compte els loops
    /*public int getDegree(T v) {
        return vertices.get(v).list.undirectedCount();
    }

    public int getIndegree(T v) {
        return vertices.get(v).list.incomingCount();
    }

    public int getOutdegree(T v) {
        return vertices.get(v).list.outgoingCount();
    }
*/

    private class Edge {
        int v1;
        int v2;
        double weight;

        Edge(int v1, int v2, double w) {
            this.v1 = v1;
            this.v2 = v2;
            weight = w;
        }
    }

    private class EdgeList {
        ArrayList<Edge> undirected;
        ArrayList<Edge> edgeLoops; //count as 2 incoming & 2 outgoing
        ArrayList<Edge> incoming;
        ArrayList<Edge> outgoing;
        ArrayList<Edge> arcLoops; // count as 1 incoming & 1 outgoing

        EdgeList() {
            undirected = new ArrayList<>();
            edgeLoops = new ArrayList<>();
            incoming = new ArrayList<>();
            outgoing = new ArrayList<>();
            arcLoops = new ArrayList<>();
        }

        int size() {
            return undirected.size() + edgeLoops.size() + incoming.size() + outgoing.size() + arcLoops.size();
        }
    }
}