package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Player extends ImageView {

    static Image playerIMageFront = new Image(Paths.get("InvadersImage/PlayerFront3132.png").toUri().toString());
    static Image playerImageRight = new Image(Paths.get("InvadersImage/PlayerRight3132.png").toUri().toString());
    static Image playerImageLeft = new Image(Paths.get("InvadersImage/PlayerLeft3132.png").toUri().toString());
    public static long shotTime=0;
    static int canBulletShotTime =200;
    Timeline timeline;
    boolean[] isGetKeyCode;

    int x=500;
    int y=600;

    Player(){
        super(playerIMageFront);
        setTranslateY(y);
        setTranslateX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    //キャラクターの移動
    public void run(){
        //keycode取得
        isGetKeyCode = Main.getKeyCodePress();

        //移動(Shiftが押されていたら低速移動モード。)
        if(isGetKeyCode[0]==true && Main.screenMinY<y) {
            if(isGetKeyCode[5]==true)y-=2;
            else y-=4;
        }
        if(isGetKeyCode[1]==true && Main.screenMaxY>y) {
            if (isGetKeyCode[5] == true) y += 2;
            else y += 4;
        }
        if(isGetKeyCode[2]==true && Main.screenMaxX>x) {
            if (isGetKeyCode[5] == true) x += 2;
            else x += 4;
        }
        if(isGetKeyCode[3]==true && Main.screenMinX<x) {
            if (isGetKeyCode[5] == true) x -= 2;
            else x -= 4;
        }

        //画像処理
        if(isGetKeyCode[2]==true) setImage(playerImageRight);
        if(isGetKeyCode[3]==true) setImage(playerImageLeft);
        if(isGetKeyCode[2]==false&&isGetKeyCode[3]==false || isGetKeyCode[2]==true && isGetKeyCode[3]== true)setImage(playerIMageFront);

        //反映
        setTranslateX(x);
        setTranslateY(y);

        //玉発射
        if (isGetKeyCode[4]==true && System.currentTimeMillis()-shotTime>canBulletShotTime) {
            Main.shot();
            shotTime = System.currentTimeMillis();
        }
    }
}
