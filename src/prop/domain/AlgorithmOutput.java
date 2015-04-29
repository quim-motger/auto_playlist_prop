package prop.domain;

import java.util.ArrayList;

/**
 * Output of an algorithm. Contains the communities found by the algorithm as well as its operation log.
 * @author Carles Garcia Cabot
 */
public class AlgorithmOutput {
    private ArrayList<HashGraph> communities;
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
    public AlgorithmOutput(ArrayList<HashGraph> communities, ArrayList<String> log) {
        this.communities = communities;
        this.log = log;
    }

    /**
     * Adds a graph
     * @param community Graph to add
     */
    public void add(HashGraph community) { communities.add(community); }

    /**
     * Adds a string to the log
     * @param l String to add
     */
    public void add(String l) { log.add(l); }

    // Getter
    public ArrayList<String> getLog() { return log; };

    public ArrayList<HashGraph> getCommunities() {
        return communities;
    }

    /**
     * Returns the densest graph. Density is defined as the number of edges divided by the number of vertices of a graph.
     * @return The densest graph
     */
    public HashGraph densestGraph() {
        //Todo: what if division by 0 or g = null
        double maxDensity = 0;
        HashGraph g = null;
        for (HashGraph gr : communities) {
            if (gr.numberOfEdges()/(double)gr.numberOfVertices() > maxDensity) {
                maxDensity = gr.numberOfEdges()/(double)gr.numberOfVertices();
                g = gr;
            }
        }
        return g;
    }

}
