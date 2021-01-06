package sampleGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Taiyou  extends ImageView implements  Observer {

    int x=0;
    int y=0;
    static Image sunImg = new Image(Paths.get("sampleGameImages/sun.png").toUri().toString());
    Timeline timeline;
    Taiyou() {
        super(sunImg);
        setTranslateX(x);
        setTranslateY(y);

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void run() {

        setTranslateX(x=x+5);

    }

    @Override
    public void update(Subject s) {

        int bee_x = ((Bee)s).x;
        int bee_y = ((Bee)s).y;
        //System.out.println("座標\n("+bee_x+","+bee_y+")("+x+","+y+")");
        if (Math.abs(bee_x-x)<=100&&Math.abs(bee_y-y)<=100){
            System.out.println("Hit！");
        }

    }
}
