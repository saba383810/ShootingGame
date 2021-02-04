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
    long bulletTime;
    long currentTime;
    boolean bgmChange = false;
    private Bounds bossBounds;
    private ArrayList<Bullet> bulletList ;

    static Image bossImg = new Image(Paths.get("InvadersImage/Character/Boss1.png").toUri().toString());
    PlayClip destroySE = new PlayClip("InvadersMusic/Cymbal.wav");
    PlayClip bossShowSE = new PlayClip("InvadersMusic/bossShow.wav");
    PlayClip shootingSE = new PlayClip("InvadersMusic/shooting.wav");
    Timeline timeline;
    private Main mainScreen;
    boolean isShootingSE =false;

    public Boss(Main mainScreen){
        super(bossImg);
        this.mainScreen = mainScreen;
        setTranslateX(x);
        setTranslateY(y);
        bossShowSE.reset();
        bossShowSE.play();

        startTime = System.currentTimeMillis();

        bulletTime= System.currentTimeMillis();
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void run(){
        currentTime=System.currentTimeMillis();
        deltaTime= System.currentTimeMillis()-startTime;
        if(deltaTime<2500){
            setTranslateY(y++);
        }else if(!(bgmChange)&&deltaTime>3000&&deltaTime<3500){
            mainScreen.changeBGM();
            bgmChange=true;
            mainScreen.addBossHealth();
        }else if(deltaTime>4000&&deltaTime<5000){
            setTranslateX(x+=2);
            if(currentTime-bulletTime>250){
                mainScreen.enemyShot(x,y,"Boss1");
                bulletTime= System.currentTimeMillis();
            }
        }else if(deltaTime>5000&&deltaTime<10000){
            if(deltaTime%2000<1000) setTranslateX(x+=4);
            else setTranslateX(x -= 4);

            if(currentTime-bulletTime>250){
                mainScreen.enemyShot(x,y,"Boss1");
                bulletTime= System.currentTimeMillis();
            }
        }else if(deltaTime>10000&&deltaTime<13000){
            if(currentTime-bulletTime>250){
                mainScreen.enemyShot(x,y,"Boss1");
                bulletTime=System.currentTimeMillis();
            }
        }else if(deltaTime>13000&&deltaTime<14000){
            setTranslateX(x+=2);
        }else if(deltaTime>14000&&deltaTime<16000){
            if(currentTime-bulletTime>250){
                mainScreen.enemyShot(x,y,"Boss1");
                bulletTime=System.currentTimeMillis();
            }
        }else if(deltaTime>16000&&deltaTime<17000){
            setTranslateX(x+=2);
        }else if(deltaTime>17000&&deltaTime<19000){
            if(currentTime-bulletTime>250){
                mainScreen.enemyShot(x,y,"Boss1");
                bulletTime=System.currentTimeMillis();
            }
        }else if(deltaTime>19000&&deltaTime<20000){
            setTranslateX(x-=2);
        }else if(deltaTime>21000){
            if(!isShootingSE){
                shootingSE.reset();
                shootingSE.clip.loop(40);
                shootingSE.play();
                isShootingSE=true;
            }
            if(currentTime-bulletTime>30){
                mainScreen.enemyShot(x,y,"Boss2");
                bulletTime=System.currentTimeMillis();
            }
        }
        //boss当たり判定取得。
        bossBounds = getBoundsInParent();
        //全てのたまのリストを取得
        bulletList = mainScreen.getBulletList();
        //現在のenemyと全てのbulletのどれかがぶつかっていれば、得点を増やし、画像をnullにする。
        for (Bullet bullet : bulletList) {
            if (bossBounds.intersects(bullet.getBoundsInParent())) {
                mainScreen.addScore(440);
                bullet.touched();
                if(deltaTime>3500) {
                    mainScreen.changeBossHP(bossHP);
                    bossHP--;
                }
                break;
            }
        }

        if(bossHP<=0){
            shootingSE.stop();
            mainScreen.addScore(44500);
            destroySE.reset();
            destroySE.play();
            mainScreen.stageClear();
            timeline.stop();
        }
    }
    public void bossStop(){
        timeline.stop();
    }
}

