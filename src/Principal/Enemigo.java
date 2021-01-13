package Principal;

import java.awt.Graphics;

public class Enemigo {
    private int x ;
    private int y;
    private int salud;
   
    public Enemigo(){
        
    }
    public Enemigo(int x, int y){
       this.x = x;
       this.y = y;
    }
    
    public void init(){
        salud=3000;
    }
    public void tick(){
          x += 1;
        }
    public void render(Graphics g){
       g.drawImage(Imagen.enemigo,x,y,50,50,null);
        }
    public int getX(){
     return x;
    }
    public int getY() {
       return y;
    }
   
    public int getSalud(){
       return salud;
    }
    
    public void setSalud(int salud){
       this.salud = salud;
    }
    

    }
