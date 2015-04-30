package prop.domain;

import java.util.ArrayList;

/**
 * The controller for {@code GirvanNewman}, {@code Louvain} and {@code CliquePercolation}
 * @author oscar.manas
 */
public class AlgorithmController {

    Graph<Song> graph;

    public AlgorithmController() {
        graph = null;
    }

    public void createInputGraph(HashGraph<Song> graph) {

    }

    public ArrayList<String> execute(String title, int algorithm, int k, ListController listController) {

        AlgorithmOutput ao = null;

        switch (algorithm) {
            case 0:
                GirvanNewman gn = new GirvanNewman();
                ao = gn.execute(graph, k);
                break;
            case 1:
                Louvain l = new Louvain();
                ao = l.execute(graph, k);
                break;
            case 2:
                CliquePercolation cp = new CliquePercolation();
                //ao = cp.execute(graph);
                break;
        }

        Graph<Song> community = ao.densestGraph();
        List list = new List(title);
        for (Song s : community.getOriginalVertices()) {
            list.addSong(s);
        }

        listController.addList(list);

        return ao.getLog();
    }

}
