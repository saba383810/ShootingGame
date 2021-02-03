package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Boss extends ImageView {

    private long startTime;
    private long deltaTime;

    int x = 260;
    int y = -100;
    int bossHP = 200;
    boolean bgmChange = false;
    private Bounds bossBounds;
    private ArrayList<Bullet> bulletList ;

    static Image bossImg = new Image(Paths.get("InvadersImage/Character/Boss1.png").toUri().toString());
    Timeline timeline;
    private Main mainScreen;

    public Boss(Main mainScreen){
        super(bossImg);
        this.mainScreen = mainScreen;
        setTranslateX(x);
        setTranslateY(y);

        startTime = System.currentTimeMillis();


        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void run(){
        deltaTime= System.currentTimeMillis()-startTime;
        if(deltaTime<2500){
            setTranslateY(y++);
        }else if(!(bgmChange)&&deltaTime>3000&&deltaTime<3500){
            mainScreen.changeBGM();
            bgmChange=true;
        }else if(deltaTime>4000&&deltaTime<5000){
            setTranslateX(x+=2);
        }else if(deltaTime>5000&&deltaTime<10000){
            if(deltaTime%2000<1000) setTranslateX(x+=4);
            else setTranslateX(x -= 4);
        }else{

        }
        //boss当たり判定取得。
        bossBounds = getBoundsInParent();
        //全てのたまのリストを取得
        bulletList = mainScreen.getBulletList();
        //現在のenemyと全てのbulletのどれかがぶつかっていれば、得点を増やし、画像をnullにする。
        for (Bullet bullet : bulletList) {
            if (bossBounds.intersects(bullet.getBoundsInParent())) {
                mainScreen.addScore(440);
                mainScreen.changeBossHP(bossHP);
                bullet.touched();
                bossHP--;
                break;
            }
        }
        if(bossHP<=0){

            mainScreen.stageClear();
            timeline.stop();
        }
    }
}

