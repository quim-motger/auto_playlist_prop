package prop.domain;

/**
 * Pair
 * @author joaquim.motger
 */

public class Pair<F,S> {

    public F first;
    public S second;

    /**
     * Pair class constructor
     * @param   first   the first object in Pair
     * @param   second  the second object in Pair
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <A,B> Pair<A,B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }}
