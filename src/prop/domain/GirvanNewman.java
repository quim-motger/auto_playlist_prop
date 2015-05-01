package prop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Girvan-Newman algorithm
 * @author oscar.manas
 */
public class GirvanNewman {

    private static final int infinity = 1000000000;
    private int n;
    private Graph<Song> graph; // Undirected, weighted graph
    private int components;
    private int[][] parents;
    private int[][] edgeScores;
    private int edges;
    private Pair<Integer,Integer> mbEdge;

    public AlgorithmOutput execute(Graph graph, int k) {
        this.graph = graph;
        n = graph.numberOfVertices();
        ArrayList<String> log = new ArrayList<>();
        components = calculateComponents();

        parents = floydWarshall();
        int i = 0;
        while (components < k && components < n) {
            StringBuilder entry = new StringBuilder();
            if (i == 0) entry.append("Initial components: " + components + "\n\n");
            entry.append("-- Round " + i + "\n");

            removeNext(entry);

            log.add(entry.toString() + "\n");
            System.out.print(log.get(log.size() - 1));
            ++i;
        }

        ArrayList<Graph> communities = getCommunities();
        return new AlgorithmOutput(communities,log);
    }

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

        entry.append("Edge removed: (" + mbEdge.first + "," + mbEdge.second + ")\n");
        graph.removeEdge(mbEdge.first,mbEdge.second);

        parents = floydWarshall();
        // If there's no path between the vertices of the edge we've just removed,
        // then there's one more connected component
        if (parents[mbEdge.first][mbEdge.second] == -1)
            ++components;
        entry.append("Components: " + components + "\n");
    }

    /**
     * Calculate the minimum path between all pairs of vertices
     * @return          a parents matrix
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
     * Calculate the betweenness of all edges
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

    private void processEdge(int i, int j) {
        if (parents[i][j] != -1) {
            if (j != i) {
                int k = parents[i][j];
                if (j >= k) {
                    edgeScores[j][k] += 1;
                    if (edgeScores[j][k] > edgeScores[mbEdge.first][mbEdge.second])
                        mbEdge = new Pair<>(j,k);
                }
                else {
                    edgeScores[k][j] += 1;
                    if (edgeScores[k][j] > edgeScores[mbEdge.first][mbEdge.second])
                        mbEdge = new Pair<>(k,j);
                }
                processEdge(i, k);
            }
        }
    }

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
                            G.addEdge(graph.getVertexT(v), graph.getVertexT(w),graph.weight(v,w));
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

