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
public class Edge {
  int origin;
  int end;
  double weight;

  public Edge(int u, int v) {
    origin = u;
    end = v;
  }
  
  public Edge(int u, int v, double w) {
      origin = u;
      end = v;
      weight = w;
  }

  @Override
  public boolean equals(Object o) {
      if(o instanceof Edge) {
          return origin == ((Edge)o).origin && end == ((Edge)o).end;
      }
      else {
          return false;
      }
    
  }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.origin;
        hash = 67 * hash + this.end;
        return hash;
    }
  
  @Override
  public String toString() {
      return origin+" "+end;
  }
}
