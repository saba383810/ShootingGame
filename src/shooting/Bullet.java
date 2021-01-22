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
    static Image bullet1Image = new Image(Paths.get("InvadersImage/bullet1.png").toUri().toString());
    static Image bullet2Image = new Image(Paths.get("InvadersImage/bullet2.png").toUri().toString());
    static Image bullet3Image = new Image(Paths.get("InvadersImage/bullet3.png").toUri().toString());
    static PlayClip pc = new PlayClip("InvadersMusic/shotSE.wav");
    String character;

    Timeline timeline;

    public Bullet(int charaX ,int charaY,String character) {
        this.character = character;
        x = charaX + 7;
        y = charaY - 10;
        setTranslateX(x);
        setTranslateY(y);
        //効果音: 重いから一旦コメント
        //pc.play();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        if (Main.screenMaxX+10>=x&&Main.screenMinX<=x&&Main.screenMaxY>=y&&Main.screenMinY<=y) {
           setTranslateY(y -= 6);
           setImage(bullet1Image);

        }
        else setImage(null);
    }
}
