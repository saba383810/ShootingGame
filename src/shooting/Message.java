package shooting;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.nio.file.Paths;

public class Message extends Pane {
    static Image messageFrame= new Image(Paths.get("InvadersImage/UI/messageFrame.png").toUri().toString());


    public  Message(){

    }
}
