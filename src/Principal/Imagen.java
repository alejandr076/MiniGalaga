package Principal;//paquete donde esta

import java.awt.image.BufferedImage;//lee las imagenes
import java.io.IOException;//exepcion
import javax.imageio.ImageIO;//ver imagener

public class Imagen {//cargar imagen

    public static BufferedImage fondo;//fondo
    public static BufferedImage jugador,enemigo,mas,menos,bala;//jugador,enemigo
    
    public static void init(){
    fondo = imageLoader("/Resources/fondo.jpg");
    jugador = imageLoader("/Resources/jugador.png");
    enemigo = imageLoader("/Resources/enemigo.png");
    bala = imageLoader("/Resources/shot.png");
    mas = imageLoader("/Resources/estrella.png");
    menos = imageLoader("/Resources/banana.png");
    }
   
     public static BufferedImage imageLoader(String path){
           try {
           return 
        ImageIO.read(Imagen.class.getResource(path));
       } catch (IOException e) {
           e.printStackTrace();
           System.exit(1);
       }
           return null;     
     }

}
