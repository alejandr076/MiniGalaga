
package Principal;


public class Tiempo implements Runnable {

    public static int x;
    public static boolean funciona=false;
    Controlador c = new Controlador();
    public Tiempo() {
    
    }
    
    @Override
    public void run() {
        x=122;
        while (x>0) {            
            funciona=true;
            try {
             Thread.sleep(2000);
             x--;   

        } catch (InterruptedException e) {
        }
        }
        

    }
}
