package prop.presentation;

import prop.domain.Algorithm;

import javax.swing.*;
import java.awt.*;

public class AlgorithmTabView extends JPanel {
    private GraphPanel graphpanel;

    public AlgorithmTabView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        graphpanel = new GraphPanel();
        add(graphpanel,BorderLayout.CENTER);
    }
}