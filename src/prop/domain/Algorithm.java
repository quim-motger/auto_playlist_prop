package prop.domain;

/**
 * Algorithm in prop.domain
 *
 */
public abstract class Algorithm {
    /**
     * Executes Community Detection Algorithm
     *
     * @param graph Input Graph
     * @param k     Number of maximum communities
     * @return Algorithm output with the steps of the algorithm and an array with the output communities
     */
    public abstract AlgorithmOutput execute(Graph<Song> graph, int k);
}
