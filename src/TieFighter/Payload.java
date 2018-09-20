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

public class Payload {
    int value;
    
    public Payload(int v) {
        value = v;
    }
    
    /**
     * 
     * @param p
     * @return 
     */
    @Override
    public boolean equals(Object p) {
        if(p instanceof Payload) {
            return value==((Payload)p).value;
        }
        else {
            return false;
        }
    }
    
    public String toString() {
        String result = "";
        result+= value;
        
        return result;
    }
}
