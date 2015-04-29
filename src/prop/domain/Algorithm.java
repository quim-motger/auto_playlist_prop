package prop.domain;

/**
 * Algorithm in prop.domain
 *
 */
public abstract class Algorithm {
    public abstract AlgorithmOutput execute(HashGraph<Song> graph, int k);
}
