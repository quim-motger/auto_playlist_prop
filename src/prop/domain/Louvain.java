package prop.domain;

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
    
    
    @Override
    public Result execute(Graph graph) {
        return null;
    }
    
    
    private void initSingletonClusters() {
        cluster = new int[nNodes];
        nClusters = nNodes;
        for (int i = 0; i<nNodes; ++i){
            cluster[i]=i;
            
        }
        
    }
    
    
    
    
}
