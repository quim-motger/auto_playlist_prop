package prop.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * Louvain in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 18/04/15
 */
public class Louvain extends Algorithm {

    private int nCommunities;
    private double sEdges;
    private AlgorithmOutput log;
    private int maxComm;


    public Louvain() {
        nCommunities = 0;
        sEdges = 0;
        log = null;
        maxComm = 0;
    }

    @Override
    public AlgorithmOutput execute(Graph<Song> graph, int k) {
        //Initialize Variables
        log = new AlgorithmOutput();
        sEdges = sumEdges(graph);
        maxComm = k;
        nCommunities = graph.numberOfVertices();

        //Execute Louvain
        int[] finalComm = executeLouvain(graph);

        //Create Community Graphs
        for (int i = 0; i < nCommunities; ++i) {
            log.add(createCommunityGraph(graph, finalComm, i));
        }


        return log;
    }

    /**
     * Louvain Algorithm
     *
     * @param graph Graph where Louvain needs to be applied
     * @return array with the number of coomunity they belong to
     */
    private int[] executeLouvain(Graph<Song> graph) {
        //Base Case
        if (graph.numberOfVertices() <= maxComm) {
            log.add("End of Algorithm: More vertices (" + graph.numberOfVertices() +
                    ") than desirable communities (" + maxComm + ")");
            return initSingletonCommunities(graph);
        }

        //Initializing Communities
        log.add("Initializing new Round");
        int[] comms = initSingletonCommunities(graph);

        //Modularity Optimization
        log.add("ModularityOptimization");
        boolean moved = modularityOptimization(graph, comms);

        //If communities were optimized
        if (moved && nCommunities>maxComm) {
            log.add("Normalizing community numbers");
            comms = normalizeComms(comms);

            log.add("CommunityAggregation");
            int[] comms2 = executeLouvain(communityAggregation(graph, comms));

            log.add("Joining communities");
            for (int i = 0; i < comms2.length; ++i) {
                changeComm(i, comms2[i], comms);
            }
            comms = normalizeComms(comms);
        } else if (!moved){
            log.add("End of Algorithm: No more communities to be created");
        }else {
            log.add("End of Algorithm: Reached maximum number of communites");
        }
        return comms;
    }

    /**
     * Apply a modularity optimization round
     *
     * @param graph Graph to be optimized
     * @param comms communities to be changed
     * @return <code>true</code> if at least one node has changed it's community
     */
    private boolean modularityOptimization(Graph<Song> graph, int[] comms) {

        int nNodes = graph.numberOfVertices();
        Random rand = new Random(42);
        boolean roundMoved = false; //Has any node been moved in the last round?
        int idNode = rand.nextInt(nNodes); //Node being optimized
        if(idNode<0) idNode*=-1;
        boolean moved = false; //Has any node been moved?


        //Stops if we are in the last node and none has moved in the last round or if we have reached the limit of communities available
        while ((idNode != nNodes || roundMoved) && nCommunities!=maxComm) {
                       

            //Iteratiu i sequencial-> si arriba al final comença de nou
            if (idNode == nNodes) {
                idNode = rand.nextInt(nNodes);
                if(idNode<0) idNode*=-1;
                roundMoved = false;
            }

            //Max Modularity Gain for idNode
            double maxModGain = 0;
            int comDest = comms[idNode];

            //Takes out idNode from its Community
            int tmp = comms[idNode];
            comms[idNode] = -1;

            //Calculate the cost of taking out the node of its community
            double modImprov = -modularityGain(idNode, comms[idNode], comms, graph, sEdges);

            //For each edge that goes from idNode out
            for (int node : graph.adjacentVertices(idNode)) {
                if (comms[idNode] != comms[node]) {

                    //Adds up the modularity improvement to add the node to the community
                    modImprov += modularityGain(idNode, comms[node], comms, graph, sEdges);

                    //If modularityGain is over the maximum saved, save the new one
                    if (modImprov > maxModGain) {
                        comDest = comms[node];
                        maxModGain = modImprov;
                    }
                }
            }

            //Returns the node and move the node if there's a possible change
            comms[idNode] = tmp;
            if (comDest != comms[idNode]) {
                log.add("Moving node " + idNode + " from " + comms[idNode] + " to " + comDest + ": " + maxModGain);
                moveMode(idNode, comms[idNode], comDest, comms);
                roundMoved = true;
                moved = true;
            }

            ++idNode;
        }
        return moved;
    }

