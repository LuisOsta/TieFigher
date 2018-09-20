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
public class PilotRoute implements Comparable<PilotRoute> {
    
    private double pathWeight;
    private boolean valid;
    private String pilotName;
    
    public PilotRoute(double w, boolean v, String name) {
        pathWeight = w;
        valid = v;
        pilotName = name;
    }
    
    public double getWeight() {
        return pathWeight;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public String getPilotName() {
        return pilotName;
    }
    
    
    public void setWeight(double w) {
        pathWeight = w;
    }
    
    public void setValid(boolean v) {
        valid = v;
    }
    
    public void setName(String n) {
        pilotName = n;
    }
    
    
    @Override
    public int compareTo(PilotRoute o) {
        if(valid) {
            if(o.valid) {
                return (int)(pathWeight - o.getWeight());
            }
            else {
                return -1;
            }
        }
        else {
            if(o.valid) {
                return 1;
            }
            else {
               return 1; 
            }
            
        }
        
    }
    
    @Override
    public String toString() {
        String result = pilotName;
        
        if(valid) {
            result += " "+pathWeight + " valid";
        }
        else {
            result += " invalid";
        }
        
        return result;
    }
}
