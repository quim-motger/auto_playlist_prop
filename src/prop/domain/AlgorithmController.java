package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The controller for {@code GirvanNewman}, {@code Louvain} and {@code CliquePercolation}.
 * @author oscar.manas
 * @see Algorithm
 * @see GirvanNewman
 * @see Louvain
 * @see CliquePercolation
 */
public class AlgorithmController {

    private ArrayList<String> log;

    /**
     * Executes the selected algorithm for generating a song list that will be added to the set.
     * This method should be called after constructing the graph in the Relation Controller, otherwise it could
     * cause a {@code NullPointerException}.
     * @param title                 the title of the list that will be created
     * @param algorithm             the selected algorithm:
     *                                  0: Girvan-Newman
     *                                  1: Louvain
     *                                  2: Clique Percolation
     * @param k                     the correlation measure; the higher the value, the more correlated the elements
     *                              within a community will be, but communities will be smaller
     * @param listController        an instance of the List Controller
     * @param relationController    an instance of the Relation Controller
     * @return                      a log of the algorithm execution
     */
    public ArrayList<String> execute(String title, int algorithm, int k, ListController listController, RelationController relationController) throws PropException {

        log = new ArrayList<String>();
        Graph<Song> graph = createInputGraph(algorithm,relationController);
        log.add("Input graph:\n" + writeGraph(graph));
        AlgorithmOutput ao = null;

        // We execute the selected algorithm and get the output
        switch (algorithm) {
            case 0:
                GirvanNewman gn = new GirvanNewman();
                ao = gn.execute(graph,k);
                break;
            case 1:
                Louvain l = new Louvain();
                ao = l.execute(graph,k);
                break;
            case 2:
                CliquePercolation cp = new CliquePercolation();
                ao = cp.execute(graph,k);
                break;
            default:
                throw new PropException(ErrorString.UNEXISTING_ALGORITHM);
        }

        // From the given communities, we select the densest one...
        Graph<Song> community = ao.densestGraph();
        // ...and generate a new list with the songs in the community
        List list = new List(title);
        for (Song s : community.getOriginalVertices()) {
            list.addSong(s);
        }
        listController.addList(list);

        log.addAll(ao.getLog());
        log.add("List created:\n" + list.obtainId() + "  " + list.obtainTitle() + "\n");

        return log;
    }

    /**
     * Converts a multigraph into the graph that will be the input of the algorithm.
     * @param algorithm             the selected algorithm:
     *                                  0: Girvan-Newman
     *                                  1: Louvain
     *                                  2: Clique Percolation
     * @param relationController    an instance of the Relation Controller
     * @return                      the input graph
     */
    private Graph<Song> createInputGraph(int algorithm, RelationController relationController) throws NullPointerException {
        Graph<Song> graph = new Graph<Song>();
        Graph<Song> G = relationController.getGraph();
        if (G == null) throw new NullPointerException(ErrorString.NULL_GRAPH);
        log.add("Original graph:\n" + writeGraph(G));

        int n = G.numberOfVertices();
        for (Song s : G.getOriginalVertices())
            graph.addVertex(s);
        boolean[][] visEdges = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                visEdges[i][j] = false;
        }

        for (int u = 0; u < n; ++u) {
            for (int v : G.adjacentVertices(u)) {
                if (!visEdges[u][v]) {
                    double w = getWeightsSum(G, u, v);
                    // That's necessary because we consider weight as relevance, but the Girvan-Newman algorithm
                    // considers weight as distance
                    if (algorithm == 0 && w != 0)
                        w = 1/w;
                    graph.addEdgeT(G.getVertexT(u), G.getVertexT(v), w);
                    visEdges[u][v] = true;
                    visEdges[v][u] = true;
                }
            }
        }
        return graph;
    }

    /**
     * Adds the weight of all the edges between a pair of vertices.
     * @param G the graph
     * @param u a vertex
     * @param v a vertex
     * @return  the sum of the weights of all the edges between {@code u} and {@code v}
     */
    private double getWeightsSum(Graph<Song> G, int u, int v){
        ArrayList<Double> weights= G.weights(u, v);
        double sum = 0;
        for (double w : weights)
            sum += w;
        return sum;
    }

    /**
     * Writes a weighted graph.
     * @param G the graph to write
     * @return  a {@code String} representing the graph
     */
    private String writeGraph(Graph G) {
        StringBuilder sb = new StringBuilder();
        Song s;
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(2);
        for (int i = 0; i < G.numberOfVertices(); ++i) {
            s = (Song) G.getVertexT(i);
            sb.append("(" + G.getVertex(s) + ")" + s.getTitle() + ": ");
            for (Integer j : (Iterable<Integer>) G.adjacentVertices(i)) {
                s = (Song) G.getVertexT((int)j);
                sb.append(s.getTitle() + "(" + nf.format(G.weight(i,j)) + ") ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

}
