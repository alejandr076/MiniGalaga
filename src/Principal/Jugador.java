package Principal;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jugador implements KeyListener{
       private int x;
       private int y;
       private boolean arriba ,abajo;
       private boolean disparo;
      
       private long actual ;
       private long retraso;
       //private int health;

      
      
    public Jugador(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public void init(){
    Ventana.frame.addKeyListener(this);
    actual = System.nanoTime();
    retraso = 100;
   // health = 3;
    }
    public void tick(){
      // if(!(health <=0)){
           if(arriba){
     
           if(y >= 10){
           y  -=4;
       }
        }
       if(abajo){
           if(y<= 340-60){
           y +=  4;}
       }
       if(disparo){
           long breaks = (System.nanoTime()-actual)/1000000;
           if(breaks > retraso){
           Controlador.bala.add(new Bala(x+30,y+30));
       }
       actual = System.nanoTime();
       }
       //}
     }
     public void render(Graphics g){
      //if(!(health <= 0)){
        
         g.drawImage(Imagen.jugador,x, y, 60, 60,null);
               
      //}
      }
    public void keyPressed(KeyEvent e) {
       int source = e.getKeyCode();
       if(source == KeyEvent.VK_UP){
           arriba = true;
       }
       if(source == KeyEvent.VK_DOWN){
           abajo= true;
          
       }
        if(source == KeyEvent.VK_Q){
           disparo = true;
        }
    }
     
    public void keyReleased(KeyEvent e) {
       int source = e.getKeyCode();
       if(source == KeyEvent.VK_UP){
           arriba = false;
       }
       if(source == KeyEvent.VK_DOWN){
           abajo= false;
          
       }
       if(source == KeyEvent.VK_Q){
           disparo = false;
       }
    }
     
    public void keyTyped(KeyEvent e) {
      
    }
    public int getX(){
       return x;
    }
    public int getY()
    {
       return y;
    }
    //public int getHealth(){
      // return health;
    //}
    //public void setHealth(int health){
      // this.health = health;
    //}
   

}
