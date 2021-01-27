package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Player extends ImageView {

    static Image playerIMageFront = new Image(Paths.get("InvadersImage/Character/PlayerFront3132.png").toUri().toString());
    static Image playerImageRight = new Image(Paths.get("InvadersImage/Character/PlayerRight3132.png").toUri().toString());
    static Image playerImageLeft = new Image(Paths.get("InvadersImage/Character/PlayerLeft3132.png").toUri().toString());
    public static long shotTime=0;
    public int playerHP=4;
    static int canBulletShotTime =100;
    static PlayClip stage1BGM = new PlayClip("InvadersMusic/stage1.wav");
    static PlayClip boss1BGM = new PlayClip("InvadersMusic/boss1.wav");
    Timeline timeline;
    boolean[] isGetKeyCode;

    private int x=290;
    private int y=600;

    Player(){
        super(playerIMageFront);
        setTranslateY(y);
        setTranslateX(x);
        boss1BGM.reset();
        boss1BGM.play();

        timeline = new Timeline(new KeyFrame(Duration.millis(20), event-> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    //キャラクターの移動
    public void run(){
        //keycode取得
        isGetKeyCode = Main.getKeyCodePress();

        //移動(Shiftが押されていたら低速移動モード。)
        if(isGetKeyCode[0] && Main.screenMinY<y) {
            if(isGetKeyCode[5])setTranslateY(y-=4);
            else setTranslateY(y-=8);
        }
        if(isGetKeyCode[1] && Main.screenMaxY>y) {
            if (isGetKeyCode[5]) setTranslateY(y+=4);
            else setTranslateY(y+=8);
        }
        if(isGetKeyCode[2] && Main.screenMaxX>x) {
            if (isGetKeyCode[5]) setTranslateX(x+=4);
            else setTranslateX(x+=8);
        }
        if(isGetKeyCode[3] && Main.screenMinX<x) {
            if (isGetKeyCode[5]) setTranslateX(x-=4);
            else setTranslateX(x-=8);
        }

        //画像処理
        if(isGetKeyCode[2]) setImage(playerImageRight);
        if(isGetKeyCode[3]) setImage(playerImageLeft);
        if(!isGetKeyCode[2] && !isGetKeyCode[3] || isGetKeyCode[2] && isGetKeyCode[3])setImage(playerIMageFront);

        //玉発射
        if (isGetKeyCode[4] && System.currentTimeMillis()-shotTime>canBulletShotTime) {
            Main.shot(x,y,"Player");
            System.out.println("("+x+","+y+")");
            shotTime = System.currentTimeMillis();
        }
    }
}
