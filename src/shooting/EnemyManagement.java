package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyManagement {
    Timeline timeline;
    long time;

    public EnemyManagement(){
        time = System.currentTimeMillis();
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void run(){
        if(System.currentTimeMillis()-time>1000){
            Main.addEnemy(0);
            Main.addEnemy(1);
            time=System.currentTimeMillis();
        }
    }
}
