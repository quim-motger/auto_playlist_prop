package prop.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm
 * @author joaquim.motger
 */
public class CliquePercolation{

    int n;      //number of graph nodes
    int m;      //number of graph edges
    int i;      //number of cliques found
    int k;
    ArrayList<Integer>[] cliques;       //list of maximal cliques
    ArrayList<Integer>[] vertexInCliques;   //lists of cliques which contains the index vertex
    ArrayList<Integer>[] communities;

    private Graph<Song> graph; // Undirected, weighted graph

    public AlgorithmOutput execute(Graph graph, int k) {
        this.graph = graph;
        n = graph.numberOfVertices();
        ArrayList<String> log = new ArrayList<>();
        //R: list of vertices that may compose a clique
        ArrayList<Integer> R = new ArrayList<>();
        //P: candidates to be added in a clique
        ArrayList<Integer> P = new ArrayList<>();
        //X: rejected vertices
        ArrayList<Integer> X = new ArrayList<>();
        //cliques: list of cliques in graph
        cliques = (ArrayList<Integer>[])new ArrayList[n];
        vertexInCliques = (ArrayList<Integer>[])new ArrayList[n];
        for (int l = 0; l < n; ++l) vertexInCliques[l] = new ArrayList<>();
        i = 0;
        int l = 0;
        while (l < n) {
            //P contains all vertices in graph initially
            P.add(l);
            ++l;
        }
        //Executes the clique percolation algorithm
        findCliques(R, P, X, log);
        percolateCliques(k, log);
        ArrayList<Graph> com = parseCommunitiesToGraphs();
        return new AlgorithmOutput(com, log);
    }

    private void findCliques(ArrayList<Integer> R, ArrayList<Integer> P, ArrayList<Integer> X, ArrayList<String> log) {
        System.out.print("Provisional clique:");
        for (int r : R) System.out.print(" " + r);
        System.out.print("\nCandidates:");
        for (int p : P) System.out.print(" " + p);
        System.out.print("\nRejected:");
        for (int x : X) System.out.print(" " + x);
        System.out.print("\n\n");
        //If there are no more candidates to be add to the clique (P is empty) and
        //there aren't any other vertices connected with all of those in R who have
        //been rejected (X is empty), then R contains a maximal clique
        if (P.isEmpty() && X.isEmpty()) {
            System.out.print("New clique found:");
            printClique(R);
            cliques[i] = new ArrayList<>();
            //R is added to array of maximal cliques
            cliques[i] = R;
            for (int r : R) {
                vertexInCliques[r].add(i);
            }
            i = i+1;
            System.out.print("\n");
        }
        ArrayList<Integer> P1 = new ArrayList<>(P);
        //Expand of every vertex in P
        for (int v : P) {
            System.out.print("Expand on " + v + "\n");
            //R U v (add v to list of vertices that may compose a clique)
            if (!R.contains(v)) R.add(v);
            //Remove non-neighbours of v from candidates to be added to clique (P) and from
            //rejected who could be added to the clique, then backtrack
            findCliques(R, intersection(P1, neighbours(v)), intersection(X, neighbours(v)), log);
            //Remove v from vertices that may compose a clique (R)
            R = remove(R,v);
            //Remove v from candidates to be added (P1)
            P1 = remove(P1,v);
            //Add v to the rejected candidates
            X.add(v);
        }
    }

    private void percolateCliques(int k, ArrayList<String> log) {
        int[] cliqueInCommunity= new int[i];
        communities = (ArrayList<Integer>[])new ArrayList[n];
        int j;
        for (j = 0; j < i; ++j) {
            cliqueInCommunity[j] = j;
            communities[j] = cliques[j];
        }
        //getCommunities();
        int com = i;
        if (i <= k) return;
        //For every clique found
        for (j = 0; j < i; ++j) {
            System.out.println("Let's work in the " + j + " clique");
            //For every vertex in the clique
            int s;
            for (s = 0; s < communities[j].size(); ++s) {
                int l = communities[j].get(s);
                System.out.println("Let's work with vertex " + l);
                //For every clique the vertex is in
                for (int m : vertexInCliques[l]) {
                    System.out.println("This vertex is also in clique " + m);
                    //If the clique is not the same
                    if (m != j && cliqueInCommunity[m] != cliqueInCommunity[j]) {
                        System.out.println("Let's add this clique in the same community");
                        //We add to the community of the initial clique the clique shared by the vertex
                        int q;
                        for (q = 0; q < communities[m].size(); ++q) {
                            int p = communities[m].get(q);
                            if (!communities[cliqueInCommunity[j]].contains(p)) {
                                communities[cliqueInCommunity[j]].add(p);
                            }
                        }
                        //We stop considering the clique as an independent community
                        communities[m].clear();
                        //Update the community that the clique is in
                        cliqueInCommunity[m] = j;
                        //Update the number of cliques
                        com = getNumCom();
                        System.out.println("Now we have " + com + " communities");
                        getCommunities();
                        if (com <= k) return;
                    }
                }
            }
        }
    }

    private ArrayList<Graph> parseCommunitiesToGraphs() {
        ArrayList<Graph> com = new ArrayList<>();
        int s;
        for (s = 0; s < i; ++s) {
            ArrayList<Integer> l = communities[s];
            if (!l.isEmpty()) {
                Graph g = new Graph();
                for (int i : l) {
                    g.addVertex(graph.getVertexT(i));
                    for (int m : graph.adjacentVertices(i)) {
                        if (g.contains(graph.getVertexT(m))) {
                            g.addEdge(graph.getVertexT(i), graph.getVertexT(m), graph.weight(i, m));
                        }
                    }
                }
                com.add(g);
            }
        }
        return com;
    }

    private int getNumCom() {
        int size = 0;
        int t;
        for (t = 0; t < i; ++t) {
            if (!communities[t].isEmpty()) ++size;
        }
        return size;
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> intersection = new ArrayList<>(A);
        intersection.retainAll(B);
        return intersection;
    }

    private ArrayList<Integer> remove(ArrayList<Integer> A, int v) {
        ArrayList<Integer> B = new ArrayList<>();
        B.add(v);
        ArrayList<Integer> difference = new ArrayList<>(A);
        difference.removeAll(B);
        return difference;
    }

    private ArrayList<Integer> neighbours(int v) {
        ArrayList<Integer> n = new ArrayList<>();
        LinkedHashSet<Integer> m = graph.adjacentVertices(v);
        for (int p : m) {
            n.add(p);
        }
        return n;
    }

    private void printClique(ArrayList<Integer> l) {
        for (int i : l) {
            System.out.print(" " + i);
        }
        System.out.print("\n");
    }

    private void getCommunities() {
        System.out.println("Communities:");
        int k;
        for (k = 0; k < i; ++k) {
            if (!communities[k].isEmpty()) {
                for (int p : communities[k]) {
                    System.out.print(" " + p);
                }
                System.out.print("\n");
            }
        }
    }

}
