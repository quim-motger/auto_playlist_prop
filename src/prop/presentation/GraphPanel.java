package prop.presentation;

import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Pattern;

/*
Then create a random generator:

        Random rand = new Random();

        Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
// Will produce a random colour with more red in it (usually "pink-ish")
        float r = rand.nextFloat();
        float g = rand.nextFloat(0.5);
        float b = rand.nextFloat(0.5);

// Will produce only bright / light colours:
        float r = rand.nextFloat(0.5) + 0.5;
        float g = rand.nextFloat(0.5) + 0.5;
        float b = rand.nextFloat(0.5) + 0.5;

        There are various other colour functions that can be used with the Color class, such as making the colour brighter:
        randomColor.brighter();
*/


public class GraphPanel extends JPanel{

        protected AlgorithmPController algorithmPController;
        private UndirectedSparseGraph<String,JungEdge> originalGraph;
        private ArrayList<UndirectedSparseGraph<String,JungEdge>> communities;

        //the visual component and renderer for the graph
        private VisualizationViewer<String, JungEdge> vv;

        private AggregateLayout<String,JungEdge> clusteringLayout;
        private Class subLayoutType = CircleLayout.class;
        private Dimension subLayoutSize;
        private ArrayList<Color> colors;

        protected JPanel controls;

        public GraphPanel(AlgorithmPController apc) {
            algorithmPController = apc;
            originalGraph = algorithmPController.getOriginalGraph();
            communities = algorithmPController.getCommunities();

            //initialize colors
            colors = new ArrayList<>();
            Random rand = new Random();
            for (int x = 0; x < communities.size(); ++x) {
                colors.add(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            }

            final UndirectedSparseGraph<String,JungEdge> selectedCommunity = communities.get(communities.size()-1);

            clusteringLayout = new AggregateLayout<String,JungEdge>(new KKLayout<String, JungEdge>(originalGraph));
            subLayoutSize = new Dimension(50,50);
            Dimension visualizationModelSize = new Dimension(550,420);
            Dimension preferredSize = getSize();

            final VisualizationModel<String,JungEdge> visualizationModel =
                    new DefaultVisualizationModel<String,JungEdge>(clusteringLayout, visualizationModelSize);
            vv =  new VisualizationViewer<String,JungEdge>(visualizationModel, preferredSize);

            vv.setBackground(Color.white);

            // Vertices' color
            final Transformer<String, Paint> vertexColor = new Transformer<String, Paint>() {
                public Paint transform(String i) {
                    return colors.get(findCommunity(communities, i));
                }
            };

            // Vertices' size
            Transformer<String, Shape> vertexSize = new Transformer<String, Shape>(){
                public Shape transform(String i){
                    Ellipse2D circle = new Ellipse2D.Double(-10,-10, 20, 20);
                    return circle;
                }
            };

            // Edges' color
            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<JungEdge, Paint>() {
                public Paint transform(JungEdge e) {
                    Pair<String> p = originalGraph.getEndpoints(e);
                    for (int j = 0; j < communities.size(); ++j) {
                        if (communities.get(j).containsVertex(p.getFirst()) && communities.get(j).containsVertex(p.getSecond()))
                            return colors.get(j);
                    }
                    return Color.black;
                 }
            });

            // Edges' thickness
            vv.getRenderContext().setEdgeStrokeTransformer(new Transformer<JungEdge, Stroke>() {
                protected final Stroke THIN = new BasicStroke(2);
                protected final Stroke THICK = new BasicStroke(4);

                public Stroke transform(JungEdge e) {
                 /*   Pair<String> p = originalGraph.getEndpoints(e);
                    if (selectedCommunity.containsVertex(p.getFirst()) && selectedCommunity.containsVertex(p.getSecond()))
                        return THICK;*/
                    return THIN;
                }
            });

            vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
            vv.getRenderContext().setVertexShapeTransformer(vertexSize);

            // Edge weight tooltip
            vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
            vv.setEdgeToolTipTransformer(new Transformer<JungEdge, String>() {
                public String transform(JungEdge edge) {
                    return edge.toString();
                }
            });

            /** LABELS **/
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
            vv.getRenderer().getVertexLabelRenderer().setPositioner(new InsidePositioner());
            vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
            vv.setForeground(Color.black); //label color

            this.setLayout(new BorderLayout(5, 5));
            final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
            add(panel);

            /** MOUSE **/
            final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,JungEdge>();
            vv.setGraphMouse(graphMouse);
            vv.addKeyListener(graphMouse.getModeKeyListener());

            /** BUTTONS **/

            final ScalingControl scaler = new CrossoverScalingControl();

            JButton plus = new JButton("Zoom in");
            plus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1.1f, vv.getCenter());
                }
            });
            JButton minus = new JButton("Zoom out");
            minus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1 / 1.1f, vv.getCenter());
                }
            });

            final JButton cluster = new JButton("Cluster");
            final JButton uncluster = new JButton("Undo cluster");
            cluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster.setEnabled(false);
                    uncluster.setEnabled(true);
                    cluster(communities.get(communities.size()-1),true);
                }
            });
            uncluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster.setEnabled(true);
                    uncluster.setEnabled(false);
                    cluster(originalGraph, false);
                }
            });


            JButton reset = new JButton("Reset");
            reset.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
                }
            });

            controls = new JPanel();
            controls.add(plus);
            controls.add(minus);
            controls.add(reset);
            controls.add(cluster);
            controls.add(uncluster);
            uncluster.setEnabled(false);
            add(controls, BorderLayout.SOUTH);

            // Lock vertices to avoid movement
            lock(originalGraph, clusteringLayout);

        }

        private Layout getLayoutFor(Class layoutClass, Graph graph) throws Exception {
            Object[] args = new Object[]{graph};
            Constructor constructor = layoutClass.getConstructor(new Class[] {Graph.class});
            return  (Layout)constructor.newInstance(args);
        }

        private void cluster(UndirectedSparseGraph<String,JungEdge> graph, boolean state) {
            if(state) {
                if (graph.getVertexCount() > 1) {
                    // put the picked vertices into a new sublayout
                    Collection<String> picked = new HashSet<String>();
                    picked.addAll(graph.getVertices());

                    Point2D center = new Point2D.Double();
                    double x = 0;
                    double y = 0;
                    for (String vertex : picked) {
                        Point2D p = clusteringLayout.transform(vertex); // gets location of the vertex
                        x += p.getX();
                        y += p.getY();
                    }
                    x /= picked.size();
                    y /= picked.size();
                    center.setLocation(x, y);

                    Graph<String, JungEdge> subGraph;
                    try {
                        subGraph = graph.getClass().newInstance();
                        for (String vertex : picked) {
                            subGraph.addVertex(vertex);
                            Collection<JungEdge> incidentEdges = graph.getIncidentEdges(vertex);
                            for (JungEdge edge : incidentEdges) {
                                Pair<String> endpoints = graph.getEndpoints(edge);
                                if (picked.containsAll(endpoints)) {
                                    // put this edge into the subgraph
                                    subGraph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
                                }
                            }
                        }

                        Layout<String, JungEdge> subLayout = getLayoutFor(subLayoutType, subGraph);
                        subLayout.setInitializer(vv.getGraphLayout());
                        subLayout.setSize(subLayoutSize);
                        clusteringLayout.put(subLayout, center);
                        vv.setGraphLayout(clusteringLayout);

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                // remove all sublayouts
                this.clusteringLayout.removeAll();
                vv.setGraphLayout(clusteringLayout);
            }
        }

    private void lock(UndirectedSparseGraph<String,JungEdge> g, AggregateLayout<String,JungEdge> alayout) {
        for (String s : g.getVertices()) {
            alayout.lock(s,true);
        }
    }

    private void setColors(ArrayList<Color> colors, int size) {
        colors = new ArrayList<>(size);
        Random rand = new Random();
        for (Color c : colors) {
            c = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        }
    }

    private int findCommunity(ArrayList<UndirectedSparseGraph<String,JungEdge>> com, String i) {
        for (int j = 0; j < com.size(); ++j) {
            if (com.get(j).containsVertex(i)) return j;
        }
        return -1; //error
    }

    public static class ExecutionPanel extends GraphPanel {

        private int step;
        private ArrayList<String> log;
        private static final char delimiter = '|';

        public ExecutionPanel(AlgorithmPController apc) {
            super(apc);

            step = -1;
            log = algorithmPController.getLogArray();

            controls.removeAll();
            final JButton backButton = new JButton("<-");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    backButtonActionPerformed(e);
                }
            });
            controls.add(backButton);

            JButton nextButton = new JButton("->");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextButtonActionPerformed(e);
                }
            });
            controls.add(nextButton);
        }

        private void backButtonActionPerformed(ActionEvent evt) {
            if (step > -1) {
                String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
                int code = Integer.parseInt(op[0]);
                switch (code) {
                    case 0:
                        removeVertexFromCommunity();
                        break;
                    case 1:
                        removeCommunityFromCommunity();
                        break;
                    case 2:
                        addEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                        break;
                }
                --step;
            }
        }

        private void nextButtonActionPerformed(ActionEvent evt) {
            if (step < log.size()-1) {
                ++step;
                String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
                int code = Integer.parseInt(op[0]);
                switch (code) {
                    case 0:
                        addVertexToCommunity();
                        break;
                    case 1:
                        addCommunityToCommunity();
                        break;
                    case 2:
                        removeEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                        break;
                }
            }
        }

        private void addVertexToCommunity() {

        }

        private void removeVertexFromCommunity() {

        }

        private void addCommunityToCommunity() {

        }

        private void removeCommunityFromCommunity() {

        }

        private void removeEdge(int u, int v) {
            System.out.println("remove edge (" + u + "," + v + ")");
        }

        private void addEdge(int u, int v) {
            System.out.println("add edge (" + u + "," + v + ")");
        }

    }

}
