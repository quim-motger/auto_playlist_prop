package prop.domain;

/**
 * AlgorithmInput in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 13/05/15
 * @see prop.domain.Algorithm
 */
public class AlgorithmInput {
    private Graph<Song> graph;
    private int nCommunities;

    /**
     * Gets main graph that will be treated in the Algorithm
     *
     * @return input graph
     */
    public Graph<Song> getGraph() {
        return graph;
    }

    /**
     * Sets main graph that will be treated in the Algorithm
     *
     * @param graph input graph
     */
    public void setGraph(Graph<Song> graph) {
        this.graph = graph;
    }

    /**
     * Gets number of maximum communities that the algorithm can output
     *
     * @return maximum communities that the algorithm can output
     */
    public int getnCommunities() {
        return nCommunities;
    }

    /**
     * Sets number of maximum communities that the algorithm can output
     *
     * @param nCommunities maximum communities that the algorithm can output
     */
    public void setnCommunities(int nCommunities) {
        this.nCommunities = nCommunities;
    }
}
