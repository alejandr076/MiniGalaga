package Principal;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
   
   public static File sound;
    public static void main(String[]args){
        
    Configuracion juego = new Configuracion("Juego",700,425);  
    juego.empezar();
        sound = new File("Galaga.wav");
        playsound(sound);
   
    }

    private static void playsound(File sound){
//            try {
//            Clip sonido = AudioSystem.getClip();
//            sonido.open(AudioSystem.getAudioInputStream(new File("Galaga.WAV")));
//            sonido.start();
//            sonido.loop(Clip.LOOP_CONTINUOUSLY);
//            
//            while(sonido.isRunning())
//               Thread.sleep(1000);
//            
//            sonido.close();
//        } catch (Exception e) {
//            System.out.println(""+e);
//        }
    try {
        Clip music = AudioSystem.getClip();
        music.open(AudioSystem.getAudioInputStream(sound));
        music.start();
    } catch (Exception e) {
    }
}
}
