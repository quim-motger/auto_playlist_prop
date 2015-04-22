package prop.domain;

// Per PROP, només necessitem multigraf no dirigit ponderat
// http://www.docjar.com/docs/api/org/jboss/util/graph/Graph.html
    /*
double default_weight;
public void setDefaultWeight(double weight);
addDirectedEdge(T from, T to, double weight);
addDirectedEdge(T from, T to); // with default weight
addEdge(T v1, T v2, double weight);
addEdge(T v1, T v2); // with default weight

     */

import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Graph is a generic mixed weighted pseudograph. That is, a weighted graph that can have multiple edges between
 two vertices and loops, and these edges can be either directed or undirected.
 * @param <T> the vertices type
 * @author Carles Garcia Cabot
 */
public class Graph<T> {
    private Integer nextVertex; // Contains next Vertex identifier to be used
    private HashMap<T, Integer> vToInt; // Each vertex has a unique identifier
    private HashSet<Integer> vertices;
    public Graph() {
        nextVertex = 0;
    }



/*
    public boolean isEmpty();
    public boolean addVertex(T v);
    public int size();
    public ArrayList<Vertex<T>> getVertices();
    public boolean removeVertex(Vertex<T> v);
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) // remove an arbitrary edge between the 2 vertices
    public void unvisitEdges();
    public void unvisitVertices()
*/
}



/*
public class Vertex<T> {
    protected T data;
    protected boolean visited;//public??
    private List<E extends Edge> incomingEdges;
    private List<E extends Edge> outgoingEdges;

    public Vertex<T>(
    T data
    );
    public Vertex<T>(
    T data, boolean visited
    );

    public T getData();

    public T setData();

    public addEdge(E extends Edge);

    public boolean hasEdge(Edge<T> e);

    public boolean remove(Edge<T> e);

    public int getDegree();

    public int getIndegree();

    public int getIndegree();

    public void visit();

    public void unvisit();
}
 */

/*
public class Edge<V extends Vertex> {

    protected V from;
    protected V to;
    protected double weight;
    private boolean visited;
    private boolean directed;
    private static defaultWeight;

}
*/