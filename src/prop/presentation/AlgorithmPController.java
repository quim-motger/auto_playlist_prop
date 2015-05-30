package prop.presentation;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import prop.domain.AlgorithmController;
import prop.domain.RelationController;

import java.util.ArrayList;

public class AlgorithmPController {

    private AlgorithmController algorithmController;
    private RelationController relationController;
    private ArrayList<UndirectedSparseGraph<String,Double>> communities;
    static final String delimiter = "\n";


    public AlgorithmPController() {
        algorithmController = new AlgorithmController();
        relationController = new RelationController();
        communities = new ArrayList<>();
    }

    public ArrayList<UndirectedSparseGraph<String,Double>> getCommunities() {
        return communities;
    }

    /** Gets the String communities from the algorithmController and converts each one to graph
     *
      */
    private void createCommunities() {
        String[] cs = algorithmController.getCommunities();
        for (String s : cs) {
            communities.add(createGraph(s));
        }
    }

    /**
     * Converts a string to a graph
     * @param graph
     * @return
     */
    private UndirectedSparseGraph<String,Double> createGraph(String graph) {
        UndirectedSparseGraph<String,Double> gr = new UndirectedSparseGraph<>();
        String[] vertices = graph.split(delimiter);
        for (int i = 0; i < vertices.length; i += 3) {
            gr.addVertex(vertices[i]);
            gr.addVertex(vertices[i+1]);
            gr.addEdge(Double.parseDouble(vertices[i+2]),vertices[i],vertices[i+1]);
        }
        return gr;
    }



}