/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TieFighter;

/**
 *
 * @author luiso
 */
/*
    Luis Osta
    ldo160030
*/
import java.util.*;

public class Graph {
    
    private ArrayList<GraphNode> graph;
    
    public Graph() {
        graph = new ArrayList<>();
    }
    
    public Graph(ArrayList<GraphNode> g) {
        graph = g;
    }
    
    /**
     * @dev
     * @param node
     */
    public void addVertex(GraphNode node) {
        graph.add(node);
    }
    
    
    
    /**
     * @dev
     * @param edge - the edge to add the respective graph node
     */
    public void addEdge(Edge edge) {
        for(GraphNode<Payload> node: graph) {
            if(node.object.equals(new Payload(edge.origin))) {
                node.addEdge(edge);
            }
        }
    }
    
    
    
    /**
     * 
     * @param edge - the connection between origin and endpoint, to find the
     *              weight of
     * @return - the value of the weight between the origin and the end point,
     *          between two vertices
     */
    public double getWeight(Edge edge) {
        for(GraphNode<Payload> node: graph) {
            if(node.object.equals(new Payload(edge.origin))) {
                
                for(Edge e: node.getNeighbors()) {
                    if(e.equals(edge)) {
                        return e.weight;
                    }
                }
            }
        }
        
        return 0;
    }
    
    
    
    /**
     * 
     * @param vertex
     * @return - the GraphNode with the matching payload value
     */
    public GraphNode<Payload> searchVertex(Payload vertex) {
        
        for(GraphNode node: graph) {
            if(node.object.equals(vertex)) {
                return node;
            }
        }
        
        return null;
    }
    
    public Edge searchEdge(Payload origin, Payload end) {
        return null;
    }
    
    
    
    /**
     * 
     * @return - the size of the graph(the number of vertices)
     */
    public int getSize() {
        return graph.size();
    }
    
    
    
    /**
     * 
     * @param vertex
     * @return 
     */
    public LinkedList<Edge> getNeighbors(Payload vertex) {
        for(GraphNode node: graph) {
            if(node.object.equals(vertex)) {
                return node.getNeighbors();
            }
        }
        
        return null;
    }
    
    /**
     * 
     * @param v
     * @param node
     * @param visited 
     */
    public void DFSHelper(int v, GraphNode<Payload> node,boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(node.toString()+" ");
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Edge> neighbors = node.getNeighbors().iterator();
        while (neighbors.hasNext())
        {
            Edge edge = neighbors.next();
            
            int n = edge.end%visited.length;
            if (!visited[n]) {
                GraphNode<Payload> next = searchVertex(new Payload(edge.end));
                DFSHelper(n, next, visited);
            }
        }
    }
 
    // The function to do DFS traversal. It uses recursive DFSUtil()
    /**
     * 
     * @param p 
     */
    public void DFS(Payload p) {
        GraphNode<Payload> node = searchVertex(p);
        if(node==null) {
            return;
        }
        else {
                // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean visited[] = new boolean[graph.size()];

            // Call the recursive helper function to print DFS traversal
            DFSHelper(p.value%visited.length,node, visited);
        }
        
    }
    
    /**
     * 
     * @param origin
     * @param end
     * @return 
     */
    public boolean isConnected(Payload origin, Payload end) {
        boolean result = false;
        if(origin==null||end==null) {
            return result;
        }
        else {
            GraphNode<Payload> node = searchVertex(origin);
            Iterator<Edge> neighbors = node.getNeighbors().iterator();
            while(neighbors.hasNext()) {
                Edge edge = neighbors.next();
                
                if(end.equals(new Payload(edge.end))) {
                    result = true;
                }
            }
        }
        return result;
    }
 
    
    /**
     * 
     * @return The value of each node in the graph
     */
    @Override
    public String toString() {
        String result = "";
        for(GraphNode node: graph) {
            result+= node.toString() +" ";
        }
        
        return result;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isEmpty() {
        return graph.size()==0;
    }
}
