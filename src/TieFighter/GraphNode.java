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


public class GraphNode<E> {
    E object;
    protected LinkedList<Edge> neighbors;
    
    public GraphNode(E o) {
        object = o;
        neighbors = new LinkedList<>();
    }
    
    public GraphNode(E o, LinkedList<Edge> n) {
        neighbors = n;
    }
    
    public LinkedList<Edge> getNeighbors() {
        return neighbors;
    }
    
    public void addEdge(Edge edge) {
        if(neighbors.contains(edge)) {
            return;
        }
        else {
            neighbors.add(edge);
        }
    }
    
    @Override
    public String toString() {
        return object.toString();
    }
    
}
