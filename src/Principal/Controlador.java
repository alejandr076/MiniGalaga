package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controlador implements KeyListener{
   
    private Jugador jugador;
    private Enemigo e;
    public static ArrayList<Bala> bala;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<Mas> mas;
    private ArrayList<Menos> menos;
   
     private long current ;
     private long delay;
     private long delay2;
     private int salud;
     private int puntos;
     private int noenemigos;
     private boolean start;
     private int t;
     
    public Controlador(){
    }
    public void init(){    
        Ventana.frame.addKeyListener(this);
        jugador = new Jugador((Configuracion.ancho2/2)+275,(Configuracion.alto2-60)-150);
        jugador.init();
        e = new Enemigo();
        e.init();
        iniciartiempo();            
        repetir();
        bala = new ArrayList<Bala>(); 
        enemigos = new ArrayList<Enemigo>();
        mas = new ArrayList<Mas>();
        menos = new ArrayList<Menos>();
        current = System.nanoTime();
        delay = 2000;
        noenemigos = 0;
        salud =  e.getSalud();
        puntos = 0;
        
        
    }
    
    public void repetir(){
        

        new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
        itemsmas();
        itemsmenos();
        }
    }, 5000);
       
    }
    
    private void iniciartiempo() {
        Runnable r = new Tiempo();
        Thread t = new Thread(r);
        t.start();
    }
    
   
    
    
    public void tick(){
       
    t = Tiempo.x;
     if(start){
        jugador.tick();
  
      for(int i = 0; i<bala.size();i++){
         bala.get(i).tick();
      }
      Random rand = new Random();

      //int temp = rand.nextInt(15);
         //System.out.println(temp);
      long breaks = (System.nanoTime()-current)/1000000;
      if(breaks > delay){
        for(int i = 0; i<2 ; i++){
       int randX = rand.nextInt(400);
       int randY = rand.nextInt(300); 
         //if(health> 0){
        enemigos.add(new Enemigo(-randX,randY)); 

        }
       
      current = System.nanoTime();
      }
      

            
      //enemigos
     for(int i = 0; i<enemigos.size(); i++){
        enemigos.get(i).tick();
     }
   
     }
    }
    
    public void itemsmas(){
        Random o = new Random();
        int rx= o.nextInt(400);
        int ry= o.nextInt(300);
        new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
        mas.add(new Mas(rx,ry));
        }
    }, 5000); //espera 5 segundos para añadir un enemigo
    }
    
        public void itemsmenos(){
        Random o = new Random();
        int rx= o.nextInt(400);
        int ry= o.nextInt(300);
        new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
        menos.add(new Menos(rx,ry));
        }
    }, 5000); 
    }
    
    public void render(Graphics  g){
        
       if(start){jugador.render(g);
      
       // balas
       for(int  i=0;i<bala.size(); i++){
           bala.get(i).render(g);
       }
       
            
       for(int i = 0;i< bala.size();i++){
           if(bala.get(i).getX()<=5){
              bala.remove(i);
              i--;
              
          } }
      
       //enemigos
      
       for(int i = 0;i<enemigos.size();i++){
           if(!(enemigos.get(i).getX()<=0
                 ||enemigos.get(i).getX()>=700 - 50
                  ||enemigos.get(i).getY()>=350 -50)){
              
           if(enemigos.get(i).getY()>= 0){
              enemigos.get(i).render(g);      
            }
           } 
            } 
      
       //choque enemigos jugador
      
       for(int i = 0; i < enemigos.size();i++){
            int ex = enemigos.get(i).getX();
            int ey = enemigos.get(i).getY(); 
          
            int px = jugador.getX();
            int py = jugador.getY();

           if( px < ex + 50 && px + 60 > ex && py < ey + 50 && py + 60 > ey ||ex==640|| t==0){
              enemigos.remove(i);
              //i--;
              //health--;
              //System.out.println(health);
              //if(health<=0){
                  enemigos.removeAll(enemigos);
                  //player.setHealth(0);
                  start = false;  
                 
              //} 
             
           }

            for(int j =0;j<mas.size();j++){
            mas.get(j).render(g);
           }

            
//        for(int j =0;j<mas.size();j++){
//        mas.get(j).render(g);
//        }
        
        for(int y =0;y<menos.size();y++){
        menos.get(y).render(g);
        }
        
           // choque balas y enemigos
         for(int j = 0 ; j<bala.size() ;j++){
             int bx = bala.get(j).getX();
             int by = bala.get(j).getY();
                            
    if(ex < bx + 60 &&  ex + 50 > bx && ey< by + 6 && ey +  50> by){
        bala.remove(j);
        j--;
        salud--;
        
        //System.out.println(salud);
        if((salud%3)==0){
        enemigos.remove(i);
        i--;
        puntos = puntos + 35;
        noenemigos = noenemigos+1;
        }    
             }// else if(mx < bx + 60 &&  mx + 50 > bx && my< by + 6 && my +  50> by){
                // bala.remove(j);
                // puntos = puntos +15;
                 //mas.remove(z);
            // }
        }
         
        for(int b = 0;b<bala.size();b++){
            int bx = bala.get(b).getX();
            int by = bala.get(b).getY();
           
            for(int j =0;j<mas.size();j++){
                int mx = mas.get(j).getX();
                int my = mas.get(j).getY();
      
                if(mx < bx + 60 &&  mx + 50 > bx && my< by + 6 && my +  50> by){
                   bala.remove(b);
                   puntos = puntos +15;
                   mas.remove(j);
                }
  
                }   
            }
           
            for(int b = 0;b<bala.size();b++){
            int bx = bala.get(b).getX();
            int by = bala.get(b).getY();
           
            for(int j =0;j<menos.size();j++){
                int mx = menos.get(j).getX();
                int my = menos.get(j).getY();
      
                if(mx < bx + 60 &&  mx + 50 > bx && my< by + 6 && my +  50> by){
                   bala.remove(b);
                   puntos = puntos -15;
                   menos.remove(j);
                }
  
                }   
            }
         
        
            
         
         
           g.setColor(Color.WHITE);
           g.setFont(new Font("arial",Font.BOLD, 18));
           g.drawString("Puntuacion : "+puntos, 25,380);
           g.drawString("Enemigos : "+noenemigos,200,380);  
           if(!(t == 0||t==-1)){
               g.drawString("Tiempo : "+t ,400,380);
           }else{
               g.drawString("Tiempo : Game Over " ,400,380);
           }
       }
      
     
      
    }
   
       else{
           g.setColor(Color.WHITE);
           g.setFont(new Font("arial",Font.PLAIN,24));
           g.drawString("Press enter to start", 270, 175);
       }
       }
    
    
    public void keyPressed(KeyEvent e) {
     int source = e.getKeyCode();
        if(source == KeyEvent.VK_ENTER){
            start = true;
            init();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
                 
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
       // TODO Auto-generated method stub
      
    }

    }
     