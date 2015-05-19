package prop.domain;

/**
 * Algorithm in prop.domain
 *
 */
public abstract class Algorithm {

    /**
     * @param input Algorithm Input Class with the desired parameters
     * @return Algorithm output with the steps of the algorithm and an array with the output communities
     */
    public AlgorithmOutput execute(AlgorithmInput input) {
        return execute(input.getGraph(), input.getNumberCommunities());
    }
    
    /**
     * Executes Community Detection Algorithm
     *
     * @param graph Input Graph
     * @param k     Number of maximum communities
     * @return Algorithm output with the steps of the algorithm and an array with the output communities
     */
    public abstract AlgorithmOutput execute(Graph<Song> graph, int k);
}
