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
    public static final String NOT_OPERATOR = "!";
    public static final char AND_OPERATOR = '&';
    public static final char OR_OPERATOR = '|';
    SongController songController;
    UserController userController;
    Graph<Song> graph;
    private ArrayList<Relation> relations;

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
        addRelationEdges();
        playbackRelations(userController.getSet().getUsers());
        return graph;
    }

    private void addRelationEdges() {
        for (Relation relation : relations) {
            addEdgesFromRelation(relation);
        }
    }

    /**
     * Adds Edges between each song played within 10 min of each other
     *
     * @param userSet Contains all users and its playbacks
     */
    public void playbackRelations(ArrayList<User> userSet) {
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
    public void addRelation(String simpRel, String exp) throws PropException {
        String[] rel = simpRel.split(Pattern.quote("\n"));
        int n = rel.length;
        SimpleRelation[] simpRelArray = new SimpleRelation[n];
        int i;
        for (i = 0; i < n; ++i) {
            String[] parts = rel[i].split(Pattern.quote(" "));
            SimpleRelation r = new SimpleRelation(songController.getSongSet(),userController.obtainUserSet(),parts[0],parts[1]);
            simpRelArray[i] = r;
        }

        Relation relation = parse(simpRelArray, exp);
        relations.add(relation);
    }

    /**
     * Add edges to Graph from relation
     * @param relation
     * @throws PropException
     */
    private void addEdgesFromRelation(Relation relation) {
        ArrayList<Song> songs = relation.evaluate();
        for (Song s1 : songs) {
            for (Song s2 : songs) {
                if (!(s1.getTitle().equals(s2.getTitle()) && s1.getArtist().equals(s2.getArtist())))
                    graph.addEdgeT(s1, s2, 1);
            }
        }
    }

    /**** PARSING *****/
    
    /**
     **
     * @param simpleRelations Array of simple relations
     * @param expression & -> AND
     *                   | -> OR
     *                   ! -> NOT
     *                   EX: (((1&2)|3)&(!1|2))
     * @return parsed relation
     */
    private Relation parse(SimpleRelation[] simpleRelations, String expression) throws PropException {
        /** BASIC CASE **/
        if(expression.contains("(")){
            if(expression.length()==1) {
                int index = extractNumber(expression.charAt(0));
                return simpleRelations[index];
            } else if(expression.contains(NOT_OPERATOR)) {
                int index = extractNumber(expression.charAt(1));
                return new NOT(simpleRelations[index], songController.getSongSet());
            } else {
                throw new PropException(ErrorString.WRONG_EXPRESSION);
            }
        }

        /** RECURSIVE CASE **/
        int middle = findMiddle(expression);
        String subExpEsq = expression.substring(1, middle);
        String subExpDre = expression.substring(middle + 1, expression.length() - 1);
        char operator = expression.charAt(middle);

        Relation rel1 = parse(simpleRelations, subExpEsq);
        Relation rel2 = parse(simpleRelations, subExpDre);
        if (operator == AND_OPERATOR) {
            return new AND(rel1, rel2);
        } else if (operator == OR_OPERATOR) {
            return new OR(rel1, rel2);
        }
        throw new PropException(ErrorString.WRONG_EXPRESSION);
    }

    /**
     * Convert from char to Integer
     * @param c is a char that represents a  number
     * @return the number that represents c
     */
    private int extractNumber(char c) {
        return (int) c -'0';
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
