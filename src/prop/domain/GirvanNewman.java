package prop.domain;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Girvan-Newman algorithm.
 * @author oscar.manas
 */
public class GirvanNewman extends Algorithm {

    private static final double infinity = Double.MAX_VALUE;
    private int n;
    private Graph<Song> graph; // Undirected, weighted graph
    private int components;
    private int[][] parents;
    private int[][] edgeScores;
    private int edges;
    private Pair<Integer,Integer> mbEdge;

    /**
     * Executes the Girvan-Newman algorithm.
     * @param _graph the graph from which communities are calculated
     * @param k     the correlation measure; the higher the value, the more correlated the elements within a
     *              community will be, but communities will be smaller
     * @return      an {@code AlgorithmOutput} object, including a list with communities and an execution log
     */
    public AlgorithmOutput execute(Graph _graph, int k) {
        graph = _graph;
        n = graph.numberOfVertices();
        ArrayList<String> log = new ArrayList<>();
        components = calculateComponents();
        if (components >= k) log.add("There are " + components + " components already!\n\n");

        parents = floydWarshall();
        int i = 0;
        while (components < k && components < n) {
            StringBuilder entry = new StringBuilder();
            if (i == 0) entry.append("Initial components: " + components + "\n\n");
            entry.append("-- Round " + i + "\n");

            removeNext(entry);

            log.add(entry.toString() + "\n");
            ++i;
        }

        ArrayList<Graph> communities = getCommunities();
        return new AlgorithmOutput(communities,log);
    }

