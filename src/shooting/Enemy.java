package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

import java.nio.file.Paths;

public class Enemy extends ImageView {
    int x ;
    int y ;
    int enemyHP;
    public int actNum=0;
    private long time;
    Timeline timeline;
    ArrayList<Bullet> bulletList ;
    Bounds enemyBounds;
    static Image enemyImage = new Image(Paths.get("InvadersImage/Character/Enemy1.png").toUri().toString());
    PlayClip destroySE = new PlayClip("InvadersMusic/destroy.wav");
    public Enemy(int actNum){
        super(enemyImage);
        this.actNum=actNum;
        //行動パターンに伴った初期位置を指定

        if(actNum==0){
            x=50;
            y=0;
            enemyHP=1;
        }else if(actNum==1){
            x=500;
            y=0;
            enemyHP=1;
        }else{

        }
        time = 500;

        //当たり判定用bounds生成
        enemyBounds = getBoundsInParent();

        setTranslateY(y);
        setTranslateX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(30), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void run() {
        if (x < Main.screenMaxX && x > Main.screenMinX && y < Main.screenMaxY&&enemyHP>0) {
            switch (actNum) {
                case 0:
                    y+=5;
                    x+=2;
                    break;
                case 1:
                    y+=5;
                    x-=2;
                    break;
                case 2:
                    break;
            }
            setTranslateX(x);
            setTranslateY(y);
            //enemy当たり判定取得。
            enemyBounds = getBoundsInParent();
            //全てのたまのリストを取得
            bulletList = Main.getBulletList();
            //現在のenemyと全てのbulletのどれかがぶつかっていれば、得点を増やし、画像をnullにする。
            for (Bullet bullet : bulletList) {
                if (enemyBounds.intersects(bullet.getBoundsInParent())) {
                    Main.addScore(350);
                    enemyHP--;
                    break;
                }
            }
            //敵玉発射処理
            if(System.currentTimeMillis()-time>800){
                if(actNum==0)Main.enemyShot(x,y,"Enemy1");
                else if(actNum==1)Main.enemyShot(x,y,"Enemy2");
                time = System.currentTimeMillis();
            }
        }else{
            destroySE.reset();
            destroySE.play();
            setImage(null);
            timeline.stop();
        }
    }
}
