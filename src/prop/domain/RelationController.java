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

    public void addSongRelation(String s, String p) {
        Relation rsongs = parsing(s,p);
        ArrayList<Song> songs = graph.getOriginalVertices();
        for (Song s1 : songs) {
            for (Song s2 : songs) {
                if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist()))
                        && rsongs.evaluateSongs(s1,s2)) graph.addEdge(s1,s2,1);
            }
        }
    }

    public void addUserRelation(String s, String p, UserController userController) {
        Relation rusers = parsing(s,p);
        for (User u : userController.obtainUserSet()) {
            if (rusers.evaluateUser(u)) {
                ArrayList<List> lists = u.getAssociatedLists();
                for (List l : lists) {
                    for (Song s1 : l.obtainSongs()) {
                        for (Song s2 : l.obtainSongs()) {
                            if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist())))
                                graph.addEdge(s1,s2,1);
                        }
                    }
                }
            }
        }
    }

    public Relation parsing(String s, String p) {
        //s contains a list of all the simple relations
        //p contains de combination of relations by its index
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
        String[] sc = p.split(" or ");
        ArrayList<Relation> AND = new ArrayList<>();
        //Split by "or" so we get sets of relations connected by an AND relation
        for (i = 0; i < sc.length; ++i) {
            String[] cc = sc[i].split(" and ");
            int j;
            Relation rand;
            int n_rel;
            if (cc[0].length() > 1) {
                n_rel = Integer.parseInt(cc[0].substring(3));
                rand = new NOT(rl.get(n_rel));
            }
            else {
                n_rel = Integer.parseInt(cc[0]);
                rand = rl.get(n_rel);
            }
            //Set all relations connected by AND into a ComplexRelation
            for (j = 1; j < cc.length; ++j) {
                n_rel = Integer.parseInt(cc[j]);
                Relation rr = rl.get(n_rel);
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