    /**
     * Recalculates the betweenness score for all edges and removes the edge with the highest betweenness score.
     * @param entry an entry of the execution log
     */
    private void removeNext(StringBuilder entry) {
        entry.append("Predecessor Matrix:\n");
        for (int[] p : parents) {
            for (int i : p)
                entry.append(i + " ");
            entry.append("\n");
        }
        entry.append("\n");

        edges = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                ArrayList<Integer> path = new ArrayList<>();
                path(i, j, path);
                entry.append("Path from " + i + " to " + j + ":\n");
                if (path.size() == 0)
                    entry.append("N/A\n");
                else {
                    for (int k : path)
                        entry.append(k + " ");
                    entry.append("\n");
                }
            }
        }
        entry.append("\n");

        entry.append("Sum of all minimum path edges: " + edges + "\n");
        entry.append("\n");

        calculateEdgeBetweenness();
        entry.append("Edge scores:\n");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                entry.append(edgeScores[i][j] + " ");
            entry.append("\n");
        }
        entry.append("\n");

        entry.append("Edge removed: (" + mbEdge.first() + "," + mbEdge.second() + ")\n");
        graph.removeEdge(mbEdge.first(),mbEdge.second());

        parents = floydWarshall();
        // If there's no path between the vertices of the edge we've just removed,
        // then there's one more connected component
        if (parents[mbEdge.first()][mbEdge.second()] == -1)
            ++components;
        entry.append("Components: " + components + "\n");
    }

    /**
     * The Floyd-Warshall algorithm. Calculates the minimum path between all pairs of vertices.
     * @return a Predecessor Matrix
     */
    private int[][] floydWarshall() {
        // Path Matrix
        // D[i][j] is the weight of the shortest path from vertex i to vertex j
        double[][] D = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                D[i][j] = infinity;
        // Initialize with the Weight Matrix
        // D[i][j] is the weight of an edge between vertex i and vertex j
        for (int i = 0; i < n; ++i) {
            for (int j : graph.adjacentVertices(i))
                D[i][j] = graph.weight(i, j);
            // The path from a vertex to itself has weight 0
            D[i][i] = 0;
        }

        // Predecessor Matrix
        // P[i][j] is the predecessor of vertex j on a shortest path from vertex i
        int[][] P = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (D[i][j] == infinity)
                    P[i][j] = -1;
                else
                    P[i][j] = i;
            }
            // We don't have to go anywhere to go from i to i
            P[i][i] = i;
        }

        // Compute successively better paths through vertex k
        for (int k = 0; k < n; ++k) {
            // Do so between each possible pair of vertices
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < n; ++i) {
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = P[k][j];
                    }
                }
            }
        }
        return P;
    }

    /**
     * Calculates the path between vertices {@code i} and {@code j} from the Predecessor Matrix.
     * @param i     a vertex
     * @param j     a vertex
     * @param path  the path between vertices {@code i} and {@code j}
     */
    private void path(int i, int j, ArrayList<Integer> path) {
        if (parents[i][j] != -1) {
            if (j == i) {
                path.add(j);
            } else {
                path(i, parents[i][j], path);
                path.add(j);
                ++edges;
            }
        }
    }

    /**
     * Calculates the betweenness score of all edges.
     */
    private void calculateEdgeBetweenness() {
        edgeScores = new int[n][n]; // By default, elements are initialized to 0
        mbEdge = new Pair<>(0,0);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                processEdge(i, j);
            }
        }
    }

    /**
     * Calculates the betweenness score between vertices {@code i} and {@code j}.
     * @param i a vertex
     * @param j a vertex
     */
    private void processEdge(int i, int j) {
        if (parents[i][j] != -1) {
            if (j != i) {
                int k = parents[i][j];
                if (j >= k) {
                    edgeScores[j][k] += 1;
                    if (edgeScores[j][k] > edgeScores[mbEdge.first()][mbEdge.second()])
                        mbEdge = new Pair<>(j,k);
                }
                else {
                    edgeScores[k][j] += 1;
                    if (edgeScores[k][j] > edgeScores[mbEdge.first()][mbEdge.second()])
                        mbEdge = new Pair<>(k,j);
                }
                processEdge(i, k);
            }
        }
    }

    /**
     * Calculates the number of connected components in the graph. A classic depth-first search.
     * @return  the number of connected components
     */
    private int calculateComponents() {
        Stack<Integer> S = new Stack<>();
        boolean[] vis = new boolean[n];
        for (boolean b : vis)
            b = false;

        int c = 0;
        for (int u = 0; u < n; ++u) {
            if (!vis[u]) {
                ++c;
                S.push(u);
                while (!S.empty()) {
                    int v = S.pop();
                    if (!vis[v]) {
                        vis[v] = true;
                        for (int w : graph.adjacentVertices(v)) {
                            S.push(w);
                        }
                    }
                }
            }
        }
        return c;
    }

    /**
     * Separates the communities of the original graph in different graphs.
     * @return an array of graphs containing all the communities in the original graph
     */
    public ArrayList<Graph> getCommunities() {
        ArrayList<Graph> communities = new ArrayList<>();
        Stack<Integer> S = new Stack<>();
        boolean[] visVertices = new boolean[n];
        boolean[][] visEdges = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            visVertices[i] = false;
            for (int j = 0; j < n; ++j)
                visEdges[i][j] = false;
        }

        for (int u = 0; u < n; ++u) {
            if (!visVertices[u]) {
                Graph G = new Graph<Song>();
                S.push(u);
                G.addVertex(graph.getVertexT(u));
                visVertices[u] = true;
                while (!S.empty()) {
                    int v = S.pop();
                    for (int w : graph.adjacentVertices(v)) {
                        if (!visVertices[w]) {
                            S.push(w);
                            G.addVertex(graph.getVertexT(w));
                            visVertices[w] = true;
                        }
                        if (!visEdges[v][w]) {
                            // We need to add the edge that way because we don't know the vertex identifiers in the new graph
                            G.addEdgeT(graph.getVertexT(v), graph.getVertexT(w), graph.weight(v, w));
                            visEdges[v][w] = true;
                            visEdges[w][v] = true;
                        }
                    }
                }
                communities.add(G);
            }
        }
        return communities;
    }

}

