package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Relation Controller
 *
 * @author gerard.casas.saez
 * @author joaquim.motger
 */
public class RelationController {

    private static final long EPSILON =  600000; //10 min
    SongController songController;
    UserController userController;
    Graph<Song> graph;
    
    public RelationController() {
        graph = new Graph();
    }

    public void initGraph(SongController sc, UserController uc) {
        songController = sc;
        userController = uc;
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

    /**
     * Adds Edges between each song played within 10 min of each other
     *
     * @param userController Contains all users and its playbacks
     */
    public void playbackRelations(UserController userController) {
        ArrayList<User> userSet = userController.obtainUsers();
        for(User u : userSet) {
            TreeSet<Playback> playbacks = u.getPlaybackRegister();
            addPlaybackRelation(playbacks);
        }

    }

    /**
     * add relations between songs related by its playback time
     * @param playbacks Array of playbacks
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
     * add relations between songs related by an specific relation expression
     * @param simpRel   the set of simple relations that compose the expression
     * @param exp       the expression to evaluate
     * @throws PropException
     */
    public void addRelation(String simpRel, String exp, int n) throws PropException{
        SimpleRelation[] simpRelArray = new SimpleRelation[n];
        String[] rel = simpRel.split(Pattern.quote("\n"));
        int i = 0;
        for (String s : rel) {
            String[] parts = s.split(Pattern.quote(" "));
            SimpleRelation r = new SimpleRelation(songController.getSongSet(),userController.obtainUserSet(),parts[0],parts[1]);
            simpRelArray[i] = r;
            i += 1;
        }
        Relation relation = parse(simpRelArray, exp);
        ArrayList<Song> songs = relation.evaluate();
        for (Song s1 : songs) {
            for (Song s2 : songs) {
                if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist())))
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
    /*public void addUserRelation(String simpRel, String exp, UserController userController) throws PropException{
        Relation rusers = parsing(simpRel, exp);
        for (User u : userController.obtainUsers()) {
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
    }*/

    /**
     * parses an expression into a Relation
     * @param simpRels  the set of simple relations that compose the expression
     * @param exp       the expression to evaluate
     * @return
     */
    /*private Relation parsing(String simpRels, String exp) {
        //s contains a list of all the simple relations
        //p contains de combination of relations by its index
        Relation r;
        String[] ss = simpRels.split("\n");
        int i;
        ArrayList<SimpleRelation> rl = new ArrayList<>();
        //Get an arrayList with all simple relations
        for (i = 0; i < ss.length; ++i) {
            String[] sr = ss[i].split(" ");
            rl.add(new SimpleRelation(sr[0], sr[1]));
        }
        for (i = 0; i < ss.length; ++i) {
            System.out.print(rl.get(i).getAttribute() + " " + rl.get(i).getValue() + "\n");
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

    /**
     **
     * @param simpleRelations Array of simple relations
     * @param expression & -> AND
     *                   | -> OR
     *                   ! -> NOT
     *                   EX: (((1&2)|3)&(!1|2))
     * @return
     */
    private Relation parse(SimpleRelation[] simpleRelations, String expression) throws PropException {
        if(!expression.contains("((") && !expression.contains("))")){
            if(expression.length()>5) throw new PropException(ErrorString.WRONG_EXPRESSION);
            
            if(expression.contains("&")){
                int idxRel1 = (int) expression.charAt(1) - '0';
                int idxRel2 = (int) expression.charAt(3) - '0';
                return new AND(simpleRelations[idxRel1],simpleRelations[idxRel2]);
            } else if (expression.contains("|")) {
                int idxRel1 = (int) expression.charAt(1) - '0';
                int idxRel2 = (int) expression.charAt(3) - '0';
                return new OR(simpleRelations[idxRel1], simpleRelations[idxRel2]);
            } else if (expression.contains("!")) {
                if (expression.length() > 2) throw new PropException(ErrorString.WRONG_EXPRESSION);
                int idxRel = (int) expression.charAt(1) - '0';
                return new NOT(simpleRelations[idxRel],songController.getSongSet());
            } else {
                if (expression.length() > 1) throw new PropException(ErrorString.WRONG_EXPRESSION);
                int idxRel = (int) expression.charAt(0) - '0';
                return simpleRelations[idxRel];
            }
        }

        int middle = findMiddle(expression);
        String subExpEsq = expression.substring(1, middle);
        String subExpDre = expression.substring(middle + 1, expression.length() - 1);
        char operator = expression.charAt(middle);

        Relation rel1 = parse(simpleRelations, subExpEsq);
        Relation rel2 = parse(simpleRelations, subExpDre);
        if (operator == '&') {
            return new AND(rel1, rel2);
        } else if (operator == '|') {
            return new OR(rel1, rel2);
        }
        throw new PropException(ErrorString.WRONG_EXPRESSION);
    }

    /**
     * From a complex expression, finds the position of the middle operator
     *
     * @param expression expression to evaluate
     * @return position of the middle operator in expression
     * @throws PropException Not a Complex expression
     */
    private int findMiddle(String expression) throws PropException {
        int cont = 0;
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == '(') ++cont;
            else if (c == ')') --cont;
            else if ((c == '&' || c == '|') & cont == 1) return i;
        }
        throw new PropException(ErrorString.WRONG_EXPRESSION);
    }


}
