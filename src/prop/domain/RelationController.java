package prop.domain;

import java.util.ArrayList;

/**
 * Relation Controller
 * @author  gerard.casas.saez
 * @author  joaquim.motger
 */
public class RelationController {

    HashGraph graph;
    boolean dirty;
    
    public RelationController() {
        graph = new HashGraph();
    }

    public void initGraph(SongController sc) {
        graph = new HashGraph();
        ArrayList<Song> ss = sc.getSongSet().getSongSet();
        for (Song s : ss) {
            graph.addVertex(s);
        }
    }

    public HashGraph getGraph() {
        return graph;
    }

    public void playbackRelations(UserController userController) {
        UserSet userSet = userController.obtainUserSet();
        for(User u : userSet) {
            
            
        }

    }

    public void addRelation(String s){
        Relation r = parsing(s);

    }

    private Relation parsing(String s) {
        Relation r = null;
        return r;
    }
}
