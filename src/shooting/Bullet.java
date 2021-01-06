package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Bullet extends Subject {
    int x;
    int y;
    static Image bulletImage = new Image(Paths.get("InvadersImage/bullet1.png").toUri().toString());
    Timeline timeline;

    public Bullet(int playerX ,int playerY) {
        super(null);

        x= playerX+7;
        y=playerY;
        setTranslateX(x);
        setTranslateY(y);

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        if (y>0) {
            setTranslateY(y -= 5);
            setImage(bulletImage);
        }
        else setImage(null);
        //notifyObservers();
    }
}
