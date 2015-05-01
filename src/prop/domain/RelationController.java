package prop.domain;

import java.util.ArrayList;

/**
 * Relation Controller
 * @author  gerard.casas.saez
 * @author  joaquim.motger
 */
public class RelationController {

    Graph graph;
    boolean dirty;
    
    public RelationController() {
        graph = new Graph();
    }

    public void initGraph(SongController sc) {
        graph = new Graph();
        ArrayList<Song> ss = sc.getSongSet().getSongSet();
        for (Song s : ss) {
            graph.addVertex(s);
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public void playbackRelations(UserController userController) {
        UserSet userSet = userController.obtainUserSet();
        for(User u : userSet) {
            
            
        }

    }

    public void addRelation(String s, String p){
        Relation r = parsing(s,p);

    }

    public Relation parsing(String s, String p) {
        Relation r;
        String[] ss = s.split("\n");
        int i;
        ArrayList<SimpleRelation> rl = new ArrayList<>();
        //Get an arrayList with all simple relations
        for (i = 0; i < ss.length; ++i) {
            String[] sr = ss[i].split(" ");
            rl.add(new SimpleRelation(sr[0], sr[1], sr[2]));
        }
        for (i = 0; i < ss.length; ++i) {
            System.out.print(rl.get(i).getType() + " " + rl.get(i).getAttribute() + " " + rl.get(i).getValue() + "\n" );
        }
        int n_rel = 0;
        String[] sc = p.split(" or ");
        ArrayList<Relation> AND = new ArrayList<>();
        //Split by "or" so we get sets of relations connected by an AND relation
        for (i = 0; i < sc.length; ++i) {
            String[] cc = sc[i].split(" and ");
            int j;
            Relation rand = rl.get(n_rel);
            ++n_rel;
            if (cc[0].length() > 1) rand = new NOT(rand);
            //Set all relations connected by AND into a ComplexRelation
            for (j = 1; j < cc.length; ++j) {
                Relation rr = rl.get(n_rel);
                ++n_rel;
                if (cc[j].length() > 1) rr = new NOT(rr);
                rand = new AND(rand, rr);
            }
            AND.add(rand);
        }
        r = AND.get(0);
        for (i = 1; i < sc.length; ++i) {
            r = new OR(r,AND.get(i));
        }
        return r;
    }
}
