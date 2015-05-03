package prop.domain;

import prop.ExpressionTree;
import prop.PropException;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Relation Controller
 * @author  gerard.casas.saez
 * @author  joaquim.motger
 */
public class RelationController {

    private static final long EPSILON =  600000; //10 min
    Graph<Song> graph;
    
    public RelationController() {
        graph = new Graph();
    }

    public void initGraph(SongController sc) {
        graph = new Graph<>();
        ArrayList<Song> ss = sc.getSongSet().getSongSet();
        for (Song s : ss) {
            graph.addVertex(s);
        }
    }

    /**
     * Return Graph created
     *
     * @return graph with all relations
     */
    public Graph getGraph() {
        return graph;
    }

    public void playbackRelations(UserController userController) {
        UserSet userSet = userController.obtainUserSet();
        for(User u : userSet) {
            TreeSet<Playback> playbacks = u.getPlaybackRegister();
            addPlaybackRelation(playbacks);
        }

    }

    /**
     * add relations between songs related by its playback time
     * @param playbacks
     */
    private void addPlaybackRelation(TreeSet<Playback> playbacks) {
        for(Playback playback : playbacks) {
            for(Playback test : playbacks) {
                long diff = obtainTimeFromPlayBack(test) - obtainTimeFromPlayBack(playback); 
                if(diff > 0 && diff < EPSILON){
                    graph.addEdgeT(playback.getSong(),test.getSong(),1);
                }
            }
        }
        
    }

    /**
     * obtain the time from a playback
     * @param playback
     * @return  the time of the playback
     */
    private long obtainTimeFromPlayBack(Playback playback) {
        return playback.getDate().getTime().getTime();
        
    }

    /**
     * add relations between songs related by an specific song relation expression
     * @param simpRel   the set of simple relations that compose the expression
     * @param exp       the expression to evaluate
     * @throws PropException
     */
    public void addSongRelation(String simpRel, String exp) throws PropException{
        Relation rsongs = parsing(simpRel, exp);
        ArrayList<Song> songs = graph.getOriginalVertices();
        for (Song s1 : songs) {
            for (Song s2 : songs) {
                if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist()))
                        && rsongs.evaluateSongs(s1,s2))
                    graph.addEdgeT(s1, s2, 1);
            }
        }
    }

    /**
     * add relations between songs of the list of the users that match with the specified relation
     * @param simpRel       the set of simple relations that compose the expression
     * @param exp           the expression to evaluate
     * @param userController    the userController that contains the users
     * @throws PropException
     */
    public void addUserRelation(String simpRel, String exp, UserController userController) throws PropException{
        Relation rusers = parsing(simpRel, exp);
        for (User u : userController.obtainUserSet()) {
            if (rusers.evaluateUser(u)) {
                ArrayList<List> lists = u.getAssociatedLists();
                for (List l : lists) {
                    for (Song s1 : l.obtainSongs()) {
                        for (Song s2 : l.obtainSongs()) {
                            if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist())))
                                graph.addEdgeT(s1, s2, 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * parses an expression into a Relation
     * @param simpRels  the set of simple relations that compose the expression
     * @param exp       the expression to evaluate
     * @return
     */
    private Relation parsing(String simpRels, String exp) {
        //s contains a list of all the simple relations
        //p contains de combination of relations by its index
        Relation r;
        String[] ss = simpRels.split("\n");
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
        String[] sc = exp.split(" or ");
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
                Relation rr;
                if (cc[0].length() > 1) {
                    n_rel = Integer.parseInt(cc[0].substring(3));
                    rr = new NOT(rl.get(n_rel));
                }
                else {
                    n_rel = Integer.parseInt(cc[0]);
                    rr = rl.get(n_rel);
                }
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
