package prop.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm
 * @author joaquim.motger
 */
public class CliquePercolation extends Algorithm {

    int n;      //number of graph nodes
    int i;      //number of cliques found
    double lWeight;
    ArrayList<Integer>[] cliques;       //list of maximal cliques
    ArrayList<Integer>[] vertexInCliques;   //lists of cliques which contains the index vertex
    ArrayList<Integer>[] communities;
    ArrayList<Integer>[] neighbours;
    ArrayList<Integer> aloneVertices;

    private Graph<Song> graph; // Undirected, weighted graph

    /**
     * Executes the CliquePercolationMethod to find communities
     * @param g the graph from which communities are calculated
     * @param k     number of max communities desired
     * @return      an <code>AlgorithmOutput</code> object, including a list with communities and an execution log
     */
    public AlgorithmOutput execute(Graph g, int k) {
        graph = g;
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
        //Find maximal cliques in graph
        neighbours();
        aloneVertices(P);
        findCliques(R, P, X, log);
        //Removes all cliques under the lWeight
        //filtCliques(log);
        //Show the maximal cliques filtered
        for (l = 0; l < i; ++l) printClique(cliques[l], log, l);
        //Merge cliques to form communities
        percolateCliques(k, log);
        //for (l = 0; l < i; ++l) if (communities[l].size() > 0) printCommunity(communities[l], log, l);
        //Pars communities (lists of integers) into graph
        ArrayList<Graph> com = parseCommunitiesToGraphs();
        //Return all communities found
        return new AlgorithmOutput(com, log);
    }

    private void aloneVertices(ArrayList<Integer> P) {
        aloneVertices = new ArrayList<>();
        for (int p : P) {
            if (neighbours[p].size() == 0) {
                cliques[i] = new ArrayList<>();
                cliques[i].add(p);
                aloneVertices.add(p);
                i = i+1;
            }
        }
    }

    /**
     * Finds the maximal cliques in <code>graph</code>
     * @param R     A list of vertices that may form a clique
     * @param P     A list of provisional candidates to be added in the clique
     * @param X     A list of rejected vertices
     * @param log   log of the Algorithm execution
     */
    private void findCliques(ArrayList<Integer> R, ArrayList<Integer> P, ArrayList<Integer> X, ArrayList<String> log) {
        //If there are no more candidates to be add to the clique (P is empty) and
        //there aren't any other vertices connected with all of those in R who have
        //been rejected (X is empty), then R contains a maximal clique
        if (P.isEmpty() && X.isEmpty()) {
            cliques[i] = new ArrayList<>();
            //R is added to array of maximal cliques
            cliques[i] = R;
            for (int r : R) {
                vertexInCliques[r].add(i);
            }
            i = i+1;
            return;
        }
        ArrayList<Integer> P1 = new ArrayList<>(P);
        int pivot = getPivot(union(P,X));
        //P = removeNeighbours(P,neighbours[pivot]);
        //Expand of every vertex in P
        for (int v : P) {
            if (!aloneVertices.contains(v)) {
                //R U v (add v to list of vertices that may compose a clique)
                if (!R.contains(v)) {
                    R.add(v);
                }
                //Remove non-neighbours of v from candidates to be added to clique (P) and from
                //rejected who could be added to the clique, then backtrack
                findCliques(R, intersection(P1, neighbours[v]), intersection(X, neighbours[v]), log);
                //Remove v from vertices that may compose a clique (R)
                R = remove(R, v);
                //Remove v from candidates to be added (P1)
                P1 = remove(P1, v);
                //Add v to the rejected candidates
                X.add(v);
            }
        }
    }

