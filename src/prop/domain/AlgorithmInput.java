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
    private Graph<Song> mGraph;
    private int nCom;

    /**
     * Gets main mGraph that will be treated in the Algorithm
     *
     * @return input mGraph
     */
    public Graph<Song> getGraph() {
        return mGraph;
    }

    /**
     * Sets main mGraph that will be treated in the Algorithm
     *
     * @param graph input mGraph
     */
    public void setGraph(Graph<Song> graph) {
        mGraph = graph;
    }

    /**
     * Gets number of maximum communities that the algorithm can output
     *
     * @return maximum communities that the algorithm can output
     */
    public int getNumberCommunities() {
        return nCom;
    }

    /**
     * Sets number of maximum communities that the algorithm can output
     *
     * @param nCommunities maximum communities that the algorithm can output
     */
    public void setNumberComunities(int nCommunities) {
        nCom = nCommunities;
    }
}
