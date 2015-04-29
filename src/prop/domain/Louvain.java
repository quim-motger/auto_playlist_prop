package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Louvain in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 18/04/15
 */
public class Louvain extends Algorithm {
    
    private int nNodes;
    private int nCommunities;
    private int [] community; //integer that says at which community the node is
    ArrayList<int[]> clustering;
    private ArrayList<Pair<Integer,Integer>>[] graph;
    private int sEdges;


    public Louvain() {
        nNodes = 0;
        nCommunities =0;
    }
    
    
    @Override
    public Result execute(Graph graph) {
        return null;
    }

    private void executeLouvain() {
        ArrayList<Pair<Integer,Integer>>[] nAgregatedGraph;
        Result ret = new Result();
        sEdges = sumEdges();
        initSingletonCommunities();
        
        //Indicates if a node can be moved in the next round
        boolean nodeMove = true;
        while (nCommunities >1 && nodeMove) {
            //Optimizes modularity of communities
            nodeMove = modularityOptimization();
            
            //Save previous state
            clustering.add(community);
            nAgregatedGraph = graph;
            
            //Creates new graph agregating all nodes in a community
            //graph = communityAgregation(nAgregatedGraph);
            //initSingletonCommunities(graph);
        }
    }
    
    
    private Graph communityAgregation() {
        //Graph<Integer,Edge<Integer>> graph = new Graph<>();
        //return graph;
        return null;
    }

    /**
     * Apply a modularity optimization round
     * @return <code>true</code> if at least one node has changed it's community
     */
    public boolean modularityOptimization() {
        //TODO:REMOVE THIS WHENEVER EXECUTE LOUVAIN IS IMPLEMENTED
        //TEMP____
        sEdges = sumEdges();
        initSingletonCommunities();
        //TEMP_____
        
        
        boolean roundMoved = false; //Has any node been moved in the last round?
        int idNode = 0; //Node being optimized
        boolean moved = false; //Has any node been moved?
        
        //Stops if we are in the last node and none has moved in the last round
        while (idNode!=nNodes || roundMoved) {
            
            //Iteratiu i sequencial-> si arriba al final comença de nou
            if(idNode==nNodes){
                idNode = 0;
                roundMoved=false;
            }
            
            //Max Modularity Gain for idNode
            double maxModGain = 0;
            int comDest= community[idNode];

            //Takes out idNode from its Community -> Confirmar que es correcte
            int tmp = community[idNode];
            community[idNode] = -1;
            
            //Calculate the cost of taking out the node of its community
            double modImprov = - modularityGain(idNode, community[idNode]);
            
            //For each edge that goes from idNode out
            for(Pair<Integer,Integer> edge:graph[idNode]){
                if(community[idNode]!= community[edge.first]) {
                    
                    //Adds up the modularity improvement to add the node to the community
                    modImprov += modularityGain(idNode, community[edge.first]);
                    
                    //If modularityGain is over the maximum saved, save the new one
                    if (modImprov > maxModGain) {
                        comDest = community[edge.first];
                        maxModGain = modImprov;
                    }
                }
            }
            
            //Returns the node and move the node if there's a possible change
            community[idNode] = tmp;
            if(comDest!= community[idNode]){
                //TODO:REMOVE THIS WHEN RESULT IMPLEMENTED ->SAVE TO RESULT
                System.out.println("Moving node "+idNode+" from "+ community[idNode]+" to "+comDest+": "+modularityGain(idNode, comDest));
                moveMode(idNode, community[idNode],comDest);
                roundMoved=true;
                moved=true;
            }
            
            ++idNode;
        }
        return moved;
    }

    /**
     * Calculates the gain of modularity that would derivate of moving a node to a community 
     * @param idNode Node that is being moved
     * @param comDest Target Community for the node
     * @return gain of modularity
     */
    private double modularityGain(int idNode, int comDest) {
        double sIn = sEdgesInCom(comDest);
        double sTot = sEdgesIncidentCom(comDest);
        double ki = sEdgesIncidentNode(idNode);
        double kiIn = sEdgesBetweenNC(idNode,comDest);
        double m = sEdges;

        double ret = ((sIn+kiIn)/(2*m))-Math.pow((sTot + ki) / (2 * m), 2);
        return ret - ((sIn/(2*m))-Math.pow(sTot / (2 * m), 2) - Math.pow(ki / (2 * m), 2));
    }


