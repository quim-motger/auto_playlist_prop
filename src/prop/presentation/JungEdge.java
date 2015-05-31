package prop.presentation;

/**
 * Edge for a Jung graph
 */
public class JungEdge {
    private Double weight;

    public JungEdge(Double d) {
        weight = d;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return weight.toString();
    }
}
