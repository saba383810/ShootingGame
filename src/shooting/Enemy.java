package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.util.ArrayList;

import java.nio.file.Paths;

public class Enemy extends ImageView {
    int x ;
    int y ;
    int enemyHP;
    public int actNum=0;
    Timeline timeline;
    ArrayList<Bullet> bulletList ;
    Bounds enemyBounds;
    static Image enemyImage = new Image(Paths.get("InvadersImage/Enemy1.png").toUri().toString());

    public Enemy(int actNum){
        super(enemyImage);
        this.actNum=actNum;
        //行動パターンに伴った初期位置を指定

        if(actNum==0){
            x=100;
            y=0;
        }else if(actNum==1){

        }else{

        }
        //当たり判定用bounds生成
        enemyBounds = getBoundsInParent();

        setTranslateY(y);
        setTranslateX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(30), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void run() {
        if (enemyHP <= 1) {
            if (x < Main.screenMaxX && x > Main.screenMinX && y < Main.screenMaxX) {
                switch (actNum) {
                    case 0:
                        y += 5;
                        x += 2;
                        break;
                    case 1:
                        y+=5;
                        break;
                    case 2:
                        break;
                }

                setTranslateX(x);
                setTranslateY(y);
            }
            //enemy当たり判定取得。
            enemyBounds = getBoundsInParent();
            //全てのたまを取得
            bulletList = Main.getBulletList();
            //現在のenemyと全てのbulletのどれかがぶつかっていれば、得点を増やし、画像をnullにする。
            for (Bullet bullet : bulletList) {
                if (enemyBounds.intersects(bullet.getBoundsInParent())) {
                    Main.addScore(100);
                    setImage(null);
                    break;
                }
            }
        }
    }
}
