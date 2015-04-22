package prop.domain;

import java.util.ArrayList;

/**
 * Louvain in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 18/04/15
 */
public class Louvain extends Algorithm {
    
    int nNodes;
    int nClusters;
    int [] cluster; //integer that says at which cluster the node is
    ArrayList<int[]> clustering;
    
    
    public Louvain() {
        nNodes = 0;
        nClusters=0;
    }
    
    
    @Override
    public Result execute(Graph graph) {
        return executeLouvain(graph);
    }

    private Result executeLouvain(Graph graph) {
        Graph nAgregatedGraph;
        Result ret = new Result();
        initSingletonClusters(graph);
        
        //Indicates if a node can be moved in the next round
        boolean nodeMove = true;
        while (nClusters>1 && nodeMove) {
            //Optimizes modularity of communities
            nodeMove = modularityOptimization(graph);
            
            //Save previous state
            clustering.add(cluster);
            nAgregatedGraph = graph;
            
            //Creates new graph agregating all nodes in a community
            graph = communityAgregation(nAgregatedGraph);
            initSingletonClusters(graph);
        }
        return ret;
    }

    
    private Graph communityAgregation(Graph oldGraph) {
        //Graph<Integer,Edge<Integer>> graph = new Graph<>();
        //return graph;
        return null;
    }

    /**
     * Apply a modularity optimization round
     * @return <code>true</code> if at least one node has changed it's community
     */
    private boolean modularityOptimization(Graph graph) {
        return false;
    }


    private void initSingletonClusters(Graph graph) {
        //nNodes = graph.getVertexs().size();
        cluster = new int[nNodes];
        nClusters = nNodes;
        for (int i = 0; i<nNodes; ++i){
            cluster[i]=i;
        }
        
    }
    
    
    
    
}