    /**
     * Applies a movement from a community to another 
     * @param idNode node being moved
     * @param comOrig Old/Original community
     * @param comDest New community which node belongs to
     */
    private void moveMode(int idNode, int comOrig, int comDest) {
        community[idNode] = comDest;
        boolean destroyCom = true;
        for(int i: community){
            if(i==comOrig) destroyCom = false;
        }
        if(destroyCom) --nCommunities;
    }


    /**
     * Creates a community for each node of the graph
     */
    private void initSingletonCommunities() {
        nNodes = graph.length;
        community = new int[nNodes];
        nCommunities = nNodes;
        for (int i = 0; i<nNodes; ++i){
            community[i]=i;
        }
        
    }

    
    /****IN/OUT*******/

    /**
     * TEMPORAL: Reads graph 
     */
    public void readGraph() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        graph = (ArrayList<Pair<Integer,Integer>>[])new ArrayList[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        int a, b, w;
        for (int i = 0; i < m; ++i) {
            a = in.nextInt();
            b = in.nextInt();
            w = in.nextInt();
            graph[a].add(new Pair<>(b,w));
            graph[b].add(new Pair<>(a,w));
        }
    }

    /**
     * TEMPORAL: Writes Graph and communities 
     */
    public void writeGraph() {
        System.out.println("Adjacency list:");
        for (ArrayList<Pair<Integer,Integer>> l : graph) {
            for (Pair<Integer,Integer> v : l) {
                System.out.print(v.first + " ");
            }
            System.out.print("\n");
        }

        System.out.println("Community List:");
        for (int i =0; i< community.length;++i) {
            System.out.println(i+":"+community[i]);
        }
        System.out.print("\n");
    }
    
    /***************Graph_Comput_Methods*****************/

    /**
     * All edges added 
     * @return Addition of the weight of all edges in the graph
     */
    private int sumEdges() {
        int sum = 0;
        for(int i=0;i<graph.length;++i) {
            for(Pair<Integer,Integer> edge : graph[i]){
                sum+=edge.second;
            }
        }
        return sum/2;
    }

    /**
     * Addition of the weight of the edges from a node to a community
     * @param idNode node where edges come from
     * @param comDest community where edges go to
     * @return addition of all edges betwee <code>idNode</code> and <code>comDest</code>
     */
    private int sEdgesBetweenNC(int idNode, int comDest) {
        int sum = 0;
        for (Pair<Integer,Integer> edge:graph[idNode])
            if(community[edge.first]==comDest)
                sum+=edge.second;
        return sum;
    }

    /**
     * Addition of the weight of all edges incident in a node
     * @param idNode specified node
     * @return  Addition of the weight of all edges incident in <code>idNode</code> 
     */
    private int sEdgesIncidentNode(int idNode) {
        int sum=0;
        for (Pair<Integer,Integer> edge:graph[idNode])
            sum+=edge.second;
        return sum;
    }

    /**
     * Addition of the weight of all edges coming to a Community
     * @param comDest specified Community
     * @return Addition of the weight of all edges coming to <code>comDest</code>
     */
    private int sEdgesIncidentCom(int comDest) {
        int sum = 0;
        for(int i=0;i<graph.length;++i) {
            if(community[i]==comDest)
                for(Pair<Integer,Integer> edge : graph[i])
                    if(community[edge.first]!=comDest)
                        sum+=edge.second;
        }
        return sum/2;
    }

    /**
     * Addition of the weight of all edges inside a Community
     * @param comDest specified Community
     * @return Addition of the weight of all edges inside <code>comDest</code>
     */
    private int sEdgesInCom(int comDest) {
        int sum = 0;
        for(int i=0;i<graph.length;++i) {
            if(community[i]==comDest)
                for(Pair<Integer,Integer> edge : graph[i])
                    if(community[edge.first]==comDest)
                        sum+=edge.second;
        }
        return sum/2;
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