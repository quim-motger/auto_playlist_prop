package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Girvan-Newman algorithm
 * @author oscar.manas
 */
public class GirvanNewman {

    private static final int infinity = 1000000000;
    private ArrayList<Pair<Integer,Integer>>[] graph; // Undirected, weighted graph
    private int[][] parents;
    private int[][] edgeScores;
    private int edges;
    private Pair<Integer,Integer> mbEdge;
    private int n;
    private int m;

    public void execute() {
        parents = floydWarshall(graph);

        System.out.println("Predecessor Matrix:");
        for (int[] p : parents) {
            for (int i : p)
                System.out.print(i + " ");
            System.out.print("\n");
        }
        System.out.print("\n");

        edges = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                ArrayList<Integer> path = new ArrayList<>();
                path(i, j, path);
                System.out.println("Path from " + i + " to " + j + ":");
                if (path.size() == 0)
                    System.out.println("N/A");
                else {
                    for (int k : path)
                        System.out.print(k + " ");
                    System.out.print("\n");
                }
            }
        }
        System.out.print("\n");

        System.out.println("Edges: " + edges + "\n");

        calculateEdgeBetweenness();
        System.out.println("Edge scores:");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                System.out.print(edgeScores[i][j] + " ");
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.println("Edge removed: (" + mbEdge.first + "," + mbEdge.second + ")");
        removeEdge(mbEdge.first,mbEdge.second);
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