    /**
     * filter Cliques under an intensity weight <code>lWeight</code>
     * @param log   log of the Algorithm execution
     */
    private void filtCliques(ArrayList<String> log) {
        /*ArrayList<Integer>[] filtedCliques = (ArrayList<Integer>[])new ArrayList[n];
        int k, j;
        j = 0;
        //log.add("Limit intensity to consider a clique: " + lWeight + "\n");
        for (k = 0; k < i; ++k) {
            //Calculates the meanWeight of the k clique
            double mw = meanWeight(cliques[k]);
            //log.add("Evaluate clique " + k + " : mean weight " + mw + "\n");
            //If the clique's intensity is under lWeight, we don't add it to the new list
            if (mw >= lWeight) {
                filtedCliques[j] = cliques[k];
                ++j;
            }
        }
        i = j;
        //cliques now contains the filtered cliques
        cliques = filtedCliques;*/
        int r,s,t;
        for (r = 0; r < i; ++r) {
            for (s = 0; s < cliques[r].size(); ++s) {
                if (neighbours[cliques[r].get(s)].size() < cliques[r].size()-1) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(cliques[r].get(s));
                    cliques[r].removeAll(l);
                }
            }
        }
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
                if (j != k && graph.areAdjacent(j,k)) {
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

    private ArrayList<Integer> removeNeighbours(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> l = new ArrayList<>(A);
        l.removeAll(B);
        return l;
    }

    private ArrayList<Integer> union(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> l = new ArrayList<>(A);
        for (int b : B) {
            if (!l.contains(b)) l.add(b);
        }
        return l;
    }

    private int getPivot(ArrayList<Integer> A) {
        int k = A.get(0);
        int s;
        for (s = 1; s < A.size(); ++s) {
            if (neighbours[A.get(s)].size() > neighbours[k].size()) k = A.get(s);
        }
        return k;
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
            if (communities[j].size() > 1) {
                StringBuilder sb = new StringBuilder();
                //For every vertex in the clique
                int s;
                for (s = 0; s < communities[j].size(); ++s) {
                    int l = communities[j].get(s);
                    //sb.append("Let's work with vertex " + l + "\n");
                    //For every clique the vertex is in
                    for (int m : vertexInCliques[l]) {
                        //If the clique is not the same

                        if (m != j && m < cliqueInCommunity.length && cliqueInCommunity[m] != cliqueInCommunity[j]) {
                            sb.append("0|" + m + "|" + j);
                            for (int M : communities[m]) sb.append("|" + M);
                            log.add(sb.toString());
                            //sb.append("Let's add this clique in the same community\n");
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
                            //sb.append("Now we have " + com + " communities\n");
                            sb = new StringBuilder();
                            if (com <= k) return;
                        }
                    }
                }
                //log.add(sb.toString() + "\n");
            }
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
                    if (graph.adjacentVertices(i).size() > 0 || l.size() == 1) {
                        g.addVertex(graph.getVertexT(i));
                        for (int m : graph.adjacentVertices(i)) {
                            if (g.contains(graph.getVertexT(m)) && l.contains(m)) {
                                g.addEdgeT(graph.getVertexT(i), graph.getVertexT(m), graph.weight(i, m));
                            }
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

    private void neighbours() {
        neighbours = (ArrayList<Integer>[])new ArrayList[n];
        int i;
        for (i = 0; i < n; ++i) {
            ArrayList<Integer> l = new ArrayList<>();
            LinkedHashSet<Integer> m = graph.adjacentVertices(i);
            for (int p : m) {
                l.add(p);
            }
            neighbours[i] = l;
        }
    }

    /**
     * adds to log the index vertices a clique contains
     * @param l     the clique - a list of integers
     * @param log   log of the Algorithm execution
     */
    private void printClique(ArrayList<Integer> l, ArrayList<String> log, int k) {
        StringBuilder sb = new StringBuilder();
        int s;
        sb.append("0|-1|" + k);
        for (s = 0; s < l.size(); ++s) {
            sb.append("|" + l.get(s));
        }
        log.add(sb.toString());
    }

    private void printCommunity(ArrayList<Integer> l, ArrayList<String> log, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + k + "] community:");
        for (int i : l) {
            sb.append(" " + i);
        }
        log.add(sb.toString());
    }

}