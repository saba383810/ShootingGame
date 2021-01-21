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
    static int canBulletShotTime =100;
    Timeline timeline;
    boolean[] isGetKeyCode;

    int x=400;
    int y=600;

    Player(){
        super(playerIMageFront);
        setTranslateY(y);
        setTranslateX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(30), event-> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    //キャラクターの移動
    public void run(){
        //keycode取得
        isGetKeyCode = Main.getKeyCodePress();

        //移動(Shiftが押されていたら低速移動モード。)
        if(isGetKeyCode[0] && Main.screenMinY<y) {
            if(isGetKeyCode[5])y-=6;
            else y-=9;
        }
        if(isGetKeyCode[1] && Main.screenMaxY>y) {
            if (isGetKeyCode[5]) y += 6;
            else y += 9;
        }
        if(isGetKeyCode[2] && Main.screenMaxX>x) {
            if (isGetKeyCode[5]) x += 6;
            else x += 9;
        }
        if(isGetKeyCode[3] && Main.screenMinX<x) {
            if (isGetKeyCode[5]) x -= 6;
            else x -= 9;
        }

        //画像処理
        if(isGetKeyCode[2]) setImage(playerImageRight);
        if(isGetKeyCode[3]) setImage(playerImageLeft);
        if(!isGetKeyCode[2] && !isGetKeyCode[3] || isGetKeyCode[2] && isGetKeyCode[3])setImage(playerIMageFront);

        //反映
        setTranslateX(x);
        setTranslateY(y);

        //玉発射
        if (isGetKeyCode[4] && System.currentTimeMillis()-shotTime>canBulletShotTime) {
            Main.shot();
            shotTime = System.currentTimeMillis();
        }
    }
}
