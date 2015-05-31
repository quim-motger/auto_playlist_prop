package prop.presentation;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import prop.PropException;
import prop.domain.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AlgorithmPController {

    private AlgorithmController algorithmController;
    private RelationController relationController;
    private SongController songController;
    private UserController userController;

    private ArrayList<String> log;
    static final String delimiter = "\n";


    public AlgorithmPController(SongController sc, UserController uc) {
        algorithmController = new AlgorithmController();
        relationController = new RelationController();
        songController = sc;
        userController = uc;
    }

    /* GETTERS */
    public AlgorithmController getAlgorithmController() {
        return algorithmController;
    }

    public RelationController getRelationController() {
        return relationController;
    }

    public String getLog() {
        StringBuilder sb = new StringBuilder();
        for (String s : log) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public UndirectedSparseGraph<String,Double> getOriginalGraph() {
        String s = algorithmController.getOriginalGraph();
        System.err.println(s);
       return createGraph(s);
    }

    /* OTHER METHODS */

    /** Gets the String communities from the algorithmController and converts each one to graph */
    public ArrayList<UndirectedSparseGraph<String,Double>> getCommunities() {
        ArrayList<UndirectedSparseGraph<String,Double>> communities = new ArrayList<>();
        String[] cs = algorithmController.getCommunities();
        for (String s : cs) {
            communities.add(createGraph(s));
        }
        return communities;
    }

    /**
     * Converts a string to a graph
     * @param graph
     * @return
     */
    private UndirectedSparseGraph<String,Double> createGraph(String graph) {
        UndirectedSparseGraph<String,Double> gr = new UndirectedSparseGraph<>();
        String[] vertices = graph.split(Pattern.quote(delimiter));
        for (int i = 0; i < vertices.length-2; i += 3) {
            gr.addVertex(vertices[i]);
            gr.addVertex(vertices[i+1]);
            gr.addEdge(Double.parseDouble(vertices[i+2]),vertices[i],vertices[i+1]);
        }
        return gr;
    }

    public void addRelation(String[] simpRel, String compRel, int n) throws PropException {
        relationController.addRelation(simpRel, compRel, n);
    }

    public void initGraph() {
       relationController.initGraph(songController, userController);
    }

    public void execute(String title, int algorithm, int k, ListController lc, RelationController rc) throws PropException {
        log = algorithmController.execute(title, algorithm, k, lc, rc);
    }
}