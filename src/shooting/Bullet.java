package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Bullet extends ImageView {
    int x;
    int y;
    static Image bullet1Image = new Image(Paths.get("InvadersImage/Bullet/bullet1.png").toUri().toString());
    static PlayClip pc = new PlayClip("InvadersMusic/shot1.wav");
    String character;
    Timeline timeline;

    public Bullet(int charaX ,int charaY,String character) {
        this.character = character;
        x = charaX + 7;
        setTranslateX(x);
        y = charaY - 10;
        setTranslateY(y);
        //効果音: 重いから一旦コメント

        pc.reset();
        pc.play();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        if (Main.screenMaxX+10>=x&&Main.screenMinX<=x&&Main.screenMaxY>=y&&Main.screenMinY<=y) {
           setTranslateY(y -= 6);
           setImage(bullet1Image);
        }
        else {
            setImage(null);
            timeline.stop();
        }
    }
}
