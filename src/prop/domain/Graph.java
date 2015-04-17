package prop.domain;

// Per PROP, només necessitem multigraf no dirigit ponderat
// http://www.docjar.com/docs/api/org/jboss/util/graph/Graph.html
    /*
double default_weight;
setDefaultWeight;
addEdge(Vertex v1, Vertex v2, double w);
addEdge(Vertex v1, Vertex v2); // with default weight
addEdge(Edge e);
addDirectedEdge;

     */

/**
 * Class Graph represents a generic mixed weighted pseudograph. That is, a weighted graph that can have multiple edges between
 two vertices and loops, and these edges can be either directed or undirected.
 * @param <V> A subclass of {@link Vertex}
 * @param <E> A subclass of {@link Edge}
 * @author Carles Garcia Cabot
 */
public class Graph<V extends Vertex, E extends Edge> {
/*
    public boolean isEmpty();
    public boolean addVertex(Vertex<T> v);
    //public boolean addVertex(T);	no perque un vertex no es pot identificar per data. Un vertex té identitat propia i pertany a un conjunt, pertant no es pot repetir
    public int size();
    public ArrayList<Vertex<T>> getVertices();
    public boolean addEdge(Vertex<T> from, Vertex<T> to, int cost) throws IllegalArgumentException;
    public List<Edge<T>> getEdges();
    public boolean removeVertex(Vertex<T> v);
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) // remove an arbitrary edge between the 2 vertices
    public void unvisitEdges();
    public void unvisitVertices()
*/
}
