package prop.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm
 * @author joaquim.motger
 */
public class CliquePercolation{

    int n;      //number of graph nodes
    int i;      //number of cliques found
    double lWeight;
    ArrayList<Integer>[] cliques;       //list of maximal cliques
    ArrayList<Integer>[] vertexInCliques;   //lists of cliques which contains the index vertex
    ArrayList<Integer>[] communities;

    private Graph<Song> graph; // Undirected, weighted graph

    /**
     * Executes the CliquePercolationMethod to find communities
     * @param graph the graph from which communities are calculated
     * @param k     number of max communities desired
     * @return      an <code>AlgorithmOutput</code> object, including a list with communities and an execution log
     */
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
        //cliques: list of maximal cliques in graph
        cliques = (ArrayList<Integer>[])new ArrayList[n];
        //vertexInCliques: an array that contains lists of the cliques that the index vertex is in
        vertexInCliques = (ArrayList<Integer>[])new ArrayList[n];
        for (int l = 0; l < n; ++l) vertexInCliques[l] = new ArrayList<>();
        i = 0;
        int l = 0;
        while (l < n) {
            //P contains all vertices in graph initially
            P.add(l);
            ++l;
        }
        //weight: we calculate a limit Weight based on the Intensity (mean Weight of a clique), considering
        //half of the meanWeight in order to consider about the 75% higher intensities a clique
        lWeight = meanTotalWeight()/2;
        //Executes the clique percolation algorithm
        log.add("### Finding maximal cliques\n");
        //Find maximal cliques in graph
        findCliques(R, P, X, log);
        log.add("### Cliques found\n");
        //Show the maximal cliques found
        for (l = 0; l < i; ++l) printClique(cliques[l], log);
        log.add("### Filter maximal cliques with a low intensity\n");
        //Removes all cliques under the lWeight
        filtCliques(log);
        log.add("### Cliques filtered\n");
        //Show the maximal cliques filtered
        for (l = 0; l < i; ++l) printClique(cliques[l], log);
        log.add("### Merging cliques to find " + k + " communities\n");
        //Merge cliques to form communities
        percolateCliques(k, log);
        //Pars communities (lists of integers) into graph
        ArrayList<Graph> com = parseCommunitiesToGraphs();
        log.add("### Communities found\n");
        //Return all communities found
        return new AlgorithmOutput(com, log);
    }

    /**
     * Finds the maximal cliques in <code>graph</code>
     * @param R     A list of vertices that may form a clique
     * @param P     A list of provisional candidates to be added in the clique
     * @param X     A list of rejected vertices
     * @param log   log of the Algorithm execution
     */
    private void findCliques(ArrayList<Integer> R, ArrayList<Integer> P, ArrayList<Integer> X, ArrayList<String> log) {
        StringBuilder sb = new StringBuilder();
        //Information with the provisional clique found
        sb.append("Provisional clique:");
        for (int r : R) sb.append(" " + r);
        log.add(sb.toString());
        sb = new StringBuilder();
        //Information with the remaining candidates to be added at the maximal clique
        sb.append("\nCandidates:");
        for (int p : P) sb.append(" " + p);
        log.add(sb.toString());
        sb = new StringBuilder();
        //Information with the rejected candidates that could be added in the clique
        sb.append("\nRejected:");
        for (int x : X) sb.append(" " + x);
        sb.append("\n");
        log.add(sb.toString());
        sb = new StringBuilder();
        //If there are no more candidates to be add to the clique (P is empty) and
        //there aren't any other vertices connected with all of those in R who have
        //been rejected (X is empty), then R contains a maximal clique
        if (P.isEmpty() && X.isEmpty()) {
            log.add("New clique found:");
            printClique(R, log);
            cliques[i] = new ArrayList<>();
            //R is added to array of maximal cliques
            cliques[i] = R;
            for (int r : R) {
                vertexInCliques[r].add(i);
            }
            i = i+1;
            log.add(sb.toString() + "\n");
        }
        ArrayList<Integer> P1 = new ArrayList<>(P);
        //Expand of every vertex in P
        for (int v : P) {
            sb.append("\nExpand on " + v + "\n");
            //R U v (add v to list of vertices that may compose a clique)
            if (!R.contains(v)) R.add(v);
            //Remove non-neighbours of v from candidates to be added to clique (P) and from
            //rejected who could be added to the clique, then backtrack
            log.add(sb.toString());
            findCliques(R, intersection(P1, neighbours(v)), intersection(X, neighbours(v)), log);
            sb = new StringBuilder();
            //Remove v from vertices that may compose a clique (R)
            R = remove(R,v);
            //Remove v from candidates to be added (P1)
            P1 = remove(P1,v);
            //Add v to the rejected candidates
            X.add(v);
        }
    }

    /**
     * filter Cliques under an intensity weight <code>lWeight</code>
     * @param log   log of the Algorithm execution
     */
    private void filtCliques(ArrayList<String> log) {
        ArrayList<Integer>[] filtedCliques = (ArrayList<Integer>[])new ArrayList[n];
        int k, j;
        j = 0;
        log.add("Limit intensity to consider a clique: " + lWeight + "\n");
        for (k = 0; k < i; ++k) {
            //Calculates the meanWeight of the k clique
            double mw = meanWeight(cliques[k]);
            log.add("Evaluate clique " + k + " : mean weight " + mw + "\n");
            //If the clique's intensity is under lWeight, we don't add it to the new list
            if (mw >= lWeight) {
                filtedCliques[j] = cliques[k];
                ++j;
            }
        }
        i = j;
        //cliques now contains the filtered cliques
        cliques = filtedCliques;
    }

    /**
     * calculates the mean weight (intensity) of a subset of vertices of <code>graph</code>
     * @param l     a list that contains the vertices of the subset
     * @return      <code>meanWeight</code> of the subset
     */
    private double meanWeight(ArrayList<Integer> l) {
        double meanWeight = 0;
        for (int j : l) {
            for (int k : l) {
                if (j != k) {
                    meanWeight += graph.weight(j,k);
                }
            }
        }
        meanWeight = meanWeight/((double)(l.size()*(l.size()-1)));
        return meanWeight;
    }

    /**
     * calculates the mean weight (intensity) of <code>graph</code>
     * @return      the meanWeight of <code>graph</code>
     */
    private double meanTotalWeight() {
        double meanWeight = 0;
        int s = graph.numberOfVertices();
        int k;
        for (k = 0; k < s; ++k) {
            int j;
            for (j = 0; j < s; ++j) {
                if (graph.areAdjacent(k,j)) meanWeight += graph.weight(k,j);
            }
        }
        meanWeight = meanWeight/((double)graph.numberOfEdges()*2);
        return meanWeight;
    }

    /**
     * Merges the maximal cliques found in <code>cliques</code> until there ara k or less communities
     * @param k     number of maximal communities
     * @param log   log of the Algorithm execution
     */
    private void percolateCliques(int k, ArrayList<String> log) {
        int[] cliqueInCommunity= new int[i];
        communities = (ArrayList<Integer>[])new ArrayList[n];
        int j;
        for (j = 0; j < i; ++j) {
            cliqueInCommunity[j] = j;
            communities[j] = cliques[j];
        }
        int com = i;
        if (i <= k) return;
        //For every clique found
        for (j = 0; j < i; ++j) {
            StringBuilder sb = new StringBuilder();
            sb.append("Let's work in the " + j + " clique\n");
            //For every vertex in the clique
            int s;
            for (s = 0; s < communities[j].size(); ++s) {
                int l = communities[j].get(s);
                sb.append("Let's work with vertex " + l + "\n");
                //For every clique the vertex is in
                for (int m : vertexInCliques[l]) {
                    //If the clique is not the same
                    if (m != j && cliqueInCommunity[m] != cliqueInCommunity[j]) {
                        sb.append("This vertex is also in clique " + m + "\n");
                        sb.append("Let's add this clique in the same community\n");
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
                        sb.append("Now we have " + com + " communities\n");
                        log.add(sb.toString() + "\n");
                        sb = new StringBuilder();
                        if (com <= k) return;
                    }
                }
            }
            log.add(sb.toString() + "\n");
        }
    }

    /**
     * parse subset of vertices into a graph
     * @return  an arrayList that contains all parsed graphs
     */
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
                            g.addEdgeT(graph.getVertexT(i), graph.getVertexT(m), graph.weight(i, m));
                        }
                    }
                }
                com.add(g);
            }
        }
        return com;
    }

    /**
     * calculates the number of <code>communities</code> found
     * @return  the number of communities
     */
    private int getNumCom() {
        int size = 0;
        int t;
        for (t = 0; t < i; ++t) {
            if (!communities[t].isEmpty()) ++size;
        }
        return size;
    }

    /**
     * calculates the intersection between <code>A</code> and <code>B</code>
     * @param A     first set
     * @param B     second set
     * @return      intersection between <code>A</code> and <code>B</code>
     */
    private ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> intersection = new ArrayList<>(A);
        intersection.retainAll(B);
        return intersection;
    }

    /**
     * removes <code>v</code> from <code>A</code>
     * @param A     the set
     * @param v     the vertex (integer) to be removed
     * @return      <code>A</code> without <code>v</code>
     */
    private ArrayList<Integer> remove(ArrayList<Integer> A, int v) {
        ArrayList<Integer> B = new ArrayList<>();
        B.add(v);
        ArrayList<Integer> difference = new ArrayList<>(A);
        difference.removeAll(B);
        return difference;
    }

    /**
     * returns a list of all neighbours of <code>v</code>
     * @param v     the vertex
     * @return      a list with all neihbours of <code>v</code>
     */
    private ArrayList<Integer> neighbours(int v) {
        ArrayList<Integer> n = new ArrayList<>();
        LinkedHashSet<Integer> m = graph.adjacentVertices(v);
        for (int p : m) {
            n.add(p);
        }
        return n;
    }

    /**
     * adds to log the index vertices a clique contains
     * @param l     the clique - a list of integers
     * @param log   log of the Algorithm execution
     */
    private void printClique(ArrayList<Integer> l, ArrayList<String> log) {
        StringBuilder sb = new StringBuilder();
        for (int i : l) {
            sb.append(" " + i);
        }
        sb.append("\n");
        log.add(sb.toString());
    }

}
