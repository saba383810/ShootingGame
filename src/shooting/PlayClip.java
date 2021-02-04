package shooting;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayClip {

    Clip clip = null;

    PlayClip( String fileName){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void play(){
        clip.start();
        clip.flush();
    }
    public void stop(){
        clip.stop();
    }
    public void reset(){
        clip.setFramePosition(0);
    }
}
