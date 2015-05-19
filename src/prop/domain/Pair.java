package prop.domain;

/**
 * Pair
 * @author joaquim.motger
 */

public class Pair<F,S> {

    private F first;
    private S second;

    /**
     * Pair class constructor
     * @param   f  the first object in Pair
     * @param   s  the second object in Pair
     */
    public Pair(F f, S s) {
        first = f;
        second = s;
    }

    public static <A,B> Pair<A,B> create(A a, B b) {
        return new Pair<>(a, b);
    }

    public F first() {return first;}

    public S second() {return second;}

    public void setFirst(F f) {first = f;}

    public void setSecond(S s) {second = s;}
}
