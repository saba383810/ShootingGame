package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Player extends ImageView {

    static Image playerIMageFront = new Image(Paths.get("InvadersImage/Character/PlayerFront3132.png").toUri().toString());
    static Image playerImageRight = new Image(Paths.get("InvadersImage/Character/PlayerRight3132.png").toUri().toString());
    static Image playerImageLeft = new Image(Paths.get("InvadersImage/Character/PlayerLeft3132.png").toUri().toString());
    public static long shotTime=0;
    public int playerHP=4;
    static int canBulletShotTime =100;
    static PlayClip hit = new PlayClip("InvadersMusic/Hit.wav");
    static PlayClip rePop = new PlayClip("InvadersMusic/rePop.wav");

    static Timeline timeline;
    Bounds playerBounds;
    boolean[] isGetKeyCode;
    public static ArrayList<EnemyBullet> enemyBulletList;
    double damageTime;
    boolean isDamage = false;
    private int x;
    private int y;
    private Main mainScreen;

    Player(Main mainScreen){
        super(playerIMageFront);
        this.mainScreen = mainScreen;
        x=290;
        y=600;
        setTranslateY(y);
        setTranslateX(x);

        damageTime = System.currentTimeMillis();
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
        if(!isDamage) {
            setOpacity(1);
            if (isGetKeyCode[2]) setImage(playerImageRight);
            if (isGetKeyCode[3]) setImage(playerImageLeft);
            if (!isGetKeyCode[2] && !isGetKeyCode[3] || isGetKeyCode[2] && isGetKeyCode[3]) setImage(playerIMageFront);
        }else{
            setOpacity(0.3);
            if(System.currentTimeMillis()-damageTime >1000){
                rePop.reset();
                rePop.play();
                isDamage=false;
            }
        }

        //玉発射
        if (!(isDamage)&&isGetKeyCode[4] && System.currentTimeMillis()-shotTime>canBulletShotTime) {
            Main.shot();
            shotTime = System.currentTimeMillis();
        }
        //当たり判定

        playerBounds = getBoundsInParent();
        //全てのたまのリストを取得
        enemyBulletList = Main.getEnemyBulletList();
        if(System.currentTimeMillis()-damageTime>2000) {
            //現在のenemyと全てのbulletのどれかがぶつかっていれば、得点を増やし、画像をnullにする。
            for (EnemyBullet bullet : enemyBulletList) {
                if (playerBounds.intersects(bullet.getBoundsInParent())&&bullet.getTranslateY()<Main.screenMaxY) {
                    hit.reset();
                    hit.play();
                    playerHP--;
                    Main.changeHP(playerHP);
                    damageTime = System.currentTimeMillis();
                    isDamage = true;
                    break;
                }
            }
        }
        if(playerHP<=0)mainScreen.gameOver();
    }
    public static void playerStop(){
        timeline.stop();
    }
}
