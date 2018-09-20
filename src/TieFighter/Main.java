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
import java.io.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Scanner graphInput = new Scanner(new File("galaxy.txt"));
        Scanner routesInput = new Scanner(new File("pilot_routes.txt"));
        PrintWriter writer = new PrintWriter(new File("patrols.txt"));
        
        
        Graph graph = new Graph();
        String line;
        while(graphInput.hasNextLine()) {
            line = graphInput.nextLine();
            int index = line.indexOf(" ");
            
            Integer vertex = Integer.parseInt(line.substring(0,index));
            String[] neighbors = line.substring(index+1).split(" ");
            
            insertVertex(vertex, graph);
            insertEdges(vertex,neighbors,graph);
        }
        
        ArrayList<PilotRoute> output_routes = new ArrayList<>();
        while(routesInput.hasNext()) {
            line = routesInput.nextLine();
            
            String[] parts = line.split(" ");
            
            String name = "";
            for(int x = 0;x<parts.length;x++) {
                if(x>=1 && isName(parts[x])) {
                    name+= " "+parts[x];
                }
                else if(isName(parts[x])) {
                    name += parts[x];
                }
                
                
            }
            
            String[] routes = line.substring(name.length()+1).split(" ");
            
            processRoutes(name, routes, graph, output_routes);
            
        }
        
        for(PilotRoute route: output_routes) {
            writer.println(route.toString());
        }
        writer.close();
    }
    
    
    /**
     * 
     * @param name
     * @param lineRoutes
     * @param graph
     * @param routes 
     */
    public static void processRoutes(String name, String[] lineRoutes, Graph graph, ArrayList<PilotRoute> routes) {
        
       boolean valid = isRouteValid(lineRoutes, graph);
       double weight = getRouteWeight(lineRoutes, graph);
       
       PilotRoute newPilot = new PilotRoute(weight, valid, name);
       
       int x = 0;
       while(true) {
           
           if(routes.size()==0) {
               routes.add(newPilot);
               break;
           }
           else if(routes.get(x).compareTo(newPilot)>0) {
               routes.add(x,newPilot);
               break;
           }
           else if(x+1==routes.size()) {
               routes.add(newPilot);
               break;
           }
           
           x++;
       }
    }
    
    /**
     * 
     * @param routes
     * @param graph
     * @return 
     */
    public static double getRouteWeight(String[] routes, Graph graph) {
        double total = 0;
        
        for(int x = 0;x<routes.length-1;x++) {
            
            total+= graph.getWeight(new Edge(Integer.parseInt(routes[x]), Integer.parseInt(routes[x+1])));
        }
        
        return total;
    }
    
    /**
     * 
     * @param routes
     * @param graph
     * @return 
     */
    public static boolean isRouteValid(String[] routes, Graph graph) {
        
        boolean result = true;
        for(int x = 0;x<routes.length-1;x++) {
            int origin = Integer.parseInt(routes[x]);
            int end = Integer.parseInt(routes[x+1]);
            
            if(!graph.isConnected(new Payload(origin),new Payload(end))) {
                result = false;
            }
        }
        
        return result;
    }
    
    /**
     * @dev - creates a GraphNode using the received vertex value and adds it to
     *      the graph
     * @param vertex - an integer value that will be used to create the Payload class
     * @param graph - the graph object that the payload will be added to
     */
    public static void insertVertex(Integer vertex, Graph graph) {
        GraphNode node = new GraphNode(new Payload(vertex));   
        graph.addVertex(node);
    }
    
    
    
    /**     
     * @dev - iterates through each received edge and setups a connection
     *          between the vertex and end value, with the considered weight
     * @param edges - the neighbors that the received vertex is connected to
     * @param vertex - the value of the graph node that will be used as a reference to create a edge
     * @param graph 
     */
    public static void insertEdges(Integer vertex, String[] edges, Graph graph) {
        
        for (String edge : edges) {
            String[] parts = edge.split(",");
            int value = Integer.parseInt(parts[0]);
            double weight = Double.parseDouble(parts[1]);
            graph.addEdge(new Edge(vertex, value, weight));
        }
    }
    
    
    /*
        @dev - determines whether the name is valid, so it can only be alphanumerical
                hyphens, and apostrophes
        @param - the name of the pilot
        @returns whether the string received is a valid name
    */
    public static boolean isName(String name) {
        boolean result = true;
        int alphaCount = 0;
        
        for(int x = 0;x<name.length();x++) {
            if(!Character.isAlphabetic(name.charAt(x))&&!Character.isDigit(name.charAt(x))&&name.charAt(x)!=' '&&name.charAt(x)!='-'&&name.charAt(x)!='\'') {
                result = false;
            }
            
            if(Character.isAlphabetic(name.charAt(x))) {
                alphaCount++;
            }
        }
        
        if(alphaCount==0) {
            return false;
        }
                
        return result;
    }
    
    
}
