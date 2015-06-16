package prop.presentation;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Pattern;

public class ExecutionPanel extends GraphPanel {

    private static final char delimiter = '|';
    private int step;
    private ArrayList<String> log;
    private HashSet<Pair<String>> hiddenEdges;
    private HashMap<Integer,Color> hashColors;
    private HashMap<String, Integer> vColors;
    private JButton nextButton;
    private JButton backButton;
    JLabel stepsLabel;

    public ExecutionPanel(AlgorithmPController apc) {
        super(apc);
        hiddenEdges = new HashSet<>();
        hashColors = new HashMap<>();
        hashColors.put(-1, Color.white);
        vColors = new HashMap<>();
        for (String sv : originalGraph.getVertices()) {
            vColors.put(sv, -1);
        }

        for (JungEdge ed : originalGraph.getEdges()) {
            ed.setColor(Color.black);
        }
        paintEdges();

        step = -1;
        log = algorithmPController.getLogArray();

        controls.removeAll();

        stepsLabel = new JLabel("0/" + log.size());
        controls.add(stepsLabel, BorderLayout.WEST);

        backButton = new JButton("<-");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });
        controls.add(backButton);
        backButton.setEnabled(false);

        nextButton = new JButton("->");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButtonActionPerformed(e);
            }
        });
        controls.add(nextButton);
        if (log.isEmpty()) nextButton.setEnabled(false);

        // Vertices' color
        paint();

    }

    private void paint() {
        Transformer<String, Paint> vertexC = new Transformer<String, Paint>() {
            public Paint transform(String i) {
                return hashColors.get(vColors.get(i));
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexC);

        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
    }

    private void paintEdges() {
        Transformer<JungEdge,Paint> edgeC = new Transformer<JungEdge, Paint>() {
            public Paint transform(JungEdge e) {
                Pair<String> p = originalGraph.getEndpoints(e);
                Color col = e.getColor();
                for (Pair<String> ps : hiddenEdges) {
                    if ((p.getFirst().equals(ps.getFirst()) && p.getSecond().equals(ps.getSecond()))
                            || (p.getFirst().equals(ps.getSecond()) && p.getSecond().equals(ps.getFirst()))) {
                        col = new Color(col.getRed(), col.getGreen(), col.getBlue(), 0);
                    }
                }
                return col;
            }
        };
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgeC);

        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        if (step > -1) {
            String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
            int code = Integer.parseInt(op[0]);
            switch (code) {
                case 0:
                    ArrayList<Integer> removedVertexs  = new ArrayList<>();
                    for(int i = 3; i<op.length;++i)
                        removedVertexs.add(Integer.parseInt(op[i]));
                    paintVertexsIntoColor(Integer.parseInt(op[1]), removedVertexs);
                    break;
                case 2:
                    addEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                    break;
            }
            --step;
            stepsLabel.setText(step+1 + "/" + log.size());
            if (step <= -1) backButton.setEnabled(false);
            else nextButton.setEnabled(true);
        }
    }

    private void nextButtonActionPerformed(ActionEvent evt) {
        if (step < log.size()-1) {
            ++step;
            stepsLabel.setText(step+1 + "/" + log.size());
            if (step >= log.size()-1) nextButton.setEnabled(false);
            backButton.setEnabled(true);

            String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
            int code = Integer.parseInt(op[0]);
            switch (code) {
                case 0:
                    ArrayList<Integer> addedVertexs  = new ArrayList<>();
                    for(int i = 3; i<op.length;++i)
                        addedVertexs.add(Integer.parseInt(op[i]));
                    paintVertexsIntoColor(Integer.parseInt(op[2]), addedVertexs);
                    break;
                case 2:
                    removeEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                    break;
            }
        }
    }

    private void paintVertexsIntoColor(int color, ArrayList<Integer> vertexsToPaint) {
        if (!hashColors.containsKey(color)) {
            Random rand2 = new Random();
            if (color < materialColors.length)
                hashColors.put(color, materialColors[color % materialColors.length]);
            else
                hashColors.put(color, new Color(rand2.nextFloat(), rand2.nextFloat(), rand2.nextFloat()));
        }
        for (Integer in : vertexsToPaint) {
            vColors.put(algorithmPController.getSongId(in), color);
        }
        paint();

    }

    private void removeEdge(int u, int v) {
        hiddenEdges.add(new Pair<String>(algorithmPController.getSongId(u),algorithmPController.getSongId(v)));
        paintEdges();
    }

    private void addEdge(int u, int v) {
        hiddenEdges.remove(new Pair<String>(algorithmPController.getSongId(u),algorithmPController.getSongId(v)));
        paintEdges();
    }



}