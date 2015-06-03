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

    /**
     * returns the first object in Pair
     * @return  first implicit object
     */
    public F first() {return first;}

    /**
     * returns the second object in Pair
     * @return  second implicit object
     */
    public S second() {return second;}

    /**
     * set the first object in Pair with the specified value
     */
    public void setFirst(F f) {first = f;}

    /**
     * set the second object in Pair with the specified value
     */
    public void setSecond(S s) {second = s;}
}
