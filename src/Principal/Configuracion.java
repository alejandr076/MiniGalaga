package Principal;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Configuracion implements Runnable {
   
    private String titulo;
    private int ancho;
    private int alto;
    private Thread hilo;
    private boolean funciona;
    private BufferStrategy buffer;
    private Graphics g;
    private int x;
    private boolean empezar;
    private Controlador control;
    private Ventana juego;
    public static final int ancho2 = 700;
    public static final int alto2 = 350;
   
    public Configuracion(String titulo,int ancho,int alto){
   
       this.titulo = titulo;
       this.ancho = ancho;
       this.alto = alto;
  
    }
    
      public void init(){
          
        juego = new Ventana(titulo,ancho,alto);
        Imagen.init();
        control = new Controlador();
        control.init();
        empezar = true;
        }
   
     public synchronized void empezar(){
        if(funciona)
         return ;
        funciona = true;
        if(hilo == null){
        hilo = new Thread(this);
        hilo.start();
        }
        
     }
     public synchronized void stop(){
        if(!(funciona))
            return;
            funciona = false;
        try {
           hilo.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
     }
     public void tick() throws InterruptedException{
        control.tick();
       }
     public void render(){
      
       buffer = juego.getCanvas().getBufferStrategy();
       if(buffer == null){
         juego.getCanvas().createBufferStrategy(3);
         return;
       }
      
      g = buffer.getDrawGraphics();
      g.clearRect(0, 0, ancho, alto);
      //draw  
     
       g.drawImage(Imagen.fondo,0,0, ancho2, alto2,null);

        
         control.render(g);      
       // menu

     //end of draw
        
      buffer.show();
      g.dispose();
        
     }
       
     
    public void run() {
        init();
        int fps = 60;
        double tiempo = 1000000000/fps;
        double d = 0;
        long current = System.nanoTime();
      

         while(funciona){
      
       d = d + (System.nanoTime()-current)/tiempo;
            current = System.nanoTime();
            if(d>=1){
           try {
               tick();
           } catch (InterruptedException ex) {
               Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
           }
              render();
               d--;
 }
   
}

    }
    }

