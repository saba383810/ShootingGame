package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class EnemyBullet extends ImageView {
    int x;
    int y;
    static Image bullet2Image = new Image(Paths.get("InvadersImage/Bullet/bullet2.png").toUri().toString());
    static Image bullet3Image = new Image(Paths.get("InvadersImage/Bullet/bullet3.png").toUri().toString());
    static PlayClip pc = new PlayClip("InvadersMusic/EnemyBulletSE.wav");
    String character;

    Timeline timeline;

    public EnemyBullet(int charaX ,int charaY,String character) {
        this.character = character;
        x = charaX + 7;
        y = charaY;
        setTranslateX(x);
        setTranslateY(y);
        pc.reset();
        pc.play();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        if (Main.screenMaxX+10>=x&&Main.screenMinX<=x&&Main.screenMaxY+10>=y&&Main.screenMinY<=y) {
            switch (character) {
                case "Enemy1":
                    setImage(bullet2Image);
                    setTranslateX(x+=3);
                    setTranslateY(y+=4);
                    break;
                case "Enemy2":
                    setImage(bullet3Image);
                    setTranslateX(x-=3);
                    setTranslateY(y+=4);
                    break;
            }
        }
        else {
            setImage(null);
            timeline.stop();
        }
    }
}
