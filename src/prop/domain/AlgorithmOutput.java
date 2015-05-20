package prop.domain;

import java.util.ArrayList;

/**
 * Output of an algorithm. Contains the communities found by the algorithm as well as its operation log.
 * @author Carles Garcia Cabot
 */
public class AlgorithmOutput {
    private ArrayList<Graph> communities;
    private ArrayList<String> log;

    /**
     * Default constructor
     */
    public AlgorithmOutput() {
        communities = new ArrayList<>();
        log = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param communities
     * @param log
     */
    public AlgorithmOutput(ArrayList<Graph> communities1, ArrayList<String> log1) {
        communities = communities1;
        log = log1;
    }

    /**
     * Adds a graph
     * @param community Graph to add (not null)
     */
    public void add(Graph community) {
        communities.add(community);
    }

    /**
     * Adds a string to the log
     * @param l String to add (not null)
     */
    public void add(String l) {
        log.add(l);
    }

    /* GETTERS */
    public ArrayList<String> getLog() { return log; }

    public ArrayList<Graph> getCommunities() {
        return communities;
    }

    /**
     * Returns the densest graph. Density is defined as the number of edges divided by the number of vertices of a graph.
     * @return The densest graph. Null if there is no graph or the graph is empty.
     */
    public Graph densestGraph() {
        double maxDensity = 0;
        Graph g = null;
        for (Graph gr : communities) {
            if (!gr.isEmpty() && gr.numberOfEdges()/(double)gr.numberOfVertices() >= maxDensity) {
                maxDensity = gr.numberOfEdges()/(double)gr.numberOfVertices();
                g = gr;
            }
        }
        return g;
    }
}
