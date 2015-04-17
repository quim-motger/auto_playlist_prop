package prop.domain;

import java.util.ArrayList;
import java.util.Set;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm
 * @author joaquim.motger
 */
public class CliquePercolation {

    private Graph graph;

    private ArrayList<Set<Song>> cliques;

    public CliquePercolation(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Set<Song>> getMaximalCliques() {
        ArrayList<Set<Song>> cliques = new ArrayList<>();
        ArrayList<Song> potential_cliques = new ArrayList<>();
        ArrayList<Song> candidates = new ArrayList<>();
        ArrayList<Song> found = new ArrayList<>();
        //candidates.addAll(graph.getVertices());
        cliques = findCliques(potential_cliques, candidates, found);
        return cliques;
    }

    public ArrayList<Set<Song>> getBiggestMaximalCliques() {
        ArrayList<Set<Song>> cliques = new ArrayList<>();
        return cliques;
    }

    public ArrayList<Set<Song>> findCliques(ArrayList<Song> potential_cliques, ArrayList<Song> candidates, ArrayList<Song> found) {
        ArrayList<Set<Song>> cliques = new ArrayList<>();
        return cliques;
    }

}
