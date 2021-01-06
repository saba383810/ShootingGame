package sampleGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Bee extends Subject  {
    int x;
    int y=300;
    static Image beeImg = new Image(Paths.get("sampleGameImages/bee.png").toUri().toString());
    Timeline timeline;
    Bee(){
        super(beeImg);
        timeline = new Timeline(new KeyFrame(Duration.millis(10),event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void run(){

        if (y>0) setTranslateY(y=y-5);
        //notifyObservers();
    }
    public void move(int tanpopo_x){
        y=300;
        setTranslateY(y);
        x= tanpopo_x;
        setTranslateX(x);
    }
}
