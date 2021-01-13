package Principal;

import java.awt.Graphics;

public class Bala {

    private int x;
    private int y;
    private int velocidad;
   
    public Bala(int x, int y){
       this.x = x;
       this.y = y;
       velocidad = 10;
      
    }
    public void tick(){
     x -= velocidad;  
    }
    public int getY(){
       return y;
    }
    public int getX(){
       return x;
    }
   
    public void render(Graphics g){
       g.drawImage(Imagen.bala,x, y, 25, 7,null);
       //g.setColor(Color.GREEN);
       //g.fillRect(x, y, 10, 6);
       //g.setColor(Color.BLACK);
    }
}


