
package Principal;

import java.awt.Graphics;


public class Mas {
    
    private int x ;
    private int y ;
    
    public Mas(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void tick(){
        x += 1000 ;
    }
    
  public void render(Graphics g){
      g.drawImage(Imagen.mas, x, y,50,50,null);
  }
  
  public int getX(){
      return x;
  }
  
  public int getY(){
      return y;
  }
    
}