    /***************Community_Modifiers*****************/

    /**
     * Change a community number in comms
     *
     * @param from  original Community
     * @param to    new Community
     * @param comms array of communities
     */
    private void changeComm(int from, int to, int[] comms) {
        if (from != to) {
            log.add("Changing Community: " + from + " to " + to);
            for (int i = 0; i < comms.length; ++i) {
                if (comms[i] == from)
                    comms[i] = to;
            }
        }
    }

    /**
     * Converts communities with random numbers into communities with values between 0 and nCommunities
     *
     * @param comms array to be converted
     * @return converted array of communities
     */
    private int[] normalizeComms(int[] comms) {
        int currentComm = 0;
        int[] ret = new int[comms.length];

        int[] commTranslator = new int[comms.length];
        for (int i = 0; i < commTranslator.length; ++i)
            commTranslator[i] = -1;

        for (int i = 0; i < comms.length; ++i) {
            if (commTranslator[comms[i]] == -1) {
                log.add("Normalizing community " + comms[i] + " into " + currentComm);
                commTranslator[comms[i]] = currentComm;
                ++currentComm;
            }
            ret[i] = commTranslator[comms[i]];
        }
        return ret;
    }


    /**
     * Applies a movement from a community to another
     *
     * @param idNode  node being moved
     * @param comOrig Old/Original community
     * @param comDest New community which node belongs to
     */
    private void moveMode(int idNode, int comOrig, int comDest, int[] comm) {
        comm[idNode] = comDest;
        boolean destroyCom = true;
        for (int i : comm) {
            if (i == comOrig) destroyCom = false;
        }
        if (destroyCom) --nCommunities;
    }


    /**
     * Creates a Community for each vertice in the graph
     *
     * @param graph Original Graph
     * @return Array with the new communities
     */
    private int[] initSingletonCommunities(Graph<Song> graph) {
        int[] comms = new int[graph.numberOfVertices()];
        int n = graph.numberOfVertices();
        nCommunities = n;
        for (int i = 0; i < n; ++i) {
            comms[i] = i;
        }
        return comms;
    }

    /***************Graph_Creators*****************/

    /**
     * Creates a community Graph from a graph
     *
     * @param graph        Original Graph
     * @param finalComms   Array with communities identifier
     * @param selectedComm Community which graph will be created
     * @return Graph with vertex and edges of selectedComm
     */
    private Graph<Song> createCommunityGraph(Graph<Song> graph, int[] finalComms, int selectedComm) {
        Graph<Song> community = new Graph<>();
        ArrayList<Song> songs = graph.getOriginalVertices();

        //Add Nodes to community
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            if (finalComms[i] == selectedComm) {
                community.addVertex(songs.get(i));
            }
        }

