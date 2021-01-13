
package Principal;

import java.awt.Graphics;


public class Menos {
    
    private int x ;
    private int y ;
    
    public Menos(int x, int y){
        this.x=x;
        this.y=y;
    }
    
  public void render(Graphics g){
      g.drawImage(Imagen.menos, x, y,50,50,null);
  }
  
  public int getX(){
      return x;
  }
  
  public int getY(){
      return y;
  }
    
}