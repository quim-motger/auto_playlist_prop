package prop.presentation;

import java.awt.*;

/**
 * Edge for a Jung graph
 */
public class JungEdge {
    private Double weight;
    private Color color;

    public JungEdge(Double d) {
        weight = d;
    }

    public double getWeight() {
        return weight;
    }
    public Color getColor() { return color; }
    public void setColor(Color c) { color = c; }
    @Override
    public String toString() {
        return weight.toString();
    }
}