        //Add Edges inside the community
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            if (finalComms[i] == selectedComm) {
                for (int adj : graph.adjacentVertices(i)) {
                    if (finalComms[adj] == selectedComm) {
                        community.addEdge(songs.get(i), songs.get(adj), graph.weight(i, adj));
                    }
                }
            }
        }
        return community;
    }

    /**
     * Applies a Community Aggregation Round
     *
     * @param graph Original Graph
     * @param comm  Communities to be agregated
     * @return graph with communities agregated
     */
    private Graph<Song> communityAggregation(Graph<Song> graph, int[] comm) {
        //Initialize round
        Graph<Song> agGraph = new Graph<>();
        ArrayList<Song> songs = graph.getOriginalVertices();

        //Add Vertices to Graph
        for (int i = 0; i < nCommunities; ++i)
            agGraph.addVertex(songs.get(i));

        //Add Edges to Graph
        for (int i = 0; i < nCommunities; ++i) {
            for (int j = 0; j < nCommunities; ++j) {
                //Add if there edges between communities
                double w = sEdgesBetweenC(graph, i, j, comm);
                if (w > 0.0)
                    agGraph.addEdge(i, j, w);
            }
            agGraph.addEdge(i, i, sEdgesInCom(i, graph, comm));
        }
        return agGraph;
    }

    /***************Graph_Comput_Methods*****************/

    /**
     * Modularity Gain of moving one vertex from one community to another
     *
     * @param idNode node to be moved
     * @param comDest destiny community
     * @param comm array of communities
     * @param graph original graph
     * @param m addition of all edges
     * @return modularity gain of moving idNode from its community to comDest
     */
    private double modularityGain(int idNode, int comDest, int[] comm, Graph<Song> graph, double m) {
        double sIn = sEdgesInCom(comDest, graph, comm);
        double sTot = sEdgesIncidentCom(comDest, graph, comm);
        double ki = sEdgesIncidentNode(idNode, graph);
        double kiIn = sEdgesBetweenNC(idNode, comDest, graph, comm);

        double ret = ((sIn + kiIn) / (2 * m)) - Math.pow((sTot + ki) / (2 * m), 2);
        return ret - ((sIn / (2 * m)) - Math.pow(sTot / (2 * m), 2) - Math.pow(ki / (2 * m), 2));
    }

    private double sEdgesBetweenC(Graph<Song> graph, int cOrig, int cDest, int[] comm) {
        double sum = 0.0;
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            if (comm[i] == cOrig) {
                for (int node : graph.adjacentVertices(i)) {
                    if (comm[node] == cDest) {
                        sum += graph.weight(i, node);
                    }
                }
            }
        }
        return sum;
    }

    /**
     * All edges added
     *
     * @return Addition of the weight of all edges in the graph
     */
    private double sumEdges(Graph graph) {
        double sum = 0;
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            LinkedHashSet l = graph.adjacentVertices(i);
            for (Object j : l) {
                sum += graph.weight((int) j, i);
            }
        }
        return sum / 2;
    }

    /**
     * Addition of the weight of the edges from a node to a community
     *
     * @param idNode  node where edges come from
     * @param comDest community where edges go to
     * @return addition of all edges betwee <code>idNode</code> and <code>comDest</code>
     */
    private double sEdgesBetweenNC(int idNode, int comDest, Graph<Song> graph, int[] comm) {
        double sum = 0;
        for (int node : graph.adjacentVertices(idNode))
            if (comm[node] == comDest)
                sum += graph.weight(idNode, node);
        return sum;
    }

    /**
     * Addition of the weight of all edges incident in a node
     *
     * @param idNode specified node
     * @return Addition of the weight of all edges incident in <code>idNode</code>
     */
    private double sEdgesIncidentNode(int idNode, Graph<Song> graph) {
        double sum = 0;
        for (int node : graph.adjacentVertices(idNode))
            sum += graph.weight(idNode, node);
        return sum;
    }

    /**
     * Addition of the weight of all edges coming to a Community
     *
     * @param comDest specified Community
     * @return Addition of the weight of all edges coming to <code>comDest</code>
     */
    private double sEdgesIncidentCom(int comDest, Graph<Song> graph, int[] comm) {
        double sum = 0;
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            if (comm[i] == comDest)
                for (int node : graph.adjacentVertices(i))
                    if (comm[node] != comDest)
                        sum += graph.weight(node, i);
        }
        return sum / 2;
    }

    /**
     * Addition of the weight of all edges inside a Community
     *
     * @param comDest specified Community
     * @return Addition of the weight of all edges inside <code>comDest</code>
     */
    private double sEdgesInCom(int comDest, Graph<Song> graph, int[] comm) {
        double sum = 0;
        for (int i = 0; i < graph.numberOfVertices(); ++i) {
            if (comm[i] == comDest)
                for (int node : graph.adjacentVertices(i))
                    if (comm[node] != comDest)
                        sum += graph.weight(node, i);
        }
        return sum / 2;
    }
}


/*
8 8
3 0 1
0 1 1
0 2 1
1 2 1
3 5 1
3 4 1
4 5 1
4 6 1
 */