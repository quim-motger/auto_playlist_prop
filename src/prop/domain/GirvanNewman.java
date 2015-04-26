package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Girvan-Newman algorithm
 * @author oscar.manas
 */
public class GirvanNewman {

    private static final int infinity = 1000000000;
    private ArrayList<String> log;
    private int n;
    private int m;
    private ArrayList<Pair<Integer,Integer>>[] graph; // Undirected, weighted graph
    private int components;
    private int[][] parents;
    private int[][] edgeScores;
    private int edges;
    private Pair<Integer,Integer> mbEdge;

    public void execute(int n) {
        log = new ArrayList<>();
        components = calculateComponents();
        System.out.println("Components: " + components);
        for (int i = 0; i < n; ++i) {
            StringBuilder entry = new StringBuilder();
            entry.append("-- Round " + i + "\n");

            removeNext(entry);

            log.add(entry.toString() + "\n");
            System.out.print(log.get(log.size() - 1));
        }
    }

    private void removeNext(StringBuilder entry) {
        parents = floydWarshall(graph);

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

        entry.append("Edges: " + edges + "\n");
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
        removeEdge(mbEdge.first, mbEdge.second);
    }

    private int[][] floydWarshall(ArrayList<Pair<Integer,Integer>>[] graph) {
        // Path Matrix
        // D[i][j] is the weight of the shortest path from vertex i to vertex j
        int[][] D = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                D[i][j] = infinity;
        // Initialize with the Weight Matrix
        // D[i][j] is the weight of an edge between vertex i and vertex j
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < graph[i].size(); ++j) {
                int v = graph[i].get(j).first;
                int w = graph[i].get(j).second;
                D[i][v] = w;
            }
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

    private void removeEdge(int i, int j) {
        for (int k = 0; k < graph[i].size(); ++k) {
            if (graph[i].get(k).first == j) {
                graph[i].remove(k);
                break;
            }
        }
        for (int k = 0; k < graph[j].size(); ++k) {
            if (graph[j].get(k).first == i) {
                graph[j].remove(k);
                break;
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
                        for (Pair<Integer, Integer> w : graph[v]) {
                            S.push(w.first);
                        }
                    }
                }
            }
        }
        return c;
    }

    public void readGraph() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        graph = (ArrayList<Pair<Integer,Integer>>[])new ArrayList[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        int a, b, w;
        for (int i = 0; i < m; ++i) {
            a = in.nextInt();
            b = in.nextInt();
            w = in.nextInt();
            graph[a].add(new Pair<>(b,w));
            graph[b].add(new Pair<>(a,w));
        }
    }

    public void writeGraph() {
        System.out.println("Adjacency list:");
        for (ArrayList<Pair<Integer,Integer>> l : graph) {
            for (Pair<Integer,Integer> v : l) {
                System.out.print(v.first + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}

